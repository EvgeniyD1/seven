<template>
  <v-app>
    <v-app-bar>
      <v-btn :icon="theme.global.name === 'dark' ? 'dark_mode' : 'light_mode'" @click="toggleTheme"></v-btn>
      <v-toolbar-title>
        <router-link class="text-decoration-none text-orange-accent-4" to="/">
          <v-btn variant="plain">SEVEN</v-btn>
        </router-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-text-field class="mt-5"
                    v-model="search"
                    clearable
                    clear-icon="clear"
                    label="Find item"
                    density="comfortable"
                    @keyup.enter="$router.push('/search/' + search)"
                    variant="underlined"
      ></v-text-field>

      <router-link class="text-decoration-none text-orange-accent-4" :to="'/search/' + search">
        <v-btn icon="search"></v-btn>
      </router-link>

      <router-link class="text-decoration-none text-orange-accent-4"
                   :to="'/users/' + this.$store.state.profile"
                   v-if="this.$store.state.profile"
      >
        <v-btn>{{ this.$store.state.profile }}</v-btn>
      </router-link>

      <router-link class="text-decoration-none text-orange-accent-4 mr-2"
                   to="/login"
                   v-if="!this.$store.state.profile"
      >
        <v-btn>LOG IN</v-btn>
      </router-link>

      <v-btn v-if="this.$store.state.profile"
             class="text-decoration-none text-orange-accent-4 mr-2"
             @click="logout"
      >LOG OUT</v-btn>

    </v-app-bar>

    <v-main>
      <router-view :key="$route.path"></router-view>
    </v-main>

  </v-app>
</template>

<script>
import axios from "axios";
import {useTheme} from 'vuetify'

export default {
  data() {
    const theme = useTheme()
    return {
      search: '',
      theme,
      toggleTheme: () => {
        theme.global.name.value = theme.global.name.value==='dark' ? 'light' : 'dark'
        this.setCookie()
      }
    }
  },
  methods: {
    async logout() {
      try {
        await axios.post('/logout', {})
        window.location.href = '/'
      } catch (e) {
        console.log(e)
        window.location.href = '/'
      }
    },
    async readCookie() {
      try {
        let response = await axios.get('/readCookie');
        this.theme.global.name = response.data
      } catch (e) {
        console.log(e)
      }
    },
    async setCookie() {
      try {
        let request = {
          theme: this.theme.global.name
        }
        await axios.post('/setCookie', request)
      } catch (e) {
        console.log(e)
      }
    }
  },
  beforeMount() {
    this.readCookie()
  }
}
</script>

<style scoped>
body {
  padding-top: 100px;
}
</style>