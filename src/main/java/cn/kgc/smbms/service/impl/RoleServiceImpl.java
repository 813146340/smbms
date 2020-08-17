package cn.kgc.smbms.service.impl;

import cn.kgc.smbms.dao.RoleMapper;
import cn.kgc.smbms.pojo.Role;
import cn.kgc.smbms.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/5
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRole() {
        return roleMapper.queryRole();
    }
}
