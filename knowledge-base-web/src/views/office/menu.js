export const menus = [
    {
        id: 'myProcess',
        url: '/office/myProcess',
        name: '我的流程',
        iconUrl: 'static/images/menu-icon-process.png',
        children: [
            {
                id: 'created',
                url: '/office/myProcess/created',
                name: '我发起的',
                iconUrl: ''
            }, {
                id: 'approval',
                url: '/office/myProcess/approval',
                name: '我的审批',
                iconUrl: ''
            }
        ]
    }, {
        id: 'processLayout',
        url: '/office/processLayout',
        name: '流程配置',
        iconUrl: 'static/images/menu-icon-layout.png',
        children: [
            {
                id: 'layoutList',
                url: '/office/processLayout/layoutList',
                name: '配置列表',
                iconUrl: ''
            }
        ]
    }
];
