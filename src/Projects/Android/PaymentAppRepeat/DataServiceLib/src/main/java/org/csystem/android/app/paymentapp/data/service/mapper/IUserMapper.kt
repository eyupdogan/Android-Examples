package org.csystem.android.app.paymentapp.data.service.mapper

import org.csystem.android.app.paymentapp.data.service.dto.UserSaveDTO
import org.csystem.android.app.paymentapp.repository.entity.User
import org.mapstruct.Mapper

@Mapper(implementationName = "UserMapperImpl")
interface IUserMapper
{
    fun toUser(userSaveDTO: UserSaveDTO): User

    fun toUserSaveDTO(user: User): UserSaveDTO
}