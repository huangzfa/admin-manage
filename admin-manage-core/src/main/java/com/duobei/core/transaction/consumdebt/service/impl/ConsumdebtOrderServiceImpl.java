package com.duobei.core.transaction.consumdebt.service.impl;

import com.duobei.common.exception.TqException;
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
import com.duobei.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    ProductDao productDao;
    private static ExecutorService executor = Executors.newFixedThreadPool(20);
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
        if (total > 0 ){
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

    @Override
    public BatchDeliveryResultVo batchDeliveryConsumdebtOrder(String filePath) {
        Long startTime = System.currentTimeMillis();
        List<Map<String,Object>> excel = ExcelUtil.loadExcel(filePath, 0);
        List<ConsumdebtOrder> list=new ArrayList<>();
        BatchDeliveryResultVo result = new BatchDeliveryResultVo();
        int[] argInt = new int[2];//定义成功失败
        CountDownLatch latch = new CountDownLatch(excel.size());
        for (Map<String, Object> map : excel) {
            Long startTime1 = System.currentTimeMillis();
            executor.execute(() -> new SimpleRunnAble(argInt,list,map,latch));
             log.info("线程的运行时间{}",System.currentTimeMillis() - startTime1);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("线程异常",e);
        }
        //executor.shutdown();
        String os = System.getProperty("os.name");
        String path="/home/admin/project/file/";
        if(os.toLowerCase().startsWith("win")){
            path = "D:"+path;
        }
        int start = filePath.lastIndexOf("/");
        int end = filePath.lastIndexOf(".");
        String fileName=filePath.substring(start + 1, end)+"失败表单.xls";
        fileName = fileName.replaceAll(" ","");
        Map<String, String> titleMap=getTitleMap();
        ExcelUtil.excelExport(list, titleMap, fileName, path);
        result.setSuccessCount(argInt[0]);
        result.setFailCount(argInt[1]);
        result.setFailFilePath(path+fileName);
        result.setSuccess(true);
        result.setMsg("操作成功");
        log.info("运行时间{}",System.currentTimeMillis() - startTime);
        return result;
    }

    private Map<String, String> getTitleMap() {

        Map<String, String> titleMap=new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 1;
            }
        });
        titleMap.put("orderNo","订单号" );
        titleMap.put("logisticsNo","运单号" );
        titleMap.put("logisticsCompany","物流公司" );
        titleMap.put("closedReason","失败原因" );
        return titleMap;
    }

    class SimpleRunnAble implements Runnable {
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
    }
}
