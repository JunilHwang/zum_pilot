import { NO_MEMBER } from '../const';

export const initState = {
  permission: NO_MEMBER,
  idx: null,
  id: null,
  name: null,
  token: null,
  bookmark: [],
};

const storageState = JSON.parse(sessionStorage.getItem('user')) || { };

export default Object.assign({ ...initState }, storageState);
