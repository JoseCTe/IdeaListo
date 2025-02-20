package com.baeolian.idealisto.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baeolian.idealisto.domain.usecase.GetPropertyDetailsUseCase
import com.baeolian.idealisto.view.data.PropertyDetailsViewData
import com.baeolian.idealisto.view.mapper.PropertyDetailsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
    private val getPropertyDetailsUseCase: GetPropertyDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PropertyDetailsState())
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<PropertyDetailsEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            getPropertyDetailsUseCase().onSuccess { propertyDetails ->
                _state.update{ current ->
                    current.copy(property = PropertyDetailsMapper.map(propertyDetails))
                }

                delay(ANIMATION_DELAY)

                _state.update { current ->
                    current.copy(isLoading = false)
                }
            }
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _event.emit(PropertyDetailsEvent.NavigateBack)
        }
    }
}

data class PropertyDetailsState(
    val isLoading: Boolean = true,
    val property: PropertyDetailsViewData? = null,
)

sealed class PropertyDetailsEvent {
    data object NavigateBack : PropertyDetailsEvent()
}

private const val ANIMATION_DELAY = 1750L
