package cn.kgc.smbms.controller;

import cn.kgc.smbms.pojo.Role;
import cn.kgc.smbms.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/7
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * @ResponseBody 表示该方法返回的是数据  而非跳转页面 不走视图解析器
     * @return
     */
    @RequestMapping(value = "/getRoleList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Role> getRole() {
        return roleService.queryRole();
    }
}
