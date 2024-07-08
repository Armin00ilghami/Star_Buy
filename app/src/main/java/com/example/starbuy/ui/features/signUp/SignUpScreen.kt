package com.example.starbuy.ui.features.signUp

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuy.R
import com.example.starbuy.ui.features.IntroScreen
import com.example.starbuy.ui.theme.BackgroundMain
import com.example.starbuy.ui.theme.Blue
import com.example.starbuy.ui.theme.MainAppTheme
import com.example.starbuy.ui.theme.Shapes
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.starbuy.util.MyScreens
import com.example.starbuy.util.NetworkChecker
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import java.util.regex.Pattern

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {


    MainAppTheme {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ) {
            //IntroScreen()
            //MainCardView(){}
            SignUpScreen()
        }
    }
}


@Composable
fun SignUpScreen() {

    val navigation = getNavController()
    val viewModel = getNavViewModel<SignUpViewModel>()

    Box {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Blue)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            IconApp()
            MainCardView (navigation ,viewModel){
                viewModel.signUpUser()
            }

        }

    }

}

@Composable
fun IconApp(){

    Surface (
        modifier = Modifier
            .clip(CircleShape)
            .size(64.dp)
    ){
        Image(
            modifier = Modifier.padding(14.dp),
            painter = painterResource(id = R.drawable.ic_icon_app),
            contentDescription = null )
    }

}

@Composable
fun MainCardView( navigation : NavController,viewModel: SignUpViewModel ,SignUpEvent: () -> Unit ){

    val name = viewModel.name.observeAsState("")
    val email = viewModel.email.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val confirmPassword = viewModel.confirmPassword.observeAsState("")
    val context = LocalContext.current


    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ) ,
        shape = Shapes.medium
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier
                    .padding(top = 18.dp , bottom = 18.dp),
                text = "Sign Up",
                style = TextStyle(color = Blue , fontSize = 28.sp ,fontWeight = FontWeight.Bold)
            )

            MainTextField(name.value ,R.drawable.ic_person ,"Your FullName"){viewModel.name.value = it}
            MainTextField(email.value ,R.drawable.ic_email ,"Email Address"){viewModel.email.value = it}
            PasswordTextField(password.value ,R.drawable.ic_password ,"Password"){viewModel.password.value = it}
            PasswordTextField(confirmPassword.value ,R.drawable.ic_password ,"Confirm Password"){viewModel.confirmPassword.value = it}

            Button(
                onClick = {

                          if (name.value.isNotEmpty() &&
                              email.value.isNotEmpty() &&
                              password.value.isNotEmpty() &&
                              confirmPassword.value.isNotEmpty()){

                              if (password.value == confirmPassword.value){
                                  if (password.value.length >= 8){
                                      if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
                                          if (NetworkChecker(context).isInternetConnected){
                                              SignUpEvent.invoke()
                                          }else{
                                              Toast.makeText(context,"Internet Not Connected!", Toast.LENGTH_LONG).show()
                                          }
                                      }else{
                                          Toast.makeText(context,"Email Format Wrong!", Toast.LENGTH_LONG).show()
                                      }
                                  }else{
                                      Toast.makeText(context,"Character Should Be More Than 8 !", Toast.LENGTH_LONG).show()
                                  }
                              }else{
                                  Toast.makeText(context,"Password and Confirm are not same!", Toast.LENGTH_SHORT).show()
                              }
                          }else{
                              Toast.makeText(context,"Please fill data complete", Toast.LENGTH_LONG).show()
                          }
                },
                modifier = Modifier.padding(top = 28.dp , bottom = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Register Account"
                )
            }

            Row (
                modifier = Modifier.padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(text = "Already have an account?")

                Spacer(modifier = Modifier.width(8.dp))

                TextButton(
                    onClick = {
                        navigation.navigate(MyScreens.SignInScreen.route){
                            popUpTo(MyScreens.SignUpScreen.route){
                                inclusive = true
                            }
                        }
                    }
                ) {
                  Text(text = "Log In" , color = Blue)
                }

            }

        }
    }
}

@Composable
fun MainTextField(
    edtValue :String,
    icon :Int,
    hint :String,
    onValueChanges :(String)-> Unit
){

   OutlinedTextField(
       label = {Text(hint)} ,
       value = edtValue ,
       singleLine = true,
       onValueChange = onValueChanges ,
       placeholder = {Text(hint)},
       modifier = Modifier
           .fillMaxWidth(0.9f)
           .padding(top = 12.dp),
       shape = Shapes.medium,
       leadingIcon = { Icon(painterResource(icon),null) }
   )
    
}

@Composable
fun PasswordTextField(
    edtValue :String,
    icon :Int,
    hint :String,
    onValueChanges :(String)-> Unit
){
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        label = {Text(hint)} ,
        value = edtValue ,
        singleLine = true,
        onValueChange = onValueChanges ,
        placeholder = {Text(hint)},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 12.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(painterResource(icon),null) },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions( keyboardType =  KeyboardType.Password ),
        trailingIcon = {

            val image =
                if (passwordVisible.value) painterResource( R.drawable.ic_invisible )
                else painterResource( R.drawable.ic_visible )

            Icon(
                painter = image,
                contentDescription = null,
                modifier = Modifier.clickable { passwordVisible.value = !passwordVisible.value }
            )

        }
    )

}