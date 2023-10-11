package kz.noxiq.jokeapp.common.network

import retrofit2.Call
import java.io.IOException

fun <T> Call<T>.executeCall(): Result<T> {
    return try {
        val response = execute()

        when {
            !response.isSuccessful -> Result.failure(Exception())
            response.code() != 200 -> Result.failure(NetworkErrorException(response.code()))
            response.body() == null -> Result.failure(NullResponseException())
            else -> Result.success(response.body()!!)
        }
    } catch (e: IOException) {
        Result.failure(e)
    }
}