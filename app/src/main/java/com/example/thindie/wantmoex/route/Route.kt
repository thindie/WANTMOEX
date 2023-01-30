package com.example.thindie.wantmoex.route

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.thindie.wantmoex.R

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

    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, Uri.parse(contentUri))
        putExtra(Intent.EXTRA_TEXT, Uri.parse(uri))
        putExtra(Intent.EXTRA_TITLE, title)
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    val share = Intent.createChooser(intent, R.string.share.toString())
    startActivity(context, share, null)
}

val actionGoBrowse: (String, Context) -> Unit = { news, context ->
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(news)
    )
    startActivity(context, urlIntent, null)
}