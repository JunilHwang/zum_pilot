---

sidebarDepth: 2

---

# 프로젝트 개요

::: tip 파일럿 프로젝트
줌인터넷 포털개발팀의 주니어 개발자가 수습 기간 동안 진행하는 파일럿 프로젝트입니다.
:::

## 주제

- 카테고리별 영상 제공 웹 서비스
- Youtube 등 영상 제공 API를 이용하여 영상 데이터 획득
- 영상 데이터를 가공하여 웹 페이지에 노출

## 목표 및 의의

- 모바일 웹 서비스 페이지 개발
- 외부 API를 이용한 데이터 획득 및 정제
- Vue.js로 front-end 구성

## 개발 스펙

### front-end

- ES6+
- Vue-cli3(Webpack 4)
- Terser Webpack plugin
- Vue.js(v2.6) + Vuex + Vue-router + etc
- SCSS
- Lodash / Swiper (선택)

### back-end

- Java8 이상
- Gradle
- Spring Boot
- Spring Data JPA (선택, DB는 H2사용)
- Ehcache
- Pebble Template Engine (선택)

### 기본 기능

- UI 디자인/구성 자유
- SSR, Prerendering 적용 필요 없음
- 브라우저 스펙 관련 처리(ex. BF 캐시) 필요 없음
- UI 컴포넌트 라이브러리 사용 제한 없음
- JQuery 사용 지양
- 서버 사이드 템플릿 사용 제한 없음
- 태블릿 모드 고려 필요 없음
- 빌보드 모바일 홈페이지 참조

## 기능 구현

### K-pop 뉴스 컨텐츠 노출

- 외부 페이지 크롤링, 뉴스 기사 가공 처리
- [빌보드 코리아](http://billboard.co.kr/main/news/list) 뉴스 컨텐츠
- [SBS](http://sbsfune.sbs.co.kr/news/ssports_list.jsp?code_category=SS04) / KBS 등 언론사 연예 뉴스 컨텐츠

### 멜론 차트 + Youtube 영상 노출

- [멜론 음원 차트](https://www.melon.com/chart/day/index.htm?classCd=GN0000) 크롤링
- 장르별 곡 명을 Youtube API를 이용하여 영상 검색
- 비디오 플레이어를 이용하여 영상 재생

### 많이 본 영상 순위 처리

- 음원 차트와 무관하게 페이지 내에서 많이 본 영상 순위 선정 및 노출

### Back-end 필수 기능 

- Cache (Local)
- Youtube 등 영상 제공 API 연동

### Front-end 필수 기능

- 모듈화 및 아키텍처링
- 화면 스와이프(플리킹) 기능
- 동영상 썸네일 출력
- 동영상 플레이어
- Dynamic Component 활용
- Bundle Analyze & Optimize
- SCSS 기능 활용

### 선택기능

- 특정 컴포넌트 보여지기 / 감추기
- 컨텐츠 데이터 획득 배치 처리
