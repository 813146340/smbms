package cn.kgc.smbms.service;

import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.pojo.Bill;

public interface BillService {

    ResultObject queryBillByName(String productName, Integer providerId, Integer isPayment);

    ResultObject insertBill(Bill bill);

    Bill getBillById(Integer billId);

    ResultObject updateBill(Bill bill);

    ResultObject delBill(Integer billId);
}
