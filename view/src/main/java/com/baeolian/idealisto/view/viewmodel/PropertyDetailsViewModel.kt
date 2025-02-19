package com.baeolian.idealisto.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor() : ViewModel() {

    private val _event: MutableSharedFlow<PropertyDetailsEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    fun onBackClicked() {
        viewModelScope.launch {
            _event.emit(PropertyDetailsEvent.NavigateBack)
        }
    }
}

sealed class PropertyDetailsEvent {
    data object NavigateBack : PropertyDetailsEvent()
}
