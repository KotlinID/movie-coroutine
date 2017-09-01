package id.kotlin.training.coroutine.deps.provider

import id.kotlin.training.coroutine.deps.component.ApplicationComponent

interface ApplicationProvider {

    fun providesApplicationComponent(): ApplicationComponent
}