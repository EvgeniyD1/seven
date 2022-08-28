<template>
  <v-form v-model="valid"
          lazy-validation
  >
    <v-file-input
        @input="img = $event.target.files[0]"
        :rules="rules"
        class="mx-3 mt-5"
        accept="image/png, image/jpeg, image/bmp"
        prepend-icon="image"
        label="Avatar"
    ></v-file-input>
    <v-btn class="mx-3 mt-5"
           @click="uploadImg"
           :disabled="!valid"
    >
      Upload
    </v-btn>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      img: '',
      rules: [
        value => {
          return !value || !value.length || value[0].size < 2000000 || 'Avatar size should be less than 2 MB!'
        },
      ],
      valid: true
    }
  },
  props: {
    nameProp: {
      type: String,
      required: true
    },
  },
  emits: ['uploadImgE'],
  methods: {
    async uploadImg() {
      try {
        let url = '/users/' + this.nameProp + '/uploadImg';
        const formData = new FormData();
        formData.append("file", this.img)
        let response = await axios.put(url, formData);
        this.img = response.data
        this.$emit('uploadImgE', this.img, false);
      } catch (e) {
        console.log(e)
      }
    },
  }
}
</script>

<style scoped>

</style>