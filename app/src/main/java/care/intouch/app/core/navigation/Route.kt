package care.intouch.app.core.navigation

interface IntouchDestination {
    val route: String
}

//Debug
object DebugMode: IntouchDestination {
    override val route = "DEBUG_MODE"
}
object Navigation: IntouchDestination {
    override val route = "NAVIGATION"
}
object Sample: IntouchDestination {
    override val route = "SAMPLE"
}

//Samples
object SampleRouteBranch: IntouchDestination {
    override val route = "SAMPLE_ROUTE_BRANCH"
}

object OnEmotionClick : IntouchDestination {
    override val route = "ON_EMOTION_CLICK"
}
object ButtonsSample: IntouchDestination {
    override val route = "BUTTONS_SAMPLE"
}
object ChipsSample: IntouchDestination {
    override val route = "CHIPS_SAMPLE"
}
object MultilineTextFieldSample: IntouchDestination {
    override val route = "MULTILINE_TEXT_FIELD_SAMPLE"
}
object NavigationSample: IntouchDestination {
    override val route = "NAVIGATION_SAMPLE"
}
object OneLineTextFieldSample: IntouchDestination {
    override val route = "ONE_LINE_TEXT_FIELD_SAMPLE"
}
object PasswordTextFieldSample: IntouchDestination {
    override val route = "PASSWORD_TEXT_FIELD_SAMPLE"
}
object SliderSample: IntouchDestination {
    override val route = "SLIDER_SAMPLE"
}
object ToggleSample: IntouchDestination {
    override val route = "TOGGLE_SAMPLE"
}

object ButtonSample2: IntouchDestination {
    override val route = "BUTTON_SAMPLE_2"
}

object CardSample: IntouchDestination {
    override val route = "CARD_SAMPLE"
}

object CheckboxSample: IntouchDestination {
    override val route = "CHECKBOX_SAMPLE"
}

object CheckmarkSample: IntouchDestination {
    override val route = "CHECKMARK_SAMPLE"
}

object PinCodeSample: IntouchDestination {
    override val route = "PIN_CODE_SAMPLE"
}

object ProgressBarSample: IntouchDestination {
    override val route = "PROGRESS_BAR_SAMPLE"
}

// Authorization
object AuthorizationRouteBranch: IntouchDestination {
    override val route = "AUTHORIZATION_ROUTE_BRANCH"
}
object Registration: IntouchDestination {
    override val route = "REGISTRATION"
}
object Authentication: IntouchDestination {
    override val route = "AUTHENTICATION"
}
object PasswordRecovery: IntouchDestination {
    override val route = "PASSWORD_RECOVERY"
}
object PinCodeConfirmation: IntouchDestination {
    override val route = "PIN_CODE_CONFIRMATION"
}
object PinCodeInstallation: IntouchDestination {
    override val route = "PIN_CODE_INSTALLATION"
}
object PinCodeEnter: IntouchDestination {
    override val route = "PIN_CODE_ENTER"
}
object SendingNotification: IntouchDestination {
    override val route = "SENDING_NOTIFICATION"
}
object Authorization: IntouchDestination {
    override val route = "AUTHORIZATION"
}

//Bottom Navigation
object MainNav: IntouchDestination {
    override val route = "MAIN_NAV"
}
object Home: IntouchDestination {
    override val route = "HOME"
}
object Plan: IntouchDestination {
    override val route = "PLAN"
}
object Diary: IntouchDestination {
    override val route = "DIARY"
}
object Profile: IntouchDestination {
    override val route = "PROFILE"
}

// Plan branch
object PlanRouteBranch: IntouchDestination {
    override val route = "PLAN_ROUTE_BRANCH/{itemId}"
    fun createRoute(itemId: Int) = "PLAN_ROUTE_BRANCH/$itemId"
}
object PlanBottomNav: IntouchDestination {
    override val route = "PLAN_BOTTOM_NAV"
}
object Task: IntouchDestination {
    override val route = "TASK"
}
object TaskIntroduction: IntouchDestination {
    override val route = "TASK_INTRODUCTION/{itemId}"
}
object TaskEstimate: IntouchDestination {
    override val route = "TASK_ESTIMATE"
}
object TaskCompleting: IntouchDestination {
    override val route = "TASK_COMPLETING"
}

// Diary branch
object DiaryRouteBranch: IntouchDestination {
    override val route = "DIARY_ROUTE_BRANCH"
}
object CreatingNoteIntroduction: IntouchDestination {
    override val route = "CREATING_NOTE_INTRODUCTION"
}
object DiaryEntries: IntouchDestination {
    override val route = "DIARY_ENTRIES"
}
object EmotionChoice: IntouchDestination {
    override val route = "EMOTIONS_CHOICE"
}

// Profile branch
object ProfileRouteBranch: IntouchDestination {
    override val route = "PROFILE_ROUTE_BRANCH"
}
object PasswordChange: IntouchDestination {
    override val route = "PASSWORD_CHANGE"
}
object NewPinCode: IntouchDestination {
    override val route = "NEW_PIN_CODE"
}
object PinCodeChange: IntouchDestination {
    override val route = "PIN_CODE_CHANGE"
}
object PinCodeConfirm: IntouchDestination {
    override val route = "PIN_CODE_CONFIRM"
}
object SuccessfulPinCodeChange: IntouchDestination {
    override val route = "SUCCESSFUL_PIN_CODE_CHANGE"
}

//Questions branch
object QuestionsRouteBranch: IntouchDestination {
    override val route = "QUESTIONS_ROUTE_BRANCH/{itemId}"
    fun createRoute(itemId: Int) = "QUESTIONS_ROUTE_BRANCH/$itemId"
}
object AssignmentsQuestion: IntouchDestination {
    override val route = "ASSIGNMENT_QUESTIONS"
}
object IntroductoryQuestion: IntouchDestination {
    override val route = "INTRODUCTORY_QUESTIONS/{itemId}"
}
