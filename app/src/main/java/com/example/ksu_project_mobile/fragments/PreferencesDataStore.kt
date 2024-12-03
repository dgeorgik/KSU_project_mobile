package com.example.ksu_project_mobile.fragments

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "app_preferences")
val FONT_SIZE_KEY = intPreferencesKey("font_size")
