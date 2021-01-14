# DynamicLink

------------

## 이미지

##### firebase로 생성한 동적 링크

<img src="https://user-images.githubusercontent.com/57051773/104540132-37547e00-5662-11eb-8cd7-32a71a3eaa62.jpg" width="60%">

##### 동적 링크 클릭 및 앱 실행(쿠폰 코드 입력됨)
<img src="https://user-images.githubusercontent.com/57051773/104538749-cdd37000-565f-11eb-82bc-b4b38593631e.jpg" width="30%">  <img src="https://user-images.githubusercontent.com/57051773/104540051-14c26500-5662-11eb-96fc-107f7cc79a10.JPG" width="30%">


------------
### 코드  
##### AndroidManifest.xml  
```xml
        <activity android:name=".DynamicLinkActivity">
            <!-- 앱링크를 사용한 동적링크 처리 intent filter -->
            <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data
                    android:scheme="http" />
                <!-- 동적링크 도메인-->
                <data
                    android:host="xfile6912.page.link"
                    android:scheme="https" />
            </intent-filter>
            <!-- deep link에 대한 intent filter -->
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data
                    android:scheme="http" />
                <!-- 딥링크 -->
                <data
                    android:host="www.focusly.shop"
                    android:scheme="https" />
            </intent-filter>
        </activity>
```  
##### DynamicLinkActivity -> dealDynamicLink function  
```
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
```

> //Handle deep link//안에 원하는 코드 삽입.

