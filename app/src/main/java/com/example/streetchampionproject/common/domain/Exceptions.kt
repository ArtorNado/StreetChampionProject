package com.example.streetchampionproject.common.domain

class Exceptions(
    val kind: Kind,
    message: String = "",
    exception: Throwable? = null,
    val errorResponseCode: ResponseCode? = null
) : RuntimeException(message, exception) {

    enum class Kind {
        BUSINESS,
        NETWORK,
        HTTP,
        UNEXPECTED
    }

    companion object {

        fun error(responseCode: ResponseCode): Exceptions =
            Exceptions(Kind.BUSINESS, responseCode.toString(), errorResponseCode = responseCode)
    }
}
