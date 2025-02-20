package com.baeolian.idealisto.view.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baeolian.idealisto.view.R
import com.baeolian.idealisto.view.utils.DateFormat
import com.baeolian.idealisto.view.utils.TimeZoneFormat
import com.baeolian.idealisto.view.utils.advancedShadow
import com.baeolian.idealisto.view.utils.toFormattedString
import java.util.Date

@Composable
fun BoxScope.FavoriteIcon(
    dateOfFavorite: Date? = null,
    onFavoriteClicked: () -> Unit,
) {
    AnimatedVisibility(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .advancedShadow()
            .clickable { onFavoriteClicked() },
        visible = dateOfFavorite != null,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(MaterialTheme.colorScheme.surface)
                .clip(CircleShape)
        ) {
            dateOfFavorite?.toFormattedString(
                dateFormat = DateFormat.Explicit(getFavoriteDateTimeFormat()),
                timeZoneFormat = TimeZoneFormat.NONE
            )?.let {
                Text(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 14.dp,
                        end = 42.dp
                    ),
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
            .clip(CircleShape)
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

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = dateOfFavorite != null,
            enter = scaleIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ) + fadeIn(),
            exit = scaleOut(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)) + fadeOut()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_love),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
                    .padding(8.dp),
                contentDescription = "Favorite Icon",
                tint = Color.Red
            )
        }
    }
}

@Composable
private fun getFavoriteDateTimeFormat(): String {
    val at = stringResource(R.string.date_time_at)
    return stringResource(R.string.date_time_format, at)
}
