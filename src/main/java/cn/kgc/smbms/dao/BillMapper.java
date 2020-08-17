package cn.kgc.smbms.dao;

import cn.kgc.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    /**
     * 根据商品名称 供货商id 是否付款进行查询
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    List<Bill> queryBillByName(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment);

    Integer insertBill(Bill bill);

    //根据订单id查询当前订单信息
    Bill queryBillById(@Param("billId") Integer billId);

    //修改信息
    Integer updateBillById(Bill bill);

    //删除订单信息
    Integer delBillById(Integer id);
}
