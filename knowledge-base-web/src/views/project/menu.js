export const menus = [
    {
        id: 'project',
        url: '/project',
        name: '项目协作',
        iconUrl: 'static/images/menu-icon-project.png',
        children: [
            {
                id: 'projectApply',
                url: '/project/projectApply',
                name: '项目立项',
                iconUrl: ''
            }, {
                id: 'projectList',
                url: '/project/projectList',
                name: '项目列表',
                iconUrl: ''
            }, {
                id: 'recycleBin',
                url: '/project/recycleBin',
                name: '回收站',
                iconUrl: ''
            }
        ]
    }
];
