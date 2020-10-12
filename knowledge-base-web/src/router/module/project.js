/**
 * @file 项目协作模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/project',
        name: 'project',
        component: () => import('views/project/project.vue'),
        meta: { title: '项目协作', auth },
        redirect: '/project/projectApply',
        children: [
            {
                path: 'projectApply',
                name: 'projectApply',
                component: () => import('views/project/projectApplyList.vue'),
                meta: { title: '项目协作-项目立项', auth }
            },
            {
                path: 'projectList',
                name: 'projectList',
                component: () => import('views/project/projectList.vue'),
                meta: { title: '项目协作-项目列表', auth }
            },
            {
                path: 'recycleBin',
                name: 'recycleBin',
                component: () => import('views/project/recycleBinList.vue'),
                meta: { title: '项目协作-回收站', auth }
            },
            {
                path: 'projectEdit',
                name: 'projectEdit',
                component: () => import('views/project/projectEdit.vue'),
                meta: { title: '项目协作-项目编辑', auth }
            },
            {
                path: 'projectDetails',
                name: 'projectDetails',
                component: () => import('views/project/projectDetails.vue'),
                meta: { title: '项目协作-项目详情', auth }
            }
        ]
    }
];
