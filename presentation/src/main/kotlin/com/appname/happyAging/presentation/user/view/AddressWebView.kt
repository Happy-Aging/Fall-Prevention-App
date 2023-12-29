package com.appname.happyAging.presentation.user.view

import android.graphics.Bitmap
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun AddressWebView(
    onSelected: (String) -> Unit,
){
    val webViewState =
        rememberWebViewState(
            url = "https://happy-aging.github.io/test_addr.html",
            additionalHttpHeaders = emptyMap()
        )

    val webViewClient = object :AccompanistWebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("onCreated", "onPageFinished !! $url")
            view?.evaluateJavascript("javascript:runDaumPostcode();") {
                Log.d("onCreated", "evaluateJavascript !! $it")
            }
        }
    }

    WebView(
        state = webViewState,
        client = webViewClient,
//        chromeClient = webChromeClient,
        onCreated = {webView ->
            with(webView){
                settings.run {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    mixedContentMode= WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                addJavascriptInterface(
                    object {
                        @android.webkit.JavascriptInterface
                        fun processDATA(address: String) = onSelected(address)
                    },
                    "AndroidApp"
                )
            }

        },
    )
}
