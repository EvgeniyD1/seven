<template>

  <v-card class="mx-auto">
    <div @click="$router.push('/collections/' + collection.id)">
      <v-img
          v-if="!collection.imgUrl"
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
      ></v-img>

      <v-img v-else
             :src=collection.imgUrl
      ></v-img>

      <v-card-title>
        {{ collection.name }}
      </v-card-title>
    </div>

<!--    todo type-->
    <v-card-subtitle>
      {{ collection.type }}
    </v-card-subtitle>

    <v-card-text>
      {{ collection.description }}
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