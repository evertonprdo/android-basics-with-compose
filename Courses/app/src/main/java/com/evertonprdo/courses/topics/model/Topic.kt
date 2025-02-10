package com.evertonprdo.courses.topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val courseAmount: Int,
    @DrawableRes val imageResourceId: Int,
)