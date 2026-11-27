package com.mobile.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.sdk.compose.components.ShopLogoImage
import kotlin.text.ifEmpty

@Composable
internal fun TopActionBar(
    onEditShopClick: () -> Unit,
    logoUri: String?,
    shopName: String
) {
    IconButton(onClick = { /* Notifications */ }) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.Gray
        )
    }
    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .clickable(onClick = onEditShopClick)
    ) {
        if (logoUri != null) {
            ShopLogoImage(logoUri = logoUri, size = 34.dp)
        } else {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEDE7F6)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = shopName.take(2).uppercase().ifEmpty { "PG" },
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0xFF673AB7)
                )
            }
        }
    }
}