package fr.benchaabane.riyadhair.data.account.repositories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import fr.benchaabane.riyadhair.data.account.api.AccountService;
import fr.benchaabane.riyadhair.data.account.dao.AccountDao;
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
public final class AccountRepositoryImpl_Factory implements Factory<AccountRepositoryImpl> {
  private final Provider<AccountDao> accountDaoProvider;

  private final Provider<AccountService> accountServiceProvider;

  private AccountRepositoryImpl_Factory(Provider<AccountDao> accountDaoProvider,
      Provider<AccountService> accountServiceProvider) {
    this.accountDaoProvider = accountDaoProvider;
    this.accountServiceProvider = accountServiceProvider;
  }

  @Override
  public AccountRepositoryImpl get() {
    return newInstance(accountDaoProvider.get(), accountServiceProvider.get());
  }

  public static AccountRepositoryImpl_Factory create(Provider<AccountDao> accountDaoProvider,
      Provider<AccountService> accountServiceProvider) {
    return new AccountRepositoryImpl_Factory(accountDaoProvider, accountServiceProvider);
  }

  public static AccountRepositoryImpl newInstance(AccountDao accountDao,
      AccountService accountService) {
    return new AccountRepositoryImpl(accountDao, accountService);
  }
}
