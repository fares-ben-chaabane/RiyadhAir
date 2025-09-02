package fr.benchaabane.riyadhair.data.offers.repositories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.data.offers.api.OffersService;
import fr.benchaabane.riyadhair.data.offers.dao.OfferDao;
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
public final class OffersRepositoryImpl_Factory implements Factory<OffersRepositoryImpl> {
  private final Provider<OfferDao> offerDaoProvider;

  private final Provider<OffersService> offersServiceProvider;

  private OffersRepositoryImpl_Factory(Provider<OfferDao> offerDaoProvider,
      Provider<OffersService> offersServiceProvider) {
    this.offerDaoProvider = offerDaoProvider;
    this.offersServiceProvider = offersServiceProvider;
  }

  @Override
  public OffersRepositoryImpl get() {
    return newInstance(offerDaoProvider.get(), offersServiceProvider.get());
  }

  public static OffersRepositoryImpl_Factory create(Provider<OfferDao> offerDaoProvider,
      Provider<OffersService> offersServiceProvider) {
    return new OffersRepositoryImpl_Factory(offerDaoProvider, offersServiceProvider);
  }

  public static OffersRepositoryImpl newInstance(OfferDao offerDao, OffersService offersService) {
    return new OffersRepositoryImpl(offerDao, offersService);
  }
}
