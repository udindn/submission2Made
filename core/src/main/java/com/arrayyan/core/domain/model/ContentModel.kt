package com.arrayyan.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ContentModel(
    val id: Int,
    val title: String? = "",
    val releaseDate: String? = "",
    val firstAirDate: String? = "",
    val overview: String? = "",
    val posterPath: String? = "",
    val createdBy: String? = "",
    val voteAverage: Double? = 0.0,
    val voteCount: Double? = 0.0,
    val originalLanguage: String? = "",
    val popularity: Double? = 0.0,
    val runtime: Int? = 0,
    val productionCountries: String? = "",
    val isFavorite: Boolean
) : Parcelable