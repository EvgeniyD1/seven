<template>
  <div align="center" class="mt-16" v-show="showElements===false">
    <h1>Not Found</h1>
    <p>Oops, we couldn't find a item. Try to find something else :)</p>
  </div>
  <div class="my-3 mx-3" v-show="showElements===true">
    <item-table-simple :item-props="elements"></item-table-simple>
    <div v-intersection="loadElements"></div>
  </div>
</template>

<script>
import ItemTableSimple from "../components/item/ItemTableSimple.vue";
import loadingMixin from "../mixins/loadingMixin";

export default {
  components: {ItemTableSimple},
  mixins: [loadingMixin],
  beforeMount() {
    this.path = '/items/full/' + this.$route.params.text.trim().replace(/\s/, ' & ')
  },
}
</script>

<style scoped>

</style>