import Vue from 'vue';
import Router from 'vue-router';
import NProgress from 'nprogress'; // 浏览器进度条

// 流程模块（协同办公）
import off from './module/office';
// 知识社区
import com from './module/community';
// 知识测评
import ass from './module/assess';
// 项目协作
import pro from './module/project';
// 知识仓库
import repo from './module/repository';
// 统计分析
import stat from './module/statistic';
// 系统设置
import sys from './module/system';
// 首页
import home from './module/home';

Vue.use(Router);

const routes = [
    {
        path: '/',
        name: 'login',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ 'views/login/login.vue'),
        meta: { title: '登录', auth: false, keepAlive: true }
    },
    {
        path: '/findPsd',
        name: 'findPsd',
        component: () => import('views/login/findPsd.vue'),
        meta: { title: '找回密码', auth: false, keepAlive: true }
    },
    ...off,
    ...com,
    ...ass,
    ...pro,
    ...repo,
    ...stat,
    ...sys,
    ...home,
];

const router = new Router({
    mode: 'hash',
    base: '/',
    routes,
    scrollBehavior () {
    // 每次路由跳转时滚动到页面顶部
        return { x: 0, y: 0 };
    }
});

const originalPush = Router.prototype.push;
Router.prototype.push = function push (location) {
    return originalPush.call(this, location).catch(err => err);
};

const self = new Vue();
router.beforeEach((to, from, next) => {
    NProgress.start();
    const title = to.meta && to.meta.title;
    if (title) {
        document.title = title; // 设置页面 title
    }
    if (to.meta && to.meta.auth) {
    // 检查是否已登录,如果没有，请重定向到登录页。
        const user = self.$session.get('user');
        if (!user) {
            location.href = '/';
        } else {
            NProgress.done();
            next();
        }
    } else {
        NProgress.done();
        next(); // 确保一定要调用 next()
    }
});

export default router;
