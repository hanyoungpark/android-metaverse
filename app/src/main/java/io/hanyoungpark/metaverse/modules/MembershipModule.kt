package io.hanyoungpark.metaverse.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.hanyoungpark.metaverse.repositories.MembershipRepository
import io.hanyoungpark.metaverse.repositories.MembershipRepositoryImpl
import io.hanyoungpark.metaverse.services.AuthenticateService
import io.hanyoungpark.metaverse.services.AuthenticateServiceImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class MembershipViewModelModule {
    @Binds
    abstract fun provideMembershipRepository(impl: MembershipRepositoryImpl): MembershipRepository
}

@InstallIn(SingletonComponent::class)
@Module
abstract class MembershipApplicationModule {
    @Binds
    abstract fun provideAuthenticateService(impl: AuthenticateServiceImpl): AuthenticateService
}
