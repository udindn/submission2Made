package com.arrayyan.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("first_air_date")
    val firstAirDate: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("origin_country")
    val productionCountries: List<String>?,
    @SerializedName("created_by")
    val createdBy: List<CreatedBy?>? = emptyList(),
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Double? = 0.0,
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("runtime")
    val runtime: Int? = 0
) : Parcelable {
    @Parcelize
    data class CreatedBy(
        @SerializedName("name")
        val name: String? = ""
    ) : Parcelable
}