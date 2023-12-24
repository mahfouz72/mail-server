<template>
    <div class="sidebar">
        <button :class="{selected: currentFolder === 'Contacts'}" @click="$emit('open','Contacts')" 
            class="sidebar-button"><i class="pi pi-users icon"></i>Contacts</button>
        <i style="border-bottom: 0.1vw solid black;border-top: 0.1vw solid black ;padding-bottom: 1.5vh;padding: 0;">
            <button :class="{selected: currentFolder === 'Inbox'}" @click="$emit('open','Inbox')"
                    class="sidebar-button"><i class="pi pi-inbox icon"></i>Inbox</button>
            <button :class="{selected: currentFolder === 'Sent'}" @click="$emit('open','Sent')"
                    class="sidebar-button"><i class="pi pi-send icon"></i>Sent</button>
            <button :class="{selected: currentFolder === 'Draft'}" @click="$emit('open','Draft')"
                    class="sidebar-button"><i class="pi pi-clock icon"></i>Draft</button>
            <button :class="{selected: currentFolder === 'Trash'}" @click="$emit('open','Trash')"
                    class="sidebar-button"><i class="pi pi-trash icon"></i>Trash</button>
        </i>
        <div class="userFolders">
            <button v-for="folder in Addedfolders" :key="folder" class="userFolder">{{ folder }}</button>
        </div>
        <button class="sidebar-button add-button" @click="addFolder"><i class="pi pi-plus-circle  icon"></i>Add Folder</button>
    </div>
</template>

<script>
export default {
    name: 'SideBar',
    props: ['currentFolder'],
    data() {
        return {
            Addedfolders: [],
        };
    },
    methods: {
        addFolder() {
            this.Addedfolders.push('New Folder');
        },
    },
}
</script>

<style scoped>
.sidebar {
    background-color: rgba(7, 10, 10, 0.821);
    height: 85.1vh;
    width: 20vw;
    margin: 0;
    border-right: 0.1vw solid black;
    border-bottom: 0.1vw solid black;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}
.sidebar-button {
    padding: 2vh;
    width: 100%;
    background-color: transparent;
    border-top-right-radius: 30px;
    border-bottom-right-radius: 30px;
    border: none;
    color: white;
    font-size: 1.2vw;
    text-align: left;
    cursor: pointer;
}
.selected {
    background-color: rgba(7, 10, 10, 0.531);
    color: black;
}
.sidebar-button.selected:hover {
    background-color: rgba(7, 10, 10, 0.531);
    color: black;
}
.sidebar-button:hover {
    background-color: rgba(7, 10, 10, 0.215);
    color: black;
}
.userFolders {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    width: 100%;
    height: 100%;
    margin-top: 2;
    overflow-y: scroll;
}
::-webkit-scrollbar{
    width: 0.8vw;
    background-color: #222121; /* Dark background */
    border-radius: 5px;
}

::-webkit-scrollbar-thumb{
    background-color: #888; /* Lighter handle */
    border-radius: 5px;
}
.userFolder {
    width: 100%;
    background-color: transparent;
    padding: 1vh;
    border-top-right-radius: 30px;
    border-bottom-right-radius: 30px;
    border: none;
    color: white;
    font-size: 1.2vw;
    text-align: left;
    cursor: pointer;
}
.userFolder:hover {
    background-color: rgba(7, 10, 10, 0.215);
    color: black;
}
.add-button {
    margin-top: auto;
    margin-bottom: 10px;
    font-weight: bold;
}
.icon{
    margin-right: 1vw;
    font-size: 1.2vw;
}
</style>
