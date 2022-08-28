<template>
  <v-row>
    <v-col :cols="isUserPage ? 6 : ''"
           :sm="isUserPage ? 6 : ''"
           :md="isUserPage ? 4 : ''"
           :lg="isUserPage ? 3 : ''"
           v-for="collection in collections"
           :key="collection.id">
      <cluster :collection="collection" :is-user-page="isUserPage"></cluster>
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
    },
    isUserPage: {
      type: Boolean,
      required: false,
      default: false
    }
  }
}
</script>

<style scoped>

</style>