<template>
  <v-app>
    <v-app-bar>
      <v-toolbar-title>
        <div @click="$router.push('/')">SEVEN</div>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <strong>{{ profile }}</strong>
      <strong>{{ access }}</strong>
      <v-btn v-if="!profile" @click="$router.push('/login')">LOG IN</v-btn>
      <v-btn v-else @click="logout">LOG OUT</v-btn>
    </v-app-bar>
    <v-main>
      <router-view></router-view>

    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      profile,
      access
    }
  },
  methods: {
    async logout() {
      await axios.post('/logout', {})
      this.$router.push('/login');
    }
  }

}
</script>

<style scoped>
body {
  padding-top: 100px;
}
</style>