<template>
  <v-form class="mx-3 mt-5"
          v-model="valid"
          lazy-validation
  >
    <v-textarea
        v-model="about"
        :counter="255"
        :rules="aboutRules"
        label="About yourself"
        required
    ></v-textarea>
    <v-btn
        :disabled="!valid"
        class="mr-4"
        @click="updateAbout"
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
      about: '',
      aboutRules: [
        v => !!v || 'Field must not be empty',
        v => (v.length <= 255) || 'Section must be less than 255 characters',
      ],
      valid: true,
    }
  },
  props: {
    nameProp: {
      type: String,
      required: true
    },
  },
  emits: ['updateA'],
  methods: {
    async updateAbout(){
      try {
        let url = '/users/' + this.nameProp + '/updateAbout';
        let request = {
          about: this.about
        }
        await axios.put(url, request);
        this.$emit('updateA', this.about, false);
      }
      catch (e) {
        console.log(e)
      }
    }
  },
}
</script>

<style scoped>

</style>