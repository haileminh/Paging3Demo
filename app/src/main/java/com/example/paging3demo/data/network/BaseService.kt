package com.example.paging3demo.data.network

import com.example.paging3demo.data.model.Result
import android.content.res.Resources
import com.example.paging3demo.data.network.exceptions.NoInternetException
import com.example.paging3demo.data.network.exceptions.UnAuthorizedException
import com.example.paging3demo.data.network.exceptions.UnKnownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseService {

    protected suspend fun <T : Any> createCall(call: suspend () -> Response<T>): Result<T> {

        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            t.printStackTrace()
            return Result.Error(mapToNetworkError(t))
        }

        if (response.isSuccessful) {
            if (response.body() != null) {
                return Result.Success(response.body()!!)
            }
        } else {
            val errorBody = response.errorBody()
            return if (errorBody != null) {
                Result.Error(mapApiException(response.code()))
            } else Result.Error(mapApiException(0))
        }
        return Result.Error(HttpException(response))
    }

    private fun mapApiException(code: Int): Exception {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> Resources.NotFoundException()
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorizedException()
            else -> UnKnownException()
        }
    }

    private fun mapToNetworkError(t: Throwable): Exception {
        return when (t) {
            is SocketTimeoutException -> SocketTimeoutException("Connection Timed Out")
            is UnknownHostException -> NoInternetException()
            else -> UnKnownException()

        }
    }
}