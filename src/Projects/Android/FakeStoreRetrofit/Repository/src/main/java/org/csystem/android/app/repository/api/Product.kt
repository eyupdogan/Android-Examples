package org.csystem.android.app.repository.api

import kotlinx.serialization.Serializable

@Serializable
class Product
{
    var id:Int = 0
    var title: String? = null
    var price:Double = 0.0
    var description: String? = null
    var category: String? = null
    var image: String? = null
    var rating: Rating? = null
}
