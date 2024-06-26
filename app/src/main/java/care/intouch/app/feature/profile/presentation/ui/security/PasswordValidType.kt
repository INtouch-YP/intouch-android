package care.intouch.app.feature.profile.presentation.ui.security

import care.intouch.app.R

enum class PasswordValidType(val stringId: Int) {
    CORRECT(-1),
    INCORRECT_CURRENT_PASSWORD(R.string.incorrect_error),
    SMALL_PASSWORD(R.string.password_lengths_small_error),
    BIG_PASSWORD(R.string.password_lengths_long_error),
    MISSING_SYMBOL(R.string.password_rules_error),
    INVALID_SYMBOL(R.string.password_special_chars_error),
    EXIST_SPACE(R.string.password_spaces_error),
    NOT_MATCH(R.string.password_not_match_error);

    fun getString(): Int {
        return this.stringId
    }
}