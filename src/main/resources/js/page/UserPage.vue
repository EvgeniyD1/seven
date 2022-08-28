<template>
  <v-container fluid>
    <v-row justify="start" align="start">

      <v-col cols="12" sm="6" md="5" lg="3">
        <user-card :user="user"></user-card>
        <cluster-form v-if="this.$store.state.profile===user.username"></cluster-form>
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
import axios from "axios";
import loadingMixin from "../mixins/loadingMixin";

export default {
  components: {Clusters, ClusterForm, UserCard},
  mixins: [loadingMixin],
  data() {
    return {
      user: {},
    }
  },
  methods: {
    async loadUser() {
      try {
        let url = '/users/' + this.$route.params.username;
        let response = await axios.get(url);
        if (response.data === '') {
          this.$router.push('/non-existing')
        }
        console.log(response.data)
        this.user = {
          username: response.data.username,
          email: response.data.email,
          status: response.data.status,
          about: response.data.about,
          notLock: response.data.notLock,
          role: response.data.role,
          imgUrl: response.data.imgUrl
        }
      } catch (e) {
        console.log(e)
      }
    },
  },
  beforeMount() {
    this.loadUser()
    this.path = '/collections/' + this.$route.params.username
  },
}
</script>

<style scoped>

</style>