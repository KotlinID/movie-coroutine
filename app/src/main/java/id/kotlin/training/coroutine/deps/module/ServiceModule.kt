package id.kotlin.training.coroutine.deps.module

import dagger.Module
import dagger.Provides
import id.kotlin.training.coroutine.data.Api
import id.kotlin.training.coroutine.services.DiscoverMovieService
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Provides
    @Singleton
    protected fun providesDiscoverMovieService(api: Api) = DiscoverMovieService(api)
}