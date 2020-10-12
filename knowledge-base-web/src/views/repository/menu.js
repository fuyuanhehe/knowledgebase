/**
 * @file 知识仓库菜单
 * @author Claire
 */
export const menus = [
    {
        id: 'repositoryDocumentMgt',
        url: '/repository/documentMgt',
        name: '文档管理',
        iconUrl: 'static/images/menu-icon-doc.png',
        children: [
            {
                id: 'companyDocuments',
                url: '/repository/documentMgt/company',
                name: '公司文档',
                iconUrl: ''
            }, {
                id: 'departmentalDocuments',
                url: '/repository/documentMgt/departmental',
                name: '部门文档',
                iconUrl: ''
            }, {
                id: 'personalDocument',
                url: '/repository/documentMgt/personal',
                name: '个人文档',
                iconUrl: ''
            }
        ]
    }, {
        id: 'repositoryKnowledgeClassify',
        url: '/repository/knowledgeClassify/1',
        name: '知识分类',
        iconUrl: 'static/images/menu-icon-sort.png',
        children: [
            {
                id: 'saleBase',
                url: '/repository/knowledgeClassify/sale',
                name: '销售知识库',
                iconUrl: ''
            }, {
                id: 'antiepidemicBase',
                url: '/repository/knowledgeClassify/antiepidemic',
                name: '防疫知识库',
                iconUrl: ''
            }, {
                id: 'processBase',
                url: '/repository/knowledgeClassify/process',
                name: '制度流程库',
                iconUrl: ''
            }
        ]
    }, {
        id: 'warehouseMgt',
        url: '/repository/warehouseMgt',
        name: '知识仓库管理',
        iconUrl: 'static/images/menu-icon-mgt.png',
        children: [
            {
                id: 'repositoryClassifyMgt',
                url: '/repository/warehouseMgt/classifyMgt',
                name: '知识分类管理',
                iconUrl: ''
            }, {
                id: 'repositoryQualityDocumentMgt',
                url: '/repository/warehouseMgt/qualityDocument',
                name: '精品文档管理',
                iconUrl: ''
            }, {
                id: 'repositoryGifMgt',
                url: '/repository/warehouseMgt/gif',
                name: '动态图管理',
                iconUrl: ''
            }, {
                id: 'repositoryDocumentCommentMgt',
                url: '/repository/warehouseMgt/documentComment',
                name: '文档评论管理',
                iconUrl: ''
            }, {
                id: 'repositoryMyCollection',
                url: '/repository/warehouseMgt/myCollection',
                name: '我收藏的',
                iconUrl: ''
            }, {
                id: 'repositoryMyComment',
                url: '/repository/warehouseMgt/myComment',
                name: '我评论的',
                iconUrl: ''
            }
        ]
    }
];
