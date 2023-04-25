package com.ramsbaby.stockview;

import com.ramsbaby.stockview.domain.info.StockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : RAMSBABY
 * @date : 2023-04-19 오후 10:32:49
 */
@SpringBootApplication
public class StockViewServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(StockViewServiceApplication.class, args);

        StockService service = context.getBean(StockService.class);
        service.initData();

    }

}
