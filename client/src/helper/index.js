import Vue from 'vue';

/**
 * 제목에 대해 cutting 처리
 * @param str -> cutting 처리 할 문자열
 * @param len -> 최대 길이
 * @returns {*}
 */
export const previewTitle = (str, len) => (
  str.length < len ? str : `${str.substring(0, len)}...`
);

/**
 * 스크롤 최하단 도달 감지
 * @param callback -> 최하단 감지 후 실행할 함수
 */
export const windowBottomSensor = (callback) => {
  const { innerHeight, scrollY } = window;
  const { scrollHeight } = document.body;
  if (scrollHeight - innerHeight - scrollY <= 0) {
    callback();
  }
};

/**
 * 동영상의 duration을 분:초 형식으로 변환
 * @param duration --> 영상의 총 길이
 * @returns {string} --> 분:초 형식의 문자열
 */
export const convertToTimeFormat = (duration) => {
  const t = Math.floor(duration);
  const m = Math.floor(t / 60);
  const s = `0${t % 60}`.substr(-2);
  return `${m}:${s}`;
};

// 이벤트 버스 생성
export const eventBus = new Vue();
export default { };
