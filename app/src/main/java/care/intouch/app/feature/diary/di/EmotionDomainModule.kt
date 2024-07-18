package care.intouch.app.feature.diary.di

import care.intouch.app.feature.diary.domain.useCase.ClearAllInformationUC
import care.intouch.app.feature.diary.domain.useCase.ClearEmotionsUC
import care.intouch.app.feature.diary.domain.useCase.DeleteDiaryUC
import care.intouch.app.feature.diary.domain.useCase.GetDiariesUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionUC
import care.intouch.app.feature.diary.domain.useCase.GetSavedAnswers
import care.intouch.app.feature.diary.domain.useCase.SaveAnswersUC
import care.intouch.app.feature.diary.domain.useCase.SaveDiaryUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionUC
import care.intouch.app.feature.diary.domain.useCase.SwitchVisibleUC
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

    @Binds
    fun bindGetDiariesUC(
        impl: GetDiariesUC.Base,
    ): GetDiariesUC

    @Binds
    fun bindDeleteDiaryUC(
        impl: DeleteDiaryUC.Base,
    ): DeleteDiaryUC

    @Binds
    fun bindSwitchVisibleUC(
        impl: SwitchVisibleUC.Base,
    ): SwitchVisibleUC

    @Binds
    fun bindSaveDiaryUC(
        impl: SaveDiaryUC.Base,
    ): SaveDiaryUC
}