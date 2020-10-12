/**
 * @file 知识仓库模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/repository',
        name: 'repository',
        component: () => import('views/repository/repository.vue'),
        meta: { title: '知识仓库', auth },
        redirect: '/repository/homepage',
        children: [
            {
                path: 'homepage',
                name: 'repositoryHomepage',
                component: () => import('views/repository/homepage/homepage.vue'),
                meta: { title: '知识仓库-首页', auth }
            },
            {
                path: 'ranking/:type',
                name: 'repositoryRanking',
                component: () => import('views/repository/homepage/ranking.vue'),
                meta: { title: '知识仓库-知识排行榜', auth }
            },
            {
                path: 'documentMgt/:type',
                name: 'repositoryDocumentMgt',
                component: () => import('views/repository/documentMgt/documentMgt.vue'),
                meta: { title: '知识仓库-文档管理', auth }
            },
            {
                path: 'knowledgeClassify',
                name: 'repositoryKnowledgeClassify',
                component: () => import('views/repository/knowledgeClassify/knowledgeClassify.vue'),
                meta: { title: '知识仓库-知识分类', auth }
            },
            {
                path: 'knowledgeClassify/:id',
                name: 'repositoryKnowledgeClassifyBase',
                component: () => import('views/repository/knowledgeClassify/knowledgeClassify.vue'),
                meta: { title: '知识仓库-知识分类', auth }
            },
            // 知识分类详情
            {
                path: 'knowledgeClassify/:id/knowledgeDetails',
                name: 'repositoryKnowledgeDetails',
                component: () => import('views/repository/knowledgeClassify/knowledgeDetails.vue'),
                meta: { title: '知识仓库-知识分类详情', auth }
            },
            // 知识仓库管理
            {
                path: 'warehouseMgt',
                name: 'repositoryWarehouseMgt',
                component: () => import('views/repository/warehouseMgt/classifyMgt/classifyMgt.vue'),
                meta: { title: '知识仓库-知识仓库管理', auth },
                redirect: '/repository/warehouseMgt/classifyMgt'
            },
            // 知识仓库管理-知识分类管理
            {
                path: 'warehouseMgt/classifyMgt',
                name: 'repositoryClassifyMgt',
                component: () => import('views/repository/warehouseMgt/classifyMgt/classifyMgt.vue'),
                meta: { title: '知识仓库-知识分类管理', auth }
            },
            // 知识仓库管理-精品文档管理
            {
                path: 'warehouseMgt/qualityDocument',
                name: 'repositoryQualityDocumentMgt',
                component: () => import('views/repository/warehouseMgt/qualityDocument/qualityDocument.vue'),
                meta: { title: '知识仓库-精品文档管理', auth }
            },
            // 知识仓库管理-精品文档管理-添加/编辑精品
            {
                path: 'warehouseMgt/qualityDocument/:type',
                name: 'repositoryQualityDocumentEdit',
                component: () => import('views/repository/warehouseMgt/qualityDocument/qualityDocumentEdit.vue'),
                meta: { title: '知识仓库-精品文档管理', auth }
            },
            // 知识仓库管理-动态图管理
            {
                path: 'warehouseMgt/gif',
                name: 'repositoryGifMgt',
                component: () => import('views/repository/warehouseMgt/gif/gif.vue'),
                meta: { title: '知识仓库-动态图管理', auth }
            },
            // 知识仓库管理-动态图管理-添加/编辑动态图
            {
                path: 'warehouseMgt/gif/:type',
                name: 'repositoryGifEdit',
                component: () => import('views/repository/warehouseMgt/gif/gifEdit.vue'),
                meta: { title: '知识仓库-动态图管理', auth }
            },
            // 知识仓库管理-文档评论管理
            {
                path: 'warehouseMgt/documentComment',
                name: 'repositoryDocumentCommentMgt',
                component: () => import('views/repository/warehouseMgt/documentComment/documentComment.vue'),
                meta: { title: '知识仓库-文档评论管理', auth }
            },
            // 知识社区管理-我收藏的
            {
                path: 'warehouseMgt/myCollection',
                name: 'repositoryMyCollection',
                component: () => import('views/repository/warehouseMgt/myCollection/myCollection.vue'),
                meta: { title: '知识仓库-我收藏的', auth }
            },
            // 知识社区管理-我评论的
            {
                path: 'warehouseMgt/myComment',
                name: 'repositoryMyComment',
                component: () => import('views/repository/warehouseMgt/myComment/myComment.vue'),
                meta: { title: '知识仓库-我评论的', auth }
            }
        ]
    },
    {
        path: '/filePreview',
        name: 'docFilePreview',
        component: () => import('views/repository/documentMgt/docFilePreview.vue'),
        meta: { title: '知识仓库-文档预览', auth, keepAlive: true }
    }, {
        path: '/docFileEdit',
        name: 'docFileEdit',
        component: () => import('views/repository/knowledgeClassify/docFileEdit.vue'),
        meta: { title: '知识仓库-文档编辑', auth, keepAlive: true }
    },
    {
        path: '/repository/homepage/bannerDetails/:id',
        name: 'bannerDetails',
        component: () => import('views/repository/homepage/bannerDetails.vue'),
        meta: { title: '知识仓库-banner详情', auth }
    }
];
