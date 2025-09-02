package fr.benchaabane.riyadhair.presentation.home;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase;
import fr.benchaabane.riyadhair.domain.offers.usecases.GetBestOffersUseCase;
import fr.benchaabane.riyadhair.domain.partners.usecases.GetPartnersUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetAccountUseCase> getAccountUseCaseProvider;

  private final Provider<GetBestOffersUseCase> getBestOffersUseCaseProvider;

  private final Provider<GetPartnersUseCase> getPartnersUseCaseProvider;

  private HomeViewModel_Factory(Provider<GetAccountUseCase> getAccountUseCaseProvider,
      Provider<GetBestOffersUseCase> getBestOffersUseCaseProvider,
      Provider<GetPartnersUseCase> getPartnersUseCaseProvider) {
    this.getAccountUseCaseProvider = getAccountUseCaseProvider;
    this.getBestOffersUseCaseProvider = getBestOffersUseCaseProvider;
    this.getPartnersUseCaseProvider = getPartnersUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getAccountUseCaseProvider.get(), getBestOffersUseCaseProvider.get(), getPartnersUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<GetAccountUseCase> getAccountUseCaseProvider,
      Provider<GetBestOffersUseCase> getBestOffersUseCaseProvider,
      Provider<GetPartnersUseCase> getPartnersUseCaseProvider) {
    return new HomeViewModel_Factory(getAccountUseCaseProvider, getBestOffersUseCaseProvider, getPartnersUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetAccountUseCase getAccountUseCase,
      GetBestOffersUseCase getBestOffersUseCase, GetPartnersUseCase getPartnersUseCase) {
    return new HomeViewModel(getAccountUseCase, getBestOffersUseCase, getPartnersUseCase);
  }
}
