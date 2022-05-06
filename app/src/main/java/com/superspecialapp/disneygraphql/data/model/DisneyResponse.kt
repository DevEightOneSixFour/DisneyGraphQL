package com.superspecialapp.disneygraphql.data.model

import com.superspecialapp.disneygraphql.CharactersQuery

data class DisneyResponse(
    val data: List<DisneyItem?>? = emptyList(),
    val count: Int? = null,
    val previousPage: String? = null,
    val nextPage: String? = null
)

fun CharactersQuery.Characters.toDisneyResponse() =
    DisneyResponse(
        data = this.items!!.map { it!!.toDisneyListItem() }
    )

fun CharactersQuery.Item.toDisneyListItem(): DisneyItem {
    val fragment = this.fragments.characterFragment
    return DisneyItem(
        id = fragment._id,
        name = fragment.name,
        films = fragment.films,
        shortFilms = fragment.shortFilms,
        tvShows = fragment.tvShows,
        videoGames = fragment.videoGames,
        parkAttractions = fragment.parkAttractions,
        allies = fragment.allies,
        enemies = fragment.enemies,
        alignment = fragment.alignment,
        sourceUrl = fragment.sourceUrl,
        imageUrl = fragment.imageUrl,
        url = fragment.url
    )
}