package cn.kgc.smbms.service;

import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.pojo.User;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @org.junit.Test
    public void getUserList() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean("userService",UserService.class);
        ResultObject userList = userService.getUserList(null, null, 1, 5);
        PageInfo page = userService.getPage(null, null, 1, 5);
        System.out.println(page);
    }
}