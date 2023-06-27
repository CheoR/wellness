package com.example.wellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellness.ui.theme.WellnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DayCard()
                }
            }
        }
    }
}

@Composable
fun DayCard(modifier: Modifier = Modifier) {
    // TODO: figure out why img size causes error when img size
    // for other prjoects are bigger than these images
    val image = painterResource(R.drawable.ic_launcher_foreground)
    val imgDescription = stringResource(R.string.img_description_11)
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier= modifier
//            .background(color = Color.Blue)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
                vertical = dimensionResource(id = R.dimen.padding_small),
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .background(color = Color.Red)
                .padding(16.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(color=Color.Green)
            ) {
                Text(
                    text = "Day 1",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier
                        .fillMaxWidth()
//                        .background(color = Color.Red)

                        .padding(dimensionResource(R.dimen.padding_small)),
                )
                Text(
                    text = "Do this",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = modifier
                        .fillMaxWidth()
//                        .background(color = Color.Yellow)

                        .padding(dimensionResource(R.dimen.padding_small)),
                )
                Spacer(Modifier.width(16.dp))
                Box(modifier=modifier) {
                    Image(
                        painter = image,
                        contentDescription = imgDescription,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small),
                    )
                }
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "It is good for your health because",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                        .fillMaxWidth()
//                        .background(color = Color.Black)

                        .padding(dimensionResource(R.dimen.padding_small)),
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WellnessTheme(darkTheme = false) {
        DayCard()
    }
}

@Preview
@Composable
fun GreetingDarkPreview() {
    WellnessTheme(darkTheme = true) {
        DayCard()
    }
}