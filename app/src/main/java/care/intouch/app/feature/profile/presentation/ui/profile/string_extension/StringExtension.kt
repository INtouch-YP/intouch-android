package care.intouch.app.feature.profile.presentation.ui.profile.string_extension

fun String.replaceChars() = this.replace("  ", " ")
    .replace("..", ".")
    .replace("--", "-")
