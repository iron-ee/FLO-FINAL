# FLO 클론코딩 팀 프로젝트


## 공용 


### 데이터베이스

```DB 설정
create user 'flo'@'%' identified by 'flo1234';
GRANT ALL PRIVILEGES ON *.* TO 'flo'@'%';
create database flo;
```

### 예외처리

![image](https://user-images.githubusercontent.com/65489223/114042129-1d70b300-98c0-11eb-9f7d-005afc1c6ef1.png)


### Error Log Batch 

![image](https://user-images.githubusercontent.com/65489223/114063782-edcba600-98d3-11eb-8720-b4f3417992ff.png)
![image](https://user-images.githubusercontent.com/65489223/114053700-26ff1880-98ca-11eb-96c4-d7da3fb0e3ca.png)




## FLO 앱 서버 (클론 코딩)


### 설명

자체적으로 DB를 구축하여 만든 FLO앱 전용 스프링부트 RESTAPI서버


### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- Spring Validation
- Spring AOP
- RestDoc


#### 추가 의존성

``` 추가 의존성
		<dependency>
			<groupId>org.springframework.restdocs</groupId>
			<artifactId>spring-restdocs-mockmvc</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.restdocs</groupId>
			<artifactId>spring-restdocs-asciidoctor</artifactId>
			<version>2.0.2.RELEASE</version>
		</dependency>
```



### yml.설정


```yml.설정
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/flo?serverTimezone=Asia/Seoul
    username: flo
    password: flo1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
#    show-sql: true
#    properties:
#      hibernate.format_sql: true
  jackson:
    serialization:
      fail-on-empty-beans: false
```


### 모델

- Song
- PlaySong
- Reply
- Storage
- StorageSong
- User
- ErrorLog


### 시큐리티 설정

![image](https://user-images.githubusercontent.com/65489223/114041529-96233f80-98bf-11eb-8775-b911425feb5d.png)



### AOP Validation 처리

![image](https://user-images.githubusercontent.com/65489223/114041570-9f141100-98bf-11eb-8f93-cb4c0e54eb11.png)


### 단위 테스트


![image](https://user-images.githubusercontent.com/65489223/114051867-72182c00-98c8-11eb-9129-259aef9914e1.png)


### API 문서

![image](https://user-images.githubusercontent.com/65489223/114053017-86105d80-98c9-11eb-9f75-aae67abc95a7.png)








## FLO 웹 서버 (클론 코딩)


### 설명

FLO웹 전용 스프링부트 RESTAPI서버 (관리자 페이지 포함)


### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- Oauth2-client


#### 추가 의존성

``` 추가 의존성
	<!-- 추가 라이브러리 -->
		<dependency>
		  <groupId>org.springframework.security</groupId>
		  <artifactId>spring-security-taglibs</artifactId>
		</dependency>
		<!-- JSP 템플릿 엔진 -->
		<dependency>
		  <groupId>org.apache.tomcat</groupId>
		  <artifactId>tomcat-jasper</artifactId>
		  <version>9.0.41</version>
		</dependency>
		<!-- JSTL -->
		<dependency>
		  <groupId>javax.servlet</groupId>
		  <artifactId>jstl</artifactId>
		  <version>1.2</version>
		</dependency>
```



### yml.설정


```yml.설정
server:
  port: 8000
  servlet:
    context-path: /
    encoding:
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/ # View Resolver 설정
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/flo?serverTimezone=Asia/Seoul
    username: flo
    password: flo1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl    
    show-sql: true
    properties:
      hibernate.format_sql: true
  
  servlet:
    multipart:
      max-file-size: 50MB       # 파일 업로드시 최대용량 설정방법
      max-request-size: 50MB  
        
  jackson:
    serialization:
      fail-on-empty-beans: false
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: 59695039153-t9tuic1fsa8fcppo4lsr5ghkjhrquue6.apps.googleusercontent.com
            client-secret: zx8s1NVJA2XVcaZ7sWBD8ylv
            scope:
            - email
            - profile
            
          facebook:
            client-id: 270559447858270
            client-secret: dd920c04175f6b8b1789f53f9cf5d84e
            scope:
            - email
            - public_profile
            
          naver:
            client-id: B2Dex08_jQFmoLYVjogp
            client-secret: BVdLGLSpy3
            scope:
            - id
            - email
            - name
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/naver
            client-name: Naver
          kakao:
            client-id: de2d1a6b581f4326574b8bef2aa50358
            client-secret: 078aa57f5836945a3be9af8e64920b45
            scope:
            - account_email
            - profile
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/kakao
            client-name: Kakao
            client-authentication-method: POST
        provider:
          naver:
            authorization-uri: https://nid.naver.com/oauth2.0/authorize
            token-uri: https://nid.naver.com/oauth2.0/token
            user-info-uri: https://openapi.naver.com/v1/nid/me
            user-name-attribute: response
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize
            token-uri: https://kauth.kakao.com/oauth/token
            user-info-uri: https://kapi.kakao.com/v2/user/me
            user-name-attribute: id
            
```


### 모델

- Song
- PlaySong
- Reply
- Video
- User
- ErrorLog

##### 연관관계
![image](https://user-images.githubusercontent.com/65489223/114064344-7fd3ae80-98d4-11eb-85b5-f2beecec0d0d.png)


### 시큐리티 설정

![image](https://user-images.githubusercontent.com/65489223/114063644-cecd1400-98d3-11eb-9f51-a1df901ad223.png)



### 화면 구성

![image](https://user-images.githubusercontent.com/65489223/114064497-adb8f300-98d4-11eb-96b7-0ccaca01b3c2.png)
![image](https://user-images.githubusercontent.com/65489223/114064536-b7425b00-98d4-11eb-9705-e659eed12637.png)
![image](https://user-images.githubusercontent.com/65489223/114064939-186a2e80-98d5-11eb-9190-5567bee77914.png)
![image](https://user-images.githubusercontent.com/65489223/114064968-215b0000-98d5-11eb-85a3-2870d4ace898.png)





## FLO 앱(클론 코딩)


### 화면구성

바인딩 서비스를 이용한 간단한 음악스트리밍어플

![image](https://user-images.githubusercontent.com/65489223/114034526-39248b00-98b9-11eb-9dac-d0d79425b0ae.png)
![image](https://user-images.githubusercontent.com/65489223/114035629-3c6c4680-98ba-11eb-8e2b-fff8fac5e43b.png)
![image](https://user-images.githubusercontent.com/65489223/114035669-47bf7200-98ba-11eb-80c3-e988b36856a2.png)
![image](https://user-images.githubusercontent.com/65489223/114036122-c0263300-98ba-11eb-80c0-2cff909c1b05.png)
![image](https://user-images.githubusercontent.com/65489223/114036167-cd432200-98ba-11eb-8ca0-9f55a84480d1.png)
![image](https://user-images.githubusercontent.com/65489223/114036233-dcc26b00-98ba-11eb-9754-7075da961e15.png)
![image](https://user-images.githubusercontent.com/65489223/114036270-e51aa600-98ba-11eb-9ae9-25f9bcaeee8c.png)
![image](https://user-images.githubusercontent.com/65489223/114036364-f9f73980-98ba-11eb-8096-794ddab76c7a.png)
![image](https://user-images.githubusercontent.com/65489223/114036332-f2d02b80-98ba-11eb-94cd-f67f4ccb6a33.png)
![image](https://user-images.githubusercontent.com/65489223/114036459-0e3b3680-98bb-11eb-8130-b0d7cbcc5533.png)
![image](https://user-images.githubusercontent.com/65489223/114036563-23b06080-98bb-11eb-8fc2-0c92e41665f9.png)
![image](https://user-images.githubusercontent.com/65489223/114035570-2f4f5780-98ba-11eb-8d49-842ef0ab1ee0.png)




### 연동서버 

스프링부트 RESTAPI서버: [https://github.com/stella6767/FLOApp-API-SERVER]



### 의존성

``` 
dependencies {
    def lifecycle_version = "2.2.0";
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    compileOnly 'org.projectlombok:lombok:1.18.10'
    annotationProcessor 'org.projectlombok:lombok:1.18.10'
    implementation 'org.greenrobot:eventbus:3.2.0'
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.github.gmazzo:fontawesome:0.4'
    implementation 'com.makeramen:roundedimageview:2.3.0'
    implementation 'com.sothree.slidinguppanel:library:3.4.0'
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'gr.pantrif:easy-android-splash-screen:0.0.1'
    
    ----- 여기까지 추가 의존성 --------
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
}
```

### AndroidManifes.xml 설정


```AndroidManifes.xml 설정
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.kang.floapp">
    <uses-permission android:name="android.permission.INTERNET" />
    <application
        android:allowBackup="true"
        android:icon="@drawable/flo_logo"
        android:label="@string/app_name"
        android:roundIcon="@drawable/flo_logo"
        android:supportsRtl="true"
        android:theme="@style/Theme.FloApp"
        android:usesCleartextTraffic="true"
        android:windowSoftInputMode="adjustResize">
        <receiver
            android:name=".utils.notification.NotificationActionService"
            android:enabled="true"
            android:exported="true"></receiver>
        <activity android:name=".view.profile.ProfileUpdateActivity" />
        <activity android:name=".view.profile.ProfileActivity" />
        <activity android:name=".view.user.JoinActivity" />
        <activity
            android:name=".view.user.LoginActivity"
            android:windowSoftInputMode="adjustResize" />
        <service
            android:name=".utils.PlayService"
            android:enabled="true"
            android:exported="true" />
        <activity android:name=".view.main.MainActivity" />
        <activity android:name=".SplashActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```



### 모델

- Song
- Category
- PlaySong
- Storage
- StorageSong
- User


![image](https://user-images.githubusercontent.com/65489223/114033215-05953100-98b8-11eb-8e66-dd0f63d45302.png)

Reply는 웹 서버와 연동을 위해서 관계가 있을 뿐, 앱에서는 사용을 안함.



### MVVM 패턴

```
    //라이브 데이터
    private MutableLiveData<List<Song>> mtSongList;
    private MutableLiveData<List<PlaySong>> mtPlayList;
    private MutableLiveData<List<Song>> mtSearchSongList;
    private MutableLiveData<List<Storage>> mtStorageList;
    private MutableLiveData<List<StorageSong>> mtStorageSongList;
    private MutableLiveData<List<Song>> mtCategoryList;
    private MutableLiveData<PlayService.LocalBinder> serviceBinder 
```




### 블로그 주소

데이터 초기화 코드: <https://blog.naver.com/PostView.nhn?blogId=alsrb9434&logNo=222271625132&categoryNo=35&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=&from=postList&userTopListOpen=true&userTopListCount=5&userTopListManageOpen=false&userTopListCurrentPage=1&userTopListManageOpen=true>




### 동영상 주소

<https://youtu.be/OCn1L7xE48k>


### PPT 주소

<https://docs.google.com/presentation/d/1JSFeEw2i_ziZRKjfQY7CMwPdn1z68YpQd7ZU_uz6vmA/edit#slide=id.p9>


### 웹 서버 - 앱 서버 연동 
![image](https://user-images.githubusercontent.com/65489223/114067160-75ff7a80-98d7-11eb-8cce-0fc066ccd200.png)