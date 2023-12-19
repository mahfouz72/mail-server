<template>
    <div class="navbar">
        <span style="position: absolute; right: 1vw;" @click="toggleDropdown">
            <label style="color: rgb(236, 228, 228); margin-right: 0.1vw;">{{ sortOption }}</label>
            <button class="dropbtn">
                <i class="pi pi-sort-alt"></i>
            </button>
            <span class="dropdown-content" v-show="showDropdown">
                <option v-for="option in sortOptions" :key="option" @click.stop="Sort(option)">
                    {{ option }}
                </option>
            </span>
        </span>
        <span style="position: absolute; left: 3vw;">
            <button class="compose" @click="this.$emit('compose')">
                <i class="pi pi-pencil" style="position: absolute;left: 0.7vw;font-size: 2.5vh;margin-top: 0.2vh;"></i>
                Compose
            </button>
        </span>
    </div>
</template>

<script>
import 'primeicons/primeicons.css';

export default {
    name: 'NavBar',
    data() {
        return {
            sortOption: '',
            sortOptions: ['Date', 'Sender', 'Receivers', 'Importance', 'Subject', 'Body', 'Attachments'],
            showDropdown: false,
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
    },
};
</script>

<style scoped>
.navbar {
    position: relative;
    background-color: rgba(7, 10, 10, 0.821);
    height: 5.3vh;
    width: 100vw;
    margin: 0;
    border-bottom: 1px solid black;
    border-top: 1px solid black;
}

.dropbtn {
    margin-top: 1vh;
    border-radius: 10px;
    cursor: pointer;
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
    border-radius: 20px;
    cursor: pointer;
    background-color: rgba(32, 30, 30, 0.863);
    border: 1px solid black;
    font-size: 3vh;
}
.compose:hover {
    background-color: rgba(22, 20, 20, 0.863);
}
</style>