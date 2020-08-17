package cn.kgc.smbms.controller;

import cn.kgc.smbms.common.Const;
import cn.kgc.smbms.common.ResponseCode;
import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.pojo.Role;
import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.RoleService;
import cn.kgc.smbms.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/4
 */
@Controller()
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /**
     * 通过此接口前往登录页面
     * @return
     */
    @RequestMapping("/gotoLogin")
    public String gotoLogin(){
        return "login";
    }

    /**
     * 登录操作
     * @param userCode
     * @param userPassword
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/doLogin.html",method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam(value = "userCode")String userCode,
                                @RequestParam(value = "userPassword")String userPassword,
                                HttpServletRequest httpServletRequest
                                ){
        ResultObject resultObject = userService.userLogin(userCode, userPassword);
        if (resultObject.isSuccess()){
            //登录成功
            //将用户信息存入session中
            httpServletRequest.getSession().setAttribute(Const.CURRENT_USER,resultObject.getData());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("frame");
            return modelAndView;
        }else {
            //登录失败
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            modelAndView.addObject("error",resultObject.getMsg());
            return modelAndView;
        }
    }

    /**
     * 登出操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.html",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute(Const.CURRENT_USER);
        //将路径进行重定向  转发:  forward:/user/gotoLogin
        return "redirect:/user/gotoLogin";
    }

    /**
     * 显示用户列表
     * @param queryname
     * @param queryUserRole
     * @param pageIndex
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "userlist.html")
    public ModelAndView userList(@RequestParam(value = "queryname", required = false) String queryname,
                                 @RequestParam(required = false,value = "queryUserRole")Integer queryUserRole,
                                 @RequestParam(required = false,value = "pageIndex",defaultValue = "1")Integer pageIndex,
                                 @RequestParam(required = false,value = "pageSize",defaultValue = "5")Integer pageSize,
                                 HttpServletRequest request
                                 ) {
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException("当前用户未登录!");
        }
        ModelAndView modelAndView = new ModelAndView();
        ResultObject userList = userService.getUserList(queryname, queryUserRole, pageIndex, pageSize);
        PageInfo page = userService.getPage(queryname, queryUserRole, pageIndex, pageSize);
        List<Role> roleList = roleService.queryRole();
        modelAndView.setViewName("userlist");
        modelAndView.addObject("userList",userList.getData());
        modelAndView.addObject("totalPageCount",page.getPages());
        modelAndView.addObject("totalCount",page.getTotal());
        modelAndView.addObject("currentPageNo",page.getPageNum());
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }

    /**
     * 前往用户添加页面
     * @return
     */
    @RequestMapping("/todouseradd")
    public String todouseradd(){
        return "useradd";
    }

    /**
     * 检查用户名的合法性
     * @param userCode
     * @ResponseBody 当返回的不是一个跳转时 使用该注解
     * @return
     */
    @RequestMapping("/checkusercode")
    @ResponseBody
    public String checkusercode(String userCode){
        System.out.println(userService.checkUserCode(userCode));
        return userService.checkUserCode(userCode);
    }

    @RequestMapping(value = "/useraddsave",method = RequestMethod.POST)
    public String addUser(User user,HttpServletRequest request){
        //检查用户是否登录
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }
        user.setCreateBy(currentUser.getId());
        ResultObject resultObject = userService.addUser(user);
        if (resultObject.isSuccess()){
            return "redirect:/user/userlist.html";
        }else {
            throw new RuntimeException(Const.FAIL);
        }
    }

    @RequestMapping("/gotoModify/{userId}")
    public ModelAndView gotoModify(@PathVariable Integer userId){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUser(userId);
        modelAndView.setViewName("usermodify");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("/modifyUser")
    public String modify(User user){
       if (userService.modifyUser(user).isSuccess()){
           return "redirect:/user/userlist.html";
       }else {
           throw new RuntimeException("修改用户失败!");
       }
    }

    @RequestMapping("/delUser/{userid}")
    @ResponseBody
    public String delUser(@PathVariable Integer userid){
        if (userService.delUser(userid).isSuccess()){
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("/userView/{userid}")
    public ModelAndView userView(@PathVariable Integer userid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userview");
        modelAndView.addObject("user",userService.getUser(userid));
        return modelAndView;
    }

    @RequestMapping("/gotoPwd")
    public ModelAndView gotoPwd(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }
       ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pwdmodify");
        return modelAndView;
    }

    @RequestMapping("/modifyPwd")
    public String gotoPwd(HttpServletRequest request,
                                @RequestParam("newpassword")String newpassword){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }
        ModelAndView modelAndView = new ModelAndView();
        if (userService.updatePwdById(user.getId(),newpassword).isSuccess()){
            return "redirect:/user/gotoLogin";
        }
        throw new RuntimeException("修改密码失败!");
    }

    @RequestMapping("/pwdVerify")
    @ResponseBody
    public String pwdVerify(@RequestParam(value = "oldpassword",required = false)String oldpassword,
                            HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            throw new RuntimeException(ResponseCode.USER_NEED_LOGIN.getMsg());
        }
        ResultObject resultObject = userService.userLogin(user.getUserCode(), oldpassword);
        if (StringUtils.isBlank(oldpassword)){
            return "error";
        }
        if (resultObject.getCode()!=1){
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/getUser/{id}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public User getUserById(@PathVariable Integer id){
//        return ResultObject.resultBySuccessData(ResponseCode.SUCCESS.getCode(),
//                ResponseCode.SUCCESS.getMsg(), userService.getUser(id));
        return userService.getUser(id);
    }

}
