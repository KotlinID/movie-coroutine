package id.kotlin.training.coroutine.services

import id.kotlin.training.coroutine.ext.clazz
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.coroutines.experimental.CoroutineContext

class CoroutinesCallAdapterFactory private constructor(private val context: CoroutineContext) : CallAdapter.Factory() {

    companion object {
        fun create(context: CoroutineContext = newFixedThreadPoolContext(5, "Retrofit-Coroutines")): CoroutinesCallAdapterFactory {
            return CoroutinesCallAdapterFactory(context)
        }
    }

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        fun createInvalidReturnTypeException(): RuntimeException {
            return IllegalStateException("Return type must be parameterized as Deferred<Foo>, Deferred<out Foo>, " +
                    "Deferred<Response<Foo>> or Deferred<Response<out Foo>>")
        }

        val rawType = getRawType(returnType)
        if (rawType != clazz<Deferred<*>>()) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw createInvalidReturnTypeException()
        }

        val deferredType = getParameterUpperBound(0, returnType)
        val rawDeferredType = getRawType(deferredType)

        return if (rawDeferredType == clazz<Response<*>>()) {
            if (deferredType !is ParameterizedType) {
                throw throw createInvalidReturnTypeException()
            }
            val responseType = getParameterUpperBound(0, deferredType)

            CoroutinesResponseCallAdapter(context, responseType)
        } else {
            CoroutinesCallAdapter(context, deferredType)
        }
    }
}