package fr.benchaabane.riyadhair.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons

import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.models.PassengerCount
import fr.benchaabane.riyadhair.presentation.home.HomeViewModel
import fr.benchaabane.riyadhair.presentation.search.components.DateSelectionCard
import fr.benchaabane.riyadhair.presentation.search.components.DepartureDestinationCard
import fr.benchaabane.riyadhair.presentation.search.components.DestinationBottomSheet
import fr.benchaabane.riyadhair.presentation.search.components.PassengerSelectorBottomSheet
import fr.benchaabane.riyadhair.presentation.search.components.PassengersCabinCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onShowResult: (String, String) -> Unit
) {
    var departureAirportCode by remember { mutableStateOf("RUH") }
    var departureAirportCity by remember { mutableStateOf("Riyadh") }
    var destinationAirportCode by remember { mutableStateOf("JED") }
    var destinationAirportCity by remember { mutableStateOf("Jeddah") }

    var passengerCount by remember { mutableStateOf(PassengerCount()) }
    var cabinClass by remember { mutableStateOf(CabinClass.ECONOMY) }

    var departureDate by remember { mutableStateOf<LocalDate?>(null) }
    var returnDate by remember { mutableStateOf<LocalDate?>(null) }

    var showDestinationBottomSheet by remember { mutableStateOf(false) }
    var showPassengerSelector by remember { mutableStateOf(false) }
    var isSelectingDeparture by remember { mutableStateOf(true) }
    var isContentVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        delay(100)
        isContentVisible = true
        viewModel.event.collectLatest {
            when (it) {
                is SearchEvent.RedirectToResult -> onShowResult(departureAirportCode, destinationAirportCode)
            }
        }
    }

    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = isContentVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 4 }
        ) + fadeIn()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            // Departure/Destination Card
            DepartureDestinationCard(
                departureAirportCode = departureAirportCode,
                departureAirportCity = departureAirportCity,
                destinationAirportCity = destinationAirportCity,
                destinationAirportCode = destinationAirportCode,
                onDepartureClick = {
                    isSelectingDeparture = true
                    showDestinationBottomSheet = true
                },
                onDestinationClick = {
                    isSelectingDeparture = false
                    showDestinationBottomSheet = true
                },
                onSwapClick = {
                    val tempCode = departureAirportCode
                    val tempCity = departureAirportCity
                    departureAirportCode = destinationAirportCode
                    departureAirportCity = destinationAirportCity
                    destinationAirportCode = tempCode
                    destinationAirportCity = tempCity
                }
            )

            // Passengers and Cabin Card
            PassengersCabinCard(
                passengerCount = passengerCount,
                cabinClass = cabinClass,
                onClick = { showPassengerSelector = true }
            )

            // Date Selection Card
            DateSelectionCard(
                departureDate = departureDate,
                returnDate = returnDate,
                onDateRangeSelected = { departure, returnD ->
                    departureDate = departure
                    returnDate = returnD
                }
            )

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))

            // Search Button
            Button(
                onClick = {
                    if (departureDate != null) {
                        onShowResult(departureAirportCode, destinationAirportCode)
                    }
                },
                enabled = departureDate != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RiyadhAirShapes.large
            ) {
                Icon(
                    imageVector = RiyadhAirIcons.Search,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(modifier = Modifier.width(RiyadhAirSpacing.sm))
                                 Text(
                     text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.search_flights_text),
                     style = MaterialTheme.typography.titleMedium,
                     fontWeight = FontWeight.Bold
                 )
            }
        }
    }

    // Destination Bottom Sheet
    if (showDestinationBottomSheet) {
        DestinationBottomSheet(
            isSelectingDeparture = isSelectingDeparture,
            onDismiss = { showDestinationBottomSheet = false },
            onAirportSelected = { airportCode, airportCity ->
                if (isSelectingDeparture) {
                    departureAirportCode = airportCode
                    departureAirportCity = airportCity
                } else {
                    destinationAirportCode = airportCode
                    destinationAirportCity = airportCity
                }
                showDestinationBottomSheet = false
            }
        )
    }

    // Passenger Selector Bottom Sheet
    if (showPassengerSelector) {
        PassengerSelectorBottomSheet(
            passengerCount = passengerCount,
            cabinClass = cabinClass,
            onDismiss = { showPassengerSelector = false },
            onConfirm = { passengers, cabin ->
                passengerCount = passengers
                cabinClass = cabin
                showPassengerSelector = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    RiyadhAirTheme {
        SearchScreen(onShowResult = { dep, arr -> })
    }
}
