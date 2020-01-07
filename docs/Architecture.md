# 프로젝트 아키텍쳐

User, Client, Server 그리고 Open API 각각의 구조와 서로간의 관계를 표현합니다.

## 1. Service Structure

해당 프로젝트는 `Single Page Appliction` + `REST API` 형태로 서비스됩니다.



## 2. Client

Front-end는 `Vue.js`를 이용하여 `Single Page Application`으로 만들었습니다. 

@startuml
:User:
rectangle Client {
  agent "<img:https://t1.daumcdn.net/cfile/tistory/2445564C58196C010B{scale=0.1}> Browser" as Browser
  rectangle "<img:https://joshua1988.github.io/images/posts/web/vuejs/logo.png{scale=0.07}> VueFramework" as Vue #e3ece0 {
    rectangle VueRouter
    collections Components
    rectangle VueStore #fff {
      rectangle State
      rectangle Mutations
      rectangle Actions
    }
  }
}

rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server" as Server #fffddd  {
  agent Controller #fff
  collections RestController #fff
}

User o--o Browser
Browser -->> VueRouter
VueRouter ->> Components
Components <<- VueStore
Vue -->> Browser
Browser o--o Components
State <<- Mutations
Mutations <<- Actions
RestController o---o Actions 
Controller o--o Browser 
@enduml

## 3. Server

Back-end는 `SpringBoot`로 `웹 서버를 구축`하고 `REST API`를 만들었습니다.

DB 구축은 `H2`와 `JPA`를 사용하였습니다.

@startuml
rectangle "\t\t\t\t<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server" #fffddd {
  card "Spring Data JPA" as JPA #fff
  agent Controller #fff
  collections RestController #fff
  collections Service #fff
  database H2 #fff
  
  rectangle Helper #fff {
    rectangle "Youtube Search API" as YSA
    package CrawlerPackage {
      rectangle MusicCrawler
      rectangle NewsCrawler
      rectangle Crawler
    }
  }
  
  rectangle Domain #fff {
    collections "<color #fff>DTO" as DTO #666
    collections "<color #fff>VO" as VO #666
    rectangle DAO {
      collections "<color #fff>Entity" as Entity #666
      collections Repository #fff
    }
  }
}

rectangle Client {
  rectangle ClientAPI
  rectangle "<img:https://t1.daumcdn.net/cfile/tistory/2445564C58196C010B{scale=0.1}> Browser" as Browser
}

rectangle Network {
  card Youtube
  gcard "<img:https://cdnimg.melon.co.kr/resource/image/web/common/logo_melon142x99.png>" as Melon #fff
  card "<img:http://billboard.co.kr/images/main2/logo.png{scale=0.5}>" as Billboard #000
  card "SBS K-POP" as SBS
}

:User:

User o-o Browser
Browser o--o Controller
ClientAPI o--o RestController
Browser <- ClientAPI 

RestController <<-- Service
RestController <-- VO

Service <<- Domain
Service <<--- Helper

Repository o--o H2
DTO -> Repository
Repository <- Entity
Domain -> Helper

YSA <<-- Youtube
NewsCrawler <<--- SBS
NewsCrawler <<--- Billboard
MusicCrawler <<--- Melon

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler
Repository --|> JPA

@enduml