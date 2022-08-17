<template>
  <v-container fluid>
    <v-row justify="start" align="start">
      <v-col cols="12" sm="6" md="5" lg="3" v-if="collection.id">
        <item-collection :collection="collection"></item-collection>
        <item-collection-form :edit-form="true"
                              :collection-props="collection"
                              v-if="this.$store.state.profile===collection.user.username || this.$store.state.access==='ADMIN'"
                              @updateCollection="updateCollection"
                              @getUrl="getUrl"
        ></item-collection-form>
      </v-col>
      <v-col cols="12" sm="6" md="7" lg="9">
        some cringe
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ItemCollection from "../component/Cluster.vue";
import ItemCollectionForm from "../component/ClusterForm.vue";

import axios from "axios";

export default {
  components: {ItemCollection, ItemCollectionForm},
  data() {
    return {
      collection: {}
    }
  },
  methods: {
    updateCollection(collection) {
      this.collection = collection;
    },
    getUrl(url) {
      this.collection.imgUrl = url;
    },
    async loadCollection() {
      try {
        let response = await axios.get('/collections/collection/' + this.$route.params.id)
        if (response.data === '') {
          this.$router.push('/non-existing')
        } else {
          this.collection = response.data
          console.log(response)
        }
      } catch (e) {
        console.log(e)
        //todo need to add errors page
        // this.$router.push('/non-existing')
      }
    }
  },
  beforeMount() {
    this.loadCollection()
  }
}
</script>

<style scoped>

</style>