package com.xfile6912.dynamiclink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dynamic_link.*

class DynamicLinkActivity : AppCompatActivity() {

    companion object{
        const val TYPE_COUPON = "coupon"
        const val CODE = "code"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_link)

        dealDynamicLink()//dynamic link를 handle하는 함수.

    }

    fun dealDynamicLink(){
        Firebase.dynamicLinks
            .getDynamicLink(intent)//intent로부터 dynamic link 받아옴.
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link //deep link 추출
                }
                ////////////// Handle deep link.///////////////////////////////////////////////////////////////////
                if (deepLink != null) {
                    val type = deepLink.lastPathSegment//deep link로부터 정보 추출.

                    when(type){
                        TYPE_COUPON-> {//coupon 코드인 경우
                            val code=deepLink.getQueryParameter(CODE)
                            coupon_text.setText(code)
                        }
                    }
                }
                ////////////// Handle deep link.///////////////////////////////////////////////////////////////////
            }
            .addOnFailureListener(this) {  e ->
                Log.w("DynamicLink", "getDynamicLink:onFailure", e)
            }
    }
}