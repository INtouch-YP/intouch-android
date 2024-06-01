package care.intouch.app.feature.authorization.di

import care.intouch.app.feature.authorization.domain.impl.ClearAccountUCImpl
import care.intouch.app.feature.authorization.domain.impl.CreateAccountUCImpl
import care.intouch.app.feature.authorization.domain.impl.GetAccountStateFlowUCImpl
import care.intouch.app.feature.authorization.domain.impl.GetAccountStateUCImpl
import care.intouch.app.feature.authorization.domain.useCase.ClearAccountUC
import care.intouch.app.feature.authorization.domain.useCase.CreateAccountUC
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateFlowUC
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AccountDomainModule {
    @Binds
    fun bindGetAccountStateFlowUC(impl: GetAccountStateFlowUCImpl): GetAccountStateFlowUC

    @Binds
    fun bindGetAccountStateUC(impl: GetAccountStateUCImpl): GetAccountStateUC

    @Binds
    fun bindClearAccountUC(impl: ClearAccountUCImpl): ClearAccountUC

    @Binds
    fun bindCreateAccountUC(impl: CreateAccountUCImpl): CreateAccountUC
}