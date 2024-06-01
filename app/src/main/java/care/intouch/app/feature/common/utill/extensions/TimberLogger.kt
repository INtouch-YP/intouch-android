package care.intouch.app.feature.common.utill.extensions

import timber.log.Timber

class TimberLogger : Logger {
    override fun d(message: String) {
        Timber.d(message)
    }
}