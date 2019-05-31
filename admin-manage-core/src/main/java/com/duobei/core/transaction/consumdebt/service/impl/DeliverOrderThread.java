package com.duobei.core.transaction.consumdebt.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.ConsumdebtOrderStateEumn;
import com.duobei.common.util.BeanUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import com.duobei.dic.ZD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**批量发货线程类
 * Created by huangzhognfa on 2018/12/5.
 */
public class DeliverOrderThread implements Callable<List<Map<String, String>>> {
    private List<String> temp;
    static ThreadLocal<List<Map<String, String>>> failList =new ThreadLocal<List<Map<String, String>>>();//失败订单
    static ThreadLocal<List<String>> successList =new ThreadLocal<List<String>>();//成功订单号
    @Override
    public List<Map<String, String>> call() throws Exception {
        List<Map<String, String>> failList1 = new ArrayList<>();
        failList.set(failList1);
        List<String> successList1 = new ArrayList<>();
        successList.set(successList1);
        ConsumdebtOrderService consumdebtOrderService = (ConsumdebtOrderService) BeanUtil.getBean("consumdebtOrderService");
        List<ConsumdebtOrder> list = consumdebtOrderService.getByOrderNos(temp);
        for(String orderNo :temp){
            String o = "";
            String msg = "";
            for(ConsumdebtOrder order :list){
                if( order.getOrderNo().equals(orderNo)){
                    o = orderNo;
                    //不是待发货状态
                    if( !order.getState().equals(ZD.consumdebtOrderState_waitDelivery)){
                        msg = "订单状态错误："+ ConsumdebtOrderStateEumn.findDescByCode(order.getState());
                    }else{
                        successList1.add(orderNo);
                    }
                    break;
                }
            }
            if(StringUtil.isBlank(o)){
                msg = "订单号不存在";
                o = orderNo;
            }
            if( !StringUtil.isBlank(o) && !StringUtil.isBlank(msg)){
                Map<String, String> map = new HashMap<>();
                map.put("orderNo",o);
                map.put("msg",msg);
                failList1.add(map);
            }
        }
        successList1 = successList.get();
        failList1 = failList.get();
        //开始发货
        if( successList1.size() > BizConstant.INT_ZERO){
            consumdebtOrderService.batchDeliver(successList1);
        }
        successList.set(null);
        return failList1;
    }

    public List<String> getTemp() {
        return temp;
    }

    public void setTemp(List<String> temp) {
        this.temp = temp;
    }
}
