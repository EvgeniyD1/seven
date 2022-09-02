import clusterModel from "./clusterModel";

export default {
    data() {
        let cluster = clusterModel.data().cluster
        return {
            item: {
                id: '',
                name: '',
                tags: [
                    {
                        id: '',
                        name: ''
                    }
                ],
                imgUrl: '',
                fieldsType: '',
                typeOne: {
                    id: '',
                    fieldOne: '',
                    fieldTwo: '',
                    fieldThree: ''
                },
                typeTwo: {
                    id: '',
                    fieldOne: '',
                    fieldTwo: '',
                    fieldThree: ''
                },
                typeThree: {
                    id: '',
                    fieldOne: '',
                    fieldTwo: '',
                    fieldThree: ''
                },
                typeFour: {
                    id: '',
                    fieldOne: '',
                    fieldTwo: '',
                    fieldThree: ''
                },
                typeFive: {
                    id: '',
                    fieldOne: '',
                    fieldTwo: '',
                    fieldThree: ''
                },
                cluster
            }
        }
    }
}