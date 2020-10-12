<!--
@file：首页
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="home">
        <div class="home-banner">
            <div class="header w100p clearfix">
                <div class="header-logo fl">
                    <img src="~assets/images/logo.png" alt="">
                </div>
                <div class="header-nav fl">
                    <ul class="nav-list clearfix">
                        <li class="nav-item cursor_p fl"
                            :class="isCurrentNav(nav.reserve) ? 'nav-item-active' : ''"
                            v-for="(nav, index) in navs"
                            :key="index"
                            @click="toRouter(nav)">
                            <span class="nav-item-title">{{ nav.name }}</span>
                            <span class="nav-item-line" v-if="isCurrentNav(nav.reserve)">
                        <span></span>
                    </span>
                        </li>
                    </ul>
                </div>
                <div class="header-login fr">
                    <img src="~assets/images/user.png" alt="">
                    <span>{{ username }}</span>
                    <div class="display_ib cursor_p" @click="loginOut">
                        <img src="~assets/images/logout.png" alt="">
                        <span>退出</span>
                    </div>
                </div>
            </div>
            <div class="banner-text">
                <h1>结构化知识库管理系统</h1>
                <span>KNOWLEDGE BASE MANAGEMENT SYSTEM</span>
            </div>
        </div>
        <div class="home-content">
            <div class="clearfix mb50">
                <newsArea class="w70p fl"></newsArea>
                <noticeArea class="w26p fr"></noticeArea>
            </div>
            <div class="clearfix">
                <learningArea class="w70p fl"></learningArea>
                <examArea class="w26p fr"></examArea>
            </div>
        </div>
    </div>
</template>

<script>
import newsArea from './components/newsArea';
import noticeArea from './components/noticeArea';
import learningArea from './components/learningArea';
import examArea from './components/examArea';
export default {
    name: 'home',
    components: {
        newsArea,
        noticeArea,
        learningArea,
        examArea
    },
    data () {
        return {
            username: this.$store.state.user ? this.$store.state.user.nickName : '', // 用户名
            navs: [], // 导航集合
            activeNav: '', // 当前导航
            currentPath: '' // 当前路由地址
        };
    },
    watch: {
        '$route.name' () {
            this.activeNav = this.$route.name;
        },
        '$route.path' () {
            this.currentPath = this.$route.path;
        }
    },
    created () {
        this.activeNav = this.$route.name;
        this.getNavs();
    },
    beforeRouteEnter (to, from, next) {
        next(vm => {
            vm.activeNav = to.name;
        });
    },
    methods: {
        async getNavs () {
            this.navs = await this.$api.getAllCurrentMenu({ pid: '0' });
        },
        /**
         * 导航路由跳转
         * @param nav 当前导航对象
         */
        toRouter (nav) {
            this.$router.push({
                path: nav.url
            });
        },
        /**
         * 判断当前页面是否属于当前导航,是则高亮
         * @param name 当前导航名
         * @returns {boolean} true: 当前页面属于当前导航，false:反之
         */
        isCurrentNav (name) {
            const pathArr = this.currentPath.split('/');
            let flg = false;
            if (pathArr.includes(name)) {
                flg = true;
            }
            return flg;
        },
        /**
         * 退出登录
         */
        loginOut: function () {
            this.$confirm('是否退出系统？', '退出', {
                center: true
            }).then(() => {
                this.$store.commit('LOGOUT');
                this.$message.success('退出成功');
                this.$router.push({ name: 'login' });
            }).catch(() => {
                this.$message.info('已取消退出');
            });
        }
    }
};
</script>

<style scoped lang="scss">
.home {
    background: #fff;
    &-banner {
        width: 100%;
        height: 480px;
        background: url('~assets/images/home-banner.png');
        background-size: cover;
        .header {
            height: 70px;
            background: transparent;

            &-logo {
                position: relative;
                width: 15%;
                height: 100%;

                img {
                    position: absolute;
                    left: 50%;
                    top: 50%;
                    transform: translate(-50%, -50%);
                }
            }
            &-nav {
                .nav-list {
                    height: 100%;
                    margin-left: 24px;
                    .nav-item {
                        height: 100%;
                        padding: 0 20px;
                        line-height: 70px;
                        text-align: center;
                        font-size:18px;
                        font-weight:400;
                        color:rgba(196,214,255,1);
                        &-active {
                            color: $color-text-white;
                        }
                        &-line {
                            display: block;
                            width: 100%;
                            height: 3px;
                            line-height: 3px;
                            text-align: center;
                            margin-top: -18px;
                            span {
                                display: inline-block;
                                width:36px;
                                height:3px;
                                background: $color-text-white;
                                border-radius:2px;
                            }
                        }
                    }
                }
            }
            &-login {
                position: relative;
                height: 100%;
                line-height: 70px;
                vertical-align: middle;
                img {
                    vertical-align: middle;
                    margin-right: 14px;
                }
                span {
                    margin-right: 40px;
                    color: $color-text-white;
                }
            }
        }
        .banner-text {
            width: 1200px;
            margin: 80px auto;
            text-align: center;
            color: #fff;
            &>h1 {
                font-size: 80px;
                letter-spacing: 10px;
                margin-bottom: 40px;
            }
            &>span {
                font-size: 22px;
                line-height:26px;
                letter-spacing: 5px;
            }
        }
    }
    &-content {
        width: 1200px;
        margin: 0 auto;
        padding: 50px 0 80px;
    }
}
</style>
