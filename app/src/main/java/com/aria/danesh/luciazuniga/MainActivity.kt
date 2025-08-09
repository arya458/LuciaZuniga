package com.aria.danesh.luciazuniga

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.aria.danesh.luciazuniga.ui.theme.LuciaZunigaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuciaZunigaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Call the WebViewScreen Composable here
                    WebViewScreen(
                        url = "https://www.google.com", // Replace with your desired URL
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WebViewScreen(url: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            // Create the WebView and apply configurations
            WebView(context).apply {
                // Configure WebSettings for full functionality
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true

                // This setting allows media to play without a user gesture
                settings.mediaPlaybackRequiresUserGesture = false

                // Set a WebViewClient to handle page navigation within the WebView
                webViewClient = WebViewClient()

                // Set a WebChromeClient to handle UI events like progress and sound
                webChromeClient = WebChromeClient()
            }
        },
        update = { webView ->
            // Load the specified URL
            webView.loadUrl(url)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    LuciaZunigaTheme {
        // You cannot preview a WebView directly, so this will show an empty container.
        // It's still useful for checking your layout structure.
        WebViewScreen(url = "about:blank")
    }
}