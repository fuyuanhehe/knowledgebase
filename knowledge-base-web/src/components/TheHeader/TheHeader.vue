<template>
    <div class="header w100p clearfix">
        <div class="header-logo fl">
            <img src="~assets/images/logo.png" alt="">
        </div>
        <div class="header-nav fl">
            <!--<el-menu :default-active="activeNav"-->
                     <!--class="TheHeader-nav-list"-->
                     <!--mode="horizontal"-->
                     <!--text-color="#C4D6FF"-->
                     <!--active-text-color="#FFFFFF"-->
                     <!--router>-->
                <!--<template v-for="(nav,index) in navs">-->
                    <!--<el-menu-item :key="index"-->
                                  <!--:index="nav.path">{{ nav.title }}-->
                    <!--</el-menu-item>-->
                <!--</template>-->
            <!--</el-menu>-->
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
</template>

<script>
// import { navs } from './navs';

export default {
    data () {
        return {
            username: this.$store.state.user ? this.$store.state.user.nickName : '', // 用户名
            navs: [], // 导航集合
            activeNav: '', // 当前导航
            currentPath: '' // 当前路由地址
        };
    },
    computed: {
        // navs () {
        //     return navs;
        // }
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
            // console.log('pathArr:', pathArr);
            // console.log('pathname:', name);
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
    .header {
        height: 70px;
        background: linear-gradient(-90deg, #009BFF, #016BFF);
        box-shadow: 0 7px 8px 0 rgba(1, 139, 254, 0.2);

        &-logo {
            position: relative;
            width: 15%;
            height: 100%;
            border-right: 1px solid #33A8FF;

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
                /*background-color: transparent !important;*/
                /*border-bottom: transparent !important;*/
                .nav-item {
                    /*width: 120px;*/
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
</style>
