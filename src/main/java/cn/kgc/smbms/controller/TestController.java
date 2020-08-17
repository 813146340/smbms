package cn.kgc.smbms.controller;

import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/7
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/gotoView")
    public ModelAndView gotoView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("test/userAdd");
        return modelAndView;
    }

    @RequestMapping("/checkview")
    public ModelAndView check(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()){
            //如果用户输入的不合法将返回输入页面
            System.out.println("用户输入不合法");
            modelAndView.addObject("user",user);
            modelAndView.setViewName("test/userAdd");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpSession session){
        //获取上传路径
        String path = session.getServletContext().getRealPath("upload");
        return  fileService.upload(file,path);
    }


    @RequestMapping("/viewTest")
    public ModelAndView viewTest(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("htmlIndex");
        return modelAndView;
    }

}
