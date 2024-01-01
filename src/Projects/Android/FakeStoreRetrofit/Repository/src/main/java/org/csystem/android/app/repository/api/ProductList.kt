package org.csystem.android.app.repository.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductList(val list: List<Product> = emptyList())