package care.intouch.app.feature.questions.presentations.ui.introductory

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.questions.presentations.ui.models.IntroductoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class IntroductoryViewModel @Inject constructor(
) : ViewModel() {

    private var _state = MutableStateFlow(IntroductoryState(""))
    val state = _state.asStateFlow()

    fun onEvent() {
        TODO()
    }
}