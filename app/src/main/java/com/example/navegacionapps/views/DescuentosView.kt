package com.example.navegacionapps.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navegacionapps.components.MainButton
import com.example.navegacionapps.components.MainIconButton
import com.example.navegacionapps.components.Space
import com.example.navegacionapps.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DescuentosView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Descuentos View") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Magenta
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {


                    }
                }
            )
        }
    ) {
        ContentDescuentosView(navController)
    }
}

@Composable
fun ContentDescuentosView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Descuentos()

        Space()

        MainButton(name = "Return home", backColor = Color.Magenta, color = Color.White) {
            navController.navigate("Home")
        }
    }
}

@Composable
private fun Descuentos(){
    Pantalla()
}

@Composable
private fun Pantalla(modifier:Modifier=Modifier) {

    Column(
        modifier=modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="Descuentos",
            modifier=Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        var precio by remember {mutableStateOf("")}
        var descPorc by remember {mutableStateOf("")}
        var descuento by remember {mutableStateOf("") }
        var total by remember { mutableStateOf("")}

        OutlinedTextField(
            value = precio,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                precio = it
            },
            label = { Text("Precio")}
        )

        OutlinedTextField(
            value = descPorc,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                descPorc = it
            },
            label = { Text("Descuento %")}
        )
        ElevatedButton(
            onClick = {
                descuento = calcularDesc(precio, descPorc).toString()
                total = calcularTotal(precio, descuento).toString()
            })
        {
            Text("Calcular")
        }

        ElevatedButton(
            onClick = {
                precio = ""
                descPorc = ""
                descuento = ""
                total = ""
            })
        {
            Text("Borrar")
        }


        OutlinedTextField(
            value = descuento,
            readOnly = true,
            onValueChange = { descuento = it },
            label = { Text("Descuento")}
        )

        OutlinedTextField(
            value = total,
            readOnly = true,
            onValueChange = { total = it },
            label = { Text("Total") }
        )
    }
}

fun calcularDesc(texto1: String, texto2: String): Double{
    val precio = texto1.toDouble()
    val descPorc = texto2.toDouble()
    val descuento = (descPorc * precio) / 100

    return descuento
}

fun calcularTotal(texto1: String, texto2: String): Double{
    val precio = texto1.toDouble()
    val descuento = texto2.toDouble()
    val total = precio - descuento

    return total
}