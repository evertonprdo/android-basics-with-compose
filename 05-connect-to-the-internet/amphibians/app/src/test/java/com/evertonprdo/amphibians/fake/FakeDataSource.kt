package com.evertonprdo.amphibians.fake

import com.evertonprdo.amphibians.model.Amphibian

object FakeDataSource {

    const val nameOne = "frog1"
    const val typeOne = "type1"
    const val descriptionOne = "description1"
    const val imgSrcOne = "url.1"

    const val nameTwo = "frog2"
    const val typeTwo = "type2"
    const val descriptionTwo = "description2"
    const val imgSrcTwo = "url.2"

    val amphibianList = listOf(
        Amphibian(
            name = nameOne,
            type = typeOne,
            description = descriptionOne,
            imgSrc = imgSrcOne
        ),
        Amphibian(
            name = nameTwo,
            type = typeTwo,
            description = descriptionTwo,
            imgSrc = imgSrcTwo
        ),
    )
}
