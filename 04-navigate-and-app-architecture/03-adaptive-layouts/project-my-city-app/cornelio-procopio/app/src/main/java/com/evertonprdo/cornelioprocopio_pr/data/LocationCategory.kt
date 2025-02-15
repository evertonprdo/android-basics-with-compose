package com.evertonprdo.cornelioprocopio_pr.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.evertonprdo.cornelioprocopio_pr.R

enum class LocationCategory(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    All(title = R.string.all_category, icon = R.drawable.clear_all),
    University(
        title = R.string.category_public_university,
        icon = R.drawable.university
    ),
    PlacesToVisit(
        title = R.string.category_places_to_visit_title,
        icon = R.drawable.places_to_visit_icon
    ),
    Hotel(title = R.string.category_hotel, icon = R.drawable.hotel_icon),
    Restaurant(
        title = R.string.category_restaurant,
        icon = R.drawable.restaurant
    ),
}