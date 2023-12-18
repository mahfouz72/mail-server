<template>
    <div class="welcomepage">
        <div class="regBox">
            <h1 style="color: black;cursor:default;">Welcome</h1>
            <form>
                <div class="form-group">
                    <input type="text" id="username" v-model="username" placeholder="Username" />
                </div>
    
                <div class="form-group">
                    <input type="email" id="email" v-model="email" placeholder="Email" />
                </div>
                <button class="Btn" @click="this.$emit('login', this.username, this.email)">Login</button>
                <button class="Btn" @click="this.$emit('signUp', this.username, this.email)">SignUp</button>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'WelcomePage',
    data() {
        return {
            username: '',
            email: ''
        };
    }
};
</script>

<style scoped>
.welcomepage {
    background-color: gainsboro;
    height: 100vh;
    width: 100vw;
}
.regBox {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin: auto;
    border: 2px solid black;
    border-radius: 10px; 
    padding: 5vh;
    width: 30vw;
    background-color: rgba(5, 5, 5, 0.104);
}
.form-group {
    margin-bottom: 3vh;
}
.Btn{
    width: 8vw;
    height: 5vh;
    margin: 1vw;
    padding: 1vh;
    border-radius: 20px;
    border: 1px solid black;
    cursor: pointer;
}
#username, #email{
    width: 50%;
    height: 4vh;
    border-radius: 5px;
    border: 1px solid black;
    cursor:text;
}
</style>
