(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5c04daa0"],{"857a":function(t,e,n){var i=n("1d80"),r=/"/g;t.exports=function(t,e,n,a){var c=String(i(t)),s="<"+e;return""!==n&&(s+=" "+n+'="'+String(a).replace(r,"&quot;")+'"'),s+">"+c+"</"+e+">"}},9911:function(t,e,n){"use strict";var i=n("23e7"),r=n("857a"),a=n("eae9");i({target:"String",proto:!0,forced:a("link")},{link:function(t){return r(this,"a","href",t)}})},a1cc:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("article",{staticClass:"news-article"},[n("figure",{staticClass:"news-article__img"},[n("a",{on:{click:function(e){return e.preventDefault(),t.viewDetail(e)}}},[n("img",{attrs:{src:t.img,alt:t.title}})])]),n("div",{staticClass:"news-article__content"},[n("h3",{staticClass:"news-article__title"},[n("a",{domProps:{innerHTML:t._s(t.title)},on:{click:function(e){return e.preventDefault(),t.viewDetail(e)}}})]),n("p",{staticClass:"news-article__regdate"},[n("FAI",{attrs:{icon:["far","clock"]}}),t._v(" "+t._s(t.regDate)+" ")],1)])])},r=[],a=(n("9911"),n("7af8")),c={methods:{viewDetail:function(){this.$store.commit(a["f"],this.link)}},props:["title","regDate","img","link"]},s=c,l=n("2877"),o=Object(l["a"])(s,i,r,!1,null,null,null);e["default"]=o.exports},eae9:function(t,e,n){var i=n("d039");t.exports=function(t){return i((function(){var e=""[t]('"');return e!==e.toLowerCase()||e.split('"').length>3}))}}}]);
//# sourceMappingURL=chunk-5c04daa0.f0b94dae.js.map