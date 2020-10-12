export const menus = [
    {
        id: 'topicCircle',
        url: '/community/topicCircle',
        name: '话题圈子类别',
        iconUrl: 'static/images/menu-icon-topic.png',
        children: [
            {
                id: 'technology',
                url: '/community/topicCircle/technology',
                name: '技术圈',
                iconUrl: ''
            }, {
                id: 'sales',
                url: '/community/topicCircle/sales',
                name: '销售圈',
                iconUrl: ''
            }
        ]
    }, {
        id: 'communityMgt',
        url: '/community/communityMgt',
        name: '知识社区管理',
        iconUrl: 'static/images/menu-icon-community.png',
        children: [
            {
                id: 'circleTypeMgt',
                url: '/community/communityMgt/circleTypeMgt',
                name: '圈子类别管理',
                iconUrl: ''
            }, {
                id: 'topicMgt',
                url: '/community/communityMgt/topicMgt',
                name: '话题管理',
                iconUrl: ''
            }
        ]
    }
];
