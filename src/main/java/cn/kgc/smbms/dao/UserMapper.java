package cn.kgc.smbms.dao;

import cn.kgc.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 验证用户名是否存在
     * @param userCode
     * @return 返回值是零或者大于零  大于0表示用户存在 等于0表示用户不存在
     */
    Integer checkUserCode(String userCode);

    /**
     * 根据用户名和密码来查询用户 如果返回对象为null 则表示用户名和密码不匹配
     * @param userCode
     * @param userPassword
     * @return
     */
    User getUserByUserCodeAndUserPassword(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

    /**
     * 查询用户列表 这里将采用动态的sql去查询数据
     * @param userName
     * @param roleId
     * @return
     */
    List<User> queryUserList(@Param("userName") String userName, @Param("roleId") Integer roleId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer addUser(User user);


    User queryUserById(@Param("userId") Integer userId);

    Integer updateUser(User user);

    Integer delUser(@Param("id") Integer id);

    Integer updatePwdById(@Param("id") Long id, @Param("pwd") String pwd);
}
