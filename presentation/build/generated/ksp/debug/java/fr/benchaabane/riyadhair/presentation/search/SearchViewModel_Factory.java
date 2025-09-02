package fr.benchaabane.riyadhair.presentation.search;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.domain.flights.usecases.GetFlightDetailsUseCase;
import fr.benchaabane.riyadhair.domain.flights.usecases.SearchFlightsUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchFlightsUseCase> searchFlightsUseCaseProvider;

  private final Provider<GetFlightDetailsUseCase> getFlightDetailsUseCaseProvider;

  private SearchViewModel_Factory(Provider<SearchFlightsUseCase> searchFlightsUseCaseProvider,
      Provider<GetFlightDetailsUseCase> getFlightDetailsUseCaseProvider) {
    this.searchFlightsUseCaseProvider = searchFlightsUseCaseProvider;
    this.getFlightDetailsUseCaseProvider = getFlightDetailsUseCaseProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchFlightsUseCaseProvider.get(), getFlightDetailsUseCaseProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<SearchFlightsUseCase> searchFlightsUseCaseProvider,
      Provider<GetFlightDetailsUseCase> getFlightDetailsUseCaseProvider) {
    return new SearchViewModel_Factory(searchFlightsUseCaseProvider, getFlightDetailsUseCaseProvider);
  }

  public static SearchViewModel newInstance(SearchFlightsUseCase searchFlightsUseCase,
      GetFlightDetailsUseCase getFlightDetailsUseCase) {
    return new SearchViewModel(searchFlightsUseCase, getFlightDetailsUseCase);
  }
}
