<template>
  <v-table>
    <thead>
    <tr>
      <th class="text-left" style="width: 200px">
        Img
      </th>
      <th class="text-left">
        Name
      </th>
      <th class="text-left">
        Collection
      </th>
      <th class="text-left">
        Author
      </th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in itemProps" :key="itemProps.id">
      <td>
        <router-link :to="'/items/' + item.id">
          <v-img v-if="item.imgUrl" :src=item.imgUrl></v-img>
          <v-img v-else :src="'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg'"></v-img>
        </router-link>
      </td>

      <td>
        <router-link class="router-link" :to="'/items/' + item.id">
          {{ item.name }}
        </router-link>
      </td>

      <td>
        <router-link class="router-link" :to="'/collections/' + item.cluster.id">
          {{ item.cluster.name }}
        </router-link>
      </td>

      <td>
        <router-link class="router-link" :to="'/users/' + item.cluster.user.username">
          {{ item.cluster.user.username }}
        </router-link>
      </td>
    </tr>
    </tbody>
  </v-table>
</template>

<script>
export default {
  props: {
    itemProps: {
      type: Array,
      required: true
    }
  },
}
</script>

<style scoped>
.router-link{
  text-decoration: none;
  color: #db7f1d;
}
.router-link:hover {
  text-decoration: underline;
}
</style>