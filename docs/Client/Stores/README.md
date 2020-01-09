# Vue Store

vuex는 vue.js에서 제공하는 **중앙집중식 상태 관리 라이브러리**입니다.

## Logic

Vuex의 로직은 다음과 같습니다.

@startuml
collections "VueComponents\n---\n\
State map to **computed**\n\
Actions and Mutations map to **methods**" as components
(REST API) as server
rectangle Vuex {
  rectangle Actions
  rectangle Mutations
  rectangle State
}
components -right-> Mutations : Commit
components -right-> Actions  : Dispatch
components <-down- State : Render
Mutations <-up- Actions : Commit
Mutations -down-> State : Replace
Actions o-right-o server : HttpCommunication
@enduml

## Vuex Module