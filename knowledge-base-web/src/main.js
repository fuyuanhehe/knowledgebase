import 'babel-polyfill';
import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import store from './store';
import axios from 'axios';
import VueAxios from 'vue-axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'nprogress/nprogress.css'; // 浏览器进度条
import 'assets/css/aindex.scss';
// import 'assets/icon/index.css';
import 'assets/icon/iconfont.js';
import echarts from 'echarts';
Vue.prototype.$echarts = echarts;

// 暂存
import session from 'utils/session';
// 公共方法
import utils from './utils/util';
// 接口
import api from 'api/index.js';
// 过滤器
import filters from './utils/filters';
// 面包屑
import VBreadcrumb from 'components/VBreadcrumb';
// 字体图标
import VIcon from 'components/VIcon';
// 图片上传
import UploadImage from 'components/upload/upload-image';
// 文件上传
import UploadFile from 'components/upload/upload-file';
// 富文本
import ueditor from 'components/ueditor';
// 暂无数据
import noData from 'components/TheNoData';
// 虚线分割线
import VDivider from 'components/VDivider.vue';
// table数据查询
import TableSearch from 'components/TableSearch';
// 右键弹出菜单
import VueContextMenu from 'vue-contextmenu';
Vue.use(VueContextMenu);
for (const key in filters) {
    Vue.filter(key, filters[key]);
}
Vue.component('v-breadcrumb', VBreadcrumb);
Vue.component('v-icon', VIcon);
Vue.component(UploadImage.name, UploadImage);
Vue.component(UploadFile.name, UploadFile);
Vue.component('ueditor', ueditor);
Vue.component('no-data', noData);
Vue.component('v-divider', VDivider);
Vue.component('table-search', TableSearch);
Vue.prototype.$api = api;
Vue.$api = api;
Vue.prototype.$session = session;
Vue.prototype.$utils = utils;

// 通过use方法加载axios插件
Vue.use(VueAxios, axios);
Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
