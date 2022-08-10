<template>
  <v-row class="mx-3 mt-5">
    <v-checkbox v-model="notLock"
                label="Is Not Blocked"
    ></v-checkbox>
    <v-checkbox v-model="role"
                label="Is Admin"
    ></v-checkbox>
  </v-row>
  <v-btn class="ml-5"
         @click="updateProfile"
  >
    Confirm
  </v-btn>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      notLock: false,
      role: false
    }
  },
  props: {
    blockProp: {
      type: Boolean,
      required: true
    },
    roleProp: {
      type: String,
      required: true
    },
    nameProp: {
      type: String,
      required: true
    },
  },
  emits: ['updateAdminProp'],
  methods: {
    async updateProfile() {
      try {
        let url = '/users/' + this.nameProp + '/updateAdminProp';
        let request = {
          notLock: this.notLock,
          role: this.role ? 'ADMIN' : 'USER'
        }
        await axios.put(url, request);
        this.$emit('updateAdminProp', this.notLock, this.role);
      } catch (e) {
        console.log(e)
      }
    }
  },
  beforeMount() {
    this.notLock = this.blockProp;
    //if add a new role, need to change
    this.role = this.roleProp !== 'USER';
  },
}
</script>

<style scoped>

</style>