package com.appname.happyAging.data.repository.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtTokenRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : JwtTokenRepository {
    override suspend fun saveJwtToken(jwtToken: JwtToken) {
        dataStore.edit {
            it[ACCESS_TOKEN] = jwtToken.accessToken
            it[REFRESH_TOKEN] = jwtToken.refreshToken
        }
    }

    override suspend fun getJwtToken(): JwtToken {
        val x= dataStore.data.map {prefs ->
            val accessToken = prefs[ACCESS_TOKEN]!!
            val refreshToken = prefs[REFRESH_TOKEN]!!
            JwtToken(accessToken, refreshToken)
        }.catch {
            throw it
        }
        return x.first()
    }

    override suspend fun deleteJwtToken() {
        dataStore.edit {
            it.remove(ACCESS_TOKEN)
            it.remove(REFRESH_TOKEN)
        }
    }

    companion object{
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}