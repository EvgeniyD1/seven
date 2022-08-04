<template>
  <v-app>
    <v-app-bar>
      <v-toolbar-title>SEVEN</v-toolbar-title>
      <v-spacer></v-spacer>
      <strong>{{ profile }}</strong>
    </v-app-bar>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data(){
    return{
      profile
    }
  }
}
</script>

<style scoped>

</style>