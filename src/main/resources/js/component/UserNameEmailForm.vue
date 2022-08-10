<template>
    <v-form class="mx-3 mt-5"
            v-model="valid"
            lazy-validation
    >
      <v-text-field
          v-model.trim="name"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
      ></v-text-field>
      <v-text-field
          v-model.trim="email"
          :rules="emailRules"
          label="E-mail"
          required
      ></v-text-field>
      <v-btn
          :disabled="!valid"
          class="mr-4"
          @click="updateProfile"
      >
        Confirm
      </v-btn>
    </v-form>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      valid: true,
    }
  },
  props: {
    nameProp: {
      type: String,
      required: true
    },
    emailProp: {
      type: String,
      required: true
    }
  },
  emits: ['updateP'],
  methods: {
    async updateProfile(){
      try {
        let url = '/users/' + this.nameProp + '/updateProfile';
        let request = {
          username: this.name,
          email: this.email
        }
        await axios.put(url, request);
        this.$emit('updateP', this.name, this.email);
        //todo
        //add logout here or change $router.store.profile
        if (this.$store.state.profile===this.nameProp){
          await axios.post('/logout', {});
        }
      }
      catch (e) {
        console.log(e)
        if (this.$store.state.profile===this.nameProp){
          window.location.href = '/';
        }
      }
    }
  },
  beforeMount() {
    this.name = this.nameProp;
    this.email = this.emailProp;
  }
}
</script>

<style scoped>

</style>