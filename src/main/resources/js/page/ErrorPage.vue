<template>
  <v-row>
    <v-col align="center" class="mt-16">
      <h1>Some Error :(</h1>
      <p>Some error has occurred. Try going
        <router-link to="/">home</router-link>
      </p>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "ErrorPage"
}
</script>

<style scoped>

</style>