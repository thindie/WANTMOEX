package com.example.thindie.wantmoex.route

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.startActivity

/**
 *   Запускем из ComponentActivity T требуемую ComponentActivity R
 */
inline fun <reified T : ComponentActivity, reified R : ComponentActivity> beginTransition(context: Context) {

    when (context) {
        is T -> {
            startActivity(context, Intent(context, R::class.java), null)

        }
        is R -> {
            startActivity(context, Intent(context, T::class.java), null)

        }
    }
}


fun actionShare(uri: String, title: String, contentUri: String, context: Context) {
    val share = Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, Uri.parse(uri))
        putExtra(Intent.EXTRA_TITLE, title)
        data = Uri.parse(contentUri)
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }, null)
    startActivity(context, share, null)
}

val actionGoBrowse: (String, Context) -> Unit = { news, context ->
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(news)
    )
    startActivity(context, urlIntent, null)
}