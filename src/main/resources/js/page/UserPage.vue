<template>
  <v-container fluid>
    <v-row justify="start" align="start">

      <v-col cols="12" sm="6" md="5" lg="3">
        <user-card :user="mainElement"></user-card>
        <cluster-form v-if="this.$store.state.profile===mainElement.username"></cluster-form>
      </v-col>

      <v-col cols="12" sm="6" md="7" lg="9">
        <clusters :collections="elements" :is-user-page="true"></clusters>
        <div v-intersection="loadElements"></div>
      </v-col>

    </v-row>
  </v-container>
</template>

<script>
import UserCard from "../component/UserCard.vue";
import ClusterForm from "../component/ClusterForm.vue";
import Clusters from "../component/Clusters.vue";
import loadingMixin from "../mixins/loadingMixin";
import mainElementMixin from "../mixins/mainElementMixin";

export default {
  components: {Clusters, ClusterForm, UserCard},
  mixins: [loadingMixin, mainElementMixin],
  beforeMount() {
    this.mainElementPath = '/users/' + this.$route.params.username
    this.loadMainElement()
    this.path = '/collections/' + this.$route.params.username
  },
}
</script>

<style scoped>

</style>