<template>
  <v-container class="w-25 my-16">

    <v-form
        v-model="valid"
        lazy-validation
    >
      <div class="text-center my-5">
        <h2 class="font-weight-thin">Registration</h2>
      </div>

      <div class="text-center mb-1 text-red-darken-4">
        <h3 class="font-weight-thin">{{ alert }}</h3>
      </div>

      <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
      ></v-text-field>

      <v-text-field
          v-model="email"
          :rules="emailRules"
          label="E-mail"
          required
      ></v-text-field>

      <v-text-field
          v-model="password"
          :rules="passwordRules"
          :append-inner-icon="show ? 'visibility' : 'visibility_off'"
          @click:append-inner="() => (this.show = !this.show)"
          :type="show ? 'text' : 'password'"
          label="Password"
          required
      ></v-text-field>

      <v-text-field
          v-model="confirmPassword"
          :rules="confirmPasswordRules"
          :append-inner-icon="show ? 'visibility' : 'visibility_off'"
          @click:append-inner="() => (this.show = !this.show)"
          :type="show ? 'text' : 'password'"
          label="Password"
          required
      ></v-text-field>

      <v-btn
          :disabled="!valid"
          class="mr-4"
          @click="createUser"
      >
        Confirm
      </v-btn>

    </v-form>

  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      show: false,
      valid: true,
      alert: '',
      name: '',
      username: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      password: '',
      passwordRules: [
        v => !!v || 'Password is required',
        v => /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(v) ||
            'Password must be contain at least eight characters, at least one uppercase letter, one lowercase letter and one number',
      ],
      confirmPassword: '',
      confirmPasswordRules: [
        v => !!v || 'Password is required',
        v => this.password === this.confirmPassword || 'Passwords must be match',
      ],
    }
  },
  methods: {
    async createUser() {
      this.alert = '';
      let url = '/users/' + this.name;
      const response = await axios.get(url);
      this.username = response.data.username;
      if (this.username === this.name) {
        this.alert = 'A user with the same name already exists';
      } else {
        const request = {
          username: this.name,
          password: this.password,
          email: this.email,
        }
        await axios.post('/registration', request);
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style scoped>

</style>