<template>
    <div class="repository clearfix">
        <div class="menu-box fl">
            <v-menu :menus="menus"></v-menu>
        </div>
        <div class="content-box fl">
            <router-view/>
        </div>
    </div>
</template>

<script>
import VMenu from 'components/VMenu';
// import { menus } from './menu';
export default {
    name: 'repository',
    components: {
        VMenu
    },
    data () {
        return {
            menus: []
        };
    },
    computed: {
        // menus () {
        //     return menus;
        // }
    },
    created () {
        this.getMenu();
    },
    methods: {
        async getMenu () {
            await this.$api.getCurrentMenuTree({ group: 'repo' }).then(res => {
                this.menus = res[0].children;
            });
        }
    }
};
</script>

<style scoped lang="scss">

</style>
