package com.bw.service.thread;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.bw.pojo.Orders;
import com.bw.service.OrdersService;
import com.bw.utils.AlipayConfig;

public class ReconverThread implements  Runnable {

    private String orderNumber;

    private OrdersService OrdersService;

    private final  Integer ReconverTime=5;

    public ReconverThread(String orderNumber,OrdersService OrdersService) {
        this.orderNumber = orderNumber;
        this.OrdersService=OrdersService;
    }

    @Override
    public void run() {
        for(int i=0;i<ReconverTime;i++){
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\""+orderNumber+"\"  }");
            AlipayTradeCancelResponse response = null;
            try {
                response = alipayClient.execute(request);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        }

    }
}
