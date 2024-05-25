package care.intouch.app.account.di


import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonDataModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences("KEY", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun coroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

}