package id.kotlin.training.coroutine

import android.support.multidex.MultiDexApplication
import id.kotlin.training.coroutine.deps.component.ApplicationComponent
import id.kotlin.training.coroutine.deps.component.DaggerApplicationComponent
import id.kotlin.training.coroutine.deps.module.NetworkModule
import id.kotlin.training.coroutine.deps.module.ServiceModule
import id.kotlin.training.coroutine.deps.provider.ApplicationProvider

class MovieApp : MultiDexApplication(), ApplicationProvider {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                                              .networkModule(NetworkModule(this))
                                              .serviceModule(ServiceModule())
                                              .build()
    }

    override fun providesApplicationComponent(): ApplicationComponent = component
}