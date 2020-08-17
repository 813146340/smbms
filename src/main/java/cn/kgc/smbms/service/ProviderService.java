package cn.kgc.smbms.service;

import cn.kgc.smbms.common.ResultObject;
import cn.kgc.smbms.pojo.Provider;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProviderService {
    List<Provider> queryProviderList();

    List<Provider> getProviderByCondition(String proCode,String proName);

    ResultObject addProvider(Provider provider);

    Provider getProById(Integer id);

    ResultObject updatePro(Provider provider);

    ResultObject delPro(Integer id);
}
