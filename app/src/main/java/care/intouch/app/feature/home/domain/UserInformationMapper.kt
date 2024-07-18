package care.intouch.app.feature.home.domain

import care.intouch.app.feature.authorization.domain.models.User
import care.intouch.app.feature.home.presentation.models.UserInformation
import javax.inject.Inject

class UserInformationMapper @Inject constructor() {
    fun map(user: User?): UserInformation {
        return UserInformation(
            userId = user?.id ?: 0,
            userName = user?.firstName ?: DEFAULT_NAME
        )
    }

    companion object {
        const val DEFAULT_NAME = "Bob"
    }
}