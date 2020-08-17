package cn.kgc.smbms.dao.impl;

import cn.kgc.smbms.dao.BaseDao;
import cn.kgc.smbms.dao.UserMapper;
import cn.kgc.smbms.pojo.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/3
 */

public class UserMapperImpl extends BaseDao  {


    public Integer checkUserCode(String userCode) {
        getConnection();//获取连接
        Integer rowCount=0;
        String sql = "select count(id) from smbms_user where userCode=?";
        Object[] params = new Object[]{userCode};
        rs = executeQuery(sql, params);

        try {
            while (rs.next()){
                rowCount =  rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //关闭连接 释放资源
            closeAll(conn,pstmt,rs);
        }
        return rowCount;
    }

   
    public User getUserByUserCodeAndUserPassword(String userCode, String userPassword) {
        getConnection();
        User user = null;
        String sql = "select id,userCode,userName,gender,birthday from smbms_user where userCode=?  and userPassword=?";
        Object[] params = new Object[]{userCode,userPassword};
        rs = executeQuery(sql,params);

        try {
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接 释放资源
            closeAll(conn,pstmt,rs);
        }

        return user;
    }
}
