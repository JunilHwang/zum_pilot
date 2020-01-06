# 프로젝트 아키텍쳐

## 서비스 구조도

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