package fr.benchaabane.riyadhair.data.flights.paging;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.data.flights.api.FlightService;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class FlightPagingSource_Factory implements Factory<FlightPagingSource> {
  private final Provider<FlightService> flightServiceProvider;

  private final Provider<String> originProvider;

  private final Provider<String> destinationProvider;

  private FlightPagingSource_Factory(Provider<FlightService> flightServiceProvider,
      Provider<String> originProvider, Provider<String> destinationProvider) {
    this.flightServiceProvider = flightServiceProvider;
    this.originProvider = originProvider;
    this.destinationProvider = destinationProvider;
  }

  @Override
  public FlightPagingSource get() {
    return newInstance(flightServiceProvider.get(), originProvider.get(), destinationProvider.get());
  }

  public static FlightPagingSource_Factory create(Provider<FlightService> flightServiceProvider,
      Provider<String> originProvider, Provider<String> destinationProvider) {
    return new FlightPagingSource_Factory(flightServiceProvider, originProvider, destinationProvider);
  }

  public static FlightPagingSource newInstance(FlightService flightService, String origin,
      String destination) {
    return new FlightPagingSource(flightService, origin, destination);
  }
}
