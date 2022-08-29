<template>
  <v-container fluid>
    <v-row justify="start" align="start">
      <v-col cols="12" sm="6" md="5" lg="3" v-if="mainElement.id">
        <cluster :collection="mainElement"></cluster>
        <cluster-form :edit-form="true"
                              :collection-props="mainElement"
                              v-if="this.$store.state.profile===mainElement.user.username || this.$store.state.access==='ADMIN'"
                              @updateCollection="updateCollection"
                              @getUrl="getUrl"
        ></cluster-form>
        <item-form :collection="mainElement"
                   v-if="this.$store.state.profile===mainElement.user.username || this.$store.state.access==='ADMIN'"
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

        <item-table :collection-props="mainElement"
                    :item-props="elements"
                    :collection-page="true"
        ></item-table>
        <div v-intersection="loadElements" id="observer"></div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Cluster from "../components/cluster/Cluster.vue";
import ClusterForm from "../components/cluster/ClusterForm.vue";
import ItemForm from "../components/item/ItemForm.vue";
import ItemTable from "../components/item/ItemTable.vue";
import loadingMixin from "../mixins/loadingMixin";
import mainElementMixin from "../mixins/mainElementMixin";

export default {
  components: {ItemTable, ItemForm, Cluster, ClusterForm},
  mixins: [loadingMixin, mainElementMixin],
  data() {
    return {
      sorting: ['id', 'name'],
      ordering: ['desc', 'asc'],
      check: false,
    }
  },
  methods: {
    updateCollection(collection) {
      this.mainElement = collection;
    },
    getUrl(imgUrl) {
      this.mainElement.imgUrl = imgUrl;
    },
    getItem(item) {
      /*intersection mb bad works*/
      if (this.elements.length!==0){
        this.elements.unshift(item);
      }
    },

    clear() {
      this.elements = [];
      this.pageNumber = 0;
      this.totalPages = 0;
      this.currentNumber = 0;
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
    }
  },
  watch: {
    check(){
      this.clear()
      if (this.checkVisible()){
        this.loadElements()
      }
    }
  },
  beforeMount() {
    this.mainElementPath = '/collections/collection/' + this.$route.params.id
    this.loadMainElement()
    this.path = '/items/collection/' + this.$route.params.id
  }
}
</script>

<style scoped>

</style>