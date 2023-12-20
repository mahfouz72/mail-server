<template>
    <div class="compose">
        <!-- Your navbar content here -->
        <table class="compose">
            <thead>
                <th class="left">New Message</th>
                <th class="right">x</th>
            </thead>
            <tr class="To"> 
                <input type="text" id="Input1" v-model="toField" placeholder="To" />
            </tr>
            <tr class="subject">
                <input type="text" id="Input2" v-model="subjectField" placeholder="Subject" />
            </tr>
            <tr class="mailBody">
                <input type="text" id="Input3" v-model="bodyFieldField"  />
            </tr>
            <tr class="bottom">
                <button>send</button>
                <button>attach</button>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'ComposeWindow',
    // Your component's logic here
    data(){
        return{
            toField: '',
            subjectField: '',
            bodyField: '',
        }
    }
}
</script>

<style scoped>

table{
    text-align: left;
    
}
thead{
  background-color:  rgba(0, 0, 0, 0.1);
  color: black;
  height: 10px;

}
th.left {
text-align: left;
}
  
th.right {
text-align: right;
}
.To{
    height: 30px;
}
.subject{
    height: 30px;
}
#Input1{
    width: 100%;
}
#Input2{
    width: 100%;
}
#Input3{
    height: 400px;
    width: 100%;
}
.bottom{
    height: 10px;
}
.compose {
    background-color: #ffff;
    height: 75.1vh;
    width: 40vw;
    margin: 0;
    border-bottom: 0.1vw solid black;
}
</style>
