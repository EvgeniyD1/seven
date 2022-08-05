<template>
  <v-container class="w-25 my-16">

    <v-form>
      <div class="text-center my-5">
        <h2 class="font-weight-thin">Log In</h2>
      </div>

      <div class="text-center mb-1 text-red-darken-4">
        <h3 class="font-weight-thin">{{ alert }}</h3>
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
      alert: ''
    }
  },
  methods: {
    async login(){
      try {
        const request = {
          username: this.name,
          password: this.password
        }
        await axios.post('/login', request);
        //!!!!!!
        window.location.href = '/'
      }
      catch (e){
        //!!!!!!
        this.alert = 'Bad credentials'
      }
    }
  }
}
</script>

<style scoped>

</style>