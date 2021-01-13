package com.xfile6912.dynamiclink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }

                // Handle the deep link.
                if (deepLink != null) {//deeplink가 널이 아닌경우 이에 대하여 처리
                    val segments = deepLink?.pathSegments
                    for(i in segments!!)
                        text.text=i
                }
                // ...
            }
            .addOnFailureListener(this) { e -> Log.w("DynamicLink", "getDynamicLink:onFailure", e) }

    }
}