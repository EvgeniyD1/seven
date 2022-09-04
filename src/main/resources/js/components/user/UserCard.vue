<template>
  <v-card v-if="user.username" position="sticky">

    <v-row align="center">
      <v-col cols="12" sm="3" md="3" lg="3" justify="center" align="center">
        <v-avatar size="x-large" class="ml-3">
          <v-icon icon="account_circle"
                  v-if="!user.imgUrl"
                  size="64px"
          ></v-icon>
<!--          <v-img v-else-->
<!--                 alt="Avatar"-->
<!--                 size="64px"-->
<!--                 :src="user.imgUrl">-->
<!--          </v-img>-->
          <img v-else :src="user.imgUrl" style="height: 64px; width: 64px">
        </v-avatar>
      </v-col>

      <v-col cols="12" sm="9" md="9" lg="9">
        <v-card-text>
          <div>{{ user.username }}</div>
          <div>{{ user.email }}</div>
          <div>{{ user.status }}</div>
          <div v-if="user.role==='ADMIN'" class="text-orange-lighten-2">ADMIN</div>
          <div v-if="!user.notLock" class="text-orange-lighten-2">BLOCKED</div>
        </v-card-text>
      </v-col>
    </v-row>

    <v-row class="text-justify ma-0">
      <v-card-text>
        {{ user.about }}
      </v-card-text>
    </v-row>

    <v-expand-transition class="my-1" v-if="this.$store.state.profile===user.username">
      <div v-show="show1">
        <v-divider></v-divider>
        <user-name-email-form :email-prop="user.email"
                              :name-prop="user.username"
                              @updateP="updateP"
        ></user-name-email-form>
      </div>
    </v-expand-transition>

    <v-expand-transition class="my-1" v-if="this.$store.state.profile===user.username">
      <div v-show="show2">
        <v-divider></v-divider>
        <user-about-form :name-prop="user.username"
                         @updateA="updateA"
        ></user-about-form>
      </div>
    </v-expand-transition>

    <v-expand-transition class="my-1" v-if="user.role && this.$store.state.access==='ADMIN'">
      <div v-show="show3">
        <v-divider></v-divider>
        <user-admin-form :block-prop="user.notLock"
                         :role-prop="user.role"
                         :name-prop="user.username"
                         @updateAdminProp="updateAdminProp"
        ></user-admin-form>
      </div>
    </v-expand-transition>

    <v-expand-transition class="my-1" v-if="this.$store.state.profile===user.username">
      <div v-show="show4">
        <v-divider></v-divider>
        <user-img-form :name-prop="user.username"
                       @uploadImgE="uploadImgE">
        </user-img-form>
      </div>
    </v-expand-transition>

    <v-card-actions v-if="this.$store.state.profile">
      <v-btn variant="text"
             @click="show1 = !show1; show2 = false; show3 = false; show4 = false"
             :append-icon="show1 ? 'expand_less' : 'expand_more'"
             v-if="this.$store.state.profile===user.username"
      >
        <v-icon size="24">settings</v-icon>
      </v-btn>

      <v-btn variant="text"
             @click="show2 = !show2; show1 = false; show3 = false; show4 = false"
             :append-icon="show2 ? 'expand_less' : 'expand_more'"
             v-if="this.$store.state.profile===user.username"
      >
        <v-icon size="24">account_circle</v-icon>
      </v-btn>

      <v-btn variant="text"
             @click="show4 = !show4; show1 = false; show2 = false; show3 = false"
             :append-icon="show4 ? 'expand_less' : 'expand_more'"
             v-if="this.$store.state.profile===user.username"
      >
        <v-icon size="24">image</v-icon>
      </v-btn>

      <v-spacer></v-spacer>
      <v-btn variant="text"
             @click="show3 = !show3; show1 = false; show2 = false; show4 = false"
             :append-icon="show3 ? 'expand_less' : 'expand_more'"
             v-if="this.$store.state.access==='ADMIN'"
      >
        <v-icon size="24">admin_panel_settings</v-icon>
        Admin
      </v-btn>

    </v-card-actions>
  </v-card>
</template>

<script>
import UserNameEmailForm from ".//UserNameEmailForm.vue";
import UserAboutForm from ".//UserAboutForm.vue";
import UserAdminForm from ".//UserAdminForm.vue";
import UserImgForm from ".//UserImgForm.vue";


export default {
  components: {UserAboutForm, UserNameEmailForm, UserAdminForm, UserImgForm},
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      show1: false,
      show2: false,
      show3: false,
      show4: false,
    }
  },
  methods: {
    updateP(name, email, show) {
      this.user.username = name;
      this.user.email = email;
      this.show1 = show;
    },
    updateA(about, show) {
      this.user.about = about;
      this.show2 = show;
    },
    updateAdminProp(notLock, role, show) {
      this.user.notLock = notLock;
      this.user.role = role ? 'ADMIN' : 'USER';
      this.show3 = show;
    },
    uploadImgE(img, show) {
      this.user.imgUrl = img;
      this.show4 = show;
    },
  }
}
</script>

<style scoped>

</style>