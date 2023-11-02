package com.example.countries.presentation.country_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countries.presentation.country_info.components.Borders
import com.example.countries.presentation.country_info.components.Details
import com.example.countries.presentation.country_info.components.SecondDetailsComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    viewModel: CountryInfoViewModel = hiltViewModel(),
    onBAckPressed: () -> Unit
) {

    val state = viewModel.state.value


    Scaffold(
        topBar = {
            state.country?.forEach { countryName ->
                val name = countryName.name?.common
                TopAppBar(
                    title = {
                        if (name != null) {
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onBAckPressed) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    ) { paddingValues ->
        state.country?.forEach { country ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(10.dp),
                contentPadding = paddingValues
            ) {

                item {
                    country.coatOfArms?.let { coa->
                        country.flags?.let { flag ->
                                Details(
                                    flag = flag.png,
                                    coa = coa,
                                    flagDesc = flag.alt
                                )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Extra Information",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    country.name?.let {
                        SecondDetailsComponent(text = "Official", value = it.official)
                    }
//                    val area = country.area.toString()
//                    val formattedArea = String.format("%,d",area.toFloat())
                    SecondDetailsComponent(
                        text = "Area",
                        value = "${country.area.toString()} sq km"
                    )

                    val string = country.population.toString()
                    val formattedString = String.format("%,d", string.toLong())
                    SecondDetailsComponent(text = "Population", value = formattedString)
                    country.region?.let { SecondDetailsComponent(text = "Region", value = it) }
                    country.subregion?.let {
                        SecondDetailsComponent(text = "Subregion", value = it)
                    }
                    country.car?.let {
                        SecondDetailsComponent(text = "Driving side", value = it.side)
                    }
                    country.fifa?.let { SecondDetailsComponent(text = "Fifa code", value = it) }
                    country.startOfWeek?.let {
                        SecondDetailsComponent(text = "Start of week", value = it)
                    }
                    country.cca2?.let { SecondDetailsComponent(text = "Cca2", value = it) }
                    country.cca3?.let { SecondDetailsComponent(text = "Cca3", value = it) }
                    country.ccn3?.let { SecondDetailsComponent(text = "Ccn3", value = it) }
                    country.cioc?.let { SecondDetailsComponent(text = "Cioc", value = it) }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Demonyms (English)",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    country.demonym?.eng?.let {
                        SecondDetailsComponent(
                            text = "Male",
                            value = it.m
                        )
                    }
                    country.demonym?.eng?.let {
                        SecondDetailsComponent(
                            text = "Female",
                            value = it.f
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Demonyms (French)",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    country.demonym?.fra?.let {
                        SecondDetailsComponent(
                            text = "Male",
                            value = it.m
                        )
                    }
                    country.demonym?.fra?.let {
                        SecondDetailsComponent(
                            text = "Female",
                            value = it.f
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Borders",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }
                country.borders?.let { borders ->
                    items(borders) { border ->
                        Borders(border = border, modifier = Modifier.padding(4.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }


    if (state.error.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }


}

@Preview
@Composable
fun PreviewInfo() {
    InfoScreen(onBAckPressed = {})
}