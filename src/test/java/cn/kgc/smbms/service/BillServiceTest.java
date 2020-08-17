package cn.kgc.smbms.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class BillServiceTest {

    @Test
    public void queryBillByName() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BillService billService = applicationContext.getBean("billService",BillService.class);
        System.out.println(billService.queryBillByName("米",null,null).getData());

    }
}