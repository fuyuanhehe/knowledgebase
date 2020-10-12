export default {
    methods: {
        /**
         * @description 格式化大小
         * @param {Number} size 大小，单位 k
         * @return {String} 返回带单位的大小
         */
        formatSize (size) {
            if (size === '' || size === undefined || typeof size !== 'number') {
                return '';
            }

            if (size < 1024) { // K
                return `${size}K`;
            } else if (size < Math.pow(1024, 2)) {
                return `${size / 1024}M`;
            } else {
                return `${size / Math.pow(1024, 3)}G`;
            }
        }
    }
};
