package com.techcult.salesman.core.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class CurrencyVisualTransformation(
    private val symbol: String = "₹",
) : VisualTransformation {




    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        if (originalText.isEmpty()) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        // Don't format if the input ends with a decimal point or is just "0"
        if (originalText.endsWith(".") || originalText == "0") {
            val output = AnnotatedString.Builder().apply {
                append("$symbol $originalText")
            }.toAnnotatedString()
            return TransformedText(output, object : OffsetMapping {
                override fun originalToTransformed(offset: Int) = offset + symbol.length + 1
                override fun transformedToOriginal(offset: Int) = (offset - (symbol.length + 1)).coerceAtLeast(0)
            })
        }

        val number = originalText.toDoubleOrNull() ?: return TransformedText(text, OffsetMapping.Identity)
        val formattedText = formatDouble(number)

        val output = AnnotatedString.Builder().apply {
            append("$symbol ")
            append(formattedText)
        }.toAnnotatedString()

        // This offset mapping keeps the cursor at the end of the text, which is a
        // user-friendly approach for currency input.
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return output.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return originalText.length
            }
        }

        return TransformedText(output, offsetMapping)
    }
}