package id.kotlin.training.coroutine.views.detail

import id.kotlin.training.coroutine.data.local.Movie
import id.kotlin.training.coroutine.ext.Configs
import id.kotlin.training.coroutine.views.base.Presenter

class DetailPresenter : Presenter<DetailView> {

    private var view: DetailView? = null

    override fun onAttach(view: DetailView) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    fun setMovieDetail(movie: Movie?) {
        @Configs val image = movie?.image

        val desc = movie?.desc
        val date = movie?.date
        val vote = movie?.vote

        view?.onDetailMovie(image, desc, date, vote)
    }
}