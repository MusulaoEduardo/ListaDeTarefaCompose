package com.eduardo.listadetarefascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardo.listadetarefascompose.ui.theme.ListaDeTarefasComposeTheme
import com.eduardo.listadeterefascompose.view.ListaTarefas
import com.eduardo.listadeterefascompose.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ListaDeTarefasComposeTheme {

                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = "listaTarefas" ){
                    composable(route = "listaTarefas",) {
                        ListaTarefas(navController = navController)
                    }

                    composable(route = "salvarTarefa") {
                        SalvarTarefa(navController = navController)
                    }
                }


            }


        }
    }
}




