package com.debasmita.tmdbmovieapp.data.model

data class CreditsResponse(
    val crew: List<Crew>
)

data class Crew(
    val name: String,
    val job: String
)

