package org.csystem.android.app.paymentapp.data.service.mapper

import org.csystem.android.app.paymentapp.data.service.dto.PaymentSaveDTO
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(implementationName = "PaymentMapperImpl")
interface IPaymentMapper
{
    @Mapping(source = "price", target = "unitPrice")
    @Mapping(source = "desc", target = "description")
    fun toPayment(paymentSaveDTO: PaymentSaveDTO):Payment
}