package care.intouch.app.feature.profile.data.mappers

import care.intouch.app.feature.profile.data.dto.PasswordDataDto
import care.intouch.app.feature.profile.domain.models.PasswordData

fun PasswordData.toData(): PasswordDataDto {
    return PasswordDataDto(
        currentPassword = this.currentPassword,
        newPassword = this.newPassword,
        newConfirmationPassword = this.newConfirmationPassword
    )
}