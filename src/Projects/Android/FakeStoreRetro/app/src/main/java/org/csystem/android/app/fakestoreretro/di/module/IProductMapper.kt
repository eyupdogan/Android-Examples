package org.csystem.android.app.fakestoreretro.di.module

import org.csystem.android.app.fakestoreretro.viewmodel.data.ProductData
import org.csystem.android.app.repository.api.entity.Product
import org.mapstruct.Mapper

@Mapper(implementationName = "ProductMapperImpl")
interface IProductMapper
{
    fun toProductDataDTO(product: Product):ProductData
}