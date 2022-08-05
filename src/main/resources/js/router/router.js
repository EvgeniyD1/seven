import {createRouter, createWebHistory} from "vue-router";
import RegistrationForm from "../page/RegistrationForm.vue";
import Main from "../page/Main.vue";
import LoginForm from "../page/LoginForm.vue";

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
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;