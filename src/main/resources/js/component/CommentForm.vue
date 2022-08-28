<template>
  <v-card>
    <v-form
        v-model="valid"
        lazy-validation
    >
      <v-textarea
          clearable
          clear-icon="clear"
          v-model.trim="comment"
          :counter="255"
          :rules="commentRules"
          label="Your comment"
          auto-grow
          rows="1"
          row-height="15"
          required
      ></v-textarea>
      <v-btn
          :disabled="!valid"
          class="mx-4 mb-4"
          @click="addOrUpdateComment"
      >
        Send
      </v-btn>
    </v-form>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      valid: false,
      comment: '',
      commentRules: [
        v => !!v || 'Comment must not be empty',
        v => (v && v.length <= 255) || 'Comment must be less than 255 characters',
      ],
      commentId: '',
    }
  },
  props: {
    itemIdProp: {
      type: Number,
      required: false
    },
    editForm: {
      type: Boolean,
      required: false,
      default: false
    },
    commentProp: {
      type: Object,
      required: false
    }
  },
  methods: {
    async addOrUpdateComment() {
      try {
        let request = {
          text: this.comment
        };
        if (!this.editForm) {
          let url = '/comments/' + this.itemIdProp + '/new';
          let response = await axios.post(url, request);
          this.$emit('getComment', response.data);
          this.comment = '';
        }
        if (this.editForm){
          let url = '/comments/' + this.commentId;
          let response = await axios.put(url, request);
          this.$emit('getUpdatedComment', response.data, false)
        }
      } catch (e) {
        console.log(e)
      }
    }
  },
  emits: ['getComment', 'getUpdatedComment'],
  beforeMount() {
    if (this.editForm){
      this.comment = this.commentProp.text;
      this.commentId = this.commentProp.id;
    }
  }
}
</script>

<style scoped>

</style>