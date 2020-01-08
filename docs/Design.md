# 프로젝트 설계

## 1. DB 설계

Database는 `H2`를 사용하였습니다.

@startuml

  object user #fff {
    idx bigint <b>primary
    id varchar(255) <b>unique
    name varchar(255)
    pw varchar(255)
  }
  object video #fff {
    idx bigint <b>primary
    search_title varchar(255)
    thumbnail varchar(255)
    title varchar(255)
    video_id varchar(255)
    view_count int
  }


object user_bookmark #fff {
  user_idx bigint <b> primary
  bookmark_idx bigint <b> primary
}
object video_like #fff {
  user_idx int <b>primary
  video_idx int <b>primary
}

user ---> "n \n" video_like 
user ---> "n\t\n" user_bookmark
video ---> "n\n" video_like 
video ---> "n  \n" user_bookmark
video_like -[hidden] user_bookmark
@enduml

## 2. REST API 설계