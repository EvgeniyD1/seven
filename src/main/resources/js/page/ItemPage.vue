<template>
  <item-table :collection-props="oneItem.cluster"
              :item-props="items"
              v-if="oneItem.id"
  ></item-table>
  <v-divider></v-divider>
  <div class="mx-3"
       v-if="oneItem.id && (this.$store.state.profile===oneItem.cluster.user.username || this.$store.state.access==='ADMIN')">
    <item-form :collection="oneItem.cluster"
               :edit-form-prop="true"
               :item-props="oneItem"
               @updatedItem="updatedItem"
               @getImg="getImg"
    ></item-form>
  </div>
</template>

<script>
import ImgForm from "../component/ImgForm.vue";
import ItemTable from "../component/ItemTable.vue";
import axios from "axios";
import ItemForm from "../component/ItemForm.vue";

export default {
  components: {ItemForm, ItemTable, ImgForm},
  data() {
    return {
      items: [],
      oneItem: {}
    }
  },
  methods: {
    async getItem() {
      try {
        let response = await axios.get('/items/' + this.$route.params.id);
        if (!response.data) {
          this.$router.push('/non-existing');
        } else {
          this.items.push(response.data);
          this.oneItem = response.data;
          console.log(this.oneItem)
        }
      } catch (e) {
        console.log(e);
        // this.$router.push('/error');
      }
    },
    updatedItem(data) {
      this.items[0] = data
    },
    getImg(data) {
      this.items[0].imgUrl = data;
    }
  },
  beforeMount() {
    this.getItem()
  }
}
</script>

<style scoped>

</style>