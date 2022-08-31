<template>
  <v-container fluid class="mt-16">

    <div class="text-center my-5">
      <h2 class="font-weight-thin">Log In</h2>
    </div>

    <div class="text-center mb-1 text-orange-lighten-2">
      <h3 class="font-weight-thin">{{ alert }}</h3>
    </div>

    <v-row justify="center">
      <v-col cols="4" sm="10" md="8" lg="4">
        <v-form>

          <v-text-field
              v-model="name"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              v-model="password"
              :append-inner-icon="show ? 'visibility' : 'visibility_off'"
              @click:append-inner="() => (this.show = !this.show)"
              :type="show ? 'text' : 'password'"
              label="Password"
              required
          ></v-text-field>

          <v-btn
              class="mr-4"
              @click="login"
          >
            LOG IN
          </v-btn>

          <v-btn
              class="mr-4"
              @click="$router.push('/registration')"
          >
            SING UP
          </v-btn>

        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      show: false,
      name: '',
      password: '',
      alert: ''
    }
  },
  methods: {
    async login() {
      try {
        const request = {
          username: this.name,
          password: this.password
        }
        await axios.post('/login', request);
        window.location.href = '/'
      } catch (e) {
        this.alert = e.response.data
      }
    }
  }
}
</script>

<style scoped>

</style>