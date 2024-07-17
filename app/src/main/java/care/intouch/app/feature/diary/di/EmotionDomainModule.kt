package care.intouch.app.feature.diary.di

import care.intouch.app.feature.diary.domain.useCase.ClearAllInformationUC
import care.intouch.app.feature.diary.domain.useCase.ClearEmotionsUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionUC
import care.intouch.app.feature.diary.domain.useCase.GetSavedAnswers
import care.intouch.app.feature.diary.domain.useCase.SaveAnswersUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EmotionDomainModule {
    @Binds
    fun bindSaveEmotionDescUC(
        impl: SaveEmotionDescUC.Base,
    ): SaveEmotionDescUC

    @Binds
    fun bindSaveEmotionUC(
        impl: SaveEmotionUC.Base,
    ): SaveEmotionUC

    @Binds
    fun bindGetEmotionDescUC(
        impl: GetEmotionDescUC.Base,
    ): GetEmotionDescUC

    @Binds
    fun bindGetEmotionUC(
        impl: GetEmotionUC.Base,
    ): GetEmotionUC

    @Binds
    fun bindClearEmotionsUC(
        impl: ClearEmotionsUC.Base,
    ): ClearEmotionsUC

    @Binds
    fun bindClearAllInformationUC(
        impl: ClearAllInformationUC.Base,
    ): ClearAllInformationUC

    @Binds
    fun bindSaveAnswersUC(
        impl: SaveAnswersUC.Base,
    ): SaveAnswersUC

    @Binds
    fun bindGetSavedAnswers(
        impl: GetSavedAnswers.Base,
    ): GetSavedAnswers
}