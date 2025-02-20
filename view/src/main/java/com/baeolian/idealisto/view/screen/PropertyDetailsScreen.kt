package com.baeolian.idealisto.view.screen

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.viewpager.widget.ViewPager
import com.baeolian.idealisto.view.R
import com.baeolian.idealisto.view.component.FullScreenLoader
import com.baeolian.idealisto.view.utils.ImagePagerAdapter
import com.baeolian.idealisto.view.viewmodel.PropertyDetailsEvent
import com.baeolian.idealisto.view.viewmodel.PropertyDetailsState
import com.baeolian.idealisto.view.viewmodel.PropertyDetailsViewModel

@Composable
fun PropertyDetailsScreen(
    viewModel: PropertyDetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    PropertyDetailsContent(
        onBackClicked = viewModel::onBackClicked,
        state = state
    )

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when (event) {
                is PropertyDetailsEvent.NavigateBack -> navigateBack()
            }
        }
    }

    FullScreenLoader(
        text = R.string.loading_details,
        visible = state.isLoading,
    )
}

@SuppressLint("InflateParams")
@Composable
private fun PropertyDetailsContent(
    modifier: Modifier = Modifier,
    state: PropertyDetailsState,
    onBackClicked: () -> Unit,
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.property_details_screen, null)
        },
        update = { view ->
            val context = view.context
            val backButton = view.findViewById<View>(R.id.backButton)
            val imagesCarousel = view.findViewById<ViewPager>(R.id.propertyImagesCarousel)
            val floorTextView = view.findViewById<TextView>(R.id.propertyFloor)
            val areaTextView = view.findViewById<TextView>(R.id.propertyArea)
            val roomsTextView = view.findViewById<TextView>(R.id.propertyRooms)
            val energyTextView = view.findViewById<TextView>(R.id.propertyEnergy)
            val priceTextView = view.findViewById<TextView>(R.id.propertyPrice)
            val bathsTextView = view.findViewById<TextView>(R.id.propertyBaths)
            val descriptionTextView = view.findViewById<TextView>(R.id.propertyDescription)
            val toggleButton = view.findViewById<TextView>(R.id.toggleDescription)

            val adapter = ImagePagerAdapter(
                context = context,
                images = state.property?.images ?: emptyList()
            )

            backButton.setOnClickListener { onBackClicked() }

            imagesCarousel.adapter = adapter
            floorTextView.text = state.property?.floor
            areaTextView.text = context.getString(R.string.property_size, state.property?.area)
            roomsTextView.text = state.property?.rooms
            energyTextView.text = state.property?.energy
            priceTextView.text = state.property?.price
            bathsTextView.text = state.property?.baths
            descriptionTextView.text = state.property?.description

            descriptionTextView.post {
                if (descriptionTextView.lineCount < MAX_DESCRIPTION_LINES) {
                    toggleButton.visibility = View.GONE
                } else {
                    toggleButton.visibility = View.VISIBLE
                }
            }

            var isExpanded = false

            toggleButton.setOnClickListener {
                if (isExpanded) {
                    descriptionTextView.maxLines = MAX_DESCRIPTION_LINES
                    descriptionTextView.ellipsize = TextUtils.TruncateAt.END
                    toggleButton.text = context.getString(R.string.property_details_read_more)
                } else {
                    descriptionTextView.maxLines = Int.MAX_VALUE
                    descriptionTextView.ellipsize = null
                    toggleButton.text = context.getString(R.string.property_details_show_less)
                }

                isExpanded = !isExpanded
            }
        }
    )
}

private const val MAX_DESCRIPTION_LINES = 4
