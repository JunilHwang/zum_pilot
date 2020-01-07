# 프로젝트 아키텍쳐

User, Client, Server 그리고 Open API 각각의 구조와 서로간의 관계를 표현합니다.

## 1. Client

`User` `Browser` `Vue Framework` 등을 포함하고 있습니다.

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

rectangle Server {
  agent Controller
  collections RestController
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

## 2. Server

@startuml
rectangle "SpringBoot Web Server" {
  rectangle Helper {
    rectangle YoutubeSearchAPI as YSA
    package CrawlerPackage {
      rectangle MusicCrawler
      rectangle NewsCrawler
      rectangle Crawler
    }
  }
  rectangle Domain {
    collections DTO
    collections VO
    collections Entity
  }
  collections Service
  collections Repository
  collections RestController
  agent Controller
  database H2
}

rectangle Client {
  rectangle ClientAPI
  rectangle Browser
}

:User:

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler
RestController <<-- Service
Service <<-- Repository
Repository <<-->> H2
Service <<- Helper 
Repository <<-- Domain  
Helper <<- Domain
Browser o--o Controller
ClientAPI o--o RestController
ClientAPI -> Browser
User o-o Browser

@enduml

## 3. Service

## 4. Relationship

@startuml

:User:

rectangle "주제별 영상 제공 서비스" as Platform {
  node "Client" {
    agent Browser
    node "VueFramework" {
      (VueRouter)
      [Components]
      node "VueStore" {
        [State]
        [Mutations]
        [Actions]
      }
    }
  }
  Node "Server" {
    package "CrawlerPacakge" {
      node "MusicCrawler"
      node "NewsCrawler"
      node "Crawler" 
    }
    node "Youtube Search API" as YSA
    node "REST API" as REST {
      node Service
      node Repository
      node RestController
    }
    database H2
  }
}

Node Network {
  node "멜론 차트" as nM
  node "빌보드 코리아" as nB
  node "SBS K-POP" as nS
  node "Youtube" as nY
}

:User: --> Browser
Browser --> VueRouter
Browser -> Components : Event
VueRouter --> Components
VueStore --> Components
State <- Mutations
State <-- Actions
Mutations <- Actions
RestController <-- Service
Service <-- Repository
Service <-- YSA
Service <---- CrawlerPacakge
CrawlerPacakge <-- Network
YSA <-- Network
Repository <-> H2
Actions ---> RestController : Http Request
Actions <--- RestController : Http Response

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler

@enduml