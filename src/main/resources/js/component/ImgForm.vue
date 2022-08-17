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
        label="Image"
    ></v-file-input>
    <v-btn class="mx-3 my-3"
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
          return !value || !value.length || value[0].size < 2000000 || 'Image size should be less than 2 MB!'
        },
      ],
      valid: true,
      uploadUrl: ''
    }
  },
  props: {
    urlProp: {
      type: String,
      required: true
    }
  },
  emits:['uploadImg'],
  methods: {
    async uploadImg() {
      let url = this.uploadUrl;
      const formData = new FormData();
      formData.append("file", this.img);
      let response = await axios.put(url , formData);
      this.$emit('uploadImg', response.data, false);
      this.img = ''
    }
  },
  beforeMount() {
    this.uploadUrl = this.urlProp;
  }
}
</script>

<style scoped>

</style>