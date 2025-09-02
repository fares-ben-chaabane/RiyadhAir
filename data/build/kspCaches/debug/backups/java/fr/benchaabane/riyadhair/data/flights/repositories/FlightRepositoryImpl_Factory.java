package fr.benchaabane.riyadhair.data.flights.repositories;

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
public final class FlightRepositoryImpl_Factory implements Factory<FlightRepositoryImpl> {
  private final Provider<FlightService> flightServiceProvider;

  private FlightRepositoryImpl_Factory(Provider<FlightService> flightServiceProvider) {
    this.flightServiceProvider = flightServiceProvider;
  }

  @Override
  public FlightRepositoryImpl get() {
    return newInstance(flightServiceProvider.get());
  }

  public static FlightRepositoryImpl_Factory create(Provider<FlightService> flightServiceProvider) {
    return new FlightRepositoryImpl_Factory(flightServiceProvider);
  }

  public static FlightRepositoryImpl newInstance(FlightService flightService) {
    return new FlightRepositoryImpl(flightService);
  }
}
