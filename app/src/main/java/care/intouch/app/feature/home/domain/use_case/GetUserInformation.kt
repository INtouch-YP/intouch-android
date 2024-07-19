package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.home.domain.UserInformationMapper
import care.intouch.app.feature.home.presentation.models.UserInformation
import javax.inject.Inject

interface GetUserInformation {
    suspend fun execute(): UserInformation

    class Base @Inject constructor(
        private val mapper: UserInformationMapper,
        private val repository: UserStorage
    ) : GetUserInformation {
        override suspend fun execute(): UserInformation {
            val user = repository.read()
            return mapper.map(user = user)
        }
    }
}