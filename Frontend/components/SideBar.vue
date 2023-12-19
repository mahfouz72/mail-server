<template>
    <div class="sidebar">
        <button class="sidebar-button" style="border-bottom: 1px solid black;padding-bottom: 1.5vh;"><i class="pi pi-users" style="margin-right: 1vw;"></i>Contacts</button>
        <button class="sidebar-button"><i class="pi pi-inbox" style="margin-right: 1vw;"></i>Inbox</button>
        <button class="sidebar-button"><i class="pi pi-send" style="margin-right: 1vw;"></i>Sent Mails</button>
        <button class="sidebar-button"><i class="pi pi-clock" style="margin-right: 1vw;"></i>Draft</button>
        <button class="sidebar-button" style="border-bottom: 1px solid black;padding-bottom: 1.5vh;"><i class="pi pi-trash" style="margin-right: 1vw;"></i>Trash</button>
        <button class="sidebar-button add-button"><i class="pi pi-plus-circle" style="margin-right: 1vw;"></i>Add Folder</button>
    </div>
</template>

<script>
export default {
    name: 'SideBar',
    // Your component's logic here
}
</script>

<style scoped>
.sidebar {
    background-color: rgba(7, 10, 10, 0.821);
    height: 85.1vh;
    width: 20vw;
    margin: 0;
    border-right: 1px solid black;
    border-bottom: 1px solid black;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

.sidebar-button {
    padding: 1vh;
    width: 100%;
    background-color: transparent;
    border: none;
    color: white;
    font-size: 1.2svw;
    text-align: left;
    cursor: pointer;
}
.sidebar-button:hover {
    background-color: rgba(7, 10, 10, 0.373);
    color: black;
}

.add-button {
    margin-top: auto;
    margin-bottom: 10px;
    font-weight: bold;
}
</style>
