package com.example.onenote.ui.presentation.setting

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ThemeMode {
    Light, Dark, Auto
}

sealed class DateStyle(val pattern: String) {
    object DateMonthYear : DateStyle("dd/MM/yyyy")
    object YearMonthDate : DateStyle("yyyy/MM/dd")
}

class SettingsViewModel : ViewModel() {
    private val _theme = MutableLiveData(ThemeMode.Auto)
    private val _materialYou = MutableLiveData(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)

    val theme: LiveData<ThemeMode> = _theme
    val materialYou: LiveData<Boolean> = _materialYou

    fun setTheme(newTheme: ThemeMode) {
        _theme.postValue(newTheme)
    }

    fun setMaterialYou(newValue: Boolean) {
        _materialYou.postValue(newValue)
    }

    @Composable
    fun getCurrentTheme(): ThemeMode {
        return if (theme.value == ThemeMode.Auto) {
            if (isSystemInDarkTheme()) ThemeMode.Dark else ThemeMode.Light
        } else theme.value!!
    }

    fun setUpAppTheme() {
        when (PreferenceUtils.getInt(PreferenceUtils.APP_THEME, ThemeMode.Auto.ordinal)) {
            ThemeMode.Auto.ordinal -> setTheme(ThemeMode.Auto)
            ThemeMode.Dark.ordinal -> setTheme(ThemeMode.Dark)
            ThemeMode.Light.ordinal -> setTheme(ThemeMode.Light)
        }
        setMaterialYou(
            PreferenceUtils.getBoolean(
                PreferenceUtils.MATERIAL_YOU, Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            )
        )
    }
}



object PreferenceUtils {
    private lateinit var prefs: SharedPreferences
    private const val PREFS_NAME = "myne_settings"

    // Preference keys
    const val APP_THEME = "theme_settings"
    const val MATERIAL_YOU = "material_you"
    const val DEFAULT_CURRENCY = "default_currency"
    const val DATE_FORMAT = "date_format"
    const val APP_LOCK = "app_lock"

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Pre-populate some preference data with default values
        if (!keyExists(DEFAULT_CURRENCY)) {
            putString(DEFAULT_CURRENCY, "$")
        }
        if (!keyExists(DATE_FORMAT)) {
            putString(DATE_FORMAT, DateStyle.DateMonthYear.pattern)
        }
    }

    private fun keyExists(key: String): Boolean {
        if (prefs.contains(key))
            return true
        return false
    }

    fun putString(key: String, value: String) {
        val prefsEditor = prefs.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun putInt(key: String, value: Int) {
        val prefsEditor = prefs.edit()
        prefsEditor.putInt(key, value)
        prefsEditor.apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        val prefsEditor = prefs.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun getString(key: String, defValue: String): String? {
        return prefs.getString(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }
}