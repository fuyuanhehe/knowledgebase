import Vue from 'vue';

// 测试及开发网关
// let url = 'http://47.110.76.241:8089'; // 测试环境
//const kkd = 'http://192.168.0.217:8089'; // 况克东
// const fy = 'http://192.168.0.151:8089'; // 符源

 let url = 'http://127.0.0.1:8089'; // 测试环境
const back = {
    'kb-repo': url,
    'kb-community': url,
    'kb-pro': url,
    'kb-act': url,
    'kb-eval': url,
    'kb-sys': url,
    'kb-stat': url,
    'kb-global': url
};
// 图片服务器地址
// Vue.prototype.$img = 'http://file.szhtkc.com:90/';
Vue.prototype.$img = url;

if (process.env.NODE_ENV == 'production') {
    const config = window.config || {};
    for (const key in back) {
        back[key] = config.service || 'http://47.110.76.241:8089';
        //  https://smbss.casicloud.com
    }
   /* Vue.prototype.$img = config.imgService || 'http://file.szhtkc.com/';
    url = 'https://smbss.casicloud.com';*/

   Vue.prototype.$img = config.imgService || 'http://47.110.76.241:8089';
    url = 'http://47.110.76.241:8089';


}

const config = [
    {
        url: back['kb-act'],
        module: 'act',
        key: 'act'
    },
    {
        url: back['kb-community'],
        module: 'community',
        key: 'community'
    },
    {
        url: back['kb-eval'],
        module: 'eval',
        key: 'assess'
    },
    {
        url: back['kb-global'],
        module: 'global',
        key: 'global'
    },
    {
        url: back['kb-pro'],
        module: 'proj',
        key: 'proj'
    },
    {
        url: back['kb-repo'],
        module: 'repo',
        key: 'repo'
    }, {
        url: back['kb-stat'],
        module: 'stat',
        key: 'stat'
    }, {
        url: back['kb-sys'],
        module: 'sys',
        key: 'sys'
    }
];
const configUrl = {};

// let urls = []

config.forEach((item) => {
    // if (process.env.NODE_ENV == 'production') {
    //   item.url += '/api/' + item.module
    // } else {
    //   if (urls.includes(item.url)) {
    //     item.url += '/' + item.module
    // } else if (item.url == 'https://vtest.szhtkc.com' || item.url == 'http://vtest.szhtkc.com' || item.url == 'https://smbss.casicloud.com') {
    //   item.url += '/api/' + item.module
    // } else {
    //   item.url += ('_apis_' + item.module)
    // }
    // }
    item.url += '/api/' + item.module;
    configUrl[item.key] = item.url;
});

export default configUrl;
