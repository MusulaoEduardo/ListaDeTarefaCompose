package com.eduardo.listadetarefascompose.datasource

import com.eduardo.listadetarefascompose.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todasTarefas

    fun salvatarefaData(tituloTarefa: String, descricao: String, prioridade: Int) {

        val tarefaMap = hashMapOf(
            "tarefa" to tituloTarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )
        db.collection("tarefas").document(tituloTarefa).set(tarefaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        var listaTarefas: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefas").get().addOnCompleteListener { querrySnapshot ->
            if (querrySnapshot.isSuccessful) {
                for (documento in querrySnapshot.result) {
                    val tarefa = documento.toObject(Tarefa::class.java)
                    listaTarefas.add(tarefa)
                    _todasTarefas.value = listaTarefas
                }
            }

        }
        return todasTarefas
    }

    fun deletarTarefa(tarefa: String){
        db.collection("tarefas").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

}