package com.example.countries.presentation.countries_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countries.Navigator
import com.example.countries.presentation.countries_list.components.CountryComponent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountriesListScreen(
    viewModel: CountryViewModel = hiltViewModel(),
    navController: NavController,
//    onAction: (UserAction) -> Unit // ui action that shall be sent to the viewModel
) {
    val state = viewModel.state.value //passing the screen state from the viewModel
    var text = viewModel.query.value

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 7.dp, end = 7.dp, bottom = 7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(4.dp)
                    .weight(.2f),
                shape = MaterialTheme.shapes.medium.copy(
                    topStart = CornerSize(10.dp),
                    topEnd = CornerSize(10.dp),
                    bottomStart = CornerSize(10.dp),
                    bottomEnd = CornerSize(10.dp)

                )
            ) {

                IconButton(onClick = {
                    viewModel.controller = navController
                    viewModel.onAction(action = UserAction.PopBackStack)
//                    onAction(UserAction.PopBackStack)
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Backstack pressed",
                        tint = MaterialTheme.colorScheme.onBackground
                    )

                }
            }

            Spacer(modifier = Modifier.width(2.dp))

            TextField(
                value = text,
                onValueChange = viewModel::onQueryChange,
                placeholder = { Text(text = "Search country") },
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = TextFieldDefaults.outlinedShape
                    )
                    .fillMaxWidth()
                    .padding(2.dp)
                    .weight(.8f),
                trailingIcon = {
                    IconButton(onClick = {
                        keyboardController?.hide() // hides the keyboard after clicking the search icon
                        viewModel.getCountries(viewModel.query.value)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search country",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                )
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize(1f)

        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(state.country.sortedBy { it.name }) { country ->
                    CountryComponent(country = country, onCountryClick = {
                        navController.navigate(route = Navigator.Info.route + "/${country.name}")
                    })
                }
            }

            if (state.error.isNotBlank()) {
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
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}

