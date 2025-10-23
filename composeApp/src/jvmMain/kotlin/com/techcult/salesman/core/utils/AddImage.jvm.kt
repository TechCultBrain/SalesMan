@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.techcult.salesman.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Uri
import java.awt.FileDialog
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView


@Composable
actual fun FilePicker(onFilePicked: (String?) -> Unit) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var filePath by remember { mutableStateOf<String?>(null) }

    val chooser = JFileChooser().apply {
        fileSelectionMode = JFileChooser.FILES_ONLY
        fileFilter = FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp", "gif")
    }
    val result = chooser.showOpenDialog(null)
    if (result == JFileChooser.APPROVE_OPTION) {
        val file: File = chooser.selectedFile
        filePath = file.absolutePath
        val uri = file.toURI()

        onFilePicked("file://${uri}")
    }else if (result == JFileChooser.CANCEL_OPTION)
    {
        onFilePicked(null)

    }

}