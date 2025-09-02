package fr.benchaabane.riyadhair.presentation.account;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase;
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
public final class AccountViewModel_Factory implements Factory<AccountViewModel> {
  private final Provider<GetAccountUseCase> getAccountUseCaseProvider;

  private AccountViewModel_Factory(Provider<GetAccountUseCase> getAccountUseCaseProvider) {
    this.getAccountUseCaseProvider = getAccountUseCaseProvider;
  }

  @Override
  public AccountViewModel get() {
    return newInstance(getAccountUseCaseProvider.get());
  }

  public static AccountViewModel_Factory create(
      Provider<GetAccountUseCase> getAccountUseCaseProvider) {
    return new AccountViewModel_Factory(getAccountUseCaseProvider);
  }

  public static AccountViewModel newInstance(GetAccountUseCase getAccountUseCase) {
    return new AccountViewModel(getAccountUseCase);
  }
}
