<template>
  <v-container class="w-25 my-16">

    <v-form>
      <div class="text-center my-5">
        <h2 class="font-weight-thin">Log In</h2>
      </div>

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
        Log In
      </v-btn>

      <v-btn
          class="mr-4"
          @click="$router.push('/registration')"
      >
        Create Account
      </v-btn>

    </v-form>

  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      show: false,
      name: '',
      password: '',
    }
  },
  methods: {
    async login(){
      const request = {
        username: this.name,
        password: this.password
      }
      await axios.post('/login', request);
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>

</style>