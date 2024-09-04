package com.eduardo.listadetarefascompose.repositorio

import com.eduardo.listadetarefascompose.datasource.DataSource
import com.eduardo.listadetarefascompose.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasReporsitorio(

) {
    private val dataSource = DataSource()

    fun salvarTarefaRep(tarefa: String, descricao: String, prioridade: Int) {
        dataSource.salvatarefaData(tarefa, descricao, prioridade)
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {
        return dataSource.recuperarTarefas()
    }

    fun deletarTarefas(tarefa: String){
        dataSource.deletarTarefa(tarefa)
    }
}