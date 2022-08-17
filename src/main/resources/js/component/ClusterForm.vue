<template>
  <v-btn @click="show1 = !show1; show2=false" class="my-2 mr-2">
    {{ editForm ? 'Edit Collection' : 'Add New Collection' }}
  </v-btn>

  <v-btn @click="show2 = !show2; show1=false"
         class="my-2 mr-2"
         v-if="editForm"
  >
    Upload Image
  </v-btn>

  <v-btn @click="deleteCollection"
         class="my-2 mr-2"
         v-if="editForm"
         append-icon="delete"
  >Del</v-btn>

  <v-expand-transition class="my-1">
    <v-card v-show="show1">
      <v-form class="mx-3 mt-5"
              ref="form"
              lazy-validation
      >

        <v-text-field
            v-model.trim="name"
            :counter="30"
            :rules="nameRules"
            label="Name"
            required
        ></v-text-field>

        <v-textarea
            v-model.trim="description"
            :counter="255"
            :rules="descriptionRules"
            label="Description"
            required
        ></v-textarea>

        <v-select v-if="!editForm"
                  v-model="select"
                  :items="fieldsType"
                  :rules="[v => !!v || 'Type of fields is required']"
                  label="Additional fields"
                  required
        ></v-select>

        <v-text-field
            v-model.trim="fieldOne"
            :counter="30"
            :rules="fieldOneRules"
            label="Field One"
            required
        ></v-text-field>

        <v-text-field
            v-model.trim="fieldTwo"
            :counter="30"
            :rules="fieldTwoRules"
            label="Field Two"
            required
        ></v-text-field>

        <v-text-field
            v-model.trim="fieldThree"
            :counter="30"
            :rules="fieldThreeRules"
            label="Field Three"
            required
        ></v-text-field>

        <v-btn
            class="mr-4 mb-4"
            @click="createOrUpdateWithValidation"
        >
          {{ editForm ? 'Confirm' : 'Create Collection' }}
        </v-btn>
      </v-form>
    </v-card>
  </v-expand-transition>

  <v-expand-transition>
    <v-card v-show="show2" v-if="editForm">
      <img-form @uploadImg="uploadImg"
          :url-prop="'/collections/collection/uploadImg/' + collectionProps.id"></img-form>
    </v-card>
  </v-expand-transition>
</template>

<script>
import axios from "axios";
import ImgForm from "./ImgForm.vue";

export default {
  components: {ImgForm},
  props: {
    editForm: {
      type: Boolean,
      required: false,
      default: false
    },
    collectionProps: {
      type: Object,
      required: false
    }
  },
  data() {
    return {
      show1: false,
      show2: false,
      name: '',
      fieldsType: [
        'Integer',
        'Text',
        'Multiline text',
        'Boolean',
        'Date'
      ],
      select: null,
      nameRules: [
        v => !!v || 'Name must not be empty',
        v => (v && v.length <= 30) || 'Name must be less than 30 characters',
      ],
      description: '',
      descriptionRules: [
        v => !!v || 'Description must not be empty',
        v => (v.length <= 255) || 'Description must be less than 255 characters',
      ],
      fieldOne: '',
      fieldOneRules: [
        v => !!v || 'Field One must not be empty',
        v => (v && v.length <= 30) || 'Field One must be less than 30 characters',
      ],
      fieldTwo: '',
      fieldTwoRules: [
        v => !!v || 'Field Two must not be empty',
        v => (v && v.length <= 30) || 'Field Two must be less than 30 characters',
      ],
      fieldThree: '',
      fieldThreeRules: [
        v => !!v || 'Field Three must not be empty',
        v => (v && v.length <= 30) || 'Field Three must be less than 30 characters',
      ],
    }
  },
  beforeMount() {
    if (this.editForm) {
      this.name = this.collectionProps.name;
      this.select = this.collectionProps.fieldsType;
      this.description = this.collectionProps.description;
      this.fieldOne = this.collectionProps.fieldOne;
      this.fieldTwo = this.collectionProps.fieldTwo;
      this.fieldThree = this.collectionProps.fieldThree;
    }
  },
  emits: ['updateCollection', 'getUrl'],
  methods: {
    uploadImg(url, show){
      this.show2 = show;
      this.$emit('getUrl', url);
    },
    async deleteCollection(){
      try {
        await axios.delete('/collections/collection/' + this.collectionProps.id);
        //todo
        this.$router.push('/users/' + this.collectionProps.user.username);
      }
      catch (e) {
        console.log(e)
      }
    },
    async createOrUpdateCollection() {
      try {
        let collection = {
          name: this.name,
          description: this.description,
          type: this.type,
          fieldsType: this.select,
          fieldOne: this.fieldOne,
          fieldTwo: this.fieldTwo,
          fieldThree: this.fieldThree
        }
        if (!this.editForm) {
          let response = await axios.post('/collections', collection);
          this.$router.push('/collections/' + response.data.id);
        }
        if (this.editForm) {
          let response = await axios.put('/collections/collection/' + this.collectionProps.id, collection);
          this.$emit('updateCollection', response.data);
          this.show1 = false;
        }
      } catch (e) {
        console.log(e)
      }
    },
    createOrUpdateWithValidation() {
      //this.$refs.form.validate() not working
      this.$refs.form.validate().then(value => {
        this.valid = value.valid
        if (this.valid) {
          this.createOrUpdateCollection()
        }
      }).catch(e => console.log(e))
    }
  }
}
</script>

<style scoped>

</style>