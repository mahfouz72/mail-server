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
      <MainBoard :emails = "emails"/>
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
        emails: ['email1', 'email2', 'email3', 'email4', 'email5', 'email6', 'email7', 'email8', 'email9', 'email10', 'email11', 'email12', 'email13', 'email14', 'email15', 'email16', 'email17', 'email18', 'email19', 'email20', 'email21', 'email22', 'email23', 'email24', 'email25', 'email26', 'email27', 'email28', 'email29', 'email30', 'email31', 'email32', 'email33', 'email34', 'email35', 'email36', 'email37', 'email38', 'email39', 'email40', 'email41', 'email42', 'email43', 'email44', 'email45', 'email46', 'email47', 'email48', 'email49', 'email50', 'email51', 'email52', 'email53', 'email54', 'email55', 'email56', 'email57', 'email58', 'email59', 'email60', 'email61', 'email62', 'email63', 'email64', 'email65', 'email66', 'email67', 'email68', 'email69', 'email70', 'email71', 'email72', 'email73', 'email74', 'email75', 'email76', 'email77', 'email78', 'email79', 'email80', 'email81', 'email82', 'email83', 'email84', 'email85', 'email86', 'email87', 'email88', 'email89', 'email90', 'email91', 'email92', 'email93', 'email94', 'email95', 'email96', 'email97', 'email98', 'email99', 'email100', 'email101', 'email102', 'email103', 'email104', 'email105', 'email106', 'email107', 'email108', 'email109', 'email110', 'email111', 'email112', 'email113', 'email114', 'email115', 'email116', 'email117', 'email118', 'email119', 'email120', 'email121', 'email122', 'email123', 'email124', 'email125', 'email126', 'email127', 'email128', 'email129', 'email130', 'email131', 'email132', 'email133', 'email134', 'email135', 'email136', 'email137', 'email138', 'email139', 'email140', 'email141', 'email142', 'email143', 'email144', 'email145', 'email146', 'email147', 'email148', 'email149', 'email150', 'email151', 'email152', 'email153', 'email154', 'email155', 'email156', 'email157', 'email158', 'email159', 'email160', 'email161', 'email162', 'email163', 'email164', 'email165', 'email166', 'email167', 'email168', 'email169', 'email170', 'email171', 'email172', 'email173', 'email174', 'email175', 'email176', 'email177', 'email178', 'email179', 'email180', 'email181', 'email182', 'email183', 'email184', 'email185', 'email186', 'email187', 'email188', 'email189', 'email190', 'email191', 'email192', 'email193', 'email194', 'email195', 'email196', 'email197', 'email198', 'email199', 'email200']
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