<template>
  <v-container fluid>
    <v-row justify="start" align="start">

      <v-col cols="12" sm="6" md="5" lg="3">
        <user-card :user="user"></user-card>
        <cluster-form v-if="this.$store.state.profile===user.username"></cluster-form>
      </v-col>

      <v-col cols="12" sm="6" md="7" lg="9">
        <clusters :collections="collections"></clusters>
        <div ref="observer"></div>
      </v-col>

    </v-row>
  </v-container>
</template>

<script>
import UserCard from "../component/UserCard.vue";
import ClusterForm from "../component/ClusterForm.vue";
import Clusters from "../component/Clusters.vue";
import axios from "axios";

export default {
  components: {Clusters, ClusterForm, UserCard},
  data() {
    return {
      user: {},
      collections: [],

      pageNumber: 0,
      totalPages: 0,
      number: 0,

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

    async loadCollections() {
      if (this.number !== this.totalPages - 1) {
        try {
          let url = '/collections/' + this.$route.params.username;
          let response = await axios.get(url, {
            params: {
              page: this.pageNumber
            }
          });
          console.log(response.data)
          this.collections = [...this.collections, ...response.data.content];

          this.pageNumber = response.data.pageable.pageNumber + 1;
          this.number = response.data.number;
          this.totalPages = response.data.totalPages

        } catch (e) {
          console.log(e)
        }
      }
    },
  },
  beforeMount() {
    this.loadUser()
  },
  mounted() {
    const options = {
      rootMargin: '0px',
      threshold: 1.0
    }
    const callback = (entries, observer) => {
      if (entries[0].isIntersecting) {
        this.loadCollections()
      }
    };
    const observer = new IntersectionObserver(callback, options);
    observer.observe(this.$refs.observer);
  }
}
</script>

<style scoped>
/*.observer{*/
/*  height: 10px;*/
/*  background: red;*/
/*}*/
</style>