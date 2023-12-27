package com.appname.happyAging.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.appname.happyAging.R
import com.appname.happyAging.core.utils.NetworkMonitor
import com.appname.happyAging.navigation.HappyAgingNavHost
import com.appname.happyAging.navigation.TopLevelDestination

@Composable
fun HappyAgingApp(
    networkMonitor: NetworkMonitor,
    appState: HappyAgingAppState = rememberHappyAgingAppState(
        networkMonitor = networkMonitor,
    ),
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    HappyAgingNavHost(
        appState = appState,
    )
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false