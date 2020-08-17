package cn.kgc.smbms.dao;

import cn.kgc.smbms.pojo.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询角色信息
     */
    List<Role> queryRole();
}
