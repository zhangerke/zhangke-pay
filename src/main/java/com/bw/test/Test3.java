package com.bw.test;

import com.bw.controller.AlipayController;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class Test3 {
    final static Logger log= Logger.getLogger(AlipayController.class);

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
