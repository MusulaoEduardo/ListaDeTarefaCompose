package com.eduardo.listadeterefascompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.eduardo.listadetarefascompose.R
import com.eduardo.listadetarefascompose.itemLista.TarefaItem
import com.eduardo.listadetarefascompose.model.Tarefa
import com.eduardo.listadetarefascompose.repositorio.TarefasReporsitorio
import com.eduardo.listadetarefascompose.ui.theme.BLACK
import com.eduardo.listadetarefascompose.ui.theme.WHITE
import com.eduardo.listadetarefascompose.ui.theme.azul1
import com.eduardo.listadetarefascompose.ui.theme.cinzaFundo
import com.eduardo.listadetarefascompose.ui.theme.green6
import com.google.firebase.Firebase


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaTarefas(
    navController: NavController
) {


    val tarefasReporsitorio = TarefasReporsitorio()
    val context = LocalContext.current

    Scaffold(
        Modifier
            .background(azul1)
            .statusBarsPadding()
            .fillMaxWidth().
            windowInsetsPadding(insets = WindowInsets.systemBars),

        topBar = {
            TopAppBar(
                backgroundColor = azul1,
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                }, elevation = 20.dp

            )
        },
        backgroundColor = cinzaFundo,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvartarefa")
                },
                containerColor = green6,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "icone de adicionar tarefa"
                )
            }
        }

    ) {


        val listaTarefas =
            tarefasReporsitorio.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn {
            itemsIndexed(listaTarefas) { position, _ ->
                TarefaItem(position = position, listaTarefas = listaTarefas, context = context, navController = navController)
//                    if (listaTarefas.isNotEmpty()) {
//                        listaTarefas.sortedBy {
//                            listaTarefas[position].prioridade
//                        }
//                    }
            }
        }
    }
}
