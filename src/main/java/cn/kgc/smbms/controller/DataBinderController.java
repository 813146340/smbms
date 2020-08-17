package cn.kgc.smbms.controller;

import cn.kgc.smbms.pojo.TestUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/10
 */
@Controller
@RequestMapping("/data")
public class DataBinderController {


    /**
     * 如果参数是基本数据类型的时候必须要传递数据
     * @param age
     * @return
     */
    @RequestMapping("/test1")
    @ResponseBody
    public String dataBinderBaseDate(Integer age){
        return "age"+age;
    }

    /**
     * http://localhost:6060/data/test2?id=1&userName=user
     *
     * http://localhost:6060/data/test2?id=1&userName=user&info.phone=1111
     * 如果在一个对象中嵌套了另外一个对象 而你想要赋值 直接通过属性名打点调来进行赋值
     * @return
     */
    @RequestMapping("/test2")
    @ResponseBody
    public String dataBinderObject(TestUser testUser){
        return testUser.toString();
    }


    @InitBinder("testUser")
    public void dataBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("User.");
    }

    @InitBinder("adminUser")
    public void dataBinderAdmin(WebDataBinder binder){
        binder.setFieldDefaultPrefix("Admin.");
    }
}
