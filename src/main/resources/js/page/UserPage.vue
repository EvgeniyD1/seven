<template>
  <v-container fluid>
    <v-row justify="start" align="center">
      <v-col cols="12" sm="3">

        <v-card>

          <v-row justify="center" align="center">
            <v-col cols="12" sm="2">
              <v-icon class="ml-3"
                      icon="account_circle"
                      size="64px"
              ></v-icon>
            </v-col>

            <v-col cols="12" sm="10">
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

          <v-card-actions v-if="this.$store.state.profile">
            <v-spacer></v-spacer>
            <v-btn color="orange-lighten-2"
                   variant="text"
                   @click="show1 = !show1; show2 = false; show3 = false"
                   :append-icon="show1 ? 'expand_less' : 'expand_more'"
                   v-if="this.$store.state.profile===user.username"
            >
              Edit profile
            </v-btn>

            <v-btn color="orange-lighten-2"
                   variant="text"
                   @click="show2 = !show2; show1 = false; show3 = false"
                   :append-icon="show2 ? 'expand_less' : 'expand_more'"
                   v-if="this.$store.state.profile===user.username"
            >
              Edit about
            </v-btn>

            <v-btn color="orange-lighten-2"
                   variant="text"
                   @click="show3 = !show3; show1 = false; show2 = false"
                   :append-icon="show3 ? 'expand_less' : 'expand_more'"
                   v-if="this.$store.state.access==='ADMIN'"
            >
              Admin
            </v-btn>
          </v-card-actions>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import UserNameEmailForm from "../component/UserNameEmailForm.vue";
import UserAboutForm from "../component/UserAboutForm.vue";
import UserAdminForm from "../component/UserAdminForm.vue";
import axios from "axios";

export default {
  components: {UserAboutForm, UserNameEmailForm, UserAdminForm},
  data() {
    return {
      user: {},
      show1: false,
      show2: false,
      show3: false,
    }
  },
  mounted() {

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
    async loadUser() {
      try {
        let url = '/users/' + this.$route.params.username;
        let response = await axios.get(url);
        this.user = {
          username: response.data.username,
          email: response.data.email,
          status: response.data.status,
          about: response.data.about,
          notLock: response.data.notLock,
          role: response.data.role
        }
      } catch (e) {
        console.log(e)
      }
    },
  },
  beforeMount() {
    this.loadUser()
  }
}
</script>

<style scoped>

</style>