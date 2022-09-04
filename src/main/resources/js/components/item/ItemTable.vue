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
        Tags
      </th>
      <th class="text-left">
        {{ collectionProps.fieldOne }}
      </th>
      <th class="text-left">
        {{ collectionProps.fieldTwo }}
      </th>
      <th class="text-left">
        {{ collectionProps.fieldThree }}
      </th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in itemProps" :key="itemProps.id">
      <td>
        <router-link :to="'/items/' + item.id">
<!--          <v-img v-if="!item.imgUrl" :src="'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg'"></v-img>-->
<!--          <v-img v-else :src="item.imgUrl"></v-img>-->
          <img v-if="!item.imgUrl" src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" style="max-height: 120px">
          <img v-else :src="item.imgUrl" style="max-height: 120px">
        </router-link>
      </td>

      <td v-if="!collectionPage">
        <router-link class="router-link" :to="'/users/' + item.cluster.user.username">
          {{ item.cluster.user.username }}
        </router-link>
      </td>

      <td v-if="!collectionPage">
        <router-link class="router-link" :to="'/collections/' + item.cluster.id">
          {{ item.cluster.name }}
        </router-link>
      </td>

      <td>
        <div v-if="collectionPage">
          <router-link class="router-link" :to="'/items/' + item.id">
            {{ item.name }}
          </router-link>
        </div>
        <div v-else>{{ item.name }}</div>
      </td>

      <td>{{ this.getTags(item.tags) }}</td>

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
  props: {
    collectionProps: {
      type: Object,
      required: true
    },
    itemProps: {
      type: Array,
      required: true
    },
    collectionPage: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  methods: {
    getTags(tags) {
      let result = '';
      for (let tag of tags) {
        result = result + '  #' + tag.name;
      }
      return result;
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