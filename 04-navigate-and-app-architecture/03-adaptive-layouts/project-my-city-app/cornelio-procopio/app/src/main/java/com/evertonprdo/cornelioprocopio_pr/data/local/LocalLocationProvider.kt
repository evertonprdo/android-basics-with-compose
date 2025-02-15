package com.evertonprdo.cornelioprocopio_pr.data.local

import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.data.Location
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory

object LocalLocationProvider {
    val allLocations = listOf(
        Location(
            banner = R.drawable.university_01,
            title = R.string.university_01_title,
            address = R.string.university_01_address,
            about = R.string.university_01_about,
            website = R.string.university_01_website,
            phone = R.string.university_01_phone,
            category = LocationCategory.University
        ),
        Location(
            banner = R.drawable.hotel_01,
            title = R.string.hotel_01_title,
            address = R.string.hotel_01_address,
            about = R.string.hotel_01_about,
            website = R.string.hotel_01_website,
            phone = R.string.hotel_01_phone,
            category = LocationCategory.Hotel
        ),
        Location(
            banner = R.drawable.hotel_02,
            title = R.string.hotel_02_title,
            address = R.string.hotel_02_address,
            about = R.string.hotel_02_about,
            website = R.string.hotel_02_website,
            phone = R.string.hotel_02_phone,
            category = LocationCategory.Hotel
        ),
        Location(
            banner = R.drawable.hotel_03,
            title = R.string.hotel_03_title,
            address = R.string.hotel_03_address,
            about = R.string.hotel_03_about,
            website = R.string.hotel_03_website,
            phone = R.string.hotel_03_phone,
            category = LocationCategory.Hotel
        ),
        Location(
            banner = R.drawable.places_to_visit_01,
            title = R.string.places_to_visit_01_title,
            address = R.string.places_to_visit_01_address,
            about = R.string.places_to_visit_01_about,
            website = R.string.places_to_visit_01_website,
            phone = null,
            category = LocationCategory.PlacesToVisit
        ),
        Location(
            banner = R.drawable.places_to_visit_02,
            title = R.string.places_to_visit_02_title,
            address = R.string.places_to_visit_02_address,
            about = R.string.places_to_visit_02_about,
            website = null,
            phone = null,
            category = LocationCategory.PlacesToVisit
        ),
        Location(
            banner = R.drawable.places_to_visit_03,
            title = R.string.places_to_visit_03_title,
            address = R.string.places_to_visit_03_address,
            about = R.string.places_to_visit_03_about,
            website = null,
            phone = null,
            category = LocationCategory.PlacesToVisit
        ),
        Location(
            banner = R.drawable.restaurant_01,
            title = R.string.restaurant_01_title,
            address = R.string.restaurant_01_address,
            about = R.string.restaurant_01_about,
            website = R.string.restaurant_01_website,
            phone = R.string.restaurant_01_phone,
            category = LocationCategory.Restaurant
        ),
        Location(
            banner = R.drawable.restaurant_02,
            title = R.string.restaurant_02_title,
            address = R.string.restaurant_02_address,
            about = R.string.restaurant_02_about,
            website = R.string.restaurant_02_website,
            phone = R.string.restaurant_02_phone,
            category = LocationCategory.Restaurant
        ),
        Location(
            banner = R.drawable.restaurant_03,
            title = R.string.restaurant_03_title,
            address = R.string.restaurant_03_address,
            about = R.string.restaurant_03_about,
            website = null,
            phone = null,
            category = LocationCategory.Restaurant
        ),
        Location(
            banner = R.drawable.restaurant_04,
            title = R.string.restaurant_04_title,
            address = R.string.restaurant_04_address,
            about = R.string.restaurant_04_about,
            website = R.string.restaurant_04_website,
            phone = R.string.restaurant_04_phone,
            category = LocationCategory.Restaurant
        ),
    )
}