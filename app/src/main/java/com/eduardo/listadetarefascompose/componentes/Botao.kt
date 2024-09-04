package com.eduardo.listadetarefascompose.componentes

import android.service.autofill.OnClickAction
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eduardo.listadetarefascompose.ui.theme.WHITE
import com.eduardo.listadetarefascompose.ui.theme.azul1

@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = WHITE,
            containerColor = azul1
        )
    )
    {
        Text(
            text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp
        )
    }
}