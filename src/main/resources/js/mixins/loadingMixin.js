import axios from "axios";

export default {
    data() {
        return {
            elements: [],
            pageNumber: 0,
            totalPages: 0,
            currentNumber: 0,
            sort: '',
            order: '',
            path: '',
            showElements: true
        }
    },
    methods: {
        async loadElements() {
            if (this.currentNumber !== this.totalPages - 1) {
                try {
                    let response = await axios.get(this.path, {
                        params: {
                            page: this.pageNumber,
                            sort: this.sort + ',' + this.order,
                        }
                    });
                    if (!response.data || response.data.content.length===0) {
                        this.showElements = false
                    } else {
                        console.log(response.data)
                        this.elements = [...this.elements, ...response.data.content];
                        this.pageNumber = response.data.pageable.pageNumber + 1;
                        this.currentNumber = response.data.number;
                        this.totalPages = response.data.totalPages;
                        this.showElements = true;
                    }
                } catch (e) {
                    console.log(e)
                }
            }
        },
    }
}