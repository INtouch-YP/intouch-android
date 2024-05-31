package care.intouch.app.feature.common.utill.extensions

class TestLogger : Logger {
    override fun d(message: String) {
        println(message)
    }
}