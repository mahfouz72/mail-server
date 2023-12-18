<template>
  <WelcomePage v-if="!userLoggedIn "
          @login = "login"
          @signUp = "signUp"/>
  <div v-else>
    <div class="Title">
      <span style="margin-left: 3px;margin-top: 2px;" class="pi pi-user">
        <span style="font-size: 2vh;cursor: default; color:floralwhite;">
          {{username}}
        </span>
      </span>
      <span style="margin: auto;cursor: default;">ֆmail</span>
    </div>
    <NavBar/>
    <div style="display: flex;">
      <SideBar/>
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
        username: '',
        email: '',
        userLoggedIn: false
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
  font-size: 3.3vh;
  font-weight: bold;
  color: white;
  background-color: rgba(3, 4, 5, 0.763);
  height: fit-content;
  padding-bottom: 0.2em;
  width: 100vw;
  margin: 0;
  display: flex;
}
</style>
