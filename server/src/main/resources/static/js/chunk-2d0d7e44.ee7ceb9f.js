(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d7e44"],{"796b":function(t,e,n){"use strict";n.r(e);var s=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("main",{staticClass:"news-detail"},[t.loaded?n("article",[n("h1",{staticClass:"news-detail__title",domProps:{innerHTML:t._s(t.news.article.title)}}),n("div",{ref:"content",staticClass:"news-detail__content",domProps:{innerHTML:t._s(t.news.article.content)}}),n("div",{staticClass:"btn-group center"},[n("router-link",{staticClass:"btn main",attrs:{to:"/"}},[t._v("메인으로")]),n("a",{staticClass:"btn default",attrs:{href:"#"}},[t._v("상단으로")])],1)]):t._e()])},a=[],i=n("2f62"),r=n("7af8"),c={beforeRouteLeave:function(t,e,n){this.$store.commit("changeRouterTransition",""),n()},computed:Object(i["b"])(["news"]),data:function(){return{loaded:!1}},created:function(){this.$store.dispatch(r["c"],this.$route.params.url),this.loaded=!0}},o=c,l=n("2877"),d=Object(l["a"])(o,s,a,!1,null,null,null);e["default"]=d.exports}}]);
//# sourceMappingURL=chunk-2d0d7e44.ee7ceb9f.js.map