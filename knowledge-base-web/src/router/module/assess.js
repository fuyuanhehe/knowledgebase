/**
 * @file 知识测评模块路由配置文件
 * @createTime 2020-03-03
 * @updateTime: 2020-04-13
 * @author Claire
 */
const auth = true;
export default [
    {
        path: '/assess',
        name: 'assess',
        component: () => import('views/assess/assess.vue'),
        meta: { title: '知识测评', auth },
        redirect: '/assess/assessMgt/learningCenter',
        children: [
            // 知识测评-学习中心
            {
                path: 'assessMgt/learningCenter',
                name: 'assessMgtLearningCenter',
                component: () => import('views/assess/assessMgt/learningCenter/learningCenter.vue'),
                meta: { title: '知识测评-学习中心', auth }
            },
            // 知识测评-学习中心-学习详情
            {
                path: 'assessMgt/learningDetail',
                name: 'assessMgtLearningDetail',
                component: () => import('views/assess/assessMgt/learningCenter/learningDetail.vue'),
                meta: { title: '知识测评-学习详情', auth }
            },
            // 知识测评-学习中心-学习详情-课程详情
            {
                path: 'assessMgt/learningShow',
                name: 'assessMgtLearningShow',
                component: () => import('views/assess/assessMgt/learningCenter/learningShow.vue'),
                meta: { title: '知识测评-课程详情', auth }
            },
            // 知识测评-考试中心
            {
                path: 'assessMgt/testingCenter',
                name: 'assessMgtTestingCenter',
                component: () => import('views/assess/assessMgt/testingCenter/testingCenter.vue'),
                meta: { title: '知识测评-考试中心', auth }
            },
            // 知识测评-考试中心-参加考试
            {
                path: 'assessMgt/takeExam',
                name: 'assessMgtTakeExam',
                component: () => import('views/assess/assessMgt/testingCenter/takeExam.vue'),
                meta: { title: '知识测评-参加考试', auth }
            },
            // 知识测评-考试中心-考试详情（查看解析）
            {
                path: 'assessMgt/examDetail',
                name: 'assessMgtExamDetail',
                component: () => import('views/assess/assessMgt/testingCenter/examDetail.vue'),
                meta: { title: '知识测评-考试详情', auth }
            },
            // 知识测评-考试中心-交卷成功
            {
                path: 'assessMgt/examCompleted',
                name: 'assessMgtExamCompleted',
                component: () => import('views/assess/assessMgt/testingCenter/examCompleted.vue'),
                meta: { title: '知识测评-交卷成功', auth }
            },
            // 知识测评-资源管理
            {
                path: 'resourceMgt',
                name: 'assessResourceMgt',
                component: () => import('views/assess/resourceMgt/learningResource/learningResource.vue'),
                meta: { title: '知识测评-资源管理', auth },
                redirect: '/assess/resourceMgt/learningResource'
            },
            // 知识测评-学习资源
            {
                path: 'resourceMgt/learningResource',
                name: 'assessResourceMgtLearningResource',
                component: () => import('views/assess/resourceMgt/learningResource/learningResource.vue'),
                meta: { title: '知识测评-资源管理', auth }
            },
            // 知识测评-学习资源-新增/编辑
            {
                path: 'resourceMgt/learningResource/:type',
                name: 'assessResourceMgtLearningResourceEdit',
                component: () => import('views/assess/resourceMgt/learningResource/learningResourceEdit.vue'),
                meta: { title: '知识测评-资源管理', auth }
            },
            // 知识测评-考试管理
            {
                path: 'resourceMgt/testMgt',
                name: 'assessResourceMgtTestMgt',
                component: () => import('views/assess/resourceMgt/examMgt/examMgt.vue'),
                meta: { title: '知识测评-考试管理', auth }
            },
            // 知识测评-编辑考试
            {
                path: 'resourceMgt/examEdit/:type',
                name: 'assessResourceMgtExamEdit',
                component: () => import('views/assess/resourceMgt/examMgt/examEdit.vue'),
                meta: { title: '知识测评-编辑考试', auth }
            },
            // 知识测评-考试统计
            {
                path: 'resourceMgt/examStatistic',
                name: 'assessResourceMgtExamStatistic',
                component: () => import('views/assess/resourceMgt/examMgt/examStatistic.vue'),
                meta: { title: '知识测评-考试统计', auth }
            },
            // 知识测评-考试答题详情
            {
                path: 'resourceMgt/examAnswerDetail',
                name: 'assessResourceMgtExamAnswerDetail',
                component: () => import('views/assess/resourceMgt/examMgt/examAnswerDetail.vue'),
                meta: { title: '知识测评-考试答题详情', auth }
            },
            // 知识测评-试卷库
            {
                path: 'resourceMgt/testPaperLibrary',
                name: 'assessResourceMgtTestPaperLibrary',
                component: () => import('views/assess/resourceMgt/testPaperLibrary/testPaperLibrary.vue'),
                meta: { title: '知识测评-试卷库', auth }
            },
            // 知识测评-试卷编辑
            {
                path: 'resourceMgt/testPaperEdit/:type/:id',
                name: 'assessResourceMgtTestPaperEdit',
                component: () => import('views/assess/resourceMgt/testPaperLibrary/testPaperEdit.vue'),
                meta: { title: '知识测评-试卷编辑', auth }
            },
            // 知识测评-试卷预览
            {
                path: 'resourceMgt/testPaperDetail/:id',
                name: 'assessResourceMgtTestPaperDetail',
                component: () => import('views/assess/resourceMgt/testPaperLibrary/testPaperDetail.vue'),
                meta: { title: '知识测评-试卷预览', auth }
            },
            // 知识测评-题库
            {
                path: 'resourceMgt/itemBank',
                name: 'assessResourceMgtItemBank',
                component: () => import('views/assess/resourceMgt/subjectBank/subjectBank.vue'),
                meta: { title: '知识测评-题库', auth }
            },
            // 知识测评-题目编辑
            {
                path: 'resourceMgt/itemBank/subjectEdit/:type',
                name: 'assessResourceMgtSubjectEdit',
                component: () => import('views/assess/resourceMgt/subjectBank/subjectEdit.vue'),
                meta: { title: '知识测评-题目编辑', auth }
            },
            // 知识测评-题目预览
            {
                path: 'resourceMgt/itemBank/subjectDetail',
                name: 'assessResourceMgtSubjectDetail',
                component: () => import('views/assess/resourceMgt/subjectBank/subjectDetail.vue'),
                meta: { title: '知识测评-题目预览', auth }
            }
        ]
    },
    // 知识测评-考试中心-考试详情(参加考试)
    {
        path: '/assess/assessMgt/testing',
        name: 'assessMgtTesting',
        component: () => import('views/assess/assessMgt/testingCenter/testing.vue'),
        meta: { title: '知识测评-考试详情', auth, keepAlive: true }
    },
];
