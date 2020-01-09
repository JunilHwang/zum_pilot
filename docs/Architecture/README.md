# 프로젝트 아키텍쳐

User, Client, Server 그리고 Open API 각각의 구조와 서로간의 관계를 표현합니다.

## 1. Simple Service Structure

해당 프로젝트는 `Single Page Appliction` + `REST API` 형태로 서비스됩니다.

@startuml
:User: 
agent "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/chrome-logo.png?token=AEPBNAI4B4SHNDMM65KH7VC6D2G7U{scale=0.1}> Browser" as Browser
skinparam defaultTextAlignment left
rectangle "주제별 영상 제공 웹 서비스" as Service {
  rectangle "\nClient" as client #eef {
    agent "Vue.js" as vue #fff
    agent "Http API" as httpApi #fff
  }
  rectangle "\n\tServer" as Server #fffccc {
    agent Controller #fff
    collections RestController #fff
    database "H2 Database" as db #fff
  }
}

cloud "Open API Service" as Network

User o-o Browser
Browser <<- vue
Browser o-o Controller
httpApi o--o RestController
vue <<- httpApi
RestController <<- db
RestController o--o Network
@enduml

## 2. Client Structure

Front-end는 `Vue.js`를 이용하여 `Single Page Application`으로 만들었습니다. 

@startuml
:User:
agent "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/chrome-logo.png?token=AEPBNAI4B4SHNDMM65KH7VC6D2G7U{scale=0.1}> Browser" as Browser
rectangle Client {
  rectangle "<img:https://joshua1988.github.io/images/posts/web/vuejs/logo.png{scale=0.07}> Vue.js" as Vue #e3ece0 {
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

rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}>\n\t Web Server" as Server #fffddd  {
  agent Controller #eef
  collections RestController #eef
}

User o-o Browser
Browser o--o Controller
RestController o-o httpApi
Browser <<- VueApp 

VueApp <<- middle
middle <<- VueStore
middle <<-- VueRouter
VueStore <<- modules
modules <<- module

State <<-- Mutations
Mutations <<-- Actions
httpApi ->> Actions

util ->> Components
Components ->> VueRouter  
VueApp <<-- Components


@enduml

## 3. Server Structure

Back-end는 `SpringBoot`로 `웹 서버를 구축`하고 `REST API`를 만들었습니다.

DB 구축은 `H2`와 `JPA`를 사용하였습니다.

@startuml

:User:
rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server" as Server #fffddd {
  agent "Spring Data JPA" as JPA #eef
  agent "Spring Security" as Security #eef
  agent Controller #eef
  collections RestController #eef
  collections Service #fff
  database "H2 Database" as db #fff
  rectangle Helper #fff {
    rectangle "Youtube Search API" as YSA #fee
    collections Crawler
  }
  collections "<color #fff>DTO" as DTO #666
  collections "<color #fff>VO" as VO #666
  collections "<color #fff>Entity" as Entity #666
  collections Repository #fff
}


rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/chrome-logo.png?token=AEPBNAI4B4SHNDMM65KH7VC6D2G7U{scale=0.1}> Browser" as Browser
rectangle Client {
  rectangle "<img:https://joshua1988.github.io/images/posts/web/vuejs/logo.png{scale=0.07}> Vue.js" as vue #e3ece0
  rectangle "Http API" as httpApi
}

cloud Network {
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/youtube-logo.png?token=AEPBNAJOKSYDRHHDL56DAGS6DVGKW{scale=0.4}>" as Youtube #fff
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/melon-logo.png?token=AEPBNALKPDB74C5PXGLPLAS6DVGHW{scale=0.5}>" as Melon #fff
  card "<img:http://billboard.co.kr/images/main2/logo.png{scale=0.5}>" as Billboard #000
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/sbs-logo.png?token=AEPBNALHCYKNRJQKZUX4E6C6DVGJU{scale=0.3}>" as SBS #fff
}

User o-o Browser
Browser o-o Controller
Browser <<-- vue
vue <<- httpApi
httpApi o-o RestController

Controller --[hidden] RestController
Controller -[hidden] Security
Security -->> RestController
Security -->> Service
RestController <<- Service
RestController <<-- VO
VO --[hidden] DTO
Service <<-- Repository
Repository <<-- Entity
Repository <<-- DTO
Repository <<-- db
Repository --|> JPA
DTO -[hidden] Entity
db --[hidden] JPA

Service <<- Helper
Helper <<-- Entity
  
Crawler --[hidden] YSA
Crawler <<- Melon
Crawler <<- SBS
Crawler <<- Billboard
YSA <<- Youtube

Melon --[hidden] SBS    
SBS --[hidden] Billboard    
Billboard --[hidden] Youtube    

@enduml

## 4. Detail Service Structure

앞서 보여드린 Structure들을 종합하면 다음과 같습니다.


@startuml

:User:
rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/chrome-logo.png?token=AEPBNAI4B4SHNDMM65KH7VC6D2G7U{scale=0.1}> Browser" as Browser

rectangle "주제별 영상 제공 웹 서비스" as WebService {
  rectangle Client {
    rectangle "<img:https://joshua1988.github.io/images/posts/web/vuejs/logo.png{scale=0.07}> Vue.js" as Vue #e3ece0 {
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
  
  rectangle "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/spring-boot-logo.png?token=AEPBNAMZ5S57U44JHVAOFVC6DU65K{scale=0.7}> Web Server" as Server #fffddd {
    agent "Spring\nData JPA" as JPA #eef
    agent "Spring\nSecurity" as Security #eef
    collections "<color #fff>VO" as VO #666
    agent Controller #eef
    collections RestController #eef
    collections Service #fff
    database "H2 Database" as db #fff
    rectangle Helper #fff {
      rectangle "Youtube Search API" as YSA #fee
      collections Crawler
    }
    collections "<color #fff>DTO" as DTO #666
    collections "<color #fff>Entity" as Entity #666
    collections Repository #fff
  }
}

cloud Network {
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/youtube-logo.png?token=AEPBNAJOKSYDRHHDL56DAGS6DVGKW{scale=0.4}>" as Youtube #fff
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/melon-logo.png?token=AEPBNALKPDB74C5PXGLPLAS6DVGHW{scale=0.5}>" as Melon #fff
  card "<img:http://billboard.co.kr/images/main2/logo.png{scale=0.5}>" as Billboard #000
  card "<img:https://raw.githubusercontent.com/JunilHwang/zum_pilot/master/docs/.vuepress/public/img/sbs-logo.png?token=AEPBNALHCYKNRJQKZUX4E6C6DVGJU{scale=0.3}>" as SBS #fff
}

User o-o Browser
Browser <<-- VueApp 
Browser o-o Controller

VueApp <<- Components
Components -->> VueRouter  
VueApp <<-- middle
middle <<-- VueStore
middle <<- VueRouter
VueStore <<- modules  
module -->> modules 
Actions -->> Mutations
Mutations -->> State

util -->> Components
util -[hidden] httpApi
httpApi -->> Actions

httpApi o--o RestController
Controller --[hidden] VO
VO -- RestController
RestController <<-- Service
RestController <<- Security
Security -->> Service
Service <<- Repository
Controller -[hidden] JPA
JPA --[hidden] db 
Entity -[hidden] DTO
Repository <<-- Entity 
Repository <<-- DTO 
JPA <|-- Repository
Repository <<- db  
Service <<-- Helper
Entity -->> Helper

Crawler -[hidden] YSA
Crawler <<---- Melon
Crawler <<---- Billboard
Crawler <<---- SBS
YSA <<---- Youtube

@enduml

