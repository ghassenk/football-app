package com.gk.app.football.data.internal.remote

import com.gk.app.football.data.internal.LeagueSearchResponse
import com.gk.app.football.data.internal.TeamSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface ApiWebservice {

    @GET("{endpoint_path}")
    suspend fun searchTeams(
        @Path("endpoint_path") endpointPath: String,
        @Header("Cache-Control") cacheControl: String = "max-age=0",
        @QueryMap queryMap: Map<String, String>? = emptyMap(),
    ): TeamSearchResponse

    @GET("{endpoint_path}")
    suspend fun searchLeagues(
        @Path("endpoint_path") endpointPath: String,
        @Header("Cache-Control") cacheControl: String = "max-age=0",
        @QueryMap queryMap: Map<String, String>? = emptyMap(),
    ): LeagueSearchResponse
}