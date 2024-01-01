package org.csystem.android.app.repository.api

import kotlinx.serialization.Serializable

@Serializable
class Rating
{
    var rate:Double = 0.0
    var count:Int = 0
}