/**
 * @file 流程模块（协同办公）路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/office',
        name: 'office',
        component: () => import('views/office/office.vue'),
        meta: { title: '协同办公', auth },
        redirect: '/office/myProcess/created',
        children: [
            // 我的流程-我发起的
            {
                path: 'myProcess/created',
                name: 'officeMyProcessCreated',
                component: () => import('views/office/myProcess/created/created.vue'),
                meta: { title: '协同办公-我的流程', auth }
            },
            // 我的流程-我的审批
            {
                path: 'myProcess/approval',
                name: 'officeMyProcessApproval',
                component: () => import('views/office/myProcess/approval/approval.vue'),
                meta: { title: '协同办公-我的流程', auth }
            },
            // 流程配置-配置列表
            {
                path: 'processLayout/layoutList',
                name: 'officeProcessLayoutList',
                component: () => import('views/office/processLayout/layoutList.vue'),
                meta: { title: '协同办公-流程配置', auth }
            }
        ]
    }
];
