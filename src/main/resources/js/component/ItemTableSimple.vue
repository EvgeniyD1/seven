<template>
  <v-table>
    <thead>
    <tr>
      <th class="text-left" style="width: 200px">
        Img
      </th>
      <th class="text-left">
        Author
      </th>
      <th class="text-left">
        Collection
      </th>
      <th class="text-left">
        Name
      </th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in itemProps" :key="itemProps.id" >
      <td>
        <v-img v-if="!item.imgUrl" src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
               @click="$router.push('/items/' + item.id)"></v-img>
        <v-img v-if="item.imgUrl" :src=item.imgUrl
               @click="$router.push('/items/' + item.id)"></v-img>
      </td>

      <td @click="$router.push('/users/' + item.cluster.user.username)">{{item.cluster.user.username}}</td>

      <td @click="$router.push('/collections/' + item.cluster.id)">{{item.cluster.name}}</td>

      <td>{{ item.name }}</td>
    </tr>
    </tbody>
  </v-table>
</template>

<script>
export default {
  props:{
    itemProps:{
      type: Array,
      required: true
    }
  },
}
</script>

<style scoped>

</style>