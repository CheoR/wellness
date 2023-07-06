package com.example.wellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wellness.model.Day
import com.example.wellness.ui.theme.WellnessTheme
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
fun DayCard(index: Int, day: Day, modifier: Modifier = Modifier) {
    // TODO: scrolling slow cause imgs
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation)),
        modifier= modifier
//            .background(color = Color.Blue)
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.padding_small),
            )
            .clickable{
                expanded = !expanded
            }

    ) {
        Column() {
            Text(
                text = "Day $index",
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.img_height)),
            )
            if(expanded) {
                Text(
                    text = stringResource(day.descriptionResourceId),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DayList(dayList: List<Day>, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            WellnessTopAppBar()
        }
    ) {it ->
        LazyColumn(
            contentPadding = it,
        ) {
            itemsIndexed(dayList){ index, day ->
                DayCard(index + 1, day)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.topbar_img_height))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun WellnessApp() {
    DayList(
        dayList = Datasource().loadDays(),
        modifier = Modifier,
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
