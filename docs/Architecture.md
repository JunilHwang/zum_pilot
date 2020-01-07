# 프로젝트 아키텍쳐

User, Client, Server 그리고 Open API 각각의 구조와 서로간의 관계를 표현합니다.

## 1. Client

`User` `Browser` `Vue Framework` 등을 포함하고 있습니다.

@startuml
:User:
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

:User: -->> Browser : 1. Site URL
Browser -->> VueRouter : "2. URI"
VueRouter ->> Components : 3. Routing
Components <<- VueStore : 4. Reactive/compute
VueFramework -->> Browser : 5. Renderer
Browser -->> Components : "  Event Listener"
State <<- Mutations
Mutations <<- Actions
@enduml

## 2. Server


@startuml
node "REST API(=Server)" {
  node Helper {
    node "Youtube Search API" as YSA
    package CrawlerPackage {
      node "MusicCrawler"
      node "NewsCrawler"
      node "Crawler" 
    }
  }
  node Service
  node Repository
  node RestController
  database H2
}

Crawler <|-- MusicCrawler
Crawler <|-- NewsCrawler
RestController <<-- Service
Service <<-- Repository
Repository <<->> H2
Service <<- Helper
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