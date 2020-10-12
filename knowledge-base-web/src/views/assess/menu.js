export const menus = [
    {
        id: 'assessMgt',
        url: '/assess/assessMgt',
        name: '测评管理',
        iconUrl: 'static/images/menu-icon-assess.png',
        children: [
            {
                id: 'learningCenter',
                url: '/assess/assessMgt/learningCenter',
                name: '学习中心',
                iconUrl: ''
            }, {
                id: 'testingCenter',
                url: '/assess/assessMgt/testingCenter',
                name: '考试中心',
                iconUrl: ''
            }
        ]
    }, {
        id: 'resourceMgt',
        url: '/assess/resourceMgt',
        name: '资源管理',
        iconUrl: 'static/images/menu-icon-resource.png',
        children: [
            {
                id: 'learningResource',
                url: '/assess/resourceMgt/learningResource',
                name: '学习资源',
                iconUrl: ''
            }, {
                id: 'examMgt',
                url: '/assess/resourceMgt/testMgt',
                name: '考试管理',
                iconUrl: ''
            }, {
                id: 'testPaperLibrary',
                url: '/assess/resourceMgt/testPaperLibrary',
                name: '试卷库',
                iconUrl: ''
            }, {
                id: 'itemBank',
                url: '/assess/resourceMgt/itemBank',
                name: '题库',
                iconUrl: ''
            }
        ]
    }
];
