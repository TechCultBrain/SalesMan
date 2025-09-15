package com.techcult.salesman.app.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techcult.salesman.app.LoadingScreen
import com.techcult.salesman.app.Navigation.Screen.ResetPasswordRoute
import com.techcult.salesman.app.Navigation.Screen.SplashRoute
import com.techcult.salesman.app.Navigation.Screen.VerifyOtpRoute
import com.techcult.salesman.feature.Home.presentation.HomeScreen
import com.techcult.salesman.feature.auth.presentation.UserLogin.UserLoginScreen
import com.techcult.salesman.feature.auth.presentation.forgotpassword.ForgotPasswordScreen
import com.techcult.salesman.feature.auth.presentation.login.LoginScreen
import com.techcult.salesman.feature.auth.presentation.register.RegisterScreen
import com.techcult.salesman.feature.auth.presentation.reset.ResetPasswordScreen
import com.techcult.salesman.feature.auth.presentation.verify.VerifyOTPScreen
import com.techcult.salesman.feature.notification.NotificationScreen

@Composable
fun SetupNavigation(startDestination: Screen) {
    val navController: NavHostController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.HomeRoute)
    {
        composable<SplashRoute> {

            LoadingScreen()
        }

        composable<Screen.LoginRoute> {
            LoginScreen(navigateToHome = {
                navController.navigate(Screen.HomeRoute) {
                    popUpTo(Screen.LoginRoute) {
                        inclusive = true
                    }
                }
            }, navigateToSignUp = {
                navController.navigate(Screen.RegisterRoute)
            }, navigateToForgotPassword = {
                navController.navigate(Screen.ForgotPasswordRoute)
            })
        }
        composable<Screen.RegisterRoute> {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Screen.LoginRoute)
                },
                onSuccess = {
                    navController.navigate(Screen.HomeRoute) {
                        popUpTo(Screen.LoginRoute)
                    }
                }
            )
        }
        composable<Screen.UserLoginRoute> {
            UserLoginScreen(onLoginSuccess = {
                navController.navigate(Screen.HomeRoute) {
                    popUpTo(Screen.UserLoginRoute)
                }
            })
        }
        composable<Screen.HomeRoute> {
            HomeScreen()
        }

        // Add more composable routes here
        composable<Screen.ProfileRoute> {

        }


        composable<Screen.NotificationsRoute> {
            NotificationScreen()
        }
        // Add more composable routes here

        composable<Screen.ForgotPasswordRoute> {
            ForgotPasswordScreen(onBackClick = {
                navController.navigate(Screen.LoginRoute)
            }, onSuccess = {})
        }

        composable<VerifyOtpRoute> {
            VerifyOTPScreen(onSuccess = {
                navController.navigate(ResetPasswordRoute) {
                    popUpTo(Screen.LoginRoute)
                }
            }, onBackClick = {}, onSupportClick = {

            })
        }

        composable<ResetPasswordRoute> {

            ResetPasswordScreen(navigateToLogin = {
                navController.navigate(Screen.LoginRoute)
            })


        }


    }
}