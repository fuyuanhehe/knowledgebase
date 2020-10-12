const path = require('path');
// 基础路径 注意发布之前要先修改这里
 const publicPath = '/';  // 本地测试路径
// const publicPath = './'; // 服务器打包路径

function resolve (dir) {
    return path.join(__dirname, dir);
}

module.exports = {
    // 部署生产环境和开发环境下的URL。
    publicPath: process.env.NODE_ENV === 'production' ? './' : publicPath,
    // 用于放置生成的静态资源 (js、css、img、fonts) 的；（项目打包之后，静态资源会放在这个文件夹下）
    assetsDir: 'assets',
    // lintOnSave：{ type:Boolean default:true } 是否使用eslint
    lintOnSave: true,
    // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
    productionSourceMap: false,
    css: {
        loaderOptions: {
            // 设置 scss 公用变量文件
            sass: {
                prependData: `@import 'src/assets/css/public_var.scss';`
            }
        }
    },
    // 调整内部的 webpack 配置
    // 默认设置: https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-service/lib/config/base.js。
    chainWebpack: config => {
    // babel-polyfill可以模拟ES6使用的环境,兼容ie
        config.entry.app = ['babel-polyfill', resolve('src/main.js')];
        // 配置路径别名
        config.resolve.alias
            .set('vue$', 'vue/dist/vue.esm.js')
            .set('@', resolve('src'))
            .set('api', resolve('src/api'))
            .set('views', resolve('src/views'))
            .set('utils', resolve('src/utils'))
            .set('assets', resolve('src/assets'))
            .set('components', resolve('src/components'));
    },
    devServer: {
        // host: 'localhost',
        host: '0.0.0.0',
        port: 8585,
        publicPath, // 和 publicPath 保持一致
        // 配置多个代理
        proxy: {
            '/api': {
                target: 'http://192.168.1.52:8080', // 要访问的接口域名
                changeOrigin: true, // 开启代理：在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
                pathRewrite: {
                    '^/api': '' // 这里理解成用'/api'代替target里面的地址,比如我要调用'http://40.00.100.100:3002/user/add'，直接写'/api/user/add'即可
                }
            }
        }
    },
    pluginOptions: {
        'process.env': {
            NODE_ENV: '"development"'
        }
    }
};
