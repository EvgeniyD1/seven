<template>
  <div v-for="commentProp in commentsProp" :key="commentProp.id">
    <Comment :comment-prop="commentProp"
             @deletedComment="deletedComment"></Comment>
  </div>
</template>

<script>
import Comment from "./Comment.vue";

export default {
  components: {Comment},
  props: {
    commentsProp: {
      type: Array,
      required: true
    }
  },
  methods: {
    deletedComment(data){
      this.$emit('deletedComment', data);
    }
  },
  emits: ['deletedComment']
}
</script>

<style scoped>

</style>