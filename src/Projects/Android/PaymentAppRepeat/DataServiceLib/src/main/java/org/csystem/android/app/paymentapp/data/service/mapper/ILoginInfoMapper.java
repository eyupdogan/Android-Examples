package org.csystem.android.app.paymentapp.data.service.mapper;

import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO;
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoStatusDTO;
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper {
    LoginInfo toLoginInfo(LoginInfoDTO loginInfoDTO);

    @Mapping(source = "loginDateTime", target = "loginDateTimeStr", dateFormat = "dd/MM/yyyy kk:mm:ss")
    LoginInfoStatusDTO toLoginInfoStatusDTO(LoginInfo loginInfo);

}
