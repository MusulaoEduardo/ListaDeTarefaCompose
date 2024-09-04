package com.eduardo.listadetarefascompose.itemLista

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.eduardo.listadetarefascompose.R
import com.eduardo.listadetarefascompose.model.Tarefa
import com.eduardo.listadetarefascompose.repositorio.TarefasReporsitorio
import com.eduardo.listadetarefascompose.ui.theme.RbGreenSelected
import com.eduardo.listadetarefascompose.ui.theme.RbRedSelected
import com.eduardo.listadetarefascompose.ui.theme.RbYellowSelected
import com.eduardo.listadetarefascompose.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>,
    context:Context,
    navController: NavController
) {

    val tituloTarefa = listaTarefas[position].tarefa
    val descricaoTarefa = listaTarefas[position].descricao
    var prioridadeLista = listaTarefas[position].prioridade

    val scope = rememberCoroutineScope()
    val tarefasReporsitorio = TarefasReporsitorio()

    fun dialogDeletar() {

       val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Deletar Tarefa").setMessage("Desejea excluir a Tarefa?")
            .setPositiveButton("Sim") { _, _ ->

                tarefasReporsitorio.deletarTarefas(tituloTarefa.toString())
                scope.launch(Dispatchers.Main) {
                    listaTarefas.removeAt(position)
                    navController.navigate("listaTarefas")
                    Toast.makeText(context,"Sucesso ao deletar a tarefa",Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não") { _, _ ->

            }.show()
        Log.d("deu certo o teste","123")
    }

//    mudanca de layout, em vez de aparecer um texto depois de Prioridade e o card,
//    eu mudei para um texto fixo ai vem a cor da card + a descrição da cor ex: verde = Baixa
    var nivelDePrioridade: String = when (prioridadeLista) {
        0 -> {
            "Sem Prioridade"
        }

        1 -> {
            "Baixa" //"Prioridade Baixa"
        }

        2 -> {
            "Media" //"Prioridade Media"
        }

        else -> {
            "Alta" //"Prioridade Alta"
        }
    }

    val color = when (prioridadeLista) {
        0 -> {
            Color.Black
        }

        1 -> {
            RbGreenSelected
        }

        2 -> {
            RbYellowSelected
        }

        else -> {
            RbRedSelected
        }
    }

    Card(
        backgroundColor = WHITE, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier.padding(20.dp)

        ) {
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, txtPrioridade1, btDeletar) = createRefs()

            Text(
                text = tituloTarefa.toString().toUpperCase(),
                modifier = Modifier.constrainAs(txtTitulo) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)

                },
                fontWeight = FontWeight.Bold,

                )

            Text(text = descricaoTarefa.toString(), modifier = Modifier.constrainAs(txtDescricao) {
                top.linkTo(txtTitulo.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 10.dp)

            })

            Text(
//                text = nivelDePrioridade,
                text = "Prioridade:", modifier = Modifier.constrainAs(txtPrioridade) {
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                })

            Card(backgroundColor = color,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)

                    }) {


            }

            Text(text = nivelDePrioridade, modifier = Modifier.constrainAs(txtPrioridade1) {
                top.linkTo(txtDescricao.bottom, margin = 10.dp)
                start.linkTo(cardPrioridade.end, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            }

            )

//            IconButton(
//                onClick = {
//
//                },
//                modifier = Modifier
//                    .constrainAs(btDeletar) {
//                        top.linkTo(parent.bottom, margin = 15.dp)
//                        start.linkTo(parent.end, margin = 15.dp)
//                        end.linkTo(parent.end, margin = 15.dp)
//                        bottom.linkTo(txtPrioridade.bottom, margin = 15.dp)
//                    }
//            ) {
//                Image(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
//                    contentDescription = "icone de deletar"
//                )
//            }

            IconButton(
                onClick = {
                    dialogDeletar()
                }, modifier = Modifier
                    .size(30.dp)
                    .constrainAs(btDeletar) {
                        top.linkTo(parent.top, margin = 15.dp)
                        start.linkTo(parent.end, margin = 15.dp)
                        end.linkTo(parent.end, margin = 15.dp)
                        bottom.linkTo(parent.top, margin = 15.dp)
                    }) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "icone de deletar"
                )
            }

        }

    }

}

