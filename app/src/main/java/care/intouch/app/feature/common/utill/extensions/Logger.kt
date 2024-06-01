package care.intouch.app.feature.common.utill.extensions

interface Logger {
    companion object : Logger {
        var realLogger: Logger? = TimberLogger()
        override fun d(message: String) {
            realLogger?.d(message)
        }
    }

    fun d(message: String)
}