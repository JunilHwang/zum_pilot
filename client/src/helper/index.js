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

export const eventBus = new Vue(); // 이벤트 버스 생성
export default { };
