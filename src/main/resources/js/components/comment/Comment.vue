<template>
  <v-card class="mb-1">
    <v-row class="mx-7 mb-0" align="start">
      <v-col cols="0" sm="0" md="0" lg="0" justify="center" align="start" class="mt-6">
<!--        <v-avatar size="x-large" @click="$router.push('/users/' + comment.user.username)">-->
<!--          <v-icon icon="account_circle"-->
<!--                  v-if="!comment.user.imgUrl"-->
<!--                  size="64px"-->
<!--          ></v-icon>-->
<!--          <v-img v-else-->
<!--                 alt="Avatar"-->
<!--                 size="64px"-->
<!--                 :src="comment.user.imgUrl">-->
<!--          </v-img>-->
<!--        </v-avatar>-->
      </v-col>

      <v-col cols="2" sm="6" md="7" lg="10" align="start">
        <v-card-text>
          <div>
            <router-link class="router-link" :to="'/users/' + comment.user.username">
              {{ comment.user.username }}
            </router-link>
            &nbsp;&nbsp;{{ comment.date }}
          </div>
          <div>{{ comment.text }}</div>
        </v-card-text>
      </v-col>

      <v-col align="end" class="mt-6"
             v-if="this.$store.state.profile===comment.user.username || this.$store.state.access==='ADMIN'">
        <v-btn variant="text"
               @click="show = !show"
               :append-icon="show ? 'expand_less' : 'expand_more'"
        >
          <v-icon size="24">edit</v-icon>
        </v-btn>

        <v-btn variant="text" @click="deleteComment">
          <v-icon size="24">delete</v-icon>
        </v-btn>
      </v-col>
    </v-row>

    <v-expand-transition>
      <div v-show="show">
        <comment-form :edit-form="true"
                      :comment-prop="comment"
                      @getUpdatedComment="getUpdatedComment"
        ></comment-form>
      </div>
    </v-expand-transition>

  </v-card>
</template>

<script>
import CommentForm from "./CommentForm.vue";
import axios from "axios";

export default {
  components: {CommentForm},
  data() {
    return {
      comment: {},
      show: false
    }
  },
  props: {
    commentProp: {
      type: Object,
      required: true
    }
  },
  beforeMount() {
    this.comment = this.commentProp;
  },
  methods: {
    getUpdatedComment(data, show) {
      this.comment = data;
      this.show = show;
    },
    async deleteComment() {
      try {
        let url = '/comments/' + this.comment.id;
        await axios.delete(url);
        this.$emit('deletedComment', this.comment);
      } catch (e) {
        console.log(e)
      }
    }
  },
  emits: ['deletedComment']
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