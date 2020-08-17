package cn.kgc.smbms.controller;

import cn.kgc.smbms.common.Const;
import cn.kgc.smbms.common.ResponseCode;
import cn.kgc.smbms.pojo.Provider;
import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/7
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @RequestMapping("/getProviderList")
    @ResponseBody
    public List<Provider> getProviderList(){
        return providerService.queryProviderList();
    }

    @RequestMapping("/gotoProvider")
    public ModelAndView gotoProvider(HttpServletRequest request,
                                    @RequestParam(required = false,value = "queryProCode") String proCode,
                                    @RequestParam(required = false,value = "queryProName") String proName){
        User user = (User)request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("providerlist");
        modelAndView.addObject("providerList",providerService.getProviderByCondition(proCode, proName));
        return modelAndView;
    }

    @RequestMapping("/gotoAdd")
    public String gotoAdd(){
        return "provideradd";
    }

    @RequestMapping("/addProvider")
    public String addProvider(Provider provider,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }

        if (providerService.addProvider(provider).isSuccess()){
            return "redirect:/provider/gotoProvider";
        }else {
            throw new RuntimeException(ResponseCode.FAIL.getMsg());
        }
    }

    @RequestMapping("/gotoModify/{proid}")
    public ModelAndView gotoModify(@PathVariable Integer proid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("providermodify");
        modelAndView.addObject("provider",providerService.getProById(proid));
        return modelAndView;
    }

    @RequestMapping("/proModify")
    public String proModify(Provider provider){
        if (providerService.updatePro(provider).isSuccess()){
            return "redirect:/provider/gotoProvider";
        }else {
            throw new RuntimeException(ResponseCode.FAIL.getMsg());
        }
    }

    @RequestMapping("/delPro/{proid}")
    @ResponseBody
    public String delPro(@PathVariable Integer proid){
        if (providerService.delPro(proid).isSuccess()){
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("/viewPro/{proid}")
    public ModelAndView viewPro(@PathVariable Integer proid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("providerview");
        modelAndView.addObject("provider",providerService.getProById(proid));
        return modelAndView;
    }
}
