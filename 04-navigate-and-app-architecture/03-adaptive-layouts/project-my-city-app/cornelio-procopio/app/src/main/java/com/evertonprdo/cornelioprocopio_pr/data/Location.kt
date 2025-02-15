package com.evertonprdo.cornelioprocopio_pr.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Location(
    @DrawableRes val banner: Int,
    @StringRes val title: Int,
    @StringRes val address: Int,
    @StringRes val about: Int,
    @StringRes val website: Int?,
    @StringRes val phone: Int?,
    val category: LocationCategory
)