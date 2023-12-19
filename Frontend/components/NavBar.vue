<template>
    <div class="navbar">
        <span class="pi pi-refresh refresh"></span>
        <span style="position: absolute; left: 3vw;">
            <button class="compose" @click="this.$emit('compose')">
                <i class="pi pi-pencil icon" style="position: absolute;left: 0.7vw;font-size: 2.5vh;margin-top: 0.2vh;"></i>
                Compose
            </button>
        </span>
        <span class="search-container">
            <input class="search-field" type="text" v-model="value" @input="search" placeholder="Search"/>
            <button class="pi pi-search icon searchbtn"></button>
        </span>
        <span style="position: absolute; right: 1vw;" @click="toggleDropdown">
            <label style="color: rgb(236, 228, 228); margin-right: 0.2vw;font-size: 2.4vh;">{{sortOption}}</label>
            <button class="dropbtn">
                <i class="pi pi-sort-alt icon"></i>
            </button>
            <span class="dropdown-content" v-show="showDropdown">
                <option v-for="option in sortOptions" :key="option" @click.stop="Sort(option)">{{option}}</option>
            </span>
        </span>
    </div>
</template>

<script>
import 'primeicons/primeicons.css';
import 'primevue/resources/themes/mira/theme.css';

export default {
    name: 'NavBar',
    data() {
        return {
            sortOption: 'Date',
            sortOptions: ['Date', 'Sender', 'Receivers', 'Importance', 'Subject', 'Body', 'Attachments'],
            showDropdown: false,
            value: '',
            items: ['Youssif', 'Ahmed', 'Mohamed'],
            searchResults: [],
        };
    },
    methods: {
        Sort(option) {
            this.sortOption = option;
            this.$emit('sort', this.sortOption);
            this.showDropdown = false;
        },
        toggleDropdown() {
            this.showDropdown = !this.showDropdown;
            console.log(this.showDropdown);
        },
        search() {
            this.searchResults = this.items.filter((item) => {
                return item.toLowerCase().startsWith(this.value.toLowerCase());
            });  
            console.log(this.items);    
        }
    },
};
</script>

<style scoped>
.navbar {
    position: relative;
    background-color: rgba(7, 10, 10, 0.821);
    height: 5.7vh;
    width: 100vw;
    margin: 0;
    border-bottom: 0.1vw solid black;
    border-top: 0.1vw solid black;
}

.dropbtn {
    margin-top: 1vh;
    border-radius: 1vw;
    border: 0.1vw solid black;
    cursor: pointer;
    padding: 0.1vh 0.4vw;
}

.dropdown-content {
    position: absolute;
    background-color: #f9f9f9;
    right: 0;
    top: 5.3vh;
}

.dropdown-content option{
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    cursor: pointer;
}
.dropdown-content option:hover {
    background-color: #b9b9b9;
}
.compose {
    color: aliceblue;
    margin-top: 0.7vh;
    width: 12vw;
    border-radius: 1vw;
    cursor: pointer;
    background-color: rgba(32, 30, 30, 0.863);
    border: 0.1vw solid black;
    font-size: 3vh;
}
.compose:hover {
    background-color: rgba(22, 20, 20, 0.863);
}
.search-container{
    margin-top:1vh;
    width: 30vw;
    position: relative;
    display: inline-block;
}
.search-field {
    width: 100%;
    padding: 0.5vh 0.5vw;
    border-radius: 10px;
    border: 0.1vw solid black;
    font-size: 2vh;
    background-color: rgba(32, 30, 30, 0.863);
    color: aliceblue;
}

.pi-search{
    position: absolute;
    right: 0.5vw;
    top: 0.5vh;
    background: none;
    border: none;
    color: black;
    font-weight: bold;
    cursor: pointer;
}

input[type="text"] {
    padding-right: 3.5vw; /* Make room for the search icon */
}
.icon{
    font-size: 1.1vw;
}
.refresh{
    position: absolute;
    left: 2vh;
    top: 1vh;
    font-size: 3vh;
    background: none;
    border: none;
    color: black;
    font-weight: bold;
    cursor: pointer;
}
.refresh:hover{
    color: white;
}
</style>