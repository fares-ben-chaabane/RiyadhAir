package fr.benchaabane.riyadhair.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.presentation.search.components.FlightCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onProceedToCheckout: (String?, String?) -> Unit,
    departure: String,
    arrival: String,
) {
    // Mock data for demonstration
    val flights = viewModel.state.collectAsStateWithLifecycle()
    var isLoading by remember { mutableStateOf(false) }

    var selectedOutboundFlight by remember { mutableStateOf<FlightUiModel?>(null) }
    var selectedReturnFlight by remember { mutableStateOf<FlightUiModel?>(null) }

    var currentStep by remember { mutableStateOf(SearchStep.OUTBOUND) }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.search(from = departure, to = arrival)
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Progress indicator for round trip
        SearchProgressIndicator(
            currentStep = currentStep,
            selectedOutbound = selectedOutboundFlight,
            selectedReturn = selectedReturnFlight,
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )

        // Flight list
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            itemsIndexed(if (currentStep == SearchStep.OUTBOUND) flights.value.flights else flights.value.returnFlights) { index, flight ->
                val isSelected = when (currentStep) {
                    SearchStep.OUTBOUND, SearchStep.OUTBOUND_ONLY ->
                        selectedOutboundFlight?.flightNumber == flight.flightNumber

                    SearchStep.RETURN ->
                        selectedReturnFlight?.flightNumber == flight.flightNumber
                }

                FlightCard(
                    flight = flight,
                    onSelectFlight = { selectedFlight ->
                        when (currentStep) {
                            SearchStep.OUTBOUND -> {
                                selectedOutboundFlight = selectedFlight
                                viewModel.selectDepartureFlight(selectedFlight)
                            }

                            SearchStep.OUTBOUND_ONLY -> {
                                selectedOutboundFlight = selectedFlight
                                viewModel.selectDepartureFlight(selectedFlight)
                            }

                            SearchStep.RETURN -> {
                                selectedReturnFlight = selectedFlight
                                viewModel.selectReturnFlight(selectedFlight)
                            }
                        }
                    },
                    isSelected = isSelected,
                    animationDelay = index * 50
                )
            }

            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(RiyadhAirSpacing.lg),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        // Continue button
        AnimatedVisibility(
            visible = (currentStep == SearchStep.OUTBOUND_ONLY && selectedOutboundFlight != null) ||
                (currentStep == SearchStep.OUTBOUND && selectedOutboundFlight != null) ||
                (currentStep == SearchStep.RETURN && selectedReturnFlight != null)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RiyadhAirSpacing.lg)
                ) {
                    // Selected flight summary
                    when (currentStep) {
                        SearchStep.OUTBOUND, SearchStep.OUTBOUND_ONLY -> {
                            selectedOutboundFlight?.let { flight ->
                                SelectedFlightSummary(
                                    flight = flight,
                                    label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.selected_outbound_flight_label)
                                )
                            }
                        }

                        SearchStep.RETURN -> {
                            selectedReturnFlight?.let { flight ->
                                SelectedFlightSummary(
                                    flight = flight,
                                    label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.selected_return_flight_label)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(RiyadhAirSpacing.md))

                    // Action button
                    Button(
                        onClick = {
                            when (currentStep) {
                                SearchStep.OUTBOUND_ONLY -> {
                                    //onProceedToCheckout()
                                }

                                SearchStep.OUTBOUND -> {
                                    currentStep = SearchStep.RETURN
                                }

                                SearchStep.RETURN -> {
                                    onProceedToCheckout(
                                        flights.value.selectedDepartureFlight?.flightNumber,
                                        flights.value.selectedReturnFlight?.flightNumber
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RiyadhAirShapes.large
                    ) {
                        Text(
                            text = when (currentStep) {
                                SearchStep.OUTBOUND_ONLY -> stringResource(fr.benchaabane.riyadhair.presentation.R.string.continue_to_reservation)
                                SearchStep.OUTBOUND -> stringResource(fr.benchaabane.riyadhair.presentation.R.string.choose_return_flight_text)
                                SearchStep.RETURN -> stringResource(fr.benchaabane.riyadhair.presentation.R.string.finalize_selection_text)
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchProgressIndicator(
    currentStep: SearchStep,
    selectedOutbound: FlightUiModel?,
    selectedReturn: FlightUiModel?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RiyadhAirShapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(RiyadhAirSpacing.lg),
            horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            // Outbound step
            ProgressStep(
                number = "1",
                title = stringResource(fr.benchaabane.riyadhair.presentation.R.string.outbound_flight_title),
                isActive = currentStep == SearchStep.OUTBOUND,
                isCompleted = selectedOutbound != null,
                modifier = Modifier.weight(1f)
            )

            // Connection line
            Divider(
                modifier = Modifier
                    .width(40.dp)
                    .align(Alignment.CenterVertically),
                thickness = 2.dp,
                color = if (selectedOutbound != null)
                    RiyadhAirColors.SkyBlue
                else
                    MaterialTheme.colorScheme.outline
            )

            // Return step
            ProgressStep(
                number = "2",
                title = stringResource(fr.benchaabane.riyadhair.presentation.R.string.return_flight_title),
                isActive = currentStep == SearchStep.RETURN,
                isCompleted = selectedReturn != null,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ProgressStep(
    number: String,
    title: String,
    isActive: Boolean,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = when {
                        isCompleted -> RiyadhAirColors.SkyBlue
                        isActive -> RiyadhAirColors.RoyalPurple
                        else -> MaterialTheme.colorScheme.outline
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isCompleted) "✓" else number,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            color = if (isActive || isCompleted)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SelectedFlightSummary(
    flight: FlightUiModel,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RiyadhAirShapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = RiyadhAirColors.RoyalPurple.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.md)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${flight.departureAirportCode} → ${flight.arrivalAirportCode}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = flight.price,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = RiyadhAirColors.SkyBlue
                )
            }
        }
    }
}

private enum class SearchStep {
    OUTBOUND,
    OUTBOUND_ONLY,
    RETURN
}

@Preview(showBackground = true)
@Composable
private fun SearchResultsScreenPreview() {
    RiyadhAirTheme {
        SearchResultsScreen(
            onProceedToCheckout = { a, b -> },
            departure = "",
            arrival = ""
        )
    }
}
