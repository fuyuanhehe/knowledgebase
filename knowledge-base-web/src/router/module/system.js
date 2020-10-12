/**
 * @file 系统设置模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/system',
        name: 'system',
        component: () => import('views/system/system.vue'),
        meta: { title: '系统设置', auth },
        redirect: '/system/systemSetup/roleMgt',
        children: [
            {
                path: 'systemSetup/roleMgt',
                name: 'roleMgt',
                component: () => import('views/system/roleMgt/roleMgt.vue'),
                meta: { title: '系统设置-角色管理', auth }
            },
            // 新增/编辑角色
            {
                path: 'systemSetup/roleMgt/:type',
                name: 'systemSetupRoleMgtEdit',
                component: () => import('views/system/roleMgt/roleEdit.vue'),
                meta: { title: '系统设置-角色管理', auth }
            },
            {
                path: 'systemSetup/userMgt',
                name: 'userMgt',
                component: () => import('views/system/userMgt/userMgt.vue'),
                meta: { title: '系统设置-用户管理', auth }
            },
            // 新增/编辑用户
            {
                path: 'systemSetup/userMgt/:type',
                name: 'systemSetupUserMgtEdit',
                component: () => import('views/system/userMgt/userEdit.vue'),
                meta: { title: '系统设置-用户管理', auth }
            },
            {
                path: 'systemSetup/organization',
                name: 'organization',
                component: () => import('views/system/organization/organization.vue'),
                meta: { title: '系统设置-组织架构', auth }
            },
            {
                path: 'systemSetup/operationLog',
                name: 'operationLog',
                component: () => import('views/system/operationLog/operationLog.vue'),
                meta: { title: '系统设置-操作日志', auth }
            },
            {
                path: 'homeMgt/news',
                name: 'news',
                component: () => import('views/system/homeMgt/news.vue'),
                meta: { title: '首页管理-公司新闻', auth }
            },
            {
                path: 'homeMgt/newsEdit',
                name: 'newsEdit',
                component: () => import('views/system/homeMgt/newsEdit.vue'),
                meta: { title: '首页管理-新闻编辑', auth }
            },
            {
                path: 'homeMgt/notice',
                name: 'notice',
                component: () => import('views/system/homeMgt/notice.vue'),
                meta: { title: '首页管理-通知公告', auth }
            },
            {
                path: 'homeMgt/noticeEdit',
                name: 'noticeEdit',
                component: () => import('views/system/homeMgt/noticeEdit.vue'),
                meta: { title: '首页管理-通知公告编辑', auth }
            }
        ]
    }
];
