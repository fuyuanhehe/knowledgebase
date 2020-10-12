<template>
    <div class="menu">
        <el-menu :default-active="activeMenu"
                 class="menu-list"
                 text-color="#575757"
                 active-text-color="#028DFD"
                 router>
            <template v-for="menu in menus">
                <!-- 带二级菜单的一级菜单 -->
                <el-submenu
                        v-if="menu.children && menu.children.length > 0"
                        :key="menu.id"
                        :index="menu.url">
                    <template slot="title">
                        <img :src="menu.iconUrl" alt="">
                        <span class="menu-title menu-title-first">{{ menu.name }}</span>
                    </template>
                    <!-- 二级菜单 -->
                    <el-menu-item
                            v-for="m in menu.children"
                            :key="m.id"
                            :index="m.url">
                        <template slot="title">
                            <img :src="m.iconUrl" alt="">
                            <span class="menu-title menu-title-second">{{ m.name }}</span>
                        </template>
                    </el-menu-item>
                </el-submenu>
                <!-- 无二级菜单的一级菜单 -->
                <!--<el-menu-item-->
                        <!--v-else-->
                        <!--:key="menu.id"-->
                        <!--:index="menu.url">-->
                    <!--<img :src="menu.iconUrl" alt="">-->
                    <!--<span slot="title" class="menu-title-first">{{ menu.name }}</span>-->
                <!--</el-menu-item>-->
            </template>
        </el-menu>
    </div>
</template>

<script>
export default {
    name: 'VMenu',
    props: {
        // 菜单集合
        menus: {
            type: Array,
            default: () => []
        }
    },
    data () {
        return {
            activeMenu: ''
        };
    },
    watch: {
        '$route.name' () {
            this.activeMenu = this.$route.path;
        }
    },
    created () {
        this.activeMenu = this.$route.path;
    }
};
</script>

<style scoped lang="scss">
    .menu {
        .menu-list {
            text-align: left;
            height: 1000px;
            overflow: auto;
            .menu-title {
            }
            .menu-title-first {
                margin-left: 20px;
            }
            .menu-title-second {
                margin-left: 25px;
            }
        }
    }
</style>
