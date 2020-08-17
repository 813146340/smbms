package cn.kgc.smbms.service.impl;

import cn.kgc.smbms.common.ResponseCode;
import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.dao.BillMapper;
import cn.kgc.smbms.pojo.Bill;
import cn.kgc.smbms.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/6
 */
@Service("billService")
public class BillServiceImpl implements BillService {

    @Resource
    private BillMapper billMapper;

    @Override
    public ResultObject queryBillByName(String productName, Integer providerId, Integer isPayment) {
        return ResultObject.resultBySuccessData(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),billMapper.queryBillByName(productName, providerId, isPayment));
    }

    @Override
    public ResultObject insertBill(Bill bill) {
        if (billMapper.insertBill(bill)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public Bill getBillById(Integer billId) {
        return billMapper.queryBillById(billId);
    }

    @Override
    public ResultObject updateBill(Bill bill) {
        if (billMapper.updateBillById(bill)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ResultObject delBill(Integer billId) {
        if (billMapper.delBillById(billId)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }
}
