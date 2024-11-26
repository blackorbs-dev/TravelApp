package blackorbs.dev.travelapp.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import blackorbs.dev.travelapp.R
import blackorbs.dev.travelapp.data.entities.Address
import blackorbs.dev.travelapp.data.entities.Location
import blackorbs.dev.travelapp.data.entities.Response
import blackorbs.dev.travelapp.data.entities.TravelStyle
import blackorbs.dev.travelapp.data.entities.Trip
import blackorbs.dev.travelapp.helpers.format
import blackorbs.dev.travelapp.ui.components.MainOutlinedButton
import blackorbs.dev.travelapp.ui.components.AddDetailsBox
import blackorbs.dev.travelapp.ui.components.DetailsViewBox
import blackorbs.dev.travelapp.ui.components.RetryBox
import blackorbs.dev.travelapp.ui.components.inlineIcon
import blackorbs.dev.travelapp.ui.theme.LocalCustomColors
import blackorbs.dev.travelapp.ui.theme.TravelAppTheme
import java.util.Calendar

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: DetailsViewModel = hiltViewModel()
){
    val trip = viewModel.trip.collectAsState(initial = null)

    LaunchedEffect(key1 = id) {
        viewModel.getTrip(id)
    }

    when(trip.value?.status){
        Response.Status.ERROR ->
            RetryBox {
                viewModel.getTrip(id)
            }
        Response.Status.SUCCESS ->
            DetailsScreenLayout(
                trip = trip.value!!.data!!
            )
        Response.Status.LOADING ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator()
            }
        null -> {}
    }
}

@Composable
fun DetailsScreenLayout(trip: Trip){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        Image(
            painter = painterResource(
                R.drawable.trip_image_body_palm
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
        ) {
            val dateBack = LocalCustomColors.current.white60
            Text(
                buildAnnotatedString {
                    appendInlineContent("${
                        R.drawable.calender_black
                    }")
                    append(trip.startDate!!.format())
                    appendInlineContent("${
                        R.drawable.arrow_right
                    }")
                    append(trip.endDate!!.format())
                },
                inlineContent = mapOf(
                    inlineIcon(R.drawable.calender_black),
                    inlineIcon(
                        R.drawable.arrow_right,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                ),
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = dateBack,
                            cornerRadius = CornerRadius(8f, 8f)
                        )
                    }
                    .padding(
                        vertical = 6.dp,
                        horizontal = 4.dp
                    ),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = LocalCustomColors.current.blueDark80
                )
            )
            Text(
                trip.title!!,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            val subTitleColor = LocalCustomColors.current.blueDark10
            Text(
                "${trip.location?.display_name} | ${stringResource(trip.travelStyle!!.textResId)}",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = subTitleColor,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(
                    top = 6.dp
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp, bottom = 22.dp
                    )
            ) {
                MainOutlinedButton(
                    onClick = { /*TODO*/ },
                    iconResId = R.drawable.handshake,
                    textResId = R.string.trip_collaboration,
                    modifier = Modifier.weight(2f)
                )
                MainOutlinedButton(
                    onClick = { /*TODO*/ },
                    iconResId = R.drawable.share,
                    textResId = R.string.share_trip,
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 12.dp)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(36.dp)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            R.drawable.dots_three
                        ),
                        contentDescription = null
                    )
                }
            }
            AddDetailsBox(
                titleResId = R.string.activities,
                detailsResId = R.string.activity_details,
                buttonTextResId = R.string.add_activity,
                backColor = LocalCustomColors.current.black,
                textColor = LocalCustomColors.current.white,
                buttonTextColor = LocalCustomColors.current.white,
                buttonBackColor = LocalCustomColors.current.blue60
            ){}
            AddDetailsBox(
                titleResId = R.string.hotels,
                detailsResId = R.string.hotels_details,
                buttonTextResId = R.string.add_hotels,
                backColor = LocalCustomColors.current.blueLight40,
                textColor = LocalCustomColors.current.black,
                buttonTextColor = LocalCustomColors.current.white,
                buttonBackColor = LocalCustomColors.current.blue60
            ){}
            AddDetailsBox(
                titleResId = R.string.flights,
                detailsResId = R.string.activity_details,
                buttonTextResId = R.string.add_flights,
                backColor = LocalCustomColors.current.blue60,
                textColor = LocalCustomColors.current.white,
                buttonTextColor = LocalCustomColors.current.blue60,
                buttonBackColor = LocalCustomColors.current.white
            ){}
            Text(
                stringResource(R.string.trip_itineraries),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    top = 26.dp, bottom = 6.dp
                )
            )
            Text(
                stringResource(R.string.trip_itineraries_details),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            DetailsViewBox(
                titleResId = R.string.flights,
                iconResId = R.drawable.airplane_in_flight,
                imageResId = R.drawable.flight,
                buttonTextResId = R.string.add_flights,
                titleColor = LocalCustomColors.current.black,
                backColor = LocalCustomColors.current.white60
            ){}
            DetailsViewBox(
                titleResId = R.string.hotels,
                iconResId = R.drawable.buildings,
                imageResId = R.drawable.hotel,
                buttonTextResId = R.string.add_hotels,
                titleColor = LocalCustomColors.current.white,
                backColor = LocalCustomColors.current.blueDark40
            ){}
            DetailsViewBox(
                titleResId = R.string.activities,
                iconResId = R.drawable.road_horizon,
                imageResId = R.drawable.parachute,
                buttonTextResId = R.string.add_activity,
                titleColor = LocalCustomColors.current.white,
                backColor = LocalCustomColors.current.blue60
            ){}
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun DetailsScreenPreview(){
    TravelAppTheme {
        Scaffold {
            DetailsScreenLayout(trip = Trip(
                id = 1,
                title = "Bahamas Family Trip",
                location = Location(
                    place_id = 1L,
                    name = "Bahama",
                    display_name = "New York, United, States of America",
                    address = Address(
                        state = null,
                        country = "USA",
                        country_code = "NG"
                    )
                ),
                startDate = Calendar.getInstance().time,
                endDate = Calendar.getInstance().time
            ).apply { travelStyle = TravelStyle.Solo })
        }
    }
}