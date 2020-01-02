/* mutations 혹은 actions 에 사용될 상수를 정의합니다. */

export const VIDEO_FETCH = 'video/fetch'; // 비디오 가져오기
export const VIDEO_PREV_FETCH = 'video/prevFetch'; // 이전 비디오 가져오기
export const VIDEO_NEXT_FETCH = 'video/nextFetch'; // 다음 비디오 가져오기
export const VIDEO_SELECT = 'video/select'; // 비디오 선택
export const VIDEO_VIEW = 'video/view'; // 비디오 조회수 증가
export const VIDEO_LIKE = 'video/like'; // 비디오 좋아요 토글
export const VIDEO_POPULAR_FETCH = 'video/popularFetch'; // 인기영상 가져오기
export const VIDEO_BOOKMARK = 'video/bookmark'; // 즐겨찾기 가져오기

export const USER_SIGN_IN = 'user/signIn'; // 로그인
export const USER_SIGN_UP = 'user/signUp'; // 회원가입
export const USER_LOGOUT = 'user/logout'; // 로그아웃
export const USER_AUTH = 'user/auth'; // 로그인 인증
export const USER_VIDEO_BOOKMARK = 'user/videoBookmark'; // 즐겨찾기 등록

export const NEWS_HEADLINE_FETCH = 'news/fetchHeadline'; // 헤드라인 뉴스 가져오기
export const NEWS_POPULAR_FETCH = 'news/fetchPopular'; // 인기 뉴스 가져오기
export const NEWS_ARTICLES_FETCH = 'news/fetchArticles'; // 일반 뉴스 가져오기
export const NEWS_ARTICLES_APPEND = 'news/appendArticles'; // 일반 뉴스 가져온 후 붙이기
export const NEWS_CONTENT_FETCH = 'news/fetchContent'; // 상세보기 내용 가져오기
export const NEWS_CONTENT_VIEW = 'news/viewContent'; // 상세보기 열기

export const MUSIC_FETCH = 'music/fetch'; // 음원 가져오기
export const MUSIC_SELECT = 'music/select'; // 음원 선택

export const MODAL_OPEN = 'modal/open'; // 모달창 열기
export const MODAL_CLOSE = 'modal/close'; // 모달창 닫기
export const MODAL_PROPERTY = 'modal/property'; // 모달창의 속성
export const MODAL_ALERT = 'modal/alert'; // 경고창 모달 열기

export default { };
