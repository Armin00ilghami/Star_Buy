package com.example.starbuy.ui.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuy.R
import com.example.starbuy.ui.theme.BackgroundMain
import com.example.starbuy.ui.theme.CardViewBackground
import com.example.starbuy.ui.theme.MainAppTheme
import com.example.starbuy.ui.theme.Shapes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){

    MainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundMain
        ){
            MainScreen()
        }
    }

}

@Composable
fun MainScreen(){

    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(Color.White)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ){

        TopToolbar()

        CategoryBar()

        ProductSubject()
        ProductSubject()

        BigPictureAdvertise()

        ProductSubject()
        ProductSubject()

    }

}

//__________________________________________________________
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolbar() {
//    Scaffold {
//
//    } attach to root and etc in material design

    TopAppBar(
        modifier = Modifier.background(Color.White),
        title = { Text(text = "Star Buy") },
        actions = {
            
            IconButton(onClick = {} ) {
                Icon( Icons.Default.ShoppingCart , null )
            }

            IconButton(onClick = {} ) {
                Icon( Icons.Default.Person , null )
            }
            
        }
    )
    
}
//__________________________________________________________
@Composable
fun CategoryBar() {

    LazyRow (
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ){

        items (10){
            CategoryItem()
        }

    }

}

@Composable
fun CategoryItem(){
    Column (
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Surface(
            shape = Shapes.medium,
            color = CardViewBackground
        ) {
            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.ic_icon_app),
                contentDescription = null
            )
        }
        Text(
            text = "hotel",
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(color = Color.Gray)
        )

    }
}
//__________________________________________________________
@Composable
fun ProductSubject() {

    Column (
        modifier = Modifier.padding(top = 32.dp)
    ){
        Text(
            text = "Popular Destinations",
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.headlineSmall //.h6
        )

        ProductBar()

    }

}

@Composable
fun ProductBar() {

    LazyRow (
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ){
        items(10){
            ProductItem()
        }
    }

}

@Composable
fun ProductItem() {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = Shapes.medium
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.img_intro),
            contentDescription = null
        )

        Column (
            modifier = Modifier.padding(10.dp)
        ){

            Text(
                text = "Product 1",
                style = TextStyle(fontSize = 15.sp , fontWeight = FontWeight.Medium)
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "1mil Toman",
                style = TextStyle(fontSize = 14.sp)
            )

            Text(
                text = "+500 sold",
                style = TextStyle(color = Color.Gray , fontSize = 13.sp)
            )

        }

    }

}

//__________________________________________________________
@Composable
fun BigPictureAdvertise() {

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(top = 32.dp , start = 16.dp , end = 16.dp)
            .clip(Shapes.medium)
            .clickable {  },
        painter = painterResource(id = R.drawable.img_intro),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )

}