package com.example.countries.presentation.country_info.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondDetailsComponent(text : String, value : String) {
    Column (modifier = Modifier
        .fillMaxWidth()
    ){

        Row (modifier = Modifier.fillMaxWidth()){
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    .weight(1f)
            ) {
                Text(
                    text = text,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                )
            }

            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    .weight(1f)
            ) {
                Text(
                    text = value,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    SecondDetailsComponent(text = "Population", value = "20000000")
}