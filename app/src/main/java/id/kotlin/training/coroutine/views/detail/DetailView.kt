package id.kotlin.training.coroutine.views.detail

import id.kotlin.training.coroutine.views.base.View

interface DetailView : View {

    fun onDetailMovie(image: String?,
                      desc: String?,
                      date: String?,
                      vote: Double?)
}