package com.duobei.core.transaction.consumdebt.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.ImportExcelUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.BatchDeliveryResultVo;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.consumdebt.dao.ConsumdebtOrderDao;
import com.duobei.core.transaction.consumdebt.dao.mapper.ConsumdebtOrderMapper;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrderExample;
import com.duobei.core.transaction.consumdebt.domain.criteria.ConsumdebtOrderCriteria;
import com.duobei.core.transaction.consumdebt.domain.vo.ConsumdebtOrderListVo;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import com.duobei.utils.BizCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("consumdebtOrderService")
@Slf4j
public class ConsumdebtOrderServiceImpl implements ConsumdebtOrderService {
    @Resource
    ConsumdebtOrderDao consumdebtOrderDao;

    @Resource
    ConsumdebtOrderMapper consumdebtOrderMapper;
    @Resource
    private BizCacheUtil bizCacheUtil;

    @Resource
    ProductDao productDao;

    //创建线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    @Override
    public ConsumdebtOrder getByUserIdAndBorrowId(Long userId, Long borrowId) {
        return consumdebtOrderDao.getByUserIdAndBorrowId(userId,borrowId);
    }

    @Override
    public ListVo<ConsumdebtOrderListVo> getListByQuery(ConsumdebtOrderCriteria consumdebtOrderCriteria) {
        //查询产品信息
        Product product = productDao.getById(consumdebtOrderCriteria.getProductId());
        Long total = queryCount(consumdebtOrderCriteria);
        //如果数量>0则查询列表
        List<ConsumdebtOrderListVo> data = null;
        if (total > BizConstant.INT_ZERO ){
            data = consumdebtOrderDao.getListByQuery(consumdebtOrderCriteria);
            for (ConsumdebtOrderListVo vo : data){
                vo.setProductName(product.getProductName());
            }
        }
        return new ListVo<ConsumdebtOrderListVo>(total.intValue(),data);
    }

    @Override
    public ConsumdebtOrder getById(Long id) {
        return consumdebtOrderDao.getById(id);
    }

    @Override
    public void updateDelivery(ConsumdebtOrder entity) throws TqException {
        int count = consumdebtOrderDao.updateDelivery(entity);
        if (count != 1){
            throw new TqException("修改失败,请刷新后重试");
        }
    }

    @Override
    public Long queryCount(ConsumdebtOrderCriteria consumdebtOrderCriteria) {
        ConsumdebtOrderExample example = new ConsumdebtOrderExample();
        ConsumdebtOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        //搜索条件封装
        if (StringUtil.isNotEmpty(consumdebtOrderCriteria.getOrderNo())){
            criteria.andOrderNoEqualTo(consumdebtOrderCriteria.getOrderNo());
        }
        if (consumdebtOrderCriteria.getProductId() != null){
            criteria.andProductIdEqualTo(consumdebtOrderCriteria.getProductId());
        }
        if (consumdebtOrderCriteria.getUserId() != null){
            criteria.andUserIdEqualTo(consumdebtOrderCriteria.getUserId());
        }
        if (StringUtil.isNotEmpty(consumdebtOrderCriteria.getLogisticsNo())){
            criteria.andLogisticsNoEqualTo(consumdebtOrderCriteria.getLogisticsNo());
        }
        if (consumdebtOrderCriteria.getEndTime() != null && consumdebtOrderCriteria.getStartTime() != null){
            //存在创建开始和结束时间条件查询
            criteria.andAddTimeBetween(consumdebtOrderCriteria.getStartTime(),consumdebtOrderCriteria.getEndTime());
        }else if(consumdebtOrderCriteria.getEndTime() != null && consumdebtOrderCriteria.getStartTime() == null){
            //存在创建结束时间条件查询
            criteria.andAddTimeLessThan(consumdebtOrderCriteria.getEndTime());
        }else if(consumdebtOrderCriteria.getEndTime() == null && consumdebtOrderCriteria.getStartTime() != null){
            //存在创建开始时间条件查询
            criteria.andAddTimeGreaterThan(consumdebtOrderCriteria.getStartTime());
        }
        if(consumdebtOrderCriteria.getState() != null){
            criteria.andStateEqualTo(consumdebtOrderCriteria.getState());
        }
        Long total = consumdebtOrderMapper.countByExample(example);
        return total;
    }

    @Override
    public List<ConsumdebtOrder> getListByReportQuery(ConsumdebtOrderCriteria criteria) {
        return consumdebtOrderDao.getListByReportQuery(criteria);
    }


    /**
     * 根据订单号批量查询
     * @param orderNos
     * @return
     */
    @Override
    public List<ConsumdebtOrder> getByOrderNos(List<String> orderNos){
        return consumdebtOrderDao.getByOrderNos(orderNos);
    }

    /**
     * 批量发货
     * @param orderNos
     */
    @Override
    public void batchDeliver(List<String> orderNos){
        consumdebtOrderDao.batchDeliver(orderNos);
    }

