package com.baeolian.idealisto.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baeolian.idealisto.domain.usecase.AddFavoriteUseCase
import com.baeolian.idealisto.domain.usecase.DeleteFavoriteUseCase
import com.baeolian.idealisto.domain.usecase.GetFavoritesUseCase
import com.baeolian.idealisto.domain.usecase.GetPropertyListUseCase
import com.baeolian.idealisto.view.data.PropertyViewData
import com.baeolian.idealisto.view.mapper.PropertyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPropertyListUseCase: GetPropertyListUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
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
                        properties = propertyList.map { PropertyMapper.map(it) }
                    )
                }

                delay(INITIAL_ANIMATION_DELAY)

                getFavoritesUseCase().collect { favorites ->
                    _state.update { current ->
                        current.copy(
                            isLoading = false,
                            properties = current.properties.map {
                                it.copy(dateOfFavorite = favorites[it.propertyCode])
                            }
                        )
                    }
                }
            }
        }
    }

    fun onPropertyClicked(propertyId: String) {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToDetailsScreen(propertyId))
        }
    }

    fun onFavoriteClicked(propertyId: String) {
        if (state.value.properties.find { it.propertyCode == propertyId }?.dateOfFavorite != null) {
            deleteFavoriteUseCase(propertyId)
        } else {
            addFavoriteUseCase(propertyId, Date())
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

private const val INITIAL_ANIMATION_DELAY = 2000L
