package com.example.countries.presentation.country_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Outlined() {

    var text by rememberSaveable {
        mutableStateOf("")

    }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)){
        TextField(
            value = text,
            onValueChange = { text = it},
            modifier = Modifier
                .background(
                    color = Color.Gray,
                    shape = TextFieldDefaults.outlinedShape
                )
                .fillMaxWidth()
                .padding(2.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewFun() {
    Outlined()
}