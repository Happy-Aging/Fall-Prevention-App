package com.appname.happyAging.di

import com.appname.happyAging.core.utils.ConnectivityManagerNetworkMonitor
import com.appname.happyAging.core.utils.NetworkMonitor
import com.appname.happyAging.data.repository.auth.AuthRepositoryImpl
import com.appname.happyAging.data.repository.auth.JwtTokenRepositoryImpl
import com.appname.happyAging.data.repository.senior.SeniorRepositoryImpl
import com.appname.happyAging.data.repository.survey.SurveyRepositoryImpl
import com.appname.happyAging.data.repository.user.UserRepositoryImpl
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import com.appname.happyAging.domain.repository.user.UserRepository
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import com.appname.happyAging.domain.repository.survey.SurveyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideSeniorRepository(
        seniorRepositoryImpl: SeniorRepositoryImpl
    ): SeniorRepository

    @Singleton
    @Binds
    abstract fun providerUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    abstract fun provideSurveyRepository(
        surveyRepositoryImpl: SurveyRepositoryImpl
    ): SurveyRepository

    @Singleton
    @Binds
    abstract fun provideJwtTokenRepository(
        jwtTokenRepositoryImpl: JwtTokenRepositoryImpl
    ): JwtTokenRepository

    @Singleton
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

}