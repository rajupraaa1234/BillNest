package com.mobile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobile.sdk.compose.components.CustomButton
import com.mobile.ui.compose.NavigationBar
import com.mobile.ui.compose.QuickActionGrid
import com.mobile.ui.compose.ShopMetricCard
import com.mobile.ui.compose.StatusBanner
import com.mobile.ui.compose.TopActionBar
import com.mobile.ui.compose.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    shopName: String,
    logoUri: String?,
    businessType: String,
    onEditShopClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopAppBar() },
                actions = { TopActionBar(onEditShopClick, logoUri, shopName) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = { NavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FE))
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            StatusBanner(onEditShopClick, shopName)
            ShopMetricCard()
            CustomButton({}, "New Sale")
            QuickActionGrid({})
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}




