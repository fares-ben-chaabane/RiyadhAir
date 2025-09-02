package fr.benchaabane.riyadhair.data.reservations.repositories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.data.reservations.api.ReservationService;
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationDao;
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
public final class ReservationRepositoryImpl_Factory implements Factory<ReservationRepositoryImpl> {
  private final Provider<ReservationDao> reservationDaoProvider;

  private final Provider<ReservationService> reservationServiceProvider;

  private ReservationRepositoryImpl_Factory(Provider<ReservationDao> reservationDaoProvider,
      Provider<ReservationService> reservationServiceProvider) {
    this.reservationDaoProvider = reservationDaoProvider;
    this.reservationServiceProvider = reservationServiceProvider;
  }

  @Override
  public ReservationRepositoryImpl get() {
    return newInstance(reservationDaoProvider.get(), reservationServiceProvider.get());
  }

  public static ReservationRepositoryImpl_Factory create(
      Provider<ReservationDao> reservationDaoProvider,
      Provider<ReservationService> reservationServiceProvider) {
    return new ReservationRepositoryImpl_Factory(reservationDaoProvider, reservationServiceProvider);
  }

  public static ReservationRepositoryImpl newInstance(ReservationDao reservationDao,
      ReservationService reservationService) {
    return new ReservationRepositoryImpl(reservationDao, reservationService);
  }
}
