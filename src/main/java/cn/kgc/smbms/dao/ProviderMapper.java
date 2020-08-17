package cn.kgc.smbms.dao;

import cn.kgc.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    List<Provider> queryProviderList();

    //根据供应商编号和供应商名称进行查询
    List<Provider> queryProByProCodeAndProName(@Param("proCode") String proCode, @Param("proName") String proName);

    Integer insertProvider(Provider provider);

    Provider getProById(@Param("id") Integer id);

    Integer updatePro(Provider provider);

    Integer delPro(@Param("id") Integer id);
}
