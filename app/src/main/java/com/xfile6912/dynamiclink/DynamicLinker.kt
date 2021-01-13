package com.xfile6912.dynamiclink

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase

object DynamicLinker {
    //context: packagename을 얻기 위해서
    //version -> 링크를 통해 열수있는 앱의 최소 버전
    fun dynamicLinkCreator(context: Context, deepLink: String, prefix: String, version: Int): Uri {
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse(deepLink)
            domainUriPrefix = prefix
            // Open links with this app on Android
            androidParameters(context.packageName) {
                minimumVersion = version
            }
        }
        return dynamicLink.uri
    }

    fun dynamicLinkCreator(context: Context, deepLink: String, prefix: String): Uri {
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse(deepLink)
            domainUriPrefix = prefix
            // Open links with this app on Android
            androidParameters(context.packageName) {
            }
        }
        return dynamicLink.uri
    }

    //for customizing(필요없는 부분 제거 및, 추가하여 동적링크 생성하는 함수)
    /*
    fun dynamicLinkCreator(context: Context): Uri {
        val dynamicLink =
            Firebase.dynamicLinks.dynamicLink { // or Firebase.dynamicLinks.shortLinkAsync
                link = Uri.parse("deepLink")
                domainUriPrefix = "prefix"
                androidParameters(context.packageName) {
                    fallbackUrl =Uri.parse("") //앱이 설치되지 않은 경우에 열리는 링크
                    minimumVersion = 0//링크를 열수 있는 앱의 최소버전
                }
                iosParameters("com.example.ios") {
                    appStoreId = ""//app store id
                        customScheme =""//
                        minimumVersion ="" //링크를 열수 있는 앱의 최소버전
                        ipadBundleId =""
                        ipadFallbackUrl =Uri.parse("")

                }
                googleAnalyticsParameters {
                    source = ""
                    medium = ""
                    campaign = ""
                    term = ""
                    content = ""
                }
                itunesConnectAnalyticsParameters {
                    providerToken = ""
                    campaignToken = ""
                    affiliateToken = ""
                }
                socialMetaTagParameters {
                    title = ""//	소셜 게시물에서 동적 링크를 공유할 때 사용되는 제목
                    description = ""//소셜 게시물에서 동적 링크를 공유할 때 사용되는 설명
                    imageUrl = Uri.parse("")//링크와 관련된 이미지의 URL
                }
            }
     */

}