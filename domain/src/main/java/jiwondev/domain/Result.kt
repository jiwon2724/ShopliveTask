package jiwondev.domain

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
}

suspend fun <T : Any> setResult(response: (suspend () -> T)): Result<T> {
    return try {
        Result.Success(response.invoke())
    } catch (e: Exception) {
        Result.Error(e)
    }
}