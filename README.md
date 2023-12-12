# ShopliveTask
Shoplive 코딩 과제 레포지토리 입니다.
# Report
## 보이는 화면을 다 구성했는가?
- Clean Architecture를 사용하여 각 모듈간 코드를 명확하게 구조화하고, 비즈니스 로직을 독립적인 계층으로 분리했습니다.
- 모듈화(Multi Module)를 통해서 각 모듈별 특정 기능, 역할을 분리했습니다.
- MVVM 패턴을 사용하여 UI 구현과 관련된 문제를 개선하고 UI 동작과 로직을 분리했습니다.
- 위 기술스택으로 요구사항에 있는 마블 character 검색 결과 tab과 마블 캐릭터 favorite 모음 tab을 구성했습니다.

![아키텍처](https://github.com/jiwon2724/ShopliveTask/assets/70135188/cf192105-363b-4b89-bd9c-2b200cf1d9e6)


## 마블 API를 사용했는가
- data 모듈 gradle에서 buildConfigField를 사용하여 마블 api의 BaseUrl, PublicKey, PrivateKey를 관리했습니다.
```kotlin
path : jiwondev.data.di

object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonConfig = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL) // BaseUrl 입력 하는 곳
            .addConverterFactory(jsonConfig.asConverterFactory("application/json".toMediaType()))
            .build()
    }
...
}
```

```kotlin
path : jiwondev.data.common
object Constant {
    val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

    fun getHash(): String {
        val hash = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".toByteArray() // PRIVATE_KEY, PUBLIC_KEY 입력 하는 곳
        val md = MessageDigest.getInstance("MD5").digest(hash)
        return md.joinToString("") { "%02x".format(it) }
    }
}
```
## UI가 무분별하게 다시 그리는 부분이 없는가


## favorite character 관리 point
- Android Jetpack Datastore를 사용하여 favorite에 등록한 카드들을 storage에 저장하여 관리했습니다.
- Kotlin Flow를 사용하여 비동기 방식으로 데이터를 저장했습니다.

## api 호출 조건을 충족했는가
- 검색 api의 무분별한 call을 막기위해 debounce기능을 구현했습니다.
- delay 일시중단 함수가 호출돼서 일시 중단 되어있는 경우, 검색 api가 호출된다면 해당 코루틴을 취소하여 이전에 호출한 api를 취소하는 로직을 구현했습니다.
- 검색어가 2글자 이상일 때 검색되도록 로직을 작성했고, 2글자 미만일 때 호출한 api는 cancel하는 로직을 작성했습니다.
![image](https://github.com/jiwon2724/ShopliveTask/assets/70135188/87228163-a5ae-4412-b5d2-eebb6e10432f)

## Branch 전략
![image](https://github.com/jiwon2724/ShopliveTask/assets/70135188/ee70c78f-42f1-49df-8b3b-2778ad7bf299)
- git-flow를 사용하여 프로젝트를 진행했습니다.
- 이슈와 PR을 1:1 관계로 flow를 진행했습니다.
- 이슈 Todo-List에 체크박스를 만들어 task 단위로 commit을 진행했습니다.
- Squash Merge로 git graph를 깔끔하게 관리했습니다.
### Branch 명
```
[작업 타입][이슈 넘버]-[작업 명]
ex). feature/#23-search
```

feat: 새로운 기능 추가
fix: 버그 수정
docs: 문서 수정
style: 코드 스타일 수정
refactor: 코드 구조 개선
test: 테스트 추가 또는 수정
chore: 기타 변경 사항
### Commit 컨벤션
|번호|작업타입|설명|
|-----|----------|---------------|
|1|BASE|파일 및 프로젝트에 필요한 기본 작업|
|2|CHORE|기타 변경 사항|
|3|FEATURE|새로운 기능 추가|
|4|REFACTOR|기존 기능 수정|
|5|UI| XML 레이아웃 구현|

## 사용 기술
|분야|기술|
|-----|----------|
|Tools|Android Studio - Flamingo|
|Launguage|Kotlin 1.8.20|
|UI|XML(100%)|
|비동기|Kotlin Coroutine(+Flow)|
|DI|Hilt|
|네트워크|Retrofit2, OkHttp, Kotlin Serialization|
|이미지|Coil|
|저장소|Datastore|
|gradle|Kotlin DSL, Version-Catalog(toml)|

