package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.questions.presentations.ui.models.QuestionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
) : ViewModel() {

    private var _state = MutableStateFlow(QuestionsState(""))
    val state = _state.asStateFlow()

    fun onEvent() {
        TODO()
    }
}