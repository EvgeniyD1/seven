<template>
  <v-container fluid>
    <v-row justify="start" align="start">
      <v-col cols="12" sm="6" md="5" lg="3" v-if="collection.id">
        <item-collection :collection="collection"></item-collection>
        <item-collection-form :edit-form="true"
                              :collection-props="collection"
                              v-if="this.$store.state.profile===collection.user.username || this.$store.state.access==='ADMIN'"
                              @updateCollection="updateCollection"
                              @getUrl="getUrl"
        ></item-collection-form>
        <item-form :collection="collection"
                   v-if="this.$store.state.profile===collection.user.username || this.$store.state.access==='ADMIN'"
                   @getItem="getItem"
        ></item-form>
      </v-col>
      <v-col cols="12" sm="6" md="7" lg="9">

        <v-form>
          <v-row>
            <v-col cols="5" sm="5" md="5" lg="5">
              <v-select
                  v-model="sort"
                  :items="sorting"
                  label="Sort"
              ></v-select>
            </v-col>
            <v-col cols="5" sm="5" md="5" lg="5">
              <v-select
                  v-model="order"
                  :items="ordering"
                  label="Order"
              ></v-select>
            </v-col>
            <v-col cols="2" sm="2" md="2" lg="2">
              <v-btn icon="search" @click="check=!check"></v-btn>
            </v-col>
          </v-row>
        </v-form>

        <item-table :collection-props="collection"
                    :item-props="items"
                    :collection-page="true"
        ></item-table>
        <div v-intersection="loadItems" id="observer"></div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ItemCollection from "../component/Cluster.vue";
import ItemCollectionForm from "../component/ClusterForm.vue";
import ItemForm from "../component/ItemForm.vue";
import ItemTable from "../component/ItemTable.vue";
import axios from "axios";

export default {
  components: {ItemTable, ItemForm, ItemCollection, ItemCollectionForm},
  data() {
    return {
      collection: {},
      items: [],

      sorting: ['id', 'name', 'tag'],
      ordering: ['desc', 'asc'],

      pageNumber: 0,
      totalPages: 0,
      number: 0,

      sort: '',
      order: '',

      check: false,
    }
  },
  watch: {
    check(){
      this.clear()
      if (this.checkVisible()){
        this.loadItems()
      }
    }
  },
  methods: {
    updateCollection(collection) {
      this.collection = collection;
    },
    getUrl(url) {
      this.collection.imgUrl = url;
    },
    getItem(data) {
      this.items.unshift(data);
    },
    clear() {
      this.items = [];
      this.pageNumber = 0;
      this.totalPages = 0;
      this.number = 0;
    },
    /*check if there is an observer in view*/
    checkVisible() {
      let target = document.getElementById("observer");
      let targetPosition = {
        top: window.pageYOffset + target.getBoundingClientRect().top,
        bottom: window.pageYOffset + target.getBoundingClientRect().bottom
      };
      let windowPosition = {
        top: window.pageYOffset,
        bottom: window.pageYOffset + document.documentElement.clientHeight
      };
      return targetPosition.bottom > windowPosition.top && targetPosition.top < windowPosition.bottom;
    },
    async loadCollection() {
      try {
        let response = await axios.get('/collections/collection/' + this.$route.params.id);
        if (!response.data) {
          this.$router.push('/non-existing');
        } else {
          this.collection = response.data;
        }
      } catch (e) {
        console.log(e)
        //todo need to add errors page
        // this.$router.push('/non-existing')
      }
    },
    async loadItems() {
      if (this.number !== this.totalPages - 1) {
        try {
          let url = '/items/collection/' + this.$route.params.id;
          let response = await axios.get(url, {
            params: {
              page: this.pageNumber,
              sort: this.sort + ',' + this.order,
            }
          })
          console.log(response.data)
          this.items = [...this.items, ...response.data.content];
          this.pageNumber = response.data.pageable.pageNumber + 1;
          this.number = response.data.number;
          this.totalPages = response.data.totalPages
        } catch (e) {
          console.log(e)
        }
      }
    }
  },
  beforeMount() {
    this.loadCollection()
  }
}
</script>

<style scoped>

</style>