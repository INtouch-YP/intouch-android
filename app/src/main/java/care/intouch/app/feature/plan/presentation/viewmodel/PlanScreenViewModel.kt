package care.intouch.app.feature.plan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import care.intouch.app.feature.plan.domain.models.PlanScreenSideEffect
import care.intouch.app.feature.plan.domain.usecase.GetAssignmentsUseCase
import care.intouch.app.feature.plan.presentation.models.PlanScreenEvent
import care.intouch.app.feature.plan.presentation.models.PlanScreenState
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanScreenViewModel @Inject constructor(
    private val getAssignmentsUseCase: GetAssignmentsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlanScreenState())
    val uiState: StateFlow<PlanScreenState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PlanScreenSideEffect>()
    val sideEffect: SharedFlow<PlanScreenSideEffect> = _sideEffect.asSharedFlow()

    init {
        getAssignments()
    }

    fun onEvent(planScreenEvent: PlanScreenEvent) {
        when(planScreenEvent) {

            PlanScreenEvent.GetAssignmentsEvent -> {
                getAssignments()
            }

            is PlanScreenEvent.SetDialogueVisibilityEvent -> {
                setDialogueIsVisible(planScreenEvent.isVisible)
            }

            is PlanScreenEvent.FilterAssignmentsEvent -> {
                updateFilterAssignments(planScreenEvent.assignmentStatus)
            }
        }
    }

    private fun getAssignments() {
        viewModelScope.launch(Dispatchers.Main) {
            getAssignmentsUseCase().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        _uiState.update { planScreenState ->
                            planScreenState.copy(
                                assignments = resource.data,
                                filteredAssignments = resource.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        val errorMessage = resource.error.message
                        _sideEffect.emit(PlanScreenSideEffect.ShowToast(message = StringVO.Plain(errorMessage)))
                    }
                }
            }
        }
    }

    private fun updateFilterAssignments(
        filterStatus: AssignmentStatus
    ) {
        if (filterStatus == AssignmentStatus.SHOW_ALL) {
            _uiState.update { planScreenState ->
                planScreenState.copy(
                    filteredAssignments = _uiState.value.assignments
                )
            }
        } else {
            val filteredAssignments = _uiState.value.assignments
                .filter { assignment -> assignment.status == filterStatus  }

            _uiState.update { planScreenState ->
                planScreenState.copy(
                    filteredAssignments = filteredAssignments
                )
            }
        }
    }

    private fun setDialogueIsVisible(isVisible: Boolean) {
        _uiState.update { planScreenState ->
            planScreenState.copy(
                isDialogueVisible = isVisible
            )
        }
    }
}