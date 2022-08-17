<template>
  <v-row>
    <v-col cols="12" sm="6" md="4" lg="3"
           v-for="collection in collections"
           :key="collection">
      <cluster :collection="collection"></cluster>
    </v-col>
  </v-row>

</template>

<script>
import Cluster from "./Cluster.vue";

export default {
  components: {Cluster},
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