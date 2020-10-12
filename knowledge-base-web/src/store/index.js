import Vue from 'vue';
import Vuex from 'vuex';
import session from 'utils/session';

Vue.use(Vuex);

/**
 * 数组json转map
 * @param json
 * @returns {Map<any, any>}
 */
let arrayToMap = (json)=>{
  let mapData = new Map();
  if (json == null) {
    return mapData;
  }
  let arrayData = JSON.parse(json);
  for (let i = 0,len = arrayData.length; i < len; i++) {
    mapData.set(arrayData[i][0], arrayData[i][1]);
  }
  return mapData;
}

export default new Vuex.Store({
    state: {
        // 用户记住密码的历史记录
        userHistoryRecord:  localStorage.getItem("userHistoryRecord") == null ? new Map() : arrayToMap(localStorage.getItem("userHistoryRecord")),
        user: session.get('user') ? session.get('user') : null
    },
    getters: {
        user: state => {
            return state.user;
        }
    },
    mutations: {
        // 修改token，并将token存入sessionStorage
        changeLogin (state, user) {
            state.user = user;
            session.set('user', user);
        },
        DelToken (state) {
            session.remove('user');
            state.user = null;
        },
        LOGOUT (state) {
            session.clear();
            state.user = null;
        },
        // 更新用户记住密码的历史记录
        changeUserHistoryRecord (state, user) {
          const userHistoryRecordMap = state.userHistoryRecord;
            userHistoryRecordMap.set(user.name, user.password);
            state.userHistoryRecord = userHistoryRecordMap;
            //注意此处JSON.stringify会将map转换成array字符串
            localStorage.setItem("userHistoryRecord", JSON.stringify(state.userHistoryRecord));
        }
    },
    actions: {
        changeLogin (context) {
            context.commit('changeLogin');
        },
        DelToken (context) {
            setTimeout(function () {
                context.commit('DelToken');
            }, 10);
        },
        LOGOUT (context) {
            setTimeout(function () {
                context.commit('LOGOUT');
            }, 10);
        }
    }
});
