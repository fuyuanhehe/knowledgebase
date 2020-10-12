const modules = {
  'bb-base': 18108,
  'bb-ums': 18106,
  'bb-sqzt': 18101,
  'bb-zcts': 18110,
  'bb-edm': 18111,
  'bb-scdj': 18104,
  'bb-zyfw': 18103
}
// fu：符源  kuang：况克东
const services = [{
  uri: '/ai_apis_',
  orgin: 'http://192.168.0.124'
}, {
  uri: '/ya_apis_',
  orgin: 'http://192.168.0.113'
}, {
  uri: '/dai_apis_',
  orgin: 'http://192.168.0.123'
}, {
  uri: '/zzq_apis_',
  orgin: 'http://192.168.1.19'
}, {
  uri: '/lin_apis_',
  orgin: 'http://192.168.0.162'
}]

const config = []
services.forEach((item) => {
  for (const key in modules) {
    config.push({
      url: item.uri,
      port: modules[key],
      module: key,
      orgin: item.orgin
    })
  }
})

const proxy = {}

config.forEach(item => {
  const target = `${item.orgin}:${item.port}`
  proxy[item.url + item.module] = {}
  proxy[item.url + item.module].target = target
  proxy[item.url + item.module].pathRewrite = {}
  proxy[item.url + item.module].pathRewrite['^' + item.url + item.module] = ''
})

module.exports = proxy
