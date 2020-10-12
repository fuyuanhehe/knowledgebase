export default {
    set (name, value) {
        sessionStorage.setItem(name, JSON.stringify(value));
    },
    get (name) {
        return JSON.parse(sessionStorage.getItem(name));
    },
    remove (name) {
        if (name.indexOf(',') > -1) {
            this.keys = name.split(',');
            for (var i = 0; i < this.keys.length; i++) {
                return sessionStorage.removeItem(this.keys[i]);
            }
        } else {
            return sessionStorage.removeItem(name);
        }
    },
    clear () {
        return sessionStorage.clear();
    },
    isLogin () {
        const user = JSON.parse(sessionStorage.getItem('user')) || {};
        if (user.id) {
            return true;
        } else {
            return false;
        }
    }
};
