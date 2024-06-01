package care.intouch.app.feature.common

sealed interface Resource<T, E> {
    class Success<T, E>(val data: T) : Resource<T, E>

    class Error<T, E>(val error: E) : Resource<T, E>

}

