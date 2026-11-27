package com.mobile.core.data.repository

import com.mobile.core.data.model.ShopDetails

interface ShopRepository {
    fun getShopDetails(): ShopDetails
    fun saveShopDetails(shopName: String, logoUri: String?, businessType: String)
    fun isSetupComplete(): Boolean
}
