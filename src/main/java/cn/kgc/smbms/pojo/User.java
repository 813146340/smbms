package cn.kgc.smbms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/3
 */

@Data
public class User {
    private Long id;
    private String userCode;
    @NotEmpty(message="用户名不能为空")
    private String userName;
    @Size(min=3,max=12,message="密码长度在3-12位")
    private String userPassword;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private Long userRole;
    private Integer age;
    private String userRoleName;
    private String phone;
    private Long createBy;
}
