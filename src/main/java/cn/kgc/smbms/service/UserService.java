package cn.kgc.smbms.service;

import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    public ResultObject userLogin(String userCode, String userPassword);

    public ResultObject getUserList(String userName, Integer roleId, Integer pageNum, Integer pageSize);

    PageInfo getPage(String userName, Integer roleId, Integer pageNum, Integer pageSize);

    public String checkUserCode(String userCode);

    public ResultObject addUser(User user);

    public User getUser(Integer userId);

    public ResultObject modifyUser(User user);

    public ResultObject delUser(Integer id);

    ResultObject updatePwdById(Long id,String pwd);
}
