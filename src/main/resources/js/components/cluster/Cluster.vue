<template>

  <v-card class="mx-auto">

    <router-link class="router-link" :to="'/collections/' + collection.id">
      <!--      <v-img v-if="!collection.imgUrl" :src="'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg'"></v-img>-->
      <!--      <v-img v-else :src="collection.imgUrl"></v-img>-->
      <img v-if="!collection.imgUrl" src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" style="max-height: 200px">
      <img v-else :src="collection.imgUrl" style="max-height: 200px">
    </router-link>

    <v-card-title>
      <router-link class="router-link" :to="'/users/' + collection.user.username" v-if="!isUserPage">
        {{ collection.user.username }}
      </router-link>

      <div>
        <router-link class="router-link" :to="'/collections/' + collection.id">
          {{ collection.name }}
        </router-link>
      </div>
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
    collection: {
      type: Object,
      required: true
    },
    isUserPage: {
      type: Boolean,
      required: false,
      default: false
    }
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: #db7f1d;
}

.router-link:hover {
  text-decoration: underline;
}
</style>