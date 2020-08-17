package cn.kgc.smbms.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/6
 */
@Data
public class Bill {
    private Long id;
    private String billCode;
    private String productName;
    private String productUnit;
    private double productCount;
    private double totalPrice;
    private Integer isPayment;
    private Date creationDate;
    private Integer providerId;
    private String providerName;
}
