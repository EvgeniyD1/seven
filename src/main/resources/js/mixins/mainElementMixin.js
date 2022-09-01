import axios from "axios";

export default {
    data() {
        return {
            mainElement: {},
            mainElementPath: ''
        }
    },
    methods: {
        async loadMainElement() {
            try {
                let response = await axios.get(this.mainElementPath);
                if (!response.data) {
                    this.$router.push('/non-existing')
                } else {
                    this.mainElement = response.data;
                }
            } catch (e) {
                console.log(e)
                // this.$router.push('/error')
            }
        },
    }
}