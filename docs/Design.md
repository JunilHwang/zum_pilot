# 프로젝트 설계

## 1. DB 설계

Database는 `H2`를 사용하였습니다.

@startuml

entity user #fff {
  * idx bigint <b> <<primary>>
  --
  * id varchar(255) <b> <<unique>>
  name varchar(255)
  pw varchar(255)
}
entity video #fff {
  * idx bigint <b> <<primary>>
  --
  search_title varchar(255)
  thumbnail varchar(255)
  title varchar(255)
  video_id varchar(255)
  view_count int
}


entity user_bookmark #fff {
  * user_idx bigint <b> <<primary>>
  * bookmark_idx bigint <b> <<primary>>
  --
}
entity video_like #fff {
  * user_idx int <b> <<primary>>
  * video_idx int <b> <<primary>>
  --
}

user ||---|{ video_like 
user ||---|{ user_bookmark
video ||---|{ video_like 
video ||---|{ user_bookmark
video_like -[hidden] user_bookmark
@enduml

news와 관련된 데이터는 영구적으로 저장할 필요가 없기 때문에 테이블을 만들지 않았습니다.

대신 캐시에 저장하여 일시적으로 데이터를 유지합니다.

## 2. REST API 설계

``` html
/api 
  ├─ /news
  │   ├─ / <<Get>>     
  │   ├─ /headline <<Get>>
  │   └─ /content <<Get>>
  ├─ /music <<Get>>
  ├─ /video
  │   ├─ / <<Get>>
  │   ├─ /popular <<Get>>
  │   ├─ /like/{idx} <<Get>>
  │   ├─ /like <<Post>>
  │   ├─ /bookmark <<Post>>
  │   └─ /{idx} <<Patch>>
  └─ /user
      ├─ / <<Get>>
      ├─ /sign-in <<Post>>
      └─ /sign-up <<Post>>
```

