import 'vuetify/styles'
import {createApp} from 'vue'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, md } from 'vuetify/iconsets/md'
import App from './App.vue'
import router from "./router/router";
import store from "./store/store";
import VIntersection from "./directives/VIntersection";

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'md',
        aliases,
        sets: {
            md,
        },
    },
})

createApp(App)
    .directive('intersection', VIntersection)
    .use(store)
    .use(router)
    .use(vuetify)
    .mount("#app");