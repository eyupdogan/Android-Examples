package org.csystem.android.app.fakestore.viewmodel.data

import org.csystem.android.app.repository.api.Rating

data class ProductData(var id:Int = 0,
                       var title: String? = null,
                       var price:Double = 0.0,
                       var description: String? = null,
                       var category: String? = null,
                       var image: String? = null,
                       var rating: Rating? = null)


