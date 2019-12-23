import Vue from 'vue';

export const previewTitle = (str, len) => (
  str.length < len ? str : `${str.substring(0, len)}...`
);

export const windowBottomSensor = (callback) => {
  const { innerHeight, scrollY } = window;
  const { scrollHeight } = document.body;
  if (scrollHeight - innerHeight - scrollY <= 0) {
    callback();
  }
};

export const eventBus = new Vue();

export { default as responseValid } from './responseValid';
export { default as $http } from './http-wrapper';

export default { };
