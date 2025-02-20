package com.baeolian.idealisto.view.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import androidx.viewpager.widget.PagerAdapter
import coil.compose.AsyncImage
import coil.load

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

class ImagePagerAdapter(
    private val context: Context,
    private val images: List<String>
) : PagerAdapter() {

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        imageView.load(images[position])
        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
