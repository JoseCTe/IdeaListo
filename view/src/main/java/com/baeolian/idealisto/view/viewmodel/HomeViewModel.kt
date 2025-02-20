package com.baeolian.idealisto.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baeolian.idealisto.domain.usecase.GetPropertyListUseCase
import com.baeolian.idealisto.view.data.PropertyViewData
import com.baeolian.idealisto.view.mapper.PropertyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPropertyListUseCase: GetPropertyListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<HomeEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            getPropertyListUseCase().onSuccess { propertyList ->
                _state.update { current ->
                    current.copy(
                        isLoading = false,
                        properties = propertyList.map { PropertyMapper.map(it) }
                    )
                }
            }.onFailure {
                // TODO: Handle error
            }
        }
    }

    fun onPropertyClicked(propertyId: String) {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToDetailsScreen(propertyId))
        }
    }

    fun onFavoriteClicked(propertyId: String) {
        // TODO: Add/remove favorite (data persistence)

        _state.update { current ->
            current.copy(
                properties = current.properties.map { property ->
                    if (property.propertyCode == propertyId) {
                        property.copy(isFavorite = !property.isFavorite)
                    } else {
                        property
                    }
                }
            )
        }
    }
}

data class HomeState(
    val isLoading: Boolean = true,
    val properties: List<PropertyViewData> = emptyList(),
)

sealed class HomeEvent {
    data class NavigateToDetailsScreen(
        val propertyId: String
    ) : HomeEvent()
}
