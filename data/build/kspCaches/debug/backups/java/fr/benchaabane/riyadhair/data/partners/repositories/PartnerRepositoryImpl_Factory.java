package fr.benchaabane.riyadhair.data.partners.repositories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.data.partners.api.PartnerService;
import fr.benchaabane.riyadhair.data.partners.dao.PartnerDao;
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
public final class PartnerRepositoryImpl_Factory implements Factory<PartnerRepositoryImpl> {
  private final Provider<PartnerDao> partnerDaoProvider;

  private final Provider<PartnerService> partnerServiceProvider;

  private PartnerRepositoryImpl_Factory(Provider<PartnerDao> partnerDaoProvider,
      Provider<PartnerService> partnerServiceProvider) {
    this.partnerDaoProvider = partnerDaoProvider;
    this.partnerServiceProvider = partnerServiceProvider;
  }

  @Override
  public PartnerRepositoryImpl get() {
    return newInstance(partnerDaoProvider.get(), partnerServiceProvider.get());
  }

  public static PartnerRepositoryImpl_Factory create(Provider<PartnerDao> partnerDaoProvider,
      Provider<PartnerService> partnerServiceProvider) {
    return new PartnerRepositoryImpl_Factory(partnerDaoProvider, partnerServiceProvider);
  }

  public static PartnerRepositoryImpl newInstance(PartnerDao partnerDao,
      PartnerService partnerService) {
    return new PartnerRepositoryImpl(partnerDao, partnerService);
  }
}
