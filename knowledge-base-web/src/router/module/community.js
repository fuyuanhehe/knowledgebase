/**
 * @file 知识社区模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/community',
        name: 'community',
        component: () => import('views/community/community.vue'),
        meta: { title: '知识社区', auth },
        redirect: '/community/homepage',
        children: [
            {
                path: 'homepage',
                name: 'communityHomepage',
                component: () => import('views/community/homepage/homepage.vue'),
                meta: { title: '知识社区-首页', auth }
            },
            {
                path: 'topicCircle',
                name: 'communityTopicCircle',
                component: () => import('views/community/topicCircle/topicCircle.vue'),
                meta: { title: '知识社区-话题圈子类别', auth }
            },
            {
                path: 'topicCircle/:id',
                name: 'communityTopicCircleSort',
                component: () => import('views/community/topicCircle/topicCircle.vue'),
                meta: { title: '知识社区-话题圈子类别', auth }
            },
            // 知识社区-话题编辑：
            // pageFrom:circle--从圈子跳转（新建）；home--从知识社区首页跳转（新建）；manage--从话题管理跳转（编辑）
            // type: 编辑类型 add--新建；edit--修改
            {
                path: 'topicEdit/:pageFrom/:circleId/:type',
                name: 'communityTopicCircleEdit',
                component: () => import('views/community/topicCircle/topicEdit.vue'),
                meta: { title: '知识社区-话题编辑', auth }
            },
            // 话题详情：circleId：圈子id
            // pageFrom:circle--从圈子跳转；home--从知识社区首页跳转；manage--从话题管理跳转（可删除评论）
            {
                path: 'topicCircle/topicDetails/:circleId/:pageFrom',
                name: 'communityTopicDetails',
                component: () => import('views/community/topicCircle/topicDetails.vue'),
                meta: { title: '知识仓库-话题详情', auth }
            },
            // 知识社区管理-圈子类别管理
            {
                path: 'communityMgt/circleTypeMgt',
                name: 'communityCircleTypeMgt',
                component: () => import('views/community/communityMgt/circleTypeMgt/circleTypeMgt.vue'),
                meta: { title: '知识社区-圈子类别管理', auth }
            },
            // 知识社区管理-话题管理
            {
                path: 'communityMgt/topicMgt',
                name: 'communityTopicMgt',
                component: () => import('views/community/communityMgt/topicMgt/topicMgt.vue'),
                meta: { title: '知识社区-话题管理', auth }
            }
        ]
    }
];
