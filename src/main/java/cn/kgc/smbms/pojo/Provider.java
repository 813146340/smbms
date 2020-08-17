package cn.kgc.smbms.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/6
 */
@Data
public class Provider {
    private Long id;
    private String proCode;
    private String proName;
    private String proContact;
    private String proPhone;
    private String proFax;
    private String proDesc;
    private String proAddress;
    private Date creationDate;
}
