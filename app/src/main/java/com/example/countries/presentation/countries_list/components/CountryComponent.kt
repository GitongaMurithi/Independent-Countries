package com.example.countries.presentation.countries_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countries.domain.model.Country
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CountryComponent(
    country: Country,
    onCountryClick: (Country) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCountryClick(country) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background( color = MaterialTheme.colorScheme.primary )
                    .padding(8.dp)
            ) {
                Row {
                    Text(
                        text = "Country: ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.width(14.dp))


                    Text(
                        text = "${country.name} ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                Row {
                    Text(
                        text = "Capital City: ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    FlowRow(
                        mainAxisSpacing = 4.dp,
                        crossAxisSpacing = 4.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        country.capital.forEach { text ->
                            Text(
                                text = text,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

