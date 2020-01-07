# 프로젝트 아키텍쳐

User, Client, Server 그리고 Open API 각각의 구조와 서로간의 관계를 표현합니다.

## 1. Simple Service Structure

해당 프로젝트는 `Single Page Appliction` + `REST API` 형태로 서비스됩니다.

@startuml
:User:
agent Browser
rectangle "주제별 영상 제공 웹 서비스" as Service {
  rectangle "\t\t\tClient" as client {
    agent "Vue.js" as vue
    agent "Http API" as clientApi
  }
  rectangle Server {
    agent Controller
    collections RestController
    database "H2 Database" as h2
  }
}

cloud Network

User o--o Browser
Browser o--o Controller
Browser o--o Network
vue -->> Browser 
vue <<- clientApi
clientApi o--o RestController
RestController o--o h2
RestController <<-- Network
@enduml

## 2. Client Structure

Front-end는 `Vue.js`를 이용하여 `Single Page Application`으로 만들었습니다. 

@startuml
:User:
agent "<img:https://t1.daumcdn.net/cfile/tistory/2445564C58196C010B{scale=0.1}> Browser" as Browser
rectangle Client {
  rectangle "<img:https://joshua1988.github.io/images/posts/web/vuejs/logo.png{scale=0.07}> Vue.js\t\t\t" as Vue #e3ece0 {
    agent VueApp
    collections Components
    rectangle "MiddleWare" as middle
    rectangle VueRouter
    rectangle VueStore
    collections modules
    rectangle ModuleContain as module #f5f5f5 {
      rectangle State
      rectangle Mutations
      rectangle Actions
    }
  }
  collections "Util Function" as util
  rectangle "Http Helper" as httpApi
}

rectangle "\t<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}>\n\t Web Server" as Server #fffddd  {
  agent Controller #fff
  collections RestController #fff
}

User -->> Browser
Browser -->> Controller
RestController o-o httpApi
Browser ->> VueApp 

VueApp <<- middle
middle <<- VueStore
middle <<-- VueRouter
VueStore <<- modules
modules <<- module

State <<-- Mutations
Mutations <<-- Actions
httpApi <<- Actions

util ->> Components
Components ->> VueRouter  
VueApp <<-- Components


@enduml

## 3. Server Structure

Back-end는 `SpringBoot`로 `웹 서버를 구축`하고 `REST API`를 만들었습니다.

DB 구축은 `H2`와 `JPA`를 사용하였습니다.

@startuml

rectangle "\t\t\t\t<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server" #fffddd {
  card "Spring Data JPA" as JPA #fff
  agent Controller #fff
  collections RestController #fff
  collections Service #fff
  database "H2 Database" as db #fff
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
  rectangle "Http API" as ClientApi
  rectangle "<img:https://t1.daumcdn.net/cfile/tistory/2445564C58196C010B{scale=0.1}> Browser" as Browser
}

cloud Network {
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/youtube-logo.png?token=AEPBNAJOKSYDRHHDL56DAGS6DVGKW{scale=0.4}>" as Youtube #fff
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/melon-logo.png?token=AEPBNALKPDB74C5PXGLPLAS6DVGHW{scale=0.5}>" as Melon #fff
  card "<img:http://billboard.co.kr/images/main2/logo.png{scale=0.5}>" as Billboard #000
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/sbs-logo.png?token=AEPBNALHCYKNRJQKZUX4E6C6DVGJU{scale=0.3}>" as SBS #fff
}

:User:

User o-o Browser
Browser o--o Controller
ClientApi o--o RestController
Browser <- ClientApi 

RestController <<-- Service
RestController <-- VO

Service <<- Domain
Service <<--- Helper

Repository o--o db
DTO -> Repository
Repository <- Entity
Domain -> Helper

MusicCrawler <<-- Melon
NewsCrawler <<- SBS
NewsCrawler <<- Billboard
YSA <<-- Youtube

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler
Repository --|> JPA

@enduml

## 4. Detail Service Structure

앞서 보여드린 Structure들을 종합하면 다음과 같습니다.

@startuml

:User:

rectangle "주제별 영상 제공 웹 서비스" {
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

  rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server\t\t\t\t\t\t\t\t\t\t" #fffddd {
    card "Spring Data JPA" as JPA #fff
    agent Controller #fff
    collections RestController #fff
    collections Service #fff
    database "H2 Database" as db #fff
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
}
 
cloud Network {
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/youtube-logo.png?token=AEPBNAJOKSYDRHHDL56DAGS6DVGKW{scale=0.4}>" as Youtube #fff
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/melon-logo.png?token=AEPBNALKPDB74C5PXGLPLAS6DVGHW{scale=0.5}>" as Melon #fff
  card "<img:http://billboard.co.kr/images/main2/logo.png{scale=0.5}>" as Billboard #000
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/sbs-logo.png?token=AEPBNALHCYKNRJQKZUX4E6C6DVGJU{scale=0.3}>" as SBS #fff
}

RestController <<-- Service
RestController <-- VO

Service <<- Domain
Service <<--- Helper

Repository o--o db
DTO -> Repository
Repository <- Entity
Domain ->> Helper

MusicCrawler <<-- Melon
NewsCrawler <<- SBS
NewsCrawler <<- Billboard
YSA <<-- Youtube

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler
Repository --|> JPA

Browser -->> VueRouter
Components <<- VueRouter 
Components <<-- VueStore
Vue -->> Browser
Browser o--o Components
State <<- Mutations
Mutations <<- Actions

Actions o--o RestController
Browser o--o Controller
User o-o Browser

@enduml

