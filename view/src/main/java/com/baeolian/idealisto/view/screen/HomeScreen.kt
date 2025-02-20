package com.baeolian.idealisto.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baeolian.idealisto.view.component.PropertyCard
import com.baeolian.idealisto.view.viewmodel.HomeEvent
import com.baeolian.idealisto.view.viewmodel.HomeState
import com.baeolian.idealisto.view.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailsScreen: (propertyId: String) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        onPropertyClicked = viewModel::onPropertyClicked,
        onFavoriteClicked = viewModel::onFavoriteClicked,
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
    state: HomeState,
    onPropertyClicked: (propertyId: String) -> Unit,
    onFavoriteClicked: (propertyId: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 32.dp),
    ) {
        items(state.properties.size) { index ->
            val property = state.properties[index]

            PropertyCard(
                modifier = Modifier.padding(vertical = 16.dp),
                property = property,
                onClick = onPropertyClicked,
                onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}
