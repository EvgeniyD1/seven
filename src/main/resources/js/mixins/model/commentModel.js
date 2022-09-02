import itemModel from "./itemModel";

export default {
    data() {
        let item = itemModel.data().item
        return {
            comment: {
                id: '',
                text: '',
                date: '',
                user: {
                    id: '',
                    username: '',
                    email: '',
                    about: '',
                    role: '',
                    status: '',
                    notLock: '',
                    imgUrl: ''
                },
                item
            }
        }
    }
}