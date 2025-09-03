package fr.benchaabane.riyadhair.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.benchaabane.riyadhair.core.dispatcher.BackgroundDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatchersModule {

    @BackgroundDispatcher
    @Provides
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO
}