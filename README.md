## SocialLogin
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

페이스북, 네이버, 카카오, 라인, 트위터, 구글 총 6개에 대한 빠른 소셜 로그인 통합 기능을 제공합니다.

## 사용 방법

*rootProject/build.gradle*
```	
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

*app/build.gradle*
```
dependencies {
    implementation 'com.github.WindSekirun:SocialLogin:1.0.0'
}
```

## 사용 가이드

### 공통
각각 서비스마다 공통적인 구조를 가지고 있어 복사 & 붙여넣기 만으로도 쉽게 사용이 가능합니다.

#### 사용하고자 하는 서비스의 로그인 클래스 변수 선언
```Java
private KakaoLogin kakaoModule;
```

#### 모듈 인스턴스 생성
```Java
kakaoModule = new KakaoLogin(this, new OnResponseListener() {
    @Override
    public void onResult(SocialType socialType, ResultType resultType, Map<UserInfoType, String> map) {

    }
});
```

#### 로그인
```Java
kakaoModule.onLogin();
```

#### onDestroy 일 때 실행
```Java
kakaoModule.onDestroy();
```

#### onActivityResult를 상속받아서 실행
```Java
kakaoModule.onActivityResult(requestCode, resultCode, data);
```

#### 각 Enum 설명
```Java
public enum SocialType { // 소셜 서비스 타입
    KAKAO, GOOGLE, FACEBOOK, LINE, NAVER, TWITTER;
}

public enum ResultType { // 성공, 실패, 취소
    SUCCESS, FAILURE, CANCEL;
}

public enum UserInfoType { // 유저 정보 필드 (전부 다 나오는 것은 아님)
    ID, NAME, ACCESS_TOKEN, EMAIL, NICKNAME, PROFILE_PICTRUE, GENDER;
}
```

### 카카오 로그인

### Dependencies 추가

```
repositories {
    maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
}
```

```
  implementation 'com.kakao.sdk:usermgmt:1.5.1'
```

#### AndroidManifest.xml 에 API 키 추가
```XML
 <meta-data
            android:name="com.kakao.sdk.AppKey"
            android:value="<YOUR-API-KEY>"/>
```

#### Application 내부에 추가

```Java
List<SocialLoginType> typeList = new ArrayList<>();
KakaoConfig kakaoConfig = new KakaoConfig.Builder()
                .setRequireEmail()
                .setRequireNickname()
                .build();

typeList.add(new SocialLoginType(SocialType.KAKAO, kakaoConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private KakaoLogin kakaoModule;
```


### 페이스북 로그인

#### Dependencies 추가
```
   implementation 'com.facebook.android:facebook-android-sdk:4.23.0'
```

#### AndroidManifest.xml 에 액티비티 추가
```Java
<activity
            android:name="com.facebook.FacebookActivity"
            android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
            android:label="@string/app_name" />
```

#### Application 내부에 추가
```Java
List<SocialLoginType> typeList = new ArrayList<>();
FacebookConfig facebookConfig = new FacebookConfig.Builder()
                .setApplicationId("<YOUR-API-KEY>")
                .setRequireEmail()
                .build();

typeList.add(new SocialLoginType(SocialType.FACEBOOK, facebookConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private FacebookLogin facebookModule;
```

### 네이버 로그인

### 파일 다운로드
[Library Jar 다운로드](https://github.com/WindSekirun/SocialLogin/raw/master/naver_login_library_4.1.4.jar)

위 파일을 다운받은 뒤 libs 폴더에 넣어주세요.

```
implementation files('libs/naver_login_library_4.1.4.jar')
```

#### Application 내부에 추가
```Java
List<SocialLoginType> typeList = new ArrayList<>();
NaverConfig naverConfig = new NaverConfig.Builder()
                .setAuthClientId("<YOUR-API-KEY>")
                .setAuthClientSecret("<YOUR-API-KEY>")
                .setClientName(getString(R.string.app_name))
                .build();
                
typeList.add(new SocialLoginType(SocialType.NAVER, naverConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private NaverLogin naverModule;
```

### 라인 로그인

### 파일 다운로드
[Library Jar 다운로드](https://github.com/WindSekirun/SocialLogin/raw/master/line-sdk-4.0.5.aar)

위 파일을 다운받은 뒤 libs 폴더에 넣어주세요.

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

```
implementation(name: 'line-sdk-4.0.5', ext: 'aar')
```

#### Application 내부에 추가
```Java
List<SocialLoginType> typeList = new ArrayList<>();
LineConfig lineConfig = new LineConfig.Builder()
                .setChannelId("<YOUR-API-KEY>")
                .build();

typeList.add(new SocialLoginType(SocialType.LINE, lineConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private LineLogin lineModule;
```

### 트위터 로그인

#### Dependencies 추가
```
    implementation 'com.twitter.sdk.android:twitter:3.1.0'
```

#### Application 내부에 추가
```Java
List<SocialLoginType> typeList = new ArrayList<>();
TwitterConfig twitterConfig = new TwitterConfig.Builder()
                .setConsumerKey("<YOUR-API-KEY>")
                .setConsumerSecret("<YOUR-API-KEY>")
                .build();

typeList.add(new SocialLoginType(SocialType.TWITTER, twitterConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private TwitterLogin twitterModule;
```

### 구글 로그인

#### 선 작업
[Google Sign-in for Android](https://developers.google.com/identity/sign-in/android/start) 의 2번 Get a configuration file 를 참조하여 google-services.json 을 모듈 내에 포함시켜야 합니다.

#### Dependencies 추가
```
    implementation 'com.google.android.gms:play-services-auth:10.2.6'
```

#### Application 내부에 추가
```Java
List<SocialLoginType> typeList = new ArrayList<>();
GoogleConfig googleConfig = new GoogleConfig.Builder()
                .setRequireEmail()
                .build();

typeList.add(new SocialLoginType(SocialType.GOOGLE, googleConfig));
SocialLogin.init(this, typeList);
```

#### 액티비티에서 사용
```Java
private GoogleLogin googleModule;
```

## 라이센스
```
Copyright 2017 WindSekirun (DongGil, Seo)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
