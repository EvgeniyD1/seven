import userModel from "./userModel";

export default {
    data() {
        let user = userModel.data().user
        return {
            cluster: {
                id: '',
                name: '',
                description: '',
                imgUrl: '',
                topic: '',
                fieldsType: '',
                fieldOne: '',
                fieldTwo: '',
                fieldThree: '',
                user
            }
        }
    }
}