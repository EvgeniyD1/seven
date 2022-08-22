<template>
  <item-table :collection-props="item[0].cluster"
              :item-props="item"
              v-if="item[0]"
  ></item-table>
</template>

<script>
import ItemTable from "../component/ItemTable.vue";
import axios from "axios";

export default {
  components: {ItemTable},
  data() {
    return {
      item: []
    }
  },
  methods: {
    async getItem(){
      let response = await axios.get('/items/' + this.$route.params.id)
      this.item.push(response.data);
      console.log(this.item)
      // this.item = response.data
    }
  },
  beforeMount() {
    this.getItem()
  }
}
</script>

<style scoped>

</style>