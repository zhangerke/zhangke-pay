package com.bw.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bw.pojo.Orders;
import com.bw.service.OrdersService;
import com.bw.utils.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    OrdersService order;
    @Scheduled(fixedDelay = 1000)
    public void findQueryList() throws AlipayApiException {
        List<Orders> list=order.findQueryList();
            System.out.println("list.size===="+list.size());
            for(int i=0;i<list.size();i++){
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

                AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
                request.setBizContent("{" +
                        "\"out_trade_no\":\""+list.get(i).getOrderNum()+"\"" +
                        "  }");
                AlipayTradeQueryResponse response = alipayClient.execute(request);
                System.out.println(response.getTotalAmount());
                if(response.isSuccess()){
                    System.out.println("调用成功");
                } else {
                    System.out.println("调用失败");
                }
        }


    }

}
