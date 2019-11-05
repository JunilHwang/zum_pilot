const previewTitle = (str, len) => (
  str.length < len ? str : `${str.substring(0, len)}...`
);

export default {
  previewTitle,
};
