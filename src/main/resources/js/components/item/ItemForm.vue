<template>
  <v-btn @click="show1 = !show1; show2=false" class="my-2 mr-2">
    {{ editForm ? 'Edit Item' : 'Add New Item' }}
  </v-btn>

  <v-btn @click="show2 = !show2; show1=false"
         class="my-2 mr-2"
         v-if="editForm"
  >
    Upload Image
  </v-btn>

  <v-btn @click="deleteItem"
         class="my-2 mr-2"
         v-if="editForm"
         append-icon="delete"
  >Delete Item
  </v-btn>

  <v-expand-transition>
    <v-card v-show="show1">
      <v-form class="mx-3 mt-5"
              ref="form"
      >

        <v-text-field
            v-model.trim="name"
            :counter="30"
            :rules="nameRules"
            label="name"
        ></v-text-field>

        <v-combobox
            v-if="!editForm"
            v-model="selectedTags"
            v-model:search-input="tagsSearch"
            :items="tags"
            label="tags"
            multiple
            chips
            hide-selected
            :rules="tagsRules"
            hint="Maximum of 3 tags"
        ></v-combobox>

        <div v-if="collection.fieldsType==='INTEGER'">
          <v-text-field
              v-model="integerFieldOne"
              :counter="8"
              :rules="integerFieldRules"
              :label="collection.fieldOne"
          ></v-text-field>

          <v-text-field
              v-model="integerFieldTwo"
              :counter="8"
              :rules="integerFieldRules"
              :label="collection.fieldTwo"
          ></v-text-field>

          <v-text-field
              v-model="integerFieldThree"
              :counter="8"
              :rules="integerFieldRules"
              :label="collection.fieldThree"
          ></v-text-field>
        </div>

        <div v-if="collection.fieldsType==='TEXT'">
          <v-text-field
              v-model.trim="textFieldOne"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldOne"
          ></v-text-field>

          <v-text-field
              v-model.trim="textFieldTwo"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldTwo"
          ></v-text-field>

          <v-text-field
              v-model.trim="textFieldThree"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldThree"
          ></v-text-field>
        </div>

        <div v-if="collection.fieldsType==='MULTILINE_TEXT'">
          <v-textarea
              v-model.trim="multilineTextFieldOne"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldOne"
          ></v-textarea>

          <v-textarea
              v-model.trim="multilineTextFieldTwo"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldTwo"
          ></v-textarea>

          <v-textarea
              v-model.trim="multilineTextFieldThree"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldThree"
          ></v-textarea>
        </div>

        <div v-if="collection.fieldsType==='BOOLEAN'">
          <v-checkbox
              v-model="booleanFieldOne"
              :label="collection.fieldOne"
          ></v-checkbox>

          <v-checkbox
              v-model="booleanFieldTwo"
              :label="collection.fieldTwo"
          ></v-checkbox>

          <v-checkbox
              v-model="booleanFieldThree"
              :label="collection.fieldThree"
          ></v-checkbox>
        </div>

        <div v-if="collection.fieldsType==='DATE'">
          <v-text-field
              v-model="dateFieldOne"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldOne"
          ></v-text-field>

          <v-text-field
              v-model="dateFieldTwo"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldTwo"
          ></v-text-field>

          <v-text-field
              v-model="dateFieldThree"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldThree"
          ></v-text-field>
        </div>

        <v-btn @click="createOrUpdateWithValidation" class="mb-4">Confirm</v-btn>
      </v-form>
    </v-card>
  </v-expand-transition>

  <v-expand-transition>
    <v-card v-show="show2" v-if="editForm">
      <img-form :url-prop="'/items/uploadImg/' + itemId"
                @uploadImg="uploadImg"
      ></img-form>
    </v-card>
  </v-expand-transition>
</template>

<script>
import ImgForm from "./ImgForm.vue";
import axios from "axios";

