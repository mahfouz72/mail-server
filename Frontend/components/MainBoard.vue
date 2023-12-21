<template>
    <div class="mainboard">
        <!-- <ComposeWindow></ComposeWindow> -->
        <div class="email-list">
            <div class="email" v-for="(email, index) in paginatedEmails" :key="index">
                {{ email }}
            </div>
        </div>
        <div class="pagination">
            <button class="pi pi-angle-left paginationBtn" @click="previousPage" :disabled="currentPage === 1"></button>
            <div class="page-numbers">
                <button v-for="pageNumber in displayedPageNumbers" :key="pageNumber" class="nmbrBtn" :class="{ active: pageNumber === currentPage }" @click="goToPage(pageNumber)">{{ pageNumber }}</button>
            </div>
            <button class="pi pi-angle-right paginationBtn" @click="nextPage" :disabled="currentPage === totalPages"></button>
        </div>
    </div>
</template>

<script>
// import ComposeWindow from './ComposeWindow.vue';
export default {
    name: 'MainBoard',
    // components:{ComposeWindow}
    props: ['emails'],
    data() {
        return {
            currentPage: 1,
            emailsPerPage: 10,
        };
    },
    computed: {
        paginatedEmails() {
            const startIndex = (this.currentPage - 1) * this.emailsPerPage;
            const endIndex = startIndex + this.emailsPerPage;
            return this.emails.slice(startIndex, endIndex);
        },
        totalPages() {
            return Math.ceil(this.emails.length / this.emailsPerPage);
        },
        displayedPageNumbers() {
            const range = 10;
            const startPage = Math.max(1, this.currentPage - Math.floor(range / 2));
            const endPage = Math.min(this.totalPages, startPage + range - 1);
            return Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
        },
    },
    methods: {
        previousPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
        },
        goToPage(pageNumber) {
            this.currentPage = pageNumber;
        },
    },
}
</script>

<style scoped>
.mainboard {
    background-color: rgba(7, 10, 10, 0.94);
    height: 85.1vh;
    width: 80vw;
    margin: 0;
    border-bottom: 0.1vw solid black;
}
.email-list {
    height: 90%;
    width: 100%;
    justify-content: flex-start;
    align-items: center;
}
.email {
    background-color: rgba(127, 129, 132, 0.821);
    height: 6.5vh;
    width: 95%;
    margin: 1vh;
    border-radius: 10px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding-left: 1vw;
    font-size: 2vh;
    color: black;
}
.email:hover {
    background-color: rgba(127, 129, 132, 0.5);
    color: white;
    cursor: pointer;
}
.pagination {
    margin-top: 2vh;
}
.paginationBtn {
    background-color: transparent;
    border: none;
    color: white;
    font-size: 3vh;;
    cursor: pointer;
}
.paginationBtn:disabled{
    color: grey;
}
.page-numbers {
    display: inline-flex;
    font-size: 1vh;
    transform: translate(0, -0.5vh);
}
.nmbrBtn {
    background-color: transparent;
    border: none;
    color: white;
    font-size: 2vh;
    cursor: pointer;
}
.nmbrBtn.active {
    color: grey;
}
</style>