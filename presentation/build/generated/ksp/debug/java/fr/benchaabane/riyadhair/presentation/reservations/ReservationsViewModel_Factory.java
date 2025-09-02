package fr.benchaabane.riyadhair.presentation.reservations;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.domain.reservations.usecases.ObserveReservationsUseCase;
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
public final class ReservationsViewModel_Factory implements Factory<ReservationsViewModel> {
  private final Provider<ObserveReservationsUseCase> observeReservationsProvider;

  private ReservationsViewModel_Factory(
      Provider<ObserveReservationsUseCase> observeReservationsProvider) {
    this.observeReservationsProvider = observeReservationsProvider;
  }

  @Override
  public ReservationsViewModel get() {
    return newInstance(observeReservationsProvider.get());
  }

  public static ReservationsViewModel_Factory create(
      Provider<ObserveReservationsUseCase> observeReservationsProvider) {
    return new ReservationsViewModel_Factory(observeReservationsProvider);
  }

  public static ReservationsViewModel newInstance(ObserveReservationsUseCase observeReservations) {
    return new ReservationsViewModel(observeReservations);
  }
}
