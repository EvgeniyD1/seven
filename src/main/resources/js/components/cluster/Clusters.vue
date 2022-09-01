<template>
  <v-row>
    <v-col :cols="isUserPage ? 6 : ''"
           :sm="isUserPage ? 6 : ''"
           :md="isUserPage ? 4 : ''"
           :lg="isUserPage ? 3 : ''"
           v-for="collection in collections"
           :key="collection.id">
      <cluster :collection-prop="collection" :is-user-page="isUserPage"></cluster>
    </v-col>
  </v-row>

</template>

<script>
import Cluster from "./Cluster.vue";

export default {
  components: {Cluster},
  props: {
    collectionsProp: {
      type: Array,
      required: true
    },
    isUserPage: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data(){
    return{
      collections: []
    }
  },
  beforeMount() {
    this.collections = this.collectionsProp
  }
}
</script>

<style scoped>

</style>