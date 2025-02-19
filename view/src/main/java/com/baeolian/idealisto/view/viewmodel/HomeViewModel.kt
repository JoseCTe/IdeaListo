package com.baeolian.idealisto.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _event: MutableSharedFlow<HomeEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    fun onPropertyClicked(propertyId: String) {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToDetailsScreen(propertyId))
        }
    }
}

sealed class HomeEvent {
    data class NavigateToDetailsScreen(
        val propertyId: String
    ) : HomeEvent()
}
