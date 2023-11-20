package com.example.onenote

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onenote.ui.presentation.HomeScreen
import com.example.onenote.ui.presentation.SplashViewModel
import com.example.onenote.ui.presentation.bottomnavigation.Screen
import com.example.onenote.ui.presentation.setting.PreferenceUtils
import com.example.onenote.ui.presentation.setting.SettingsViewModel
import com.example.onenote.ui.theme.OneNoteTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    lateinit var settingsViewModel: SettingsViewModel
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}
        settingsViewModel.setUpAppTheme()

        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.isLoading.value
        }

        // refresh reminders
        mainViewModel.refreshReminders()

        val appLockStatus = PreferenceUtils.getBoolean(PreferenceUtils.APP_LOCK, false)

        if (appLockStatus && !mainViewModel.appUnlocked) {
            executor = ContextCompat.getMainExecutor(this)
            biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        super.onAuthenticationSucceeded(result)
                        getString(R.string.auth_successful).toToast(this@MainActivity)
                        // make app contents visible after successful authentication.
                        setAppContents()
                        mainViewModel.appUnlocked = true
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        /**
                         * On auth error check if user can still authenticate or no, i.e. make sure
                         * that user has not removed authentication from the device and fingerprint
                         * hardware is available. if not then make app contents visible and disable
                         *  app lock to avoid user from becoming unable to access their data.
                         */
                        /**
                         * On auth error check if user can still authenticate or no, i.e. make sure
                         * that user has not removed authentication from the device and fingerprint
                         * hardware is available. if not then make app contents visible and disable
                         *  app lock to avoid user from becoming unable to access their data.
                         */
                        val biometricManager = BiometricManager.from(this@MainActivity)
                        if (biometricManager.canAuthenticate(Utils.getAuthenticators()) != BiometricManager.BIOMETRIC_SUCCESS) {
                            setAppContents()
                            mainViewModel.appUnlocked = true
                            PreferenceUtils.putBoolean(PreferenceUtils.APP_LOCK, false)
                        } else {
                            finish() // close the app.
                        }
                    }
                })

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.bio_lock_title))
                .setSubtitle(getString(R.string.bio_lock_subtitle))
                .setAllowedAuthenticators(Utils.getAuthenticators())
                .build()

            biometricPrompt.authenticate(promptInfo)

        } else {
            setAppContents()
        }
    }

    fun setAppContents() {
        setContent {
            GreenStashTheme(settingsViewModel = settingsViewModel) {
                val systemUiController = rememberSystemUiController()
                systemUiController.setNavigationBarColor(
                    color = MaterialTheme.colorScheme.background,
                    darkIcons = settingsViewModel.getCurrentTheme() == ThemeMode.Light
                )

                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                    darkIcons = settingsViewModel.getCurrentTheme() == ThemeMode.Light
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    val screen by mainViewModel.startDestination
                    NavGraph(navController = navController, screen)
                }
            }
        }
    }