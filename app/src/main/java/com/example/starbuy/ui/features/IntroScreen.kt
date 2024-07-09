package com.example.starbuy.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.starbuy.R
import com.example.starbuy.ui.StarBuyUi
import com.example.starbuy.ui.theme.BackgroundMain
import com.example.starbuy.ui.theme.Blue
import com.example.starbuy.ui.theme.MainAppTheme
import com.example.starbuy.util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController


@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ) {
            IntroScreen()
        }
    }
}

@Composable
fun IntroScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(Blue)
    }

    val navigation = getNavController()
    
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource( R.drawable.img_intro ) ,
        contentDescription = null ,
        contentScale = ContentScale.Crop
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(0.78f),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            onClick = {
                navigation.navigate(MyScreens.SignUpScreen.route)
            }
        ) {

            Text(
                text = "Sign Up"
            )

        }

        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {
                navigation.navigate(MyScreens.SignInScreen.route)
            }
        ) {

            Text(
                text = "Sign In" ,
                color = Color.Blue
            )

        }

    }

}