package com.example.countries.presentation.country_info.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun Details(text: String, flag: String , coa : String , flagDesc : String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//            Text(
//                text = "Google maps",
//                modifier = Modifier.weight(1f),
//                style = TextStyle(
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//            )
//
//            Text(
//                text = "Street maps",
//                modifier = Modifier.weight(1f),
//                style = TextStyle(
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//            )
//        }
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Row(modifier = Modifier.fillMaxWidth()) {
//            Box(
//                modifier = Modifier
//                    .height(200.dp)
//                    .weight(1f)
//                    .border(
//                        width = 2.dp,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//            ) {
//
//            }
//            Spacer(modifier = Modifier.width(4.dp))
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(200.dp)
//                    .border(
//                        width = 2.dp,
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//            ) {
//
//            }
//        }
//
//        Spacer(modifier = Modifier.height(14.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Flag",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = "Coat of arms",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xff9e9e9d))
                    .height(100.dp)
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
            ) {
                val painter = rememberAsyncImagePainter(model = flag)

                val painterState = painter.state

                Image(painter = painter, contentDescription = "Flag image" , modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 0.dp))

                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .background(color = Color(0xff9e9e9d))
                    .weight(1f)
                    .height(100.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
            ) {
                val painter = rememberAsyncImagePainter(model = coa)

                val painterState = painter.state

                Image(painter = painter, contentDescription = "Coat of arms" , modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp))

                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(
                text = flagDesc,
                modifier = Modifier
                    .padding(1.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ComponentPreview() {
    Details(text = "Kenya", flag = "" , coa = "" , flagDesc = "")
}
