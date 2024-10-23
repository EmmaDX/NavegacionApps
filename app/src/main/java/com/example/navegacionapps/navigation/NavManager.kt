package com.example.navegacionapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacionapps.views.HomeView
import com.example.navegacionapps.views.DogYearView
import com.example.navegacionapps.views.DescuentosView
import com.example.navegacionapps.views.LotoView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"  ){
        composable("Home"){
            HomeView(navController)
        }
        composable("DogYear"){
            DogYearView(navController)
        }
        composable("Descuentos"){
            DescuentosView(navController)
        }
        composable("Loteria"){
            LotoView(navController)
        }
    }
}