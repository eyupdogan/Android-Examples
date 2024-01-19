package org.csystem.android.app.payment.repository.dao

import com.karandev.util.data.repository.ICrudRepository
import org.csystem.android.app.payment.repository.entity.LoginInfo

interface ILoginInfoRepository: ICrudRepository<LoginInfo, Long>
{
    fun findByUsername(username:String):List<LoginInfo>

    fun findSuccessLoginsByUsername(username: String):List<LoginInfo>
    fun findFailLoginsByUsername(username: String):List<LoginInfo>

    fun findLastSuccessLoginsByUsername(username: String):List<LoginInfo>
    fun findLastFailLoginsByUsername(username: String):List<LoginInfo>
}