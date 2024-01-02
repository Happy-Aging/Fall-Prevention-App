package com.appname.happyAging.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.jwtTokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "jwt_token")