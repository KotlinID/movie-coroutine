package id.kotlin.training.coroutine.views.home

import id.kotlin.training.coroutine.data.local.Movie
import id.kotlin.training.coroutine.data.remote.DiscoverMovie
import id.kotlin.training.coroutine.ext.Config
import id.kotlin.training.coroutine.ext.Configs
import id.kotlin.training.coroutine.ext.getDate
import id.kotlin.training.coroutine.services.DiscoverMovieService
import id.kotlin.training.coroutine.views.base.Presenter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Response

class MoviePresenter : Presenter<MovieView> {

    private var view: MovieView? = null

    override fun onAttach(view: MovieView) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    fun discoverMovie(service: DiscoverMovieService) {
        view?.onProgress()

        @Configs val apiKey = Config.API_KEY
        @Configs val defaultSort = Config.DEFAULT_SORT

        launch(UI) {
            val response: Response<DiscoverMovie> = service.discoverMovie(apiKey, defaultSort)

            @Configs val movieBaseUrl = Config.BASE_MOVIE_URL
            val data = response.body()?.results ?: throw Exception("No Result!")
            val movies = data.map {
                val title = it.title
                val desc = it.overview
                val date = getDate(it.releaseDate)
                val images = movieBaseUrl.plus(it.posterPath)
                val vote = it.voteAverage

                Movie(title, desc, date, images, vote)
            }

            view?.onSuccess(movies)
        }
    }

    fun openMovieDetail(movie: Movie) {
        view?.onOpenMovieDetail(movie)
    }
}