package com.baeolian.idealisto.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baeolian.idealisto.view.viewmodel.HomeEvent
import com.baeolian.idealisto.view.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailsScreen: (propertyId: String) -> Unit,
) {
    HomeContent(
        onPropertyClicked = viewModel::onPropertyClicked
    )

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeEvent.NavigateToDetailsScreen -> {
                    navigateToDetailsScreen(event.propertyId)
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onPropertyClicked: (propertyId: String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        // TODO
    }
}
