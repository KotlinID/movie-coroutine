package id.kotlin.training.coroutine.views.base

interface Presenter<in T : View> {

    fun onAttach(view: T)

    fun onDetach()
}