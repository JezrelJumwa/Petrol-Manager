package com.cartracker.app.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Returns the short prefix string for the given currency code. */
fun currencySymbol(currency: String): String = when (currency) {
    "USD" -> "$"
    else  -> "Ksh."
}

/** Formats an amount with the correct prefix, e.g. "Ksh. 1,500.00" or "$ 12.50". */
fun formatAmount(amount: Double, currency: String): String =
    "${currencySymbol(currency)} %,.2f".format(amount)

/**
 * A minimal two-option currency toggle (Ksh. / $).
 * The currently selected option renders as a filled [Button];
 * the other as an [OutlinedButton].
 */
@Composable
fun CurrencySelector(
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Currency:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 4.dp)
        )
        listOf("KSH" to "Ksh.", "USD" to "$").forEach { (value, label) ->
            if (selected == value) {
                Button(onClick = {}) { Text(label) }
            } else {
                OutlinedButton(onClick = { onSelect(value) }) { Text(label) }
            }
        }
    }
}
