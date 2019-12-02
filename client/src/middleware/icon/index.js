import Vue from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faClock } from '@fortawesome/free-regular-svg-icons';
import {
  faChevronUp,
  faPlay,
  faPause,
  faExpand,
  faCompress,
  faVolumeMute,
  faVolumeDown,
  faThumbsUp,
  faEye,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

library.add(
  faClock,
  faChevronUp,
  faPlay,
  faPause,
  faExpand,
  faCompress,
  faVolumeMute,
  faVolumeDown,
  faThumbsUp,
  faEye,
);
Vue.component('FAI', FontAwesomeIcon);
