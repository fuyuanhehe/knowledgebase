/**
 * @file 统计分析模块路由配置文件
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/statistic',
        name: 'statistic',
        component: () => import('views/statistic/statistic.vue'),
        meta: { title: '统计分析', auth },
        redirect: '/statistic/staffStatistic',
        children: [
            {
                path: 'staffStatistic',
                name: 'staffStatistic',
                component: () => import('views/statistic/staffStatistic.vue'),
                meta: { title: '统计分析-员工统计', auth }
            },
            {
                path: 'testStatistic',
                name: 'testStatistic',
                component: () => import('views/statistic/testStatistic.vue'),
                meta: { title: '统计分析-测评考试统计', auth }
            },
            {
                path: 'repositoryStatistic',
                name: 'repositoryStatistic',
                component: () => import('views/statistic/repositoryStatistic.vue'),
                meta: { title: '统计分析-知识仓库统计', auth }
            }
        ]
    }
];
