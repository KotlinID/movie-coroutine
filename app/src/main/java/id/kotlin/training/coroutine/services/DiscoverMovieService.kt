package id.kotlin.training.coroutine.services

import id.kotlin.training.coroutine.data.Api
import id.kotlin.training.coroutine.data.remote.DiscoverMovie
import retrofit2.Response

class DiscoverMovieService(private val api: Api) {

    suspend fun discoverMovie(key: String, sortBy: String): Response<DiscoverMovie> {
        return api.discoverMovie(key, sortBy).await()
    }
}