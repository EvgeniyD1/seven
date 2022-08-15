<template>
  <v-row>
    <v-col cols="12" sm="6" md="4" lg="3"
           v-for="collection in collections"
           :key="collection">
      <item-collection :collection="collection"></item-collection>
    </v-col>
  </v-row>

</template>

<script>
import ItemCollection from "./ItemCollection.vue";

export default {
  components: {ItemCollection},
  props: {
    collections: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>

</style>