package com.baeolian.idealisto.view.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.baeolian.idealisto.view.R

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    rightContent: @Composable (() -> Unit)? = null,
    replicateIconTransparentlyForSymmetry: Boolean = false,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.padding(
                start = 4.dp,
                end = 16.dp
            ),
            onClick = onBackClicked
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        rightContent?.invoke()

        if (replicateIconTransparentlyForSymmetry) {
            IconButton(
                onClick = { /* Do nothing */ },
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 4.dp
                ),
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}
