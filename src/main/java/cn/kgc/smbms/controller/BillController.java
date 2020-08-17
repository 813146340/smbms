package cn.kgc.smbms.controller;

import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.dao.ProviderMapper;
import cn.kgc.smbms.pojo.Bill;
import cn.kgc.smbms.pojo.Provider;
import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.BillService;
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
 * @date:2020/8/6
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/billlist.html")
    public ModelAndView queryBillList(@RequestParam(required = false,value = "queryProductName") String queryProductName,
                                      @RequestParam(required = false,value = "queryProviderId")Integer queryProviderId,
                                      @RequestParam(required = false,value = "method")String method,
                                      @RequestParam(required = false,value = "queryIsPayment")Integer queryIsPayment,
                                      HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userSession");
        if (user==null){
            throw new RuntimeException("你尚未进行登录!");
        }
        ResultObject resultObject = billService.queryBillByName(queryProductName, queryProviderId, queryIsPayment);
        List<Provider> providerList = providerService.queryProviderList();
        modelAndView.setViewName("billlist");
        modelAndView.addObject("billList",resultObject.getData());
        modelAndView.addObject("providerList",providerList);
        return modelAndView;
    }

    @RequestMapping("/gotoBillAdd")
    public ModelAndView gotoBillAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("billadd");
        List<Provider> providers = providerService.queryProviderList();
        modelAndView.addObject("providerList",providers);
        return modelAndView;
    }

    @RequestMapping("/saveBill")
    public String saveBill(Bill bill){
        if (billService.insertBill(bill).isSuccess()){
            return "redirect:/bill/billlist.html";
        }else {
            throw new RuntimeException("添加订单失败!");
        }
    }

    @RequestMapping("/gotoModify/{billid}")
    public ModelAndView gotoModify(@PathVariable Integer billid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("billmodify");
        //查询当前选中的订单信息
        modelAndView.addObject("bill",billService.getBillById(billid));
        return modelAndView;
    }

    @RequestMapping("/billModify")
    public String billModify(Bill bill){
        if (billService.updateBill(bill).isSuccess()){
            return "redirect:/bill/billlist.html";
        }else {
            throw new RuntimeException("修改失败!");
        }
    }

    @RequestMapping("/delBill/{billid}")
    @ResponseBody
    public String delBill(@PathVariable Integer billid){
        if (billService.delBill(billid).isSuccess()){
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("/billView/{billid}")
    public ModelAndView billView(@PathVariable Integer billid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("billview");
        modelAndView.addObject("bill",billService.getBillById(billid));
        return modelAndView;
    }
}
