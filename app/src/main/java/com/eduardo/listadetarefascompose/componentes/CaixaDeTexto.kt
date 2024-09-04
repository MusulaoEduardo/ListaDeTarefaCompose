package com.eduardo.listadetarefascompose.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.eduardo.listadetarefascompose.ui.theme.BLACK
import com.eduardo.listadetarefascompose.ui.theme.ShapeeditText
import com.eduardo.listadetarefascompose.ui.theme.WHITE
import com.eduardo.listadetarefascompose.ui.theme.lightBlue

// labelTf=titulo tarefa
@Composable
fun CaixaDeTexto(
    value:String,onValueChange:(String) -> Unit,
    modifier: Modifier,
    labelTf:String,
    maxLines:Int,
    keyboardType: KeyboardType

){
        OutlinedTextField(
            value = value,
            onValueChange,
            modifier,
            label = {
                Text(text = labelTf)
            },
            maxLines = maxLines,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = BLACK,
                focusedBorderColor = lightBlue,
                focusedLabelColor = lightBlue,
                backgroundColor = WHITE,
                cursorColor = lightBlue //define a cor do cursor, caso o contratrio estava pegando a cor do TopAppBar e acho
            ),
            shape = ShapeeditText.small,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )

        )
}

