package com.superspecialapp.disneygraphql.data.model

import com.superspecialapp.disneygraphql.fragment.CharacterFragment

data class DisneyItem(
    val id: Int? = null,
    val name: String? = null,
    val films: List<String?>? = emptyList(),
    val shortFilms: List<String?>? = emptyList(),
    val tvShows: List<String?>? = emptyList(),
    val videoGames: List<String?>? = emptyList(),
    val parkAttractions: List<String?>? = emptyList(),
    val allies: List<String?>? = emptyList(),
    val enemies: List<String?>? = emptyList(),
    val alignment: String? = null,
    val sourceUrl: String? = null,
    val imageUrl: String? = null,
    val url: String? = null,
)

fun CharacterFragment.toDisneyItem() =
    DisneyItem(
        id = this._id,
        name = this.name,
        films = this.films,
        shortFilms = this.shortFilms,
        tvShows = this.tvShows,
        videoGames = this.videoGames,
        parkAttractions = this.parkAttractions,
        allies = this.allies,
        enemies = this.enemies,
        alignment = this.alignment,
        sourceUrl = this.sourceUrl,
        imageUrl = this.imageUrl,
        url = this.url
    )

