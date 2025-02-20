package com.baeolian.idealisto.view.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    url: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter? = null,
    shouldShowLoadingIndicator: Boolean = true,
) {
    var isLoading by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = url,
            contentDescription = "Asynchronous Image Loader",
            contentScale = contentScale,
            placeholder = placeholder,
            error = placeholder,
            filterQuality = FilterQuality.None,
            onLoading = { isLoading = true },
            onError = { isLoading = false },
            onSuccess = { isLoading = false }
        )

        AnimatedVisibility(
            visible = isLoading && shouldShowLoadingIndicator,
            modifier = Modifier.align(Alignment.Center)
        ) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        }
    }
}
