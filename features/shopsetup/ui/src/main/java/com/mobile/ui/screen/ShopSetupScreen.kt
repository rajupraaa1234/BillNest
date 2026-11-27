package com.mobile.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.sdk.compose.components.ShopLogoImage

data class BusinessTypeItem(
    val name: String,
    val icon: ImageVector
)

@Composable
fun ShopSetupScreen(
    initialShopName: String = "",
    initialLogoUri: String? = null,
    initialBusinessType: String = "Grocery",
    onSetupComplete: (shopName: String, logoUri: String?, businessType: String) -> Unit
) {
    var shopName by remember { mutableStateOf(initialShopName) }
    var selectedLogoUri by remember { mutableStateOf(initialLogoUri) }
    var selectedBusinessType by remember { mutableStateOf(initialBusinessType) }
    var isError by remember { mutableStateOf(false) }

    val businessTypes = remember {
        listOf(
            BusinessTypeItem("Grocery", Icons.Default.ShoppingBag),
            BusinessTypeItem("General", Icons.Default.Store),
            BusinessTypeItem("Medical", Icons.Default.LocalHospital),
            BusinessTypeItem("Clothing", Icons.Default.Checkroom),
            BusinessTypeItem("Electronics", Icons.Default.Devices),
            BusinessTypeItem("Other", Icons.Default.MoreHoriz)
        )
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedLogoUri = it.toString()
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF673AB7)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Storefront,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Set Up Your Shop",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "A few details to personalize your BillNest experience.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Logo Box
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.clickable {
                    imagePickerLauncher.launch("image/*")
                }
            ) {
                ShopLogoImage(
                    logoUri = selectedLogoUri,
                    size = 90.dp
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF673AB7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Choose Logo",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            TextButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = if (selectedLogoUri == null) "Upload Shop Logo" else "Change Logo",
                    fontSize = 13.sp,
                    color = Color(0xFF673AB7)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Shop Name Field
            OutlinedTextField(
                value = shopName,
                onValueChange = {
                    shopName = it
                    if (it.isNotBlank()) isError = false
                },
                label = { Text("Shop / Mart Name *") },
                placeholder = { Text("Pinkvity General Store") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Storefront,
                        contentDescription = null
                    )
                },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = "Shop name is required",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Business Type Section
            Text(
                text = "Business Type",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Grid of Business Types (2 columns x 3 rows)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                for (i in businessTypes.indices step 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val item1 = businessTypes[i]
                        val isSelected1 = item1.name == selectedBusinessType

                        BusinessTypeCard(
                            item = item1,
                            isSelected = isSelected1,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedBusinessType = item1.name }
                        )

                        if (i + 1 < businessTypes.size) {
                            val item2 = businessTypes[i + 1]
                            val isSelected2 = item2.name == selectedBusinessType

                            BusinessTypeCard(
                                item = item2,
                                isSelected = isSelected2,
                                modifier = Modifier.weight(1f),
                                onClick = { selectedBusinessType = item2.name }
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Button
            Button(
                onClick = {
                    if (shopName.isBlank()) {
                        isError = true
                    } else {
                        onSetupComplete(shopName.trim(), selectedLogoUri, selectedBusinessType)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
            ) {
                Text(
                    text = "Continue  →",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BusinessTypeCard(
    item: BusinessTypeItem,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF673AB7) else Color.LightGray.copy(alpha = 0.4f)
    val backgroundColor = if (isSelected) Color(0xFF673AB7) else Color(0xFFF8F9FE)
    val contentColor = if (isSelected) Color.White else Color.DarkGray

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.name,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color(0xFF673AB7),
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.name,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = contentColor
            )
        }
    }
}
