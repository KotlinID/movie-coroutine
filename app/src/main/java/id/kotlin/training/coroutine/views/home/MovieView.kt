package id.kotlin.training.coroutine.views.home

import id.kotlin.training.coroutine.data.local.Movie
import id.kotlin.training.coroutine.views.base.View

interface MovieView : View {

    fun onProgress()

    fun onSuccess(movies: List<Movie>)

    fun onOpenMovieDetail(movie: Movie)
}