<template>
  <div class="my-3 mx-3">
    <item-table-simple :item-props="items"></item-table-simple>
    <div v-intersection="full"></div>
  </div>
</template>

<script>
import axios from "axios";
import ItemTableSimple from "../component/ItemTableSimple.vue";

export default {
  components: {ItemTableSimple},
  data() {
    return {
      items: [],
      pageNumber: 0,
      totalPages: 0,
      number: 0,
    }
  },
  methods: {
    async full() {
      if (this.number !== this.totalPages - 1) {
        try {
          let url = '/items/full/' + this.$route.params.text.trim().replace(/\s/, ' & ');
          let response = await axios.get(url, {
            params: {
              page: this.pageNumber
            }
          });
          console.log(response)
          if (!response.data) {
            this.$router.push('/non-existing');
          } else {
            this.items = [...this.items, ...response.data.content]
            this.pageNumber = response.data.pageable.pageNumber + 1;
            this.number = response.data.number;
            this.totalPages = response.data.totalPages;
          }
        } catch (e) {
          console.log(e)
        }
      }
    }
  }
}
</script>

<style scoped>

</style>