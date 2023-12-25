<template>
    <div class="mainboard">
        <div class="email-list">
            <div class="email" v-for="(email, index) in paginatedEmails" :key="index" @click="this.$emit('openMail',email.id)">
                <div v-if="multiselect" class="checkbox">
                    <input type="checkbox" :checked="selectedEmails[email.id]" @click.stop="selectEmail(email)">
                </div>
                <div class="user">
                    <div v-for="line in getEmailUser(email)" :key="line.value">
                        <label style="font-weight: bold;cursor: pointer;">{{ line.label }}</label>
                        <i>{{ line.value }}</i>
                    </div>
                </div>
                <div class="content">
                    <i><label style="font-weight: bold;cursor: pointer;">Subject: </label>{{ chopString(email.subject,25) }}</i>
                    <i><label style="font-weight: bold;cursor: pointer;">Body: </label>{{ chopString(email.body,85) }}</i>
                </div>
                <div class="date">{{ email.date }}</div>
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
export default {
    name: 'MainBoard',
    props: ['emails', 'currentFolder', 'selectedEmails', 'multiselect'],
    data() {
        return {
            currentPage: 1,
            emailsPerPage: 10,
            compselectedEmails: {},
        };
    },
    watch: {
        multiselect(newVal) {
            if(!newVal){
                this.compselectedEmails = {};
                this.$emit('update:selectedEmails', this.compselectedEmails);
            }
        }
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
        getEmailUser(email) {
            if(this.currentFolder === 'Inbox') {
                return [{ label: 'From: ', value: email.from }];
            }else if(this.currentFolder === 'Sent' || this.currentFolder === 'Draft') {
                return [{ label: 'To: ', value: email.to }];
            }else if(this.currentFolder === 'Trash'){
                return [{ label: 'From: ', value: email.from }, { label: 'To:', value: email.to }];
            }
        },
        chopString(string, length){
            if(string.length > length){
                return `${string.slice(0, length)}...`;
            }else{
                return string;
            }
        },
        selectEmail(email){
            this.compselectedEmails[email.id] = !this.compselectedEmails[email.id];
            this.$emit('update:selectedEmails', this.compselectedEmails);
        },
    }
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
    padding-left: 1vw;
    padding-top: 1.5vh;
}
.email {
    background-color: rgba(127, 129, 132, 0.821);
    height: 6.5vh;
    width: 95%;
    margin-bottom: 1vh;
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
.user{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    width: 20%;
    height: 100%;
    margin-right: 1vw;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    width: 55%;
    height: 100%;
    margin-right: 1vw;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.date {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 8%;
    height: 40%;
}
.checkbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 5%;
    height: 40%;
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