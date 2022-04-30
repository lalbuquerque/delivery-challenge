package com.farmstead.delivery.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import com.farmstead.delivery.persistency.LocalDataWrapper
import com.farmstead.delivery.domain.CurrentDelivery
import com.farmstead.delivery.ui.theme.ComposeTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStoreLocalDataWrapper: LocalDataWrapper<CurrentDelivery>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                val navController = rememberAnimatedNavController()

                AnimatedNavHost(navController = navController,
                    startDestination = Screens.Delivery.title,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }) {

                    composable(Screens.Delivery.title) {
                        DeliveryListScreen(applicationContext, navController,
                            localDataWrapper = dataStoreLocalDataWrapper)
                    }
                }
            }
        }
    }
}
