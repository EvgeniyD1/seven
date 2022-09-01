<template>

  <v-card class="mx-auto">
    <div @click="$router.push('/collections/' + collection.id)">
      <v-img
          v-if="!collection.imgUrl"
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
      ></v-img>
      <v-img v-else
             :src="collection.imgUrl"
      ></v-img>
    </div>
    <v-card-title>
      <div class="text-orange-accent-4"
           @click="$router.push('/users/' + collection.user.username)"
           v-if="!isUserPage"
      >
        {{ collection.user.username }}
      </div>
      <div>{{ collection.name }}</div>
    </v-card-title>

    <v-card-subtitle>
      {{ collection.topic }}
    </v-card-subtitle>

    <v-card-text>
      {{ collection.description }}
    </v-card-text>

  </v-card>

</template>

<script>
export default {
  props: {
    collectionProp: {
      type: Object,
      required: true
    },
    isUserPage: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data(){
    return{
      collection: {}
    }
  },
  beforeMount() {
    this.collection = this.collectionProp
  }
}
</script>

<style scoped>

</style>