export default {
  components: {ImgForm},

  props: {
    collection: {
      type: Object,
      required: true
    },
    itemProps: {
      type: Object,
      required: false
    },
    editFormProp: {
      type: Boolean,
      required: false,
      default: false
    }
  },

  watch: {
    async tagsSearch(value) {
      try {
        if (value) {
          let url = '/tags/' + value
          let response = await axios.get(url)
          this.tags = response.data
        }
      } catch (e) {
        console.log(e)
      }
    },
    selectedTags (value) {
      if (value.length > 3) {
        this.$nextTick(() => this.selectedTags.pop())
      }
    },
  },

  data() {
    return {
      name: '',
      nameRules: [
        v => !!v || 'Name must not be empty',
        v => (v && v.length <= 30) || 'Name must be less than 30 characters',
      ],

      tags: [],
      selectedTags: [],
      tagsSearch: '',
      tagsRules: [
        v => (v && v.length !== 0) || 'Tags must not be empty',
        v => (v && v.length <= 3) || 'Maximum of 3 tags',
      ],

      integerFieldOne: '',
      integerFieldTwo: '',
      integerFieldThree: '',
      integerFieldRules: [
        v => !!v || 'Field must not be empty',
        v => (v && v.length <= 8) || 'Field must be less than 8 characters',
        v => /^[0-9]+$/.test(v) || 'Only numbers allowed',
      ],

      textFieldOne: '',
      textFieldTwo: '',
      textFieldThree: '',
      textFieldRules: [
        v => !!v || 'Field must not be empty',
        v => (v && v.length <= 30) || 'Field must be less than 30 characters',
      ],

      multilineTextFieldOne: '',
      multilineTextFieldTwo: '',
      multilineTextFieldThree: '',
      multilineTextFieldRules: [
        v => !!v || 'Field must not be empty',
        v => (v && v.length <= 255) || 'Field must be less than 255 characters',
      ],

      booleanFieldOne: false,
      booleanFieldTwo: false,
      booleanFieldThree: false,

      dateFieldOne: '',
      dateFieldTwo: '',
      dateFieldThree: '',
      dateFieldRules: [
        v => !!v || 'Field must not be empty',
        v => (v && v.length <= 10) || 'Field must be less than 10 characters',
        v => /^\d{4}\/(0?[1-9]|1[012])\/(0?[1-9]|[12][0-9]|3[01])$/.test(v) || 'Date format must be YYYY/MM/DD',
      ],

      show1: false,
      show2: false,
      fieldOne: '',
      fieldTwo: '',
      fieldThree: '',
      itemId: '',
      valid: false,
      editForm: false,
    }
  },

  methods: {
    async createOrUpdateItem() {
      try {
        switch (this.collection.fieldsType) {
          case 'INTEGER' : {
            this.fieldOne = this.integerFieldOne;
            this.fieldTwo = this.integerFieldTwo;
            this.fieldThree = this.integerFieldThree;
          }
            break;
          case 'TEXT': {
            this.fieldOne = this.textFieldOne;
            this.fieldTwo = this.textFieldTwo;
            this.fieldThree = this.textFieldThree;
          }
            break;
          case 'MULTILINE_TEXT': {
            this.fieldOne = this.multilineTextFieldOne;
            this.fieldTwo = this.multilineTextFieldTwo;
            this.fieldThree = this.multilineTextFieldThree;
          }
            break;
          case 'BOOLEAN': {
            this.fieldOne = this.booleanFieldOne;
            this.fieldTwo = this.booleanFieldTwo;
            this.fieldThree = this.booleanFieldThree;
          }
            break;
          case 'DATE': {
            this.fieldOne = this.dateFieldOne;
            this.fieldTwo = this.dateFieldTwo;
            this.fieldThree = this.dateFieldThree;
          }
            break;
        }
        let request = {
          name: this.name,
          tags: this.selectedTags,
          fieldsType: this.collection.fieldsType,
          fieldOne: this.fieldOne,
          fieldTwo: this.fieldTwo,
          fieldThree: this.fieldThree
        }
        if (!this.editForm) {
          let url = '/items/' + this.collection.id + '/new';
          let response = await axios.post(url, request);
          this.$emit('getItem', response.data);
        }
        if (this.editForm) {
          let url = '/items/' + this.itemId;
          let response = await axios.put(url, request);
          this.$emit('updatedItem', response.data);
        }
        this.show1 = false;

        if (!this.editForm) {
          this.name = '';
          this.selectedTags = '';
          this.integerFieldOne = '';
          this.integerFieldTwo = '';
          this.integerFieldThree = '';
          this.textFieldOne = '';
          this.textFieldTwo = '';
          this.textFieldThree = '';
          this.multilineTextFieldOne = '';
          this.multilineTextFieldTwo = '';
          this.multilineTextFieldThree = '';
          this.booleanFieldOne = false;
          this.booleanFieldTwo = false;
          this.booleanFieldThree = false;
          this.dateFieldOne = '';
          this.dateFieldTwo = '';
          this.dateFieldThree = '';
        }
      } catch (e) {
        console.log(e)
      }
    },
    createOrUpdateWithValidation() {
      this.$refs.form.validate().then(value => {
        this.valid = value.valid
        if (this.valid) {
          this.createOrUpdateItem()
        }
      }).catch(e => console.log(e))
    },
    uploadImg(data, show) {
      this.show2 = show;
      this.$emit('getImg', data);
    },
    async deleteItem() {
      try {
        await axios.delete('/items/' + this.itemId);
        this.$router.push('/collections/' + this.collection.id);
      } catch (e) {
        console.log(e)
      }
    }
  },

  emits: ['getItem', 'updatedItem', 'getImg'],

  beforeMount() {
    this.editForm = this.editFormProp;
    if (this.editForm) {
      this.name = this.itemProps.name;
      this.itemId = this.itemProps.id;
      if (this.itemProps.fieldsType === 'INTEGER') {
        this.integerFieldOne = this.itemProps.typeOne.fieldOne;
        this.integerFieldTwo = this.itemProps.typeOne.fieldTwo;
        this.integerFieldThree = this.itemProps.typeOne.fieldThree;
      }
      if (this.itemProps.fieldsType === 'TEXT') {
        this.textFieldOne = this.itemProps.typeTwo.fieldOne;
        this.textFieldTwo = this.itemProps.typeTwo.fieldTwo;
        this.textFieldThree = this.itemProps.typeTwo.fieldThree;
      }
      if (this.itemProps.fieldsType === 'MULTILINE_TEXT') {
        this.multilineTextFieldOne = this.itemProps.typeThree.fieldOne;
        this.multilineTextFieldTwo = this.itemProps.typeThree.fieldTwo;
        this.multilineTextFieldThree = this.itemProps.typeThree.fieldThree;
      }
      if (this.itemProps.fieldsType === 'BOOLEAN') {
        this.booleanFieldOne = this.itemProps.typeFour.fieldOne;
        this.booleanFieldTwo = this.itemProps.typeFour.fieldTwo;
        this.booleanFieldThree = this.itemProps.typeFour.fieldThree;
      }
      if (this.itemProps.fieldsType === 'DATE') {
        this.dateFieldOne = this.itemProps.typeFive.fieldOne;
        this.dateFieldTwo = this.itemProps.typeFive.fieldTwo;
        this.dateFieldThree = this.itemProps.typeFive.fieldThree;
      }
    }
  }
}
</script>

<style scoped>

</style>