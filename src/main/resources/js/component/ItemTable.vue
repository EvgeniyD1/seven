<template>
  <v-table>
    <thead>
    <tr>
      <th class="text-left" width="200">
        Img
      </th>
      <th class="text-left" v-if="!collectionPage">
        Author
      </th>
      <th class="text-left" v-if="!collectionPage">
        Collection
      </th>
      <th class="text-left">
        Name
      </th>
      <th class="text-left">
        Tag
      </th>
      <th class="text-left">
        {{collectionProps.fieldOne}}
      </th>
      <th class="text-left">
        {{collectionProps.fieldTwo}}
      </th>
      <th class="text-left">
        {{collectionProps.fieldThree}}
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

      <td @click="$router.push('/users/' + item.cluster.user.username)"
          v-if="!collectionPage"
      >{{item.cluster.user.username}}</td>

      <td @click="$router.push('/collections/' + item.cluster.id)"
          v-if="!collectionPage"
      >{{item.cluster.name}}</td>

      <td>{{ item.name }}</td>
      <td>{{ item.tag }}</td>

      <td v-if="item.fieldsType==='INTEGER'">{{ item.typeOne.fieldOne }}</td>
      <td v-if="item.fieldsType==='INTEGER'">{{ item.typeOne.fieldTwo }}</td>
      <td v-if="item.fieldsType==='INTEGER'">{{ item.typeOne.fieldThree }}</td>

      <td v-if="item.fieldsType==='TEXT'">{{ item.typeTwo.fieldOne }}</td>
      <td v-if="item.fieldsType==='TEXT'">{{ item.typeTwo.fieldTwo }}</td>
      <td v-if="item.fieldsType==='TEXT'">{{ item.typeTwo.fieldThree }}</td>

      <td v-if="item.fieldsType==='MULTILINE_TEXT'">{{ item.typeThree.fieldOne }}</td>
      <td v-if="item.fieldsType==='MULTILINE_TEXT'">{{ item.typeThree.fieldTwo }}</td>
      <td v-if="item.fieldsType==='MULTILINE_TEXT'">{{ item.typeThree.fieldThree }}</td>

      <td v-if="item.fieldsType==='BOOLEAN'">{{ item.typeFour.fieldOne }}</td>
      <td v-if="item.fieldsType==='BOOLEAN'">{{ item.typeFour.fieldTwo }}</td>
      <td v-if="item.fieldsType==='BOOLEAN'">{{ item.typeFour.fieldThree }}</td>

      <td v-if="item.fieldsType==='DATE'">{{ item.typeFive.fieldOne }}</td>
      <td v-if="item.fieldsType==='DATE'">{{ item.typeFive.fieldTwo }}</td>
      <td v-if="item.fieldsType==='DATE'">{{ item.typeFive.fieldThree }}</td>
    </tr>
    </tbody>
  </v-table>
</template>

<script>
export default {
  props:{
    collectionProps: {
      type: Object,
      required: true
    },
    itemProps:{
      type: Array,
      required: true
    },
    collectionPage: {
      type: Boolean,
      required: false,
      default: false
    }
  },
}
</script>

<style scoped>

</style>