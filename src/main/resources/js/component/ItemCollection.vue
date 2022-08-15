<template>

  <v-card class="mx-auto"
  >
    <v-img
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        height="200px"
        cover
    ></v-img>

    <v-card-title>
      kek{{ collection.name }}
    </v-card-title>

    <v-card-subtitle>
      kek{{ collection.type }}
    </v-card-subtitle>

    <v-card-text>
      kek{{ collection.description }}
    </v-card-text>

  </v-card>

</template>

<script>
export default {
  props: {
    collection: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>

</style>