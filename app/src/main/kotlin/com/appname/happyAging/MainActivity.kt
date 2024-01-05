package com.appname.happyAging

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.appname.happyAging.MainActivityUiState.Loading
import com.appname.happyAging.MainActivityUiState.Success
import com.appname.happyAging.core.utils.NetworkMonitor
import com.appname.happyAging.navigation.TopLevelDestination
import com.appname.happyAging.theme.Happy_agingTheme
import com.appname.happyAging.ui.HappyAgingApp
import com.google.firebase.Firebase
import com.google.firebase.initialize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var networkMonitor: NetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        Firebase.initialize(context = this)
        super.onCreate(savedInstanceState)


        var uiState: MainActivityUiState by mutableStateOf(Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach { uiState = it }
                    .collect{}
            }
        }

        splashScreen.setKeepVisibleCondition {
            Log.d("MainActivity", "uiState: $uiState")
            when (uiState) {
                is Success -> false
                else -> true
            }
        }

        setContent {
            Happy_agingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(uiState) {
                        is Loading -> {
                            Spacer(modifier =Modifier.fillMaxSize())
                        }
                        is Success -> {
                            val isLogin = (uiState as Success).jwtToken != null
                            val startDestination = if(isLogin) {
                                TopLevelDestination.MAIN
                            } else {
                                TopLevelDestination.LOGIN
                            }
                            HappyAgingApp(
                                networkMonitor = networkMonitor,
                                startDestination = startDestination,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
