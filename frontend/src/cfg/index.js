export default {
    install(Vue) {
        Vue.config.globalProperties.$backendUrl = 'http://localhost:8080';
        Vue.config.globalProperties.$handshakeUrl = 'ws://localhost:8080';
    }
}