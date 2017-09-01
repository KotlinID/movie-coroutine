package id.kotlin.training.coroutine.deps.component

import dagger.Component
import id.kotlin.training.coroutine.deps.module.NetworkModule
import id.kotlin.training.coroutine.deps.module.ServiceModule
import id.kotlin.training.coroutine.views.home.MovieActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        NetworkModule::class,
        ServiceModule::class
))
interface ApplicationComponent {

    fun inject(movieActivity: MovieActivity)
}