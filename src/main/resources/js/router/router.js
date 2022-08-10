import {createRouter, createWebHashHistory} from "vue-router";
import RegistrationForm from "../page/RegistrationForm.vue";
import Main from "../page/Main.vue";
import LoginForm from "../page/LoginForm.vue";
import UserPage from "../page/UserPage.vue";

const routes = [
    {
        path: '/registration',
        component: RegistrationForm
    },
    {
        path: '',
        component: Main
    },
    {
        path: '/login',
        component: LoginForm
    },
    {
        path: '/users/:username',
        component: UserPage
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;