<template>
  <div id="app">
    <div v-if="!$route.meta.keepAlive" class="app-page">
      <v-header></v-header>
      <router-view v-if="isRouterAlive"/>
    </div>
    <div v-else class="app-other">
      <router-view v-if="isRouterAlive"/>
    </div>
  </div>
</template>

<script>
import TheHeader from 'components/TheHeader/TheHeader.vue';// 头部
export default {
    name: 'App',
    provide () {
        return {
            reload: this.reload
        };
    },
    components: {
        'v-header': TheHeader
    },
    data () {
        return {
            isRouterAlive: true
        };
    },
    methods: {
        /**
         * 声明全局reload方法
         */
        reload () {
            this.isRouterAlive = false;
            this.$nextTick(function () {
                this.isRouterAlive = true;
            });
        }
    }
};
</script>

<style lang="scss">
html, body, #app {
  font-family: "Microsoft YaHei", 微软雅黑;
  font-size: 14px;
  background-color: $bg-color-base;
  height: 100%;
  min-height: 100%;
  .app-page {
    background-color: $bg-color-base;
  }
  .app-other {
    height: 100%;
  }
}
</style>
