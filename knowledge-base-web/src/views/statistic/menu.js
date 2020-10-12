export const menus = [
    {
        id: 'statistic',
        url: '/statistic',
        name: '统计分析',
        iconUrl: 'static/images/menu-icon-statistic.png',
        children: [
            {
                id: 'staffStatistic',
                url: '/statistic/staffStatistic',
                name: '员工统计',
                iconUrl: ''
            }, {
                id: 'testStatistic',
                url: '/statistic/testStatistic',
                name: '测评考试统计',
                iconUrl: ''
            }, {
                id: 'repositoryStatistic',
                url: '/statistic/repositoryStatistic',
                name: '知识仓库统计',
                iconUrl: ''
            }
        ]
    }
];
