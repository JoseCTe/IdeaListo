package com.baeolian.idealisto.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.baeolian.idealisto.view.R
import com.baeolian.idealisto.view.data.PropertyViewData
import com.baeolian.idealisto.view.utils.CustomAsyncImage
import com.baeolian.idealisto.view.utils.advancedShadow

@Composable
fun PropertyCard(
    modifier: Modifier = Modifier,
    property: PropertyViewData,
    onClick: (String) -> Unit,
    onFavoriteClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .advancedShadow()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(property.propertyCode) }
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(2f)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            property.images?.takeIf { it.isNotEmpty() }?.let { imageList ->
                val displayImages = remember(imageList) {
                    if (imageList.size > MAX_IMAGES_TO_LOAD) {
                        imageList.take(MAX_IMAGES_TO_LOAD)
                    } else {
                        imageList
                    }
                }

                val pagerState = rememberPagerState(pageCount = { displayImages.size })

                HorizontalPager(state = pagerState) { page ->
                    CustomAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .aspectRatio(2f),
                        url = imageList[page]
                    )
                }

                Row(
                    Modifier
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                        .advancedShadow(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
            }

            FavoriteIcon(
                isFavorite = property.isFavorite,
                onFavoriteClicked = { onFavoriteClicked(property.propertyCode) }
            )

            /*Icon(
                painter = painterResource(R.drawable.ic_love),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable { onFavoriteClicked(property.propertyCode) }
                    .advancedShadow()
                    .padding(8.dp),
                contentDescription = "Favorite Icon",
                tint = if (property.isFavorite) {
                    Color.Red
                } else {
                    Color.White
                }
            )*/
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            property.title?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            property.municipality?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            PropertyCardHorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                property.size?.let {
                    Text(
                        text = stringResource(R.string.property_size, it),
                        style = MaterialTheme.typography.bodySmall,
                    )

                    PropertyCardVerticalDivider()
                }

                property.rooms?.let {
                    Text(
                        text = stringResource(R.string.property_rooms, it),
                        style = MaterialTheme.typography.bodySmall,
                    )

                    PropertyCardVerticalDivider()
                }

                property.price?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}

@Composable
private fun PropertyCardHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}

@Composable
private fun PropertyCardVerticalDivider() {
    VerticalDivider(
        modifier = Modifier.padding(horizontal = 12.dp),
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
private fun BoxScope.FavoriteIcon(
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
            .clip(CircleShape)
            .advancedShadow()
            .clickable { onFavoriteClicked() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_love),
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center)
                .padding(8.dp),
            contentDescription = "Favorite Icon Contour",
            tint = Color.White
        )

        Icon(
            painter = painterResource(R.drawable.ic_love),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
                .padding(8.dp),
            contentDescription = "Favorite Icon",
            tint = if (isFavorite) {
                Color.Red
            } else {
                Color.White
            }
        )
    }
}

private const val MAX_IMAGES_TO_LOAD = 8
