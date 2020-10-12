import dayjs from 'dayjs';

export default {
    date (value, format) {
        if (!value) return '';
        return dayjs(value).format(format || 'YYYY-MM-DD HH:mm');
    },
    filterLabel (value) {
      if (!value) return '';
      return value.replace(/<.*?>/g, "");
    }
};
