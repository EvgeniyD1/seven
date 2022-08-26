import {createRouter, createWebHashHistory} from "vue-router";
import RegistrationForm from "../page/RegistrationForm.vue";
import Main from "../page/Main.vue";
import LoginForm from "../page/LoginForm.vue";
import UserPage from "../page/UserPage.vue";
import NotFound from "../page/NotFound.vue";
import CollectionPage from "../page/ClusterPage.vue";
import ItemPage from "../page/ItemPage.vue";
import ErrorPage from "../page/ErrorPage.vue";
import SearchPage from "../page/SearchPage.vue";

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