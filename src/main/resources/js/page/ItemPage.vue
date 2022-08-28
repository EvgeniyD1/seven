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
  <div align="center" class="my-3 text-h3">Comments</div>
  <div align="center" class="my-1 text-h5" v-if="!this.$store.state.profile">Sign in to leave a comment</div>
  <div class="mx-3 my-1" v-if="oneItem.id && this.$store.state.profile">
    <comment-form :item-id-prop="oneItem.id"
                  @getComment="getComment"></comment-form>
  </div>
  <div class="mx-3 my-1">
    <comments :comments-prop="comments"
              @deletedComment="deletedComment"></comments>
    <div v-intersection="loadComments"></div>
  </div>
</template>

<script>
import ImgForm from "../component/ImgForm.vue";
import ItemTable from "../component/ItemTable.vue";
import axios from "axios";
import ItemForm from "../component/ItemForm.vue";
import CommentForm from "../component/CommentForm.vue";
import Comments from "../component/Comments.vue";


export default {
  components: {Comments, CommentForm, ItemForm, ItemTable, ImgForm},
  data() {
    return {
      items: [],
      oneItem: {},
      comments: [],

      pageNumber: 0,
      totalPages: 0,
      number: 0,
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
    async loadComments() {
      if (this.number !== this.totalPages - 1) {
        try {
          let url = '/comments/item/' + this.$route.params.id;
          let response = await axios.get(url, {
            params: {
              page: this.pageNumber
            }
          })
          console.log(response.data)

          this.comments = [...this.comments, ...response.data.content];
          this.pageNumber = response.data.pageable.pageNumber + 1;
          this.number = response.data.number;
          this.totalPages = response.data.totalPages;
        } catch (e) {
          console.log(e)
        }
      }
    },
    updatedItem(data) {
      this.items[0] = data
    },
    getImg(data) {
      this.items[0].imgUrl = data;
    },
    getComment(data) {
      this.comments.unshift(data);
    },
    deletedComment(data){
      this.comments = this.comments.filter(comment => comment.id!==data.id)
    }
  },
  beforeMount() {
    this.getItem()
  }
}
</script>

<style scoped>

</style>