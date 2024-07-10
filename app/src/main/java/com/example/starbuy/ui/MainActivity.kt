package com.example.starbuy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starbuy.di.myModules
import com.example.starbuy.ui.features.IntroScreen
import com.example.starbuy.ui.features.signIn.SignInScreen
import com.example.starbuy.ui.features.signUp.SignUpScreen
import com.example.starbuy.ui.theme.BackgroundMain
import com.example.starbuy.ui.theme.MainAppTheme
import com.example.starbuy.util.KEY_CATEGORY_ARG
import com.example.starbuy.util.KEY_PRODUCT_ARG
import com.example.starbuy.util.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Koin (appDeclaration = {
                androidContext(this@MainActivity)
                modules( myModules )
            } ) {
                MainAppTheme {
                    Surface(
                        color = BackgroundMain,
                        modifier = Modifier.fillMaxSize()
                    ){
                        StarBuyUi()
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ){
            StarBuyUi()
        }
    }
}

@Composable
fun StarBuyUi(){
    val navController = rememberNavController()
    KoinNavHost(
        navController = navController,
        startDestination = MyScreens.MainScreen.route
    ) {

        composable( MyScreens.MainScreen.route ){
            MainScreen()
        }

        composable(
            route = MyScreens.ProductScreen.route + "/" + KEY_PRODUCT_ARG ,
            arguments = listOf(navArgument(KEY_PRODUCT_ARG){
                type = NavType.IntType
            })
        ){
            ProductScreen( it.arguments!!.getInt(KEY_PRODUCT_ARG , -1) )
        }

        composable(
            route = MyScreens.CategoryScreen.route + "/" + KEY_CATEGORY_ARG ,
            arguments = listOf(navArgument(KEY_CATEGORY_ARG){
                type = NavType.StringType
            })
        ){
            CategoryScreen(it.arguments!!.getString(KEY_CATEGORY_ARG , "null"))
        }

        composable( MyScreens.ProfileScreen.route){
            ProfileScreen()
        }

        composable(  MyScreens.CartScreen.route ){
            CartScreen()
        }

        composable(  MyScreens.SignUpScreen.route ){
            SignUpScreen()
        }

        composable( MyScreens.SignInScreen.route) {
            SignInScreen()
        }

        composable( MyScreens.IntroScreen.route){
            IntroScreen()
        }

        composable(MyScreens.NoInternetScreen.route){
            NoInternetScreen()
        }

    }
}


@Composable
fun NoInternetScreen() {

}




@Composable
fun CartScreen() {

}

@Composable
fun ProfileScreen() {

}

@Composable
fun CategoryScreen(categoryName :String) {

}

@Composable
fun ProductScreen(productId : Int) {

}

@Composable
fun MainScreen() {

}


