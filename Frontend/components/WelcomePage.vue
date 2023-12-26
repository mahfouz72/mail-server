<template>
    <div class="welcomepage">
        <div class="regBox">
            <i class="Welcome">Welcome</i>
            <form @submit.prevent>
                <div class="form-group">
                    <input type="text" id="username" v-model="username" placeholder="Username" />
                </div>
    
                <div class="form-group">
                    <input type="email" id="email" v-model="email" placeholder="Email" />
                </div>
                <div class="welmsg" :class="msgColor()">
                    {{ WelcomeMsg }}
                </div>
                <div class="Buttons">
                    <button class="Btn" @click="this.$emit('login', this.username, this.email)">Login</button>
                    <button class="Btn" @click="this.$emit('signUp', this.username, this.email)">SignUp</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'WelcomePage',
    props: ['WelcomeMsg'],
    data() {
        return {
            username: '',
            email: '',
        };
    },
    methods: {
        msgColor() {
            if (this.WelcomeMsg === 'Invalid username or email!') {
                return 'red';
            } else if (this.WelcomeMsg === 'User already exists!') {
                return 'red';
            } else if (this.WelcomeMsg === 'User created successfully!') {
                return 'green';
            }
        },
    },
};
</script>

<style scoped>
.welcomepage {
    background-color: rgb(164, 211, 222);
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;
}
.Welcome{
    position: relative;
    top: -5%;
    font-size: 4vh;
    font-weight: bold;
}
.regBox {
    border: 1px solid black;
    padding: 5vh;
    width: 30vw;
    height: 40vh;
    background-color: white;
}
.form-group {
    height: 7vh;
}
#username, #email{
    position: relative;
    left: -1%;
    width: 25vw;
    height: 4vh;
    border: none;
    background-color: transparent;
    border-bottom: 1px solid black;
    padding: 1vh;
    margin: 1vh;
}
#username:focus, #email:focus{
    outline: none;
}
.welmsg{
    padding: 1vh;
    width: 100%;
    height: 4vh;
    font-size: 1.7vh;
    font-weight: bold;
    text-align: center;
}
.red{
    color: red;
}
.green{
    color: green;
}
.Buttons{
    margin-top: 1vh;
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 100%;
    height: 7vh;
}
.Btn{
    width: 8vw;
    height: 5vh;
    padding: 1vh;
    border-radius: 20px;
    cursor: pointer;
    font-weight: bold;
    background-color: azure;
}
.Btn:hover{
    background-color: rgb(205, 246, 255);
}
</style>