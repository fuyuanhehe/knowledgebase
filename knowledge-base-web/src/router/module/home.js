/**
 * @file 系统设置模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/home',
        name: 'home',
        component: () => import('views/home/home.vue'),
        meta: { title: '首页', auth: auth, keepAlive: true }
    },
    {
        path: '/home/newsList',
        name: 'newsList',
        component: () => import('views/home/moreList.vue'),
        meta: { title: '首页-公司新闻', auth: auth }
    }, {
        path: '/home/noticeList',
        name: 'noticeList',
        component: () => import('views/home/moreList.vue'),
        meta: { title: '首页-通知公告', auth: auth }
    }, {
        path: '/home/newsDetails',
        name: 'newsDetails',
        component: () => import('views/home/itemDetails.vue'),
        meta: { title: '首页-公司新闻详情', auth: auth }
    }, {
        path: '/home/noticeDetails',
        name: 'noticeDetails',
        component: () => import('views/home/itemDetails.vue'),
        meta: { title: '首页-通知公告详情', auth: auth }
    }, {
        path: '/home/learningMoreList',
        name: 'learningMoreList',
        component: () => import('views/home/learningMoreList.vue'),
        meta: { title: '首页-最新学习', auth: auth }
    }, {
        path: '/home/examMoreList',
        name: 'examMoreList',
        component: () => import('views/home/examMoreList.vue'),
        meta: { title: '首页-最新考试', auth: auth }
    }
];
