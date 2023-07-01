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
                    WellnessApp()
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
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation)),
        modifier= modifier
//            .background(color = Color.Blue)
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.padding_small),
            )

    ) {
        Column() {
            Text(
                text = "Day #",
                modifier = Modifier.padding(
//                    TODO: adjust space between day and activity
                    horizontal = dimensionResource(R.dimen.padding_medium),
                    vertical = dimensionResource(R.dimen.padding_tiny),
                ),
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(day.stringResourceId),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.bodySmall,
            )
            Image(
                painter = painterResource(day.imageResourceId),
                contentDescription = stringResource(day.descriptionResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.img_height)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(day.descriptionResourceId),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.bodySmall,
            )

        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WellnessApp() {
    Scaffold(
        content = {
            DayList(
                dayList = Datasource().loadDays(),
                modifier = Modifier,
            )
        }
    )
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    WellnessTheme(darkTheme = false) {
        WellnessApp()
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingDarkPreview() {
    WellnessTheme(darkTheme = true) {
        WellnessApp()
    }
}
