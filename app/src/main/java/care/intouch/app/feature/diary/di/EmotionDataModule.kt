package care.intouch.app.feature.diary.di

import care.intouch.app.feature.diary.data.EmotionsRepository
import care.intouch.app.feature.diary.data.EmotionsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EmotionDataModule {
    @Singleton
    @Binds
    fun bindEmotionRepository(
        impl: EmotionsRepositoryImpl,
    ): EmotionsRepository
}