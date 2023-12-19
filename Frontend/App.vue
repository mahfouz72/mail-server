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
    <NavBar/>
    <div style="display: flex;">
      <SideBar :currentFolder = "currentFolder" @open="open"/>
      <MainBoard/>
    </div>
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
        email: '',
        userLoggedIn: true,
        currentFolder: 'Inbox',
      }
    },
    methods: {
      login(username, email){
        this.username = username;
        this.email = email;
        this.userLoggedIn = true;
      },
      signUp(username, email){
        this.username = username;
        this.email = email;
        this.userLoggedIn = true;
      },
      logout(){
        this.username = '';
        this.email = '';
        this.userLoggedIn = false;
      },
      open(folder){
        this.currentFolder = folder;
      }
    }
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