<template>
    <div class="compose">
        <!-- Your navbar content here -->
        <table class="compose">
            <thead>
                <th class="left">New Message</th>
                <th class="right" @Click="this.$emit('closeWindow')">x</th>
            </thead>
            <tr class="To">
                <input type="text" id="Input1" v-model="toField" placeholder="To" />
            </tr>
            <tr class="subject">
                <input type="text" id="Input2" v-model="subjectField" placeholder="Subject" />
            </tr>
            <tr class="mailBody">
                <textarea id="textAreaInput" name="textAreaInput" v-model="bodyField"></textarea>
            </tr>
            <tr class="bottom">
                <input type="file" id="fileupload" ref="fileInput" @change="uploadfile(e)" style="display: none;">
                <div > 
                    <span @click="download()" style="color: blue; cursor: pointer;">{{ attachmentNames }}</span>
                    <span @click="detach()" v-show="attachmentNames !==''" style="cursor: pointer;">   x</span>
                </div>
                <button class="pi pi-paperclip" @click="this.$refs.fileInput.click();"></button>
                <button class="pi pi-send"></button>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'ComposeWindow',
    // Your component's logic here
    data() {
        return {
            toField: '',
            subjectField: '',
            bodyField: '',
            attachmentNames:'', //attachments 
            attachmentId: '',

        }
    },
    methods: {
        async uploadfile() {            
            let fileInput = document.getElementById('fileupload');
            let formData = new FormData();
            formData.append("data", fileInput.files[0]);
            await fetch('http://localhost:8080/attach', {
                method: "POST",
                body: formData
            }).then(result => result.json())
                .then(result => {
                    this.attachmentNames=result.fileName // attachment file name
                    this.attachmentId = result.id// attachment Id
                    //alert(JSON.stringify(result)) // testing and will ve remove
                });
        },
        download() {
            console.log("download")
            fetch('http://localhost:8080/download/' + this.attachmentId, {
                method: "GET"
            }).then(res => {
                console.log("downloaded successfully") //testing
                window.open(res.url)
            })
        },
        detach() {
            fetch('http://localhost:8080/detach/' + this.attachmentId, {
                method: "DELETE",
            }).then(res => {
                this.attachmentNames = ''
                console.log(res.url) //testing
                console.log("deleted successfully") 
            })
        }
    }
}
</script>

<style scoped>
table {
    text-align: left;

}

thead {
    background-color: rgba(0, 0, 0, 0.1);
    color: black;
    height: 10px;

}

th.left {
    text-align: left;
}

th.right {
    text-align: right;
    cursor: pointer;
}

.To {
    height: 30px;
}

.subject {
    height: 30px;
}

#Input1 {
    width: 100%;
}

#Input2 {
    width: 100%;
}

#textAreaInput {
    height: 400px;
    width: 100%;
    text-align: right top;

}

.bottom {
    height: 10px;
}

.pi-paperclip {
    cursor: pointer;
}

.pi-send {
    height: 30px;
    border-radius: 12px;
    width: 60px;
    cursor: pointer;
    background-color: rgba(0, 0, 255, 0.4);
}

.compose {
    background-color: #ffff;
    height: 100%;
    width: 100%;
    margin: 0;
    border-bottom: 0.1vw solid black;
}
</style>
