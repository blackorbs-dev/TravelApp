package blackorbs.dev.travelapp.data

import android.util.Log
import blackorbs.dev.travelapp.data.entities.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RequestHandler<T>(
    private val call: Call<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun execute(): Flow<Response<T>> = flow{
        emit(Response.loading())
        try {
            Log.d("RequestHandler", "Executing... ${call.request().url()}")
            val response = call.execute()
            if(response.isSuccessful){
                response.body()?.let {
                    emit(Response.success(it))
                    return@flow
                }
            }
            emit(Response.error(response.message()))
        }
        catch (e: IOException){
            Log.e("RequestHandler", e.message ?: e.toString())
            emit(Response.error(e.message ?: e.toString()))
        }
        catch (e: HttpException){
            Log.e("RequestHandler", e.message ?: e.toString())
            emit(Response.error(e.message ?: e.toString()))
        }
    }.flowOn(dispatcher)
}

class RequestAdapter<T>(private val responseType: Type): CallAdapter<T, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Any = RequestHandler(call)
}

class RequestAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = try {
        val enclosingType = (returnType as ParameterizedType)
        if (enclosingType.rawType != RequestHandler::class.java)
            null
        else {
            val type = enclosingType.actualTypeArguments[0]
            RequestAdapter<Any>(type)
        }
    } catch (ex: ClassCastException) {
        null
    }

    companion object {
        @JvmStatic
        fun create() = RequestAdapterFactory()
    }

}