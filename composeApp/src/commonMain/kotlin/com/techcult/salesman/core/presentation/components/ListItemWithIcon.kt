package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.techcult.salesman.core.presentation.theme.LocalPadding
import org.jetbrains.compose.resources.painterResource
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.img_placeholder

@Composable
fun ListItemWithIcon(uri: String, titleText: String, subtitleText : String,modifier: Modifier = Modifier)
{
    Row(modifier = modifier.padding(LocalPadding.current.tiny),verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier.size(54.dp).clip(MaterialTheme.shapes.medium),
            model = uri,
            contentDescription = null,
            placeholder = painterResource(Res.drawable.img_placeholder),
            error = painterResource(Res.drawable.img_placeholder),
            clipToBounds = true

        )
        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
        Column {
            Text(text = titleText, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
            Text(text = subtitleText, style = MaterialTheme.typography.bodySmall,color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }

    }

}