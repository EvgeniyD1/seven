import {createRouter, createWebHashHistory} from "vue-router";
import RegistrationForm from "../pages/RegistrationForm.vue";
import Main from "../pages/Main.vue";
import LoginForm from "../pages/LoginForm.vue";
import UserPage from "../pages/UserPage.vue";
import NotFound from "../pages/NotFound.vue";
import CollectionPage from "../pages/ClusterPage.vue";
import ItemPage from "../pages/ItemPage.vue";
import ErrorPage from "../pages/ErrorPage.vue";
import SearchPage from "../pages/SearchPage.vue";

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
    },
    {
        path: '/:pathMatch(.*)*',
        component: NotFound
    },
    {
        path: '/non-existing',
        component: NotFound
    },
    {
        path: '/collections/:id',
        component: CollectionPage
    },
    {
        path: '/items/:id',
        component: ItemPage
    },
    {
        path: '/error',
        component: ErrorPage
    },
    {
        path: '/search/:text',
        component: SearchPage
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;