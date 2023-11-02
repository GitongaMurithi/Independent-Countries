package com.example.countries.presentation.countries_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onActionButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "World countries",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        },
        actions = {
            IconButton(onClick = onActionButtonClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search country",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}