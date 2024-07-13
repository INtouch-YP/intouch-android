package care.intouch.app.feature.authorization.presentation

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

enum class PasswordValidType(val stringId: StringVO) {
    CORRECT(StringVO.Resource(-1)),
    SMALL_PASSWORD(StringVO.Resource(R.string.password_lengths_small_error)),
    BIG_PASSWORD(StringVO.Resource(R.string.password_lengths_long_error)),
    MISSING_SYMBOL(StringVO.Resource(R.string.password_rules_error)),
    INVALID_SYMBOL(StringVO.Resource(R.string.password_special_chars_error)),
    EXIST_SPACE(StringVO.Resource(R.string.password_spaces_error)),
    NOT_MATCH(StringVO.Resource(R.string.password_not_match_error));

    fun getString(): StringVO {
        return this.stringId
    }
}