    /**
     * 导入订单号，批量发货
     * @param filePath
     * @return
     */
    @Override
    public BatchDeliveryResultVo batchDeliveryConsumdebtOrder(String filePath) {
        BatchDeliveryResultVo result = new BatchDeliveryResultVo();
        try {
            URL url = new URL(filePath);//把远程文件地址转换成URL格式
            InputStream in = url.openStream();
            List<List<Object>> listob = ImportExcelUtil.getBankListByExcel(in,filePath);
            if( listob.size() >1000){
                result.setSuccess(false);
                result.setMsg("订单数量最大限制为1000，请分批导入");
            }
            in.close();
            List<Map<String, String>> failOrderNo = new ArrayList<>();//发送失败
            // 第一遍筛选
            List<String> orderNos = new ArrayList<>();
            for (int i = 0; i < listob.size(); i++) {
                List<Object> lo = listob.get(i); //第行数据
                //删除空列
                if( lo.size() == BizConstant.INT_ZERO ){
                    continue;
                }
                String orderNo = String.valueOf(lo.get(0)).replace(" ", "");//第一列
                //删除空格
                if( StringUtil.isBlank(orderNo) ){
                    Map<String, String> map = new HashMap<>();
                    map.put("orderNo", "");
                    map.put("msg","订单号为空" );
                    failOrderNo.add(map);
                }
                orderNos.add(orderNo);
            }
            List<List<String>> list_list = averageAssign(orderNos, 100);
            List<Future<List<Map<String, String>>>> resultList = new ArrayList<>();
            //线程执行类
            DeliverOrderThread thread = null;
            for (List<String> temp : list_list){
                thread = new DeliverOrderThread();
                thread.setTemp(temp);
                //启动线程并返回执行结果
                Future<List<Map<String, String>>> future = executor.submit(thread);
                resultList.add(future);
            }
            //关闭线程
            //executor.shutdown();
            for (Future<List<Map<String, String>>> failList : resultList) {
                try {
                    failOrderNo.addAll(failList.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            result.setSuccess(true);
            result.setFailCount(failOrderNo.size());
            bizCacheUtil.saveObjectList("failOrderNo",failOrderNo);
            result.setSuccessCount(listob.size() - failOrderNo.size());
            result.setMsg("操作成功");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg("excle解析失败");
        }
        return result;
    }
    public static List<List<String>> averageAssign(List<String> source, int n){
        List<List<String>> result=new ArrayList<>();
        if( source.size()<= n){
            result.add(source);
        }else{
            int index_1 = 0,index_2 = 0;
            while( index_2< source.size() ){
                List<String> value=null;
                index_2 = index_2 + n;
                if( index_2 >= source.size()){
                    index_2 = source.size();
                }
                value=source.subList(index_1 , index_2);
                index_1 = index_2;
                result.add(value);
            }
        }
        return result;
    }

   /* class SimpleRunnAble implements Runnable {
        private final Map<String, Object> map;
        private final CountDownLatch latch;
        private final List<ConsumdebtOrder> list;
        private final int[] argInt;


        SimpleRunnAble(int[] argInt,List<ConsumdebtOrder> list,Map<String, Object> map,CountDownLatch latch) {
            this.map = map;
            this.latch = latch;
            this.list  = list;
            this.argInt = argInt;
            run();
        }

        @Override
        public void run() {
            Long startTime = System.currentTimeMillis();
            ConsumdebtOrder consumdebtOrderDo = new ConsumdebtOrder();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                if ("订单号".equals(entry.getKey())) {
                    ConsumdebtOrder consumdebtOrder = consumdebtOrderDao.getByOrderNo((String) entry.getValue());
                    if (consumdebtOrder != null) {
                        consumdebtOrderDo.setId(consumdebtOrder.getId());
                        consumdebtOrderDo.setOrderNo(consumdebtOrder.getOrderNo());
                    } else{
                        consumdebtOrderDo.setOrderNo((String) entry.getValue());
                    }
                } else if ("运单号".equals(entry.getKey())) {
                    consumdebtOrderDo.setLogisticsNo((String) entry.getValue());
                } else if ("快递公司".equals(entry.getKey())) {
                    consumdebtOrderDo.setLogisticsCompany((String) entry.getValue());
                } else if ("收件人手机".equals(entry.getKey())) {
                    consumdebtOrderDo.setConsigneeMobile((String) entry.getValue());
                }
            }
            int i = consumdebtOrderDo.getId() != null ? consumdebtOrderDao.deliveryConsumdebtOrder(consumdebtOrderDo) : 0;
            if (i > 0) {
                argInt[0] +=1; 	//successCount +=1;
            }else {
                argInt[1] +=1;
                consumdebtOrderDo.setClosedReason("订单号不存在或已发货");
                list.add(consumdebtOrderDo);
            }
            latch.countDown();
            log.info("单个运行时间{}",System.currentTimeMillis() - startTime);
        }
    }*/
}
