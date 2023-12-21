<template>
  <WelcomePage v-if="!userLoggedIn "
          @login = "login"
          @signUp = "signUp"/>
  <div v-else>
    <div class="Title">
      <span style="position: absolute; left: 0.1vw;margin-top: 0.2vh;">
        <i class="pi pi-user icon" style="font-size: 2.8vh;"></i>
        <i style="font-size: 2vh;margin-left: 0.2vw;">{{username}}</i>
      </span>
      <span style="margin: auto;cursor: default;">ֆmail</span>
      <span style="position: absolute; right: 0.1vw;">
        <button class="logout" @click="logout">
          <i class="pi pi-sign-out" style="font-size: 2vh;"></i>
          Logout
        </button>
      </span>
    </div>
    <NavBar :multiselect = "multiselect" @toggleMultiSelect="toggleMultiSelect"/>
    <div style="display: flex;">
      <SideBar :currentFolder = "currentFolder" @open="open"/>
      <MainBoard :emails="emails" :currentFolder="currentFolder" :multiselect="multiselect" v-model:selectedEmails="selectedEmails"/></div>
    <footer>
      ©2023 OOP Assingment 4
    </footer>
  </div>
</template>

<script>
import MainBoard from './components/MainBoard.vue';
import NavBar from './components/NavBar.vue';
import SideBar from './components/SideBar.vue';
import WelcomePage from './components/WelcomePage.vue';
import 'primeicons/primeicons.css';

export default {
    name: "App",
    components: {WelcomePage, NavBar, SideBar, MainBoard},
    data() {
      return {
        username: 'Youssif',
        useremail: '',
        userLoggedIn: true,
        currentFolder: 'Inbox',
        emails: [],
        email: {
          id: '',
          from: '',
          to: '',
          subject: '',
          body: '',
          date: '',
          priority: '',
          attachments:[]
        },
        multiselect: true,
        selectedEmails: {},
      }
    },
    methods: {
      login(username, useremail){
        this.username = username;
        this.useremail = useremail;
        this.userLoggedIn = true;
      },
      signUp(username, useremail){
        this.username = username;
        this.useremail = useremail;
        this.userLoggedIn = true;
      },
      logout(){
        this.username = '';
        this.useremail = '';
        this.userLoggedIn = false;
      },
      open(folder){
        this.currentFolder = folder;
        this.emails = [];
        const startDate = new Date('2/15/2023');
        for (let i = 1; i < 80; i++) {
          const date = new Date(startDate.getTime() + (i * 24 * 60 * 60 * 1000));
          const formattedDate = `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
          this.email = {
            id: i,
            from: `Esmail Mahmoud Hassan m3rf4 el esm b3t el tholathy`,
            to: `Youssif Khaled Ahmed Abdelaziz Mohamed`,
            subject: `OOP Assignment Assesment w klam ktyr 34an n5ouf el overflow`,
            body: `E7na gamdyn f45 ana delwa2ty 48al 3lCompose aho w m7fouz 48al 3llogin page w 4o8l 3aly w 2rbna n5ls`,
            date: formattedDate,
            priority: 'High',
            attachments: []
          };
          this.emails.push(this.email);
        }
      },
      toggleMultiSelect(){
        console.log(this.selectedEmails);
        this.multiselect = !this.multiselect;
        this.selectedEmails = {};
      }
    },
}
</script>

<style>
#app{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
}
body{
  margin: 0;
  padding-top: 0%;
  height: 100%;
  width: 100%;
}
footer{
  background-color: rgba(3, 4, 5, 0.763);
  height: 5vh;
  color: whitesmoke;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2vh;
}
.Title {
  font-size: 3.1vh;
  font-weight: bold;
  color: white;
  background-color: rgba(3, 4, 5, 0.763);
  height: fit-content;
  padding-bottom: 0.2em;
  width: 100vw;
  margin: 0;
  display: flex;
}
.logout {
  background-color: transparent;
  border: none;
  color: white;
  font-size: 2vh;
  cursor: pointer;
}
.logout:hover {
  color: black;
}
</style>