package com.eduardo.listadeterefascompose.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eduardo.listadetarefascompose.componentes.Botao
import com.eduardo.listadetarefascompose.componentes.CaixaDeTexto
import com.eduardo.listadetarefascompose.constantes.Constates
import com.eduardo.listadetarefascompose.repositorio.TarefasReporsitorio
import com.eduardo.listadetarefascompose.ui.theme.RbGreenDisable
import com.eduardo.listadetarefascompose.ui.theme.RbGreenSelected
import com.eduardo.listadetarefascompose.ui.theme.RbRedDisable
import com.eduardo.listadetarefascompose.ui.theme.RbRedSelected
import com.eduardo.listadetarefascompose.ui.theme.RbYellowDisable
import com.eduardo.listadetarefascompose.ui.theme.RbYellowSelected
import com.eduardo.listadetarefascompose.ui.theme.WHITE
import com.eduardo.listadetarefascompose.ui.theme.azul1
import com.eduardo.listadetarefascompose.ui.theme.green1
import com.eduardo.listadetarefascompose.ui.theme.green5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SalvarTarefa(
    navController: NavController
) {

    var scope = rememberCoroutineScope()
    var context = LocalContext.current
    var tarefasRepositorio = TarefasReporsitorio()

    Scaffold(
        Modifier
            .background(azul1)
            .statusBarsPadding()
            .windowInsetsPadding(insets = WindowInsets.systemBars), topBar = {
            TopAppBar(backgroundColor = azul1, title = {
                Text(
                    text = "Salvar Tarefa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = WHITE
                )
            }


            )
        }) {


        var tituloTarefa by remember {
            mutableStateOf("")
        }

        var descricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange = {
                    tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp, 20.dp, 18.dp, 0.dp),
                labelTf = "Titulo Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            CaixaDeTexto(
                value = descricaoTarefa,
                onValueChange = {
                    descricaoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(18.dp, 15.dp, 18.dp, 0.dp),
                labelTf = "Descricao Terefa",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Prioridade")

                RadioButton(
                    selected = prioridadeBaixaTarefa, onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = RbGreenDisable, selectedColor = RbGreenSelected
                    )
                )

                RadioButton(
                    selected = prioridadeMediaTarefa, onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = RbYellowDisable, selectedColor = RbYellowSelected
                    )
                )

                RadioButton(
                    selected = prioridadeAltaTarefa, onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = RbRedDisable, selectedColor = RbRedSelected
                    )
                )


            }



            Botao(
                onClick = {
                    var messagem: Boolean = true

                    scope.launch(Dispatchers.IO) {
                        if (tituloTarefa.isEmpty()) {
                            messagem = false
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Baixa
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Media
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeAltaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Alta
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Baixa
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Baixa
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && prioridadeMediaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Media
                            )
                            messagem = true
                        } else if (tituloTarefa.isNotEmpty() && prioridadeAltaTarefa) {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.prioridade_Alta
                            )
                            messagem = true
                        } else {
                            tarefasRepositorio.salvarTarefaRep(
                                tituloTarefa, descricaoTarefa, Constates.sem_Prioridade
                            )
                            messagem = true
                        }


                    }

                    scope.launch(Dispatchers.Main) {
                        if (messagem) {
                            Toast.makeText(
                                context, "Sucesso ao salvar a tarefa", Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                context, "Titulo da tarefa e obrigatorio", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp), texto = "Salvar"
            )

        }
    }


}