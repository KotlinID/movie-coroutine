package id.kotlin.training.coroutine.views.home

import id.kotlin.training.coroutine.data.local.Movie

interface MovieListener {

    fun onClick(movie: Movie)
}