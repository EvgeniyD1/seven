<template>
  <v-app>
    <v-app-bar>

      <v-toolbar-title>
        <v-btn variant="plain" @click="$router.push('/')">SEVEN</v-btn>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn v-if="this.$store.state.profile"
             @click="$router.push('/users/' + this.$store.state.profile)">{{ this.$store.state.profile }}
      </v-btn>

      <v-btn v-if="!this.$store.state.profile" @click="$router.push('/login')">LOG IN</v-btn>
      <v-btn v-else @click="logout">LOG OUT</v-btn>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
<!--      make rerender, not need $watch in component-->
<!--      <router-view :key="$route.path"></router-view>-->
    </v-main>

  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  methods: {
    async logout() {
      try {
        await axios.post('/logout', {})
        window.location.href = '/'
      }
      catch (e){
        console.log(e)
        // !!!!
        window.location.href = '/'
      }
    }
  }

}
</script>

<style scoped>
body {
  padding-top: 100px;
}
</style>