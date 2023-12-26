<template>
    <div class="mainboard">
        <div v-if="listing==='emails'" class="email-list">
            <div class="email" v-for="(email, index) in paginatedElements" :key="index"
                @click="this.$emit('openMail', email.id)">
                <div v-if="multiselect" class="checkbox">
                    <input type="checkbox" :value="email.id" @click.stop="selectEmail(email)" v-model="checkedEmails">
                </div>
                <div class="user">
                    <div v-for="line in getEmailUser(email)" :key="line.value">
                        <label style="font-weight: bold;cursor: pointer;">{{ line.label }}</label>
                        <i>{{ line.value }}</i>
                    </div>
                </div>
                <div class="content">
                    <i><label style="font-weight: bold;cursor: pointer;">Subject: </label>{{ chopString(email.subject, 25)
                    }}</i>
                    <i><label style="font-weight: bold;cursor: pointer;">Body: </label>{{ chopString(email.body, 85) }}</i>
                </div>
                <div class="date">{{ email.date }}</div>
                <div v-if="!multiselect" class="emailOptions">
                    <i class="pi pi-folder-open moveEmail icon" @click.stop="moveEmail(email)"></i>
                    <i class="pi pi-trash deleteEmail icon" @click.stop="deleteEmail(email)"></i>
                </div>
            </div>
        </div>
        <div v-else-if="listing==='Contacts'" class="contacts-list">
            <div class="contact" v-for="(contact, index) in paginatedElements" :key="index">
                <div v-if="multiselect" class="checkbox">
                    <input type="checkbox" :value="contact.id" @click.stop="selectcontact(contact)" v-model="checkedcontacts">
                </div>
                <div class="user">
                    <div v-for="line in getcontactUser(contact)" :key="line.value">
                        <label style="font-weight: bold;cursor: pointer;">{{ line.label }}</label>
                        <i>{{ line.value }}</i>
                    </div>
                </div>
                <div class="content">
                    <i><label style="font-weight: bold;cursor: pointer;">Subject: </label>{{ chopString(contact.subject, 25)
                    }}</i>
                    <i><label style="font-weight: bold;cursor: pointer;">Body: </label>{{ chopString(contact.body, 85) }}</i>
                </div>
                <div class="date">{{ contact.date }}</div>
                <div v-if="!multiselect" class="contactOptions">
                    <i class="pi pi-folder-open movecontact icon" @click.stop="movecontact(contact)"></i>
                    <i class="pi pi-trash deletecontact icon" @click.stop="deletecontact(contact)"></i>
                </div>
            </div>
        </div>
        <div class="pagination">
            <button class="pi pi-angle-left paginationBtn" @click="previousPage" :disabled="currentPage === 1"></button>
            <div class="page-numbers">
                <button v-for="pageNumber in displayedPageNumbers" :key="pageNumber" class="nmbrBtn"
                    :class="{ active: pageNumber === currentPage }" @click="goToPage(pageNumber)">{{ pageNumber }}</button>
            </div>
            <button class="pi pi-angle-right paginationBtn" @click="nextPage"
                :disabled="currentPage === totalPages"></button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MainBoard',
    props: ['emails', 'currentFolder', 'selectedEmails', 'multiselect', 'listing', 'Contacts'],
    data() {
        return {
            checkedEmails: [],
            selEmails: [],
            currentPage: 1,
            elementsPerPage: 10,
        };
    },
    watch: {
        multiselect() {
            this.checkedEmails = [];
            this.selEmails = [];
        }
    },
    computed: {
        paginatedElements() {
            const startIndex = (this.currentPage - 1) * this.elementsPerPage;
            const endIndex = startIndex + this.elementsPerPage;
            if(this.listing==='emails')
                return this.emails.slice(startIndex, endIndex);
            else if(this.listing==='Contacts')
                return this.Contacts.slice(startIndex, endIndex);
            else return [];
        },
        totalPages() {
            if(this.listing==='emails')
                return Math.ceil(this.emails.length / this.elementsPerPage);
            else if(this.listing==='Contacts')
                return Math.ceil(this.Contacts.length / this.elementsPerPage);
            else return [];
        },
        displayedPageNumbers() {
            const range = 10;
            const startPage = Math.max(1, this.currentPage - Math.floor(range / 2));
            const endPage = Math.min(this.totalPages, startPage + range - 1);
            return Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
        }
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
            if (this.currentFolder === 'Inbox') {
                return [{ label: 'From: ', value: email.from }];
            } else if (this.currentFolder === 'Sent' || this.currentFolder === 'Draft') {
                return [{ label: 'To: ', value: email.to }];
            } else if (this.currentFolder === 'Trash') {
                return [{ label: 'From: ', value: email.from }, { label: 'To:', value: email.to }];
            }
        },
        chopString(string, length) {
            if (string.length > length) {
                return `${string.slice(0, length)}...`;
            } else {
                return string;
            }
        },
        selectEmail(email){
            const index = this.checkedEmails.indexOf(email.id);
            if (index === -1) {
                this.checkedEmails.push(email.id);
            } else {
                this.checkedEmails.splice(index, 1);
            }
            this.checkedEmails = this.checkedEmails.filter(id => {
                return this.emails.find(email => email.id === id);
            });
            this.selEmails = this.checkedEmails.map(id => {
                return this.emails.find(email => email.id === id);
            });
            console.log('selected emails in mainboard');
            console.log(this.selEmails);
            this.$emit('update:selectedEmails', this.selEmails);
        },
        // moveEmail(email) {
        //     this.compselectedEmails[email.id] = !this.compselectedEmails[email.id];
        //     this.$emit('update:selectedEmails', this.compselectedEmails);
        //     this.$emit('moveEmail');
        // },
        deleteEmail(email) {
            this.selEmails.push(email);
            this.$emit('deleteEmail', this.selEmails);
        }
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

.user {
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

.emailOptions {
    position: relative;
    left: 5.5vw;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 5%;
    height: 100%;
    color: black;
}

.moveEmail {
    font-size: 3.1vh;
    padding: 1vh;
}

.deleteEmail {
    font-size: 3vh;
    padding: 1vh;
}

.moveEmail:hover {
    color: teal;
}

.deleteEmail:hover {
    color: red;
}

.email .emailOptions {
    display: none;
}

.email:hover .emailOptions {
    display: flex;
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
    font-size: 3vh;
    ;
    cursor: pointer;
}

.paginationBtn:disabled {
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
}</style>