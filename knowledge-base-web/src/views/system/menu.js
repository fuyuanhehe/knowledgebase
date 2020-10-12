export const menus = [
    {
        id: 'systemSetup',
        url: '/system/systemSetup',
        name: '系统设置',
        iconUrl: 'static/images/menu-icon-sys.png',
        children: [
            {
                id: 'roleMgt',
                url: '/system/systemSetup/roleMgt',
                name: '角色管理',
                iconUrl: ''
            }, {
                id: 'userMgt',
                url: '/system/systemSetup/userMgt',
                name: '用户管理',
                iconUrl: ''
            }, {
                id: 'organization',
                url: '/system/systemSetup/organization',
                name: '组织架构',
                iconUrl: ''
            }, {
                id: 'operationLog',
                url: '/system/systemSetup/operationLog',
                name: '操作日志',
                iconUrl: ''
            }
        ]
    },
    {
        id: 'homeMgt',
        url: '/system/homeMgt',
        name: '首页管理',
        iconUrl: 'static/images/menu-icon-homeMgt.png',
        children: [
            {
                id: 'news',
                url: '/system/homeMgt/news',
                name: '公司新闻',
                iconUrl: ''
            },
            {
                id: 'notice',
                url: '/system/homeMgt/notice',
                name: '通知公告',
                iconUrl: ''
            }
        ]
    }
];
