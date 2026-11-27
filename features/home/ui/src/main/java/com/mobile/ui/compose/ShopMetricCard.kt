package com.mobile.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShopMetricCard(){
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(
                title = "Today's Sales",
                value = "₹8,450",
                icon = Icons.Default.ShoppingBag,
                iconBg = Color(0xFFE8F5E9),
                iconTint = Color(0xFF2E7D32),
                modifier = Modifier.Companion.weight(1f)
            )
            MetricCard(
                title = "Bills",
                value = "32",
                icon = Icons.Default.Receipt,
                iconBg = Color(0xFFE3F2FD),
                iconTint = Color(0xFF1976D2),
                modifier = Modifier.Companion.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(
                title = "Items in Stock",
                value = "1,240",
                icon = Icons.Default.Inventory2,
                iconBg = Color(0xFFFFF3E0),
                iconTint = Color(0xFFF57C00),
                modifier = Modifier.Companion.weight(1f)
            )
            MetricCard(
                title = "Low Stock",
                value = "18",
                icon = Icons.Default.Warning,
                iconBg = Color(0xFFFFEBEE),
                iconTint = Color(0xFFD32F2F),
                modifier = Modifier.Companion.weight(1f)
            )
        }
    }
}