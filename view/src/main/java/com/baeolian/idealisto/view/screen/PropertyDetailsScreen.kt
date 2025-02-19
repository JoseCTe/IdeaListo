package com.baeolian.idealisto.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baeolian.idealisto.view.viewmodel.PropertyDetailsEvent
import com.baeolian.idealisto.view.viewmodel.PropertyDetailsViewModel

@Composable
fun PropertyDetailsScreen(
    viewModel: PropertyDetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    PropertyDetailsContent(
        onBackClicked = viewModel::onBackClicked,
    )

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when (event) {
                is PropertyDetailsEvent.NavigateBack -> navigateBack()
            }
        }
    }
}

@Composable
private fun PropertyDetailsContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        // TODO
    }
}
