package com.mobile.billnest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.mobile.billnest.data.ShopPreferences
import com.mobile.billnest.ui.screens.SplashScreen
import com.mobile.billnest.ui.theme.BillNestTheme
import com.mobile.ui.screen.HomeScreen
import com.mobile.ui.screen.OnboardingScreen
import com.mobile.ui.screen.ShopSetupScreen

enum class AppScreen {
    SPLASH,
    ONBOARDING,
    SETUP,
    HOME
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BillNestTheme {
                val context = LocalContext.current
                val prefs = remember { ShopPreferences(context) }

                var currentScreen by remember {
                    mutableStateOf(
                        if (prefs.isSetupComplete) AppScreen.HOME else AppScreen.SPLASH
                    )
                }

                var shopName by remember { mutableStateOf(prefs.shopName) }
                var logoUri by remember { mutableStateOf(prefs.shopLogoUri) }
                var businessType by remember { mutableStateOf(prefs.businessType) }

                // Once setup is complete, prevent back navigation to onboarding/setup
                if (currentScreen == AppScreen.HOME) {
                    BackHandler {
                        finish()
                    }
                }

                when (currentScreen) {
                    AppScreen.SPLASH -> {
                        SplashScreen(
                            onSplashFinished = {
                                if (prefs.isSetupComplete) {
                                    currentScreen = AppScreen.HOME
                                } else {
                                    currentScreen = AppScreen.ONBOARDING
                                }
                            }
                        )
                    }

                    AppScreen.ONBOARDING -> {
                        OnboardingScreen(
                            onOnboardingFinished = {
                                currentScreen = AppScreen.SETUP
                            }
                        )
                    }

                    AppScreen.SETUP -> {
                        ShopSetupScreen(
                            initialShopName = shopName,
                            initialLogoUri = logoUri,
                            initialBusinessType = businessType,
                            onSetupComplete = { newShopName, newLogoUri, newBusinessType ->
                                prefs.shopName = newShopName
                                prefs.shopLogoUri = newLogoUri
                                prefs.businessType = newBusinessType
                                prefs.isSetupComplete = true

                                shopName = newShopName
                                logoUri = newLogoUri
                                businessType = newBusinessType
                                currentScreen = AppScreen.HOME
                            }
                        )
                    }

                    AppScreen.HOME -> {
                        HomeScreen(
                            shopName = shopName,
                            logoUri = logoUri,
                            businessType = businessType,
                            onEditShopClick = {
                                currentScreen = AppScreen.SETUP
                            }
                        )
                    }
                }
            }
        }
    }
}
