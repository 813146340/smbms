package cn.kgc.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/5
 */
@Controller
@RequestMapping("/test")
public class ExceptionController {

    @RequestMapping("/e")
    public String testException(){
        int num = (int)(Math.random()*10);
        if (num%2==0){
            throw new RuntimeException("系统发生了内部异常,工程师正在抢修中........");
        }
        return "login";
    }

    /**
     * controller中的局部异常处理方法
     * @param e
     * @return
     */
//    @ExceptionHandler
//    public ModelAndView exceptionHandler(RuntimeException e){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error");
//        modelAndView.addObject("error",e.getMessage());
//        return modelAndView;
//    }
}
