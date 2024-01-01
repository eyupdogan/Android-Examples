package org.csystem.android.app.fakestore.di.module.mapper

import org.csystem.android.app.fakestore.viewmodel.data.ProductData
import org.csystem.android.app.repository.api.Product
import org.mapstruct.Mapper

@Mapper(implementationName = "ProductMapperImpl")
interface IProductMapper
{
    fun toProductDataDTO(product:Product):ProductData
}