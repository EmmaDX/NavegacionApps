package com.example.navegacionapps.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.navegacionapps.R
import com.example.navegacionapps.components.MainButton
import com.example.navegacionapps.components.MainIconButton
import com.example.navegacionapps.components.Space
import com.example.navegacionapps.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DogYearView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "DogYear View") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentDogYearView(navController)
    }
}

@Composable
fun ContentDogYearView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnosPerrunos()

        Space()

        MainButton(name = "Return home", backColor = Color.Blue, color = Color.White) {
            navController.navigate("Home")
        }
    }
}

@Composable
fun AnosPerrunos(){
    PosicionPantalla(
        titulo="Mis Años Perrunos",
        imagen= painterResource(id= R.drawable.perrito)

    )
}
@Composable
private fun PosicionPantalla(titulo:String, imagen: Painter, modifier:Modifier=Modifier) {
    val context = LocalContext.current
    Column(
        modifier=modifier.padding(16.dp),

        ){
        var edad by remember { mutableStateOf("") }
        var resultado by remember {
            mutableStateOf("")
        }
        Image(
            painter=imagen,
            contentDescription =null,
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center
        )
        Text(
            text=titulo,
            modifier=Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        OutlinedTextField(
            value = edad,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                if (!it.isDigitsOnly()) {
                    val alert = "Solo puedes ingresar numeros"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, alert, duration)
                    toast.show()
                } else {
                    edad = it
                }
            },
            label = { Text("Mi edad humana")}

        )

        ElevatedButton(
            onClick = {
                if (edad.isEmpty()){
                    val alert = "La edad no puede estar vacia"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, alert, duration)
                    toast.show()
                } else {
                    var res=0
                    res=edad.toInt() * 7
                    resultado=res.toString()
                }

            })
        {
            Text("Calcular")
        }

        OutlinedTextField(
            value = resultado,
            readOnly = true,
            onValueChange = { resultado = it },
            label = { Text("Edad Perruna") }
        )

        ElevatedButton(
            onClick = {
                resultado = ""
                edad = ""
            })
        {
            Text("Limpiar")
        }
    }

}