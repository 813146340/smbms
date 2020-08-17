package cn.kgc.smbms.service.impl;

import cn.kgc.smbms.common.ResponseCode;
import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.dao.UserMapper;
import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/3
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultObject userLogin(String userCode, String userPassword) {
        //验证参数的合法性 非空判断为空返回true
        if (StringUtils.isBlank(userCode)||StringUtils.isBlank(userPassword)){
            return ResultObject.resultByErrorMsg(ResponseCode.PARAMETER_IS_NOT_FOUND.getCode(),ResponseCode.PARAMETER_IS_NOT_FOUND.getMsg());
        }
        //检查用户名是否存在
        Integer rowCount = userMapper.checkUserCode(userCode);
        if (rowCount<=0){
            //用户名不存在
            return ResultObject.resultByErrorMsg(-1,"用户名不存在!");
        }
        //TODO 如果需要密码加密，在这里可以密码加密
        //登录
        User user = userMapper.getUserByUserCodeAndUserPassword(userCode,userPassword);
        if (user==null){
            return ResultObject.resultByErrorMsg(-1,"密码不正确!");
        }
        //登录成功
        return ResultObject.resultBySuccessData(1,"登录成功!",user);
    }

    @Override
    public ResultObject getUserList(String userName, Integer roleId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.queryUserList(userName, roleId);
        List<User> newList = new ArrayList<>();
        for (User user:userList){
            User newUser = new User();
            BeanUtils.copyProperties(user,newUser);
            newList.add(newUser);
        }
        PageInfo pageInfo = new PageInfo(userList);
        return ResultObject.resultBySuccessData(1,"成功",newList);
    }

    /**
     * 分区分页大小
     * @param userName
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo getPage(String userName, Integer roleId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.queryUserList(userName, roleId);
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    @Override
    public String checkUserCode(String userCode){
        Integer rowCount = userMapper.checkUserCode(userCode);
        System.out.println(rowCount);
        if (rowCount>0){
            //用户名存在
            return "exist";
        }else {
            //用户名不存在
            return "not exist";
        }
    }

    @Override
    public ResultObject addUser(User user) {
        Integer rowCount = userMapper.addUser(user);
        if (rowCount>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public ResultObject modifyUser(User user) {
        if (userMapper.updateUser(user)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }
        return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    @Override
    public ResultObject delUser(Integer id) {
        if (userMapper.delUser(id)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }
        return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    @Override
    public ResultObject updatePwdById(Long id, String pwd) {
        if (userMapper.updatePwdById(id, pwd)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }
        return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

}
