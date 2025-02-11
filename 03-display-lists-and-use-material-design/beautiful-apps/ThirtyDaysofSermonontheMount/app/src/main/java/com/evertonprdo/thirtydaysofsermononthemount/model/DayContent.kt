package com.evertonprdo.thirtydaysofsermononthemount.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DayContent(
    val day: Int,
    @StringRes val titleResId: Int,
    @StringRes val contentResId: Int,
    @StringRes val contentReferenceResId: Int,
    @DrawableRes val imageResId: Int
)
