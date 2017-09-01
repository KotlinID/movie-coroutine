package id.kotlin.training.coroutine.data

import id.kotlin.training.coroutine.data.remote.DiscoverMovie
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie")
    fun discoverMovie(@Query("api_key") key: String,
                      @Query("sort_by") sortBy: String): Deferred<Response<DiscoverMovie>>
}