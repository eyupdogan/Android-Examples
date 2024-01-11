package org.csystem.android.app.generator.random.viewmodel.data

import java.io.Serializable

data class RandomTextGeneratorInfo(
    var fileName: String,
    var count: Int,
    var min: Int,
    var bound: Int
) : Serializable