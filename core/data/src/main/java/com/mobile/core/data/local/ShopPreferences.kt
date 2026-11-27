package com.mobile.core.data.local

import android.content.Context
import android.content.SharedPreferences

class ShopPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var shopName: String
        get() = prefs.getString(KEY_SHOP_NAME, "Pinkvity General Store") ?: "Pinkvity General Store"
        set(value) = prefs.edit().putString(KEY_SHOP_NAME, value).apply()

    var shopLogoUri: String?
        get() = prefs.getString(KEY_SHOP_LOGO_URI, null)
        set(value) = prefs.edit().putString(KEY_SHOP_LOGO_URI, value).apply()

    var businessType: String
        get() = prefs.getString(KEY_BUSINESS_TYPE, "Grocery") ?: "Grocery"
        set(value) = prefs.edit().putString(KEY_BUSINESS_TYPE, value).apply()

    var isSetupComplete: Boolean
        get() = prefs.getBoolean(KEY_IS_SETUP_COMPLETE, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_SETUP_COMPLETE, value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "billnest_shop_prefs"
        private const val KEY_SHOP_NAME = "key_shop_name"
        private const val KEY_SHOP_LOGO_URI = "key_shop_logo_uri"
        private const val KEY_BUSINESS_TYPE = "key_business_type"
        private const val KEY_IS_SETUP_COMPLETE = "key_is_setup_complete"
    }
}
