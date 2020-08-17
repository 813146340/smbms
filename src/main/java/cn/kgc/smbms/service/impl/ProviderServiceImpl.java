package cn.kgc.smbms.service.impl;

import cn.kgc.smbms.common.ResponseCode;
import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.dao.ProviderMapper;
import cn.kgc.smbms.pojo.Provider;
import cn.kgc.smbms.service.ProviderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/6
 */
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> queryProviderList() {
        return providerMapper.queryProviderList();
    }

    @Override
    public List<Provider> getProviderByCondition(String proCode, String proName) {
        return providerMapper.queryProByProCodeAndProName(proCode, proName);
    }

    @Override
    public ResultObject addProvider(Provider provider) {
        if (providerMapper.insertProvider(provider)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public Provider getProById(Integer id) {
        return providerMapper.getProById(id);
    }

    @Override
    public ResultObject updatePro(Provider provider) {
        if (providerMapper.updatePro(provider)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else{
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ResultObject delPro(Integer id) {
        if (providerMapper.delPro(id)>0){
            return ResultObject.resultBySuccess(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else{
            return ResultObject.resultByErrorMsg(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }

    }
}
