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

## 2. REST API 설계