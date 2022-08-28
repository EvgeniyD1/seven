<template>
  <item-table :collection-props="oneItem.cluster"
              :item-props="items"
              v-if="oneItem.id"
  ></item-table>
  <v-divider></v-divider>
  <div class="mx-3 my-1"
       v-if="oneItem.id && (this.$store.state.profile===oneItem.cluster.user.username || this.$store.state.access==='ADMIN')">
    <item-form :collection="oneItem.cluster"
               :edit-form-prop="true"
               :item-props="oneItem"
               @updatedItem="updatedItem"
               @getImg="getImg"
    ></item-form>
  </div>
  <div align="center" class="my-3 text-h5" v-if="!this.$store.state.profile">Sign in to leave a comment</div>
  <div class="mx-3 my-1" v-if="oneItem.id && this.$store.state.profile">
    <comment-form :item-id-prop="oneItem.id"
                  @getComment="getComment"></comment-form>
  </div>
  <div class="mx-3 my-1">
    <comments :comments-prop="elements"
              @deletedComment="deletedComment"></comments>
    <div v-intersection="loadElements"></div>
  </div>
</template>

<script>
import ImgForm from "../components/item/ImgForm.vue";
import ItemTable from "../components/item/ItemTable.vue";
import axios from "axios";
import ItemForm from "../components/item/ItemForm.vue";
import CommentForm from "../components/comment/CommentForm.vue";
import Comments from "../components/comment/Comments.vue";
import loadingMixin from "../mixins/loadingMixin";


export default {
  components: {Comments, CommentForm, ItemForm, ItemTable, ImgForm},
  mixins: [loadingMixin],
  data() {
    return {
      items: [],
      oneItem: {},
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
    },
    getComment(data) {
      this.elements.unshift(data);
    },
    deletedComment(data){
      this.elements = this.elements.filter(comment => comment.id!==data.id)
    }
  },
  beforeMount() {
    this.getItem()
    this.path = '/comments/item/' + this.$route.params.id
  }
}
</script>

<style scoped>

</style>