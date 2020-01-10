# 분석 및 최적화

Vue Project를 Build할 때 사용한 분석 및 최적화 도구와 방법에 대해 소개합니다.

## Code Splitting

Vue.js는 SPA(Single Page Application)을 만드는 도구이며
Code Splitting은 SPA의 성능을 향상시키는 방법입니다.
SPA는 초기 실행시에 필요한 자원을 모두 다운 받아야 합니다.
그런데 Code Splitting을 활용하게 되면 필요한 시점에 자원을 사용합니다.

::: tip Lazy Loading
`Dynamic Import` + `webpackChunkName`을 사용하면 Lazy Loading이 가능합니다.
- Dynamic Import는 `const moduleName = () => import('path')` 형태로 사용할 수 있습니다.
- path 앞에 할 때 prefix로 `/*webpackChunkName: name*/`을 붙이면 리소스를 분리하고 묶을 수 있습니다.
- `index.js`를 이용하면 쉽게 관리할 수 있습니다.
:::

**실제 사용 예는 다음과 같습니다.**  

### 폴더 구조
```{4,12,15}
client/src
├─ components
│   ├─ video
│   │   ├─ index.js
│   │   ├─ Article.vue
│   │   ├─ List.vue
│   │   ├─ Meta.vue
│   │   ├─ Player.vue
│   │   └─ Controls.vue
│   └─ ...
├─ views
│   ├─ index.js
│   ├─ Popular.vue
│   └─ ...
├─ middleware/router/index.js
└─ ...
```
`**/index.js`를 이용하여 import/export를 관리합니다.


### index.js를 사용하면 좋은 점 

다음과 같이 `index.js를 생략하여 import` 할 수 있습니다.

``` js
import { VideoPlayer, VideoControls } from 'components/video/index.js'
import { NewsArticle, NewsDetail } from 'components/news/index.js'
import { SiteHeader, SiteFooter } from 'components/common/index.js'
import { Alert } from 'components/modal/index.js'

// index.js를 생략할 수 있습니다.
import { VideoPlayer, VideoControls } from 'components/video'
import { NewsArticle, NewsDetail } from 'components/news'
import { SiteHeader, SiteFooter } from 'components/common'
import { Alert } from 'components/modal'
```
  
### Code Splitting 적용

``` js
/* client/src/views/index.js */
export const News = () => import(/* webpackChunkName: "views" */'./News.vue');
export const Chart = () => import(/* webpackChunkName: "views" */'./Chart.vue');
export const Login = () => import(/* webpackChunkName: "views" */'./Login.vue');
export const Join = () => import(/* webpackChunkName: "views" */'./Join.vue');
export const Popular = () => import(/* webpackChunkName: "views" */'./Popular.vue');
export const Bookmark = () => import(/* webpackChunkName: "views" */'./Bookmark.vue');

/* client/src/components/video/index.js */
export const VideoList = () => import(/* webpackChunkName: "chart" */'./List.vue');
export const VideoPlayer = () => import(/* webpackChunkName: "chart" */'./Player.vue');
export const VideoControls = () => import(/* webpackChunkName: "chart" */'./Controls.vue');
export const VideoMeta = () => import(/* webpackChunkName: "chart" */'./Meta.vue');
export const VideoArticle = () => import(/* webpackChunkName: "chart" */'./Article.vue');

/* 나머지 생략 */
```

**client/src/middleware/router/index.js** 
``` js
/* 앞 내용 생략 */
import { News, Chart, Login, Join, Popular, Bookmark } from '@/views';
const routes = [
  { path: '/', component: News, alias: '/news' },
  { path: '/chart', component: Chart },
  { path: '/sign-in', component: Login },
  { path: '/sign-up', component: Join },
  { path: '/popular', component: Popular },
  { path: '/bookmark', component: Bookmark },
];
/* 뒷 내용 생략 */
```
**client/src/views/Chart.vue** 
``` html
<template><!-- 생략 --></template>
<script>
// 앞 생략
import { Flicking } from '@egjs/vue-flicking';
import { ChartArticle } from '@/components/chart';
import { VideoPlayer } from '@/components/video';
import { Spinner } from '@/components/common';
const components = { ChartArticle, VideoPlayer, Flicking, Spinner };
// 뒤 생략
</script>
```