package com.appname.happyAging.di


import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class PrefModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "mPref",
            Context.MODE_PRIVATE
        )
    }
}