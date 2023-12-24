<template>
    <div class="compose">
        <table class="compose">
            <thead>
                <th class="title">New Message</th>
                <th class="close" @click="this.$emit('closeWindow')">x</th>
            </thead>
            <tr>
                <input class="To" type="text" id="Input1" v-model="toField" placeholder="To" />
                <select v-model="priority">
                    <option v-for="option in priorityOptions" :value="option" :key="option">
                        {{ option }}
                    </option>
                </select>
            </tr>
            <tr>
                <input class="subject" type="text" id="Input2" v-model="subjectField" placeholder="Subject" />
            </tr>
            <tr>
                <textarea class="mailBody" id="textAreaInput" name="textAreaInput" style="resize: none;"
                    v-model="bodyField"></textarea>
            </tr>
            <tr class="downBar">
                <input type="file" id="fileupload" ref="fileInput" @change="uploadfile(e)" style="display: none;">
                <button class="pi pi-paperclip attach" @click="this.$refs.fileInput.click();"></button>
                <div class="attchList">
                    <div v-for="attachment in attachmentNames" :key="attachment" class="Attachment">
                        <span @click="download()" style="color.hover: blue; cursor: pointer; ">{{ attachment }}</span>
                        <!-- <span @click="detach()" v-show="attachment !==''" style="cursor: pointer;">x</span> -->
                    </div>
                </div>
                <button class="pi pi-send send" @click="sendmail()"></button>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'ComposeWindow',
    emits: ['closeComposeWindow'],
    data() {
        return {
            toField: '',
            subjectField: '',
            bodyField: '',
            attachmentNames: ['Att1', 'Att2', 'Att3', 'a', 'b', 'Att1', 'Att2', 'Att3', 'a', 'b'], //attachments 
            attachmentIds: [],
            priority: 'MEDIUM',
            priorityOptions: ['urgent', 'high', 'MEDIUM', 'low'],
            wasDraft:'false',
        }
    },
    methods: {
        async sendmail() {

             const mailRequest = {
                from: 'user1@cse.com',
                to: [this.toField],
                subject: this.subjectField,
                body: this.bodyField,
                priority: this.priority,
            };
            console.log("mail reuest: "+JSON.stringify(mailRequest))
            fetch(`http://localhost:8080/compose?wasDraft=${this.wasDraft}`, { 
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(mailRequest),
            })
            .then(res => res.json())
            .then(data => {
              console.log(JSON.stringify(data))
            })
            .catch(error => {
              console.error('Error:', error)
            });
        },
        async uploadfile() {
            let fileInput = document.getElementById('fileupload');
            let formData = new FormData();
            formData.append("data", fileInput.files[0]);
            await fetch('http://localhost:8080/attach', {
                method: "POST",
                body: formData
            }).then(result => result.json())
                .then(result => {
                    this.attachmentNames.push(result.fileName) // attachment file name
                    this.attachmentIds.push(result.id)        // attachment Id
                    //alert(JSON.stringify(result)) // testing and will ve remove
                });
        },
        download() {
            console.log("download")
            fetch('http://localhost:8080/download/' + this.attachmentIds, {
                method: "GET"
            }).then(res => {
                console.log("downloaded successfully") //testing
                window.open(res.url)
            })
        },
        detach() {
            fetch('http://localhost:8080/detach/' + this.attachmentIds, {
                method: "DELETE",
            }).then(res => {
                this.attachmentNames = ''
                console.log(res.url) //testing
                console.log("deleted successfully")
            })
        },

    }
}
</script>

<style scoped>
table {
    text-align: left;
    padding-left: 0.5vw;
}

.dd {
    width: 10vw;
    height: 4vh;
    margin-bottom: 1.2vh;
    margin-left: 1vw;
    margin-right: 1vw;
    background-color: azure;

}

.title {
    font-size: 2.5vh;
    font-weight: bold;
    background-color: transparent;
    color: rgb(0, 195, 255);
}

.close {
    color: whitesmoke;
    background-color: transparent;
    width: 2vw;
    font-size: 1.5vw;
    font-weight: bold;
    text-align: center;
    cursor: pointer;
}

.close:hover {
    color: rgb(240, 0, 0);
}

.To {
    height: 4vh;
    border-radius: 5vh;
    border: 0.2vh solid black;
    padding-left: 0.5vw;
    margin-bottom: 1.2vh;
    width: 50vw;
    background-color: azure;
}

.subject {
    height: 4.5vh;
    border-radius: 5vh;
    border: 0.2vh solid black;
    padding-left: 0.5vw;
    margin-bottom: 1.2vh;
    width: 70vw;
    background-color: azure;
}

.mailBody {
    border-radius: 1vh;
    border: 0.2vh solid black;
    padding-left: 1vw;
    padding-top: 1vh;
    margin-bottom: 1vh;
    height: 50vh;
    width: 75vw;
    text-align: right top;
    resize: none;
    background-color: azure;
}

.compose {
    background-color: #282525;
    height: 85.1vh;
    width: 80vw;
    margin: 0;
    border-bottom: 0.1vw solid black;
}

.attach {
    width: 6vw;
    height: 2.5vw;
    font-size: 2.7vh;
    margin-left: 0.5vw;
    margin-right: 0.5vw;
    cursor: pointer;
    margin-bottom: 0.5vh;
    border-radius: 4vh;
    background-color: rgb(217, 225, 225);
}

.attach:hover {
    color: rgb(7, 45, 11);
    background-color: rgb(131, 129, 129);
}

.send {
    position: relative;
    left: 50vw;
    width: 6vw;
    height: 2.5vw;
    font-size: 2.7vh;
    cursor: pointer;
    margin-bottom: 0.5vh;
    border-radius: 4vh;
    background-color: rgb(217, 225, 225);
}

.send:hover {
    color: rgb(7, 45, 11);
    background-color: rgb(131, 129, 129);
}

.attchList {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    margin-bottom: 1vh;
    margin-left: 1vw;
    width: 10vw;
    height: 15vh;
    overflow-y: scroll;
    padding: 0.2vw;
}

.Attachment {
    width: 8vw;
    height: 3vh;
    margin-bottom: 0.5vh;
    border-radius: 4vh;
    background-color: #9d9d9d;
    padding-left: 0.5vw;
    padding-right: 0.5vw;
    font-size: 2vh;
    font-weight: bold;
    color: black;
}

.downBar {
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: transparent;
    height: 16vh;
    width: 100%;
}

::-webkit-scrollbar {
    width: 0.8vw;
    background-color: #817f7f;
    /* Dark background */
    border-radius: 5px;
}

::-webkit-scrollbar-thumb {
    background-color: #423e3e;
    /* Lighter handle */
    border-radius: 5px;
}
</style>