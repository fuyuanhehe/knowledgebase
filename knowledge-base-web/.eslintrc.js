module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  parserOptions: {
    parser: 'babel-eslint'
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    "indent": [1, 4], // 缩进
    "semi": [ "error", "always"],//必须分号结束！
    'vue/require-v-for-key': 'off',
    'no-extend-native': ["error", { "exceptions": ["Array"] }]
  }
}
