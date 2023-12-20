<template>
    <div class="mainboard">
        <!-- Your navbar content here -->
        <ComposeWindow></ComposeWindow>
    </div>
</template>

<script>
import ComposeWindow from './ComposeWindow.vue';
export default {
    name: 'MainBoard',
    components:{ComposeWindow}
    // Your component's logic here
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
</style>
