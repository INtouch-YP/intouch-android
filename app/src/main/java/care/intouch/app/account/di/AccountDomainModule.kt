package care.intouch.app.account.di

import care.intouch.app.account.domain.api.ClearAccountUC
import care.intouch.app.account.domain.api.CreateAccountUC
import care.intouch.app.account.domain.api.GetAccountStateFlowUC
import care.intouch.app.account.domain.api.GetAccountStateUC
import care.intouch.app.account.domain.impl.ClearAccountUCImpl
import care.intouch.app.account.domain.impl.CreateAccountUCImpl
import care.intouch.app.account.domain.impl.GetAccountStateFlowUCImpl
import care.intouch.app.account.domain.impl.GetAccountStateUCImpl
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