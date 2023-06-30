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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.wellness.model.Day
import com.example.wellness.ui.theme.WellnessTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.example.wellness.data.Datasource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    DayCard(
//                        Day(
//                            R.string.day_3,
//                            R.string.description_3,
//                            R.drawable.unsplash_3,
//                        )
//                    )
                    Scaffold(
                        content = {
                            DayList(dayList = Datasource().loadDays())
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DayCard(day: Day, modifier: Modifier = Modifier) {
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
        Column() {
            Text(
                text = "Day #",
                modifier = Modifier.padding(
//                    TODO: adjust space between day and activity
                    horizontal = 16.dp,
                    vertical = 4.dp,
                ),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(day.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodySmall,
            )
            Image(
                painter = painterResource(day.imageResourceId),
                contentDescription = stringResource(day.descriptionResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(day.descriptionResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodySmall,
            )

        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
////                .background(color = Color.Red)
//                .padding(16.dp),
//        ){
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
////                    .background(color=Color.Green)
//            ) {
//                Text(
//                    text = "Day 1",
//                    style = MaterialTheme.typography.labelSmall,
//                    modifier = modifier
//                        .fillMaxWidth()
////                        .background(color = Color.Red)
//
//                        .padding(dimensionResource(R.dimen.padding_small)),
//                )
//                Text(
//                    text = "Do this",
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = modifier
//                        .fillMaxWidth()
////                        .background(color = Color.Yellow)
//
//                        .padding(dimensionResource(R.dimen.padding_small)),
//                )
//                Spacer(Modifier.width(16.dp))
//                Box(modifier=modifier) {
//                    Image(
//                        painter = image,
//                        contentDescription = imgDescription,
//                        contentScale = ContentScale.FillWidth,
//                        modifier = Modifier
//                            .clip(MaterialTheme.shapes.small),
//                    )
//                }
//                Spacer(Modifier.width(16.dp))
//                Text(
//                    text = "It is good for your health because",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = modifier
//                        .fillMaxWidth()
////                        .background(color = Color.Black)
//
//                        .padding(dimensionResource(R.dimen.padding_small)),
//                )
//            }
//        }

    }
}

@Composable
private fun DayList(dayList: List<Day>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(dayList){ day ->
            DayCard(day)
        }
    }
}

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingPreview() {
    WellnessTheme(darkTheme = false) {
//        DayCard(
//            Day(
//                R.string.day_1,
//                R.string.description_1,
//                R.drawable.unsplash_1,
//            )
//        )
        Scaffold(
            content = {
                DayList(
                    dayList = Datasource().loadDays(),
                    modifier = Modifier,
                )
            }
        )
    }
}
//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun GreetingDarkPreview() {
    WellnessTheme(darkTheme = true) {
//        DayCard(
//            Day(
//                R.string.day_2,
//                R.string.description_2,
//                R.drawable.unsplash_2,
//            )
//        )
        Scaffold(
            content = {
                DayList(dayList = Datasource().loadDays())
            }
        )
    }
}