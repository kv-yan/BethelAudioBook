package ru.bethel.domain.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadHelper {

    fun downloadFile(viewModelScope: CoroutineScope, url: String, file: File, onDownloadComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val urlConnection = URL(url).openConnection() as HttpURLConnection
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val outputStream = FileOutputStream(file)

                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.close()
                inputStream.close()
                urlConnection.disconnect()

                withContext(Dispatchers.Main) {
                    onDownloadComplete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
