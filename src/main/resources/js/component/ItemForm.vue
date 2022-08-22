<template>
  <v-btn @click="show = !show" class="my-2 mr-2">
    Add new item
  </v-btn>

  <v-expand-transition>
    <v-card v-show="show">
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

        <v-text-field
            v-model.trim="tag"
            :counter="15"
            :rules="tagRules"
            label="Tag"
            required
        ></v-text-field>

        <div v-if="collection.fieldsType==='INTEGER'">
          <v-text-field
              v-model.trim="integerFieldOne"
              :counter="10"
              :rules="integerFieldRules"
              :label="collection.fieldOne"
              required
          ></v-text-field>

          <v-text-field
              v-model.trim="integerFieldTwo"
              :counter="10"
              :rules="integerFieldRules"
              :label="collection.fieldTwo"
              required
          ></v-text-field>

          <v-text-field
              v-model.trim="integerFieldThree"
              :counter="10"
              :rules="integerFieldRules"
              :label="collection.fieldThree"
              required
          ></v-text-field>
        </div>

        <div v-if="collection.fieldsType==='TEXT'">
          <v-text-field
              v-model.trim="textFieldOne"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldOne"
              required
          ></v-text-field>

          <v-text-field
              v-model.trim="textFieldTwo"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldTwo"
              required
          ></v-text-field>

          <v-text-field
              v-model.trim="textFieldThree"
              :counter="30"
              :rules="textFieldRules"
              :label="collection.fieldThree"
              required
          ></v-text-field>
        </div>

        <div v-if="collection.fieldsType==='MULTILINE_TEXT'">
          <v-textarea
              v-model.trim="multilineTextFieldOne"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldOne"
              required
          ></v-textarea>

          <v-textarea
              v-model.trim="multilineTextFieldTwo"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldTwo"
              required
          ></v-textarea>

          <v-textarea
              v-model.trim="multilineTextFieldThree"
              :counter="255"
              :rules="multilineTextFieldRules"
              :label="collection.fieldThree"
              required
          ></v-textarea>
        </div>

        <div v-if="collection.fieldsType==='BOOLEAN'">
          <v-checkbox
              v-model="booleanFieldOne"
              :label="collection.fieldOne"
              required
          ></v-checkbox>

          <v-checkbox
              v-model="booleanFieldTwo"
              :label="collection.fieldTwo"
              required
          ></v-checkbox>

          <v-checkbox
              v-model="booleanFieldThree"
              :label="collection.fieldThree"
              required
          ></v-checkbox>
        </div>

        <div v-if="collection.fieldsType==='DATE'">
          <v-text-field
              v-model="dateFieldOne"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldOne"
              required
          ></v-text-field>

          <v-text-field
              v-model="dateFieldTwo"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldTwo"
              required
          ></v-text-field>

          <v-text-field
              v-model="dateFieldThree"
              :rules="dateFieldRules"
              :counter="10"
              :label="collection.fieldThree"
              required
          ></v-text-field>
        </div>

        <v-btn @click="createOrUpdateWithValidation" class="mb-4">Confirm</v-btn>
      </v-form>
    </v-card>
  </v-expand-transition>
</template>

<script>
import axios from "axios";

export default {
  props: {
    collection: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      name: '',
      nameRules: [
        v => !!v || 'Name must not be empty',
        v => (v && v.length <= 30) || 'Name must be less than 30 characters',
      ],
      tag: '',
      tagRules: [
        v => !!v || 'Tag must not be empty',
        v => (v && v.length <= 15) || 'Tag must be less than 15 characters',
      ],
      integerFieldOne: '',
      integerFieldTwo: '',
      integerFieldThree: '',
      integerFieldRules: [
        v => !!v || 'Field must not be empty',
        v => (v && v.length <= 10) || 'Field must be less than 10 characters',
        v => /[0-9]+/.test(v) || 'Only numbers allowed',
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
      show: false,
      fieldOne: '',
      fieldTwo: '',
      fieldThree: '',
      valid: false,
      editForm: false,
    }
  }, methods: {
    async createOrUpdateItem() {
      try {
        let url = '/items/' + this.collection.id + '/new';
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
          tag: this.tag,
          fieldsType: this.collection.fieldsType,
          fieldOne: this.fieldOne,
          fieldTwo: this.fieldTwo,
          fieldThree: this.fieldThree
        }
        let response = await axios.post(url, request);
        this.$emit('getItem', response.data);
        this.show = false;

        if (!this.editForm) {
          this.name = '';
          this.tag = '';
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
    }
  },
  emits: ['getItem']
}
</script>

<style scoped>

</style>