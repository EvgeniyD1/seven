<template>
  <h3 class="mx-3 my-3 d-flex justify-center">The biggest collections</h3>
  <div class="mx-3 my-3">
    <clusters :collections="collections"></clusters>
  </div>
  <h3 class="mx-3 my-3 d-flex justify-center">Latest updates</h3>
  <div class="mx-3 my-3">
    <item-table-simple :item-props="items"></item-table-simple>
  </div>
</template>

<script>
import Clusters from "../component/Clusters.vue";
import axios from "axios";
import ItemTableSimple from "../component/ItemTableSimple.vue";

export default {
  components: {ItemTableSimple, Clusters},
  data() {
    return {
      collections: [],
      items: []
    }
  },
  methods: {
    async loadCollections() {
      try {
        let url = '/collections';
        let response = await axios.get(url);
        this.collections = response.data.content;
      } catch (e) {
        console.log(e)
      }
    },
    async loadItems() {
      try {
        let url = '/items';
        let response = await axios.get(url);
        this.items = response.data.content;
      } catch (e) {
        console.log(e)
      }
    }
  },
  beforeMount() {
    this.loadCollections()
    this.loadItems()
  }
}
</script>

<style scoped>

</style>