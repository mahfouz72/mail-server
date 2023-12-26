<template>
  <WelcomePage v-if="!userLoggedIn" @login="login" @signUp="signUp" :WelcomeMsg="WelcomeMsg"/>
  <div v-else>
    <div class="Title">
      <span style="position: absolute; left: 0.1vw;margin-top: 0.2vh;">
        <i class="pi pi-user icon" style="font-size: 2.8vh;"></i>
        <i style="font-size: 2vh;margin-left: 0.2vw;">{{ username }}</i>
      </span>
      <span style="margin: auto;cursor: default;">ֆmail</span>
      <span style="position: absolute; right: 0.1vw;">
        <button class="logout" @click="logout">
          <i class="pi pi-sign-out" style="font-size: 2vh;"></i>
          Logout
        </button>
      </span>
    </div>
    <NavBar :currentFolder="currentFolder" :multiselect="multiselect" :composing="composing"
      @toggleMultiSelect="toggleMultiSelect" @compose="composing = !composing" @sort="sort"
      @deleteEmail="deleteEmail(this.selectedEmails)" @open="open(this.currentFolder)"/>
    <div style="display: flex;">
      <SideBar :currentFolder="currentFolder" :useremail="useremail" @open="open" @openContacts="openContacts"
        @openDraft="openDraft" />
      <div>
        <MainBoard v-if="!composing && windowState === 'viewFolders'" @openMail="openMail" :emails="emails" :Contacts="Contacts" :listing="listing"
          @moveEmail="moveEmail" @deleteEmail="deleteEmail" :currentFolder="currentFolder" :multiselect="multiselect"
          v-model:selectedEmails="selectedEmails"></MainBoard>
        <ViewMail v-else-if="!composing && windowState === 'viewMail'" @closeViewMail="windowState = 'viewFolders'"
          :email="email" />
        <ComposeWindow v-else @closeWindow="composing = false" :useremail="useremail" />
      </div>
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
import ComposeWindow from './components/ComposeWindow.vue';
import ViewMail from './components/ViewMail.vue';
import 'primeicons/primeicons.css';

export default {
  name: "App",
  components: { WelcomePage, NavBar, SideBar, MainBoard, ComposeWindow, ViewMail },
  data() {
    return {
      username: '',
      useremail: '',
      userLoggedIn: false,
      WelcomeMsg: '',
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
        attachments: []
      },
      multiselect: false,
      selectedEmails: [],
      composing: false,
      windowState: 'viewFolders',
      Contacts: [],
      listing: 'emails'
    }
  },
  methods: {
    login(username, useremail) {
      const userRequest = {
        userName: username,
        email: useremail,
      };
      fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userRequest),
      })
        .then(res => res.text())
        .then(data => {
          console.log(data)
          if (data === 'true') {
            this.username = username;
            this.useremail = useremail;
            this.userLoggedIn = true;
            this.WelcomeMsg = '';
            this.open('Inbox');
          } else {
            this.WelcomeMsg = 'Invalid username or email!';
          }
        })
    },
    signUp(username, useremail) {
      const userRequest = {
        userName: username,
        email: useremail,
      };
      fetch('http://localhost:8080/user/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userRequest),
      })
        .then(res => res.json())
        .then(data => {
          if (data.errors) {
            this.WelcomeMsg  = data.errors[0].defaultMessage;
          } else {
            this.WelcomeMsg = 'User created successfully!';
          }
        })
        .catch(error => console.error('Error:', error));
    },
    logout() {
      this.username = '';
      this.useremail = '';
      this.userLoggedIn = false;
    },
    open(folder) {
      this.currentFolder = folder;
      this.emails = [];
      this.selectedEmails = [];
      this.listing = 'emails';
      fetch('http://localhost:8080/' + this.useremail + '/' + folder.toLowerCase(), {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      })
        .then(res => res.json())
        .then(data => {
          console.log(JSON.stringify(data))
          if (data.length > 0) {
            data.forEach(email => {
              this.emails.push({
                id: email.id,
                from: email.from,
                to: email.to,
                subject: email.subject,
                body: email.body,
                date: email.dateTime,
                priority: email.priority,
                attachments: email.attachments
              })
            });
          } else {
            this.emails = [];
          }
          console.log(this.emails)
        })
        .catch(error => {
          console.error('Error:', error)
        });
      this.multiselect = false;
    },
    openContacts(){
      this.listing = 'contacts';
      this.Contacts = [{name: 'user2',emails:['user21@cse.com','user22@cse.com']},{name: 'user3',emails:['user31@cse.com','user32@cse.com']},{name: 'user4',emails:['user41@cse.com','user42@cse.com']}];
    },
    openDraft(){
      this.windowState = 'viewMail';
    },
    toggleMultiSelect() {
      console.log(this.selectedEmails);
      this.multiselect = !this.multiselect;
      this.selectedEmails = [];
    },
    openMail(id) {
      this.windowState = 'viewMail';
      this.email = this.emails.find(email => email.id === id);
    },
    sort(option) {
      fetch(`http://localhost:8080/${this.useremail}/${this.currentFolder}?sortingCriteria=${option}`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      })
        .then(res => res.json())
        .then(data => {
          console.log(JSON.stringify(data))
          if (data.length > 0) {
            this.emails = [];
            data.forEach(email => {
              this.emails.push({
                id: email.id,
                from: email.from,
                to: email.to,
                subject: email.subject,
                body: email.body,
                date: email.date,
                priority: email.priority,
                attachments: email.attachments
              })
            });
          }
          console.log(this.emails)
        })
        .catch(error => {
          console.error('Error:', error)
        });
    },
    deleteEmail(selEmails) {
      console.log('selected emails:');
      console.log(this.selectedEmails);
      for (let email of selEmails) {
        fetch('http://localhost:8080/' + this.useremail + '/' + this.currentFolder.toLowerCase() + '/' + email.id, {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
        }).then(res => res.json())
          .then(data => {
            console.log(JSON.stringify(data))
            this.emails = this.emails.filter(e => e.id !== email.id);
          })
      }
      this.selectedEmails = [];
    }
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
}

body {
  margin: 0;
  padding-top: 0%;
  height: 100%;
  width: 100%;
}

footer {
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