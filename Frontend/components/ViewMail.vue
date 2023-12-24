<template>
    <div class="viewMail">
        <div class="viewMailHeader">
            <div class="viewMailHeaderLeft">
                <button class="backBtn" @click="$emit('closeViewMail')">
                    <i class="pi pi-arrow-left"></i>
                </button>
                <span class="viewMailHeaderSubject">{{email.subject}}</span>
            </div>
        </div>
        <div class="viewMailBody">
            <div class="viewMailBodyHeader">
                <div class="viewMailBodyHeaderLeft">
                    <span class="viewMailBodyHeaderFrom">From: {{email.from}}</span>
                    <span class="viewMailBodyHeaderTo">To: {{email.to}}</span>
                </div>
                <div class="viewMailBodyHeaderRight">
                    <span class="viewMailBodyHeaderDate">{{email.date}}</span>
                </div>
            </div>
            <div class="viewMailBodyBody">
                <span class="viewMailBodyBodyText">{{email.body}}</span>
            </div>
            <div class="viewMailBodyFooter">
                <span class="viewMailBodyFooterAttachments">Attachments: </span>
                <span class="viewMailBodyFooterAttachments" v-for="attachment in email.attachments" :key="attachment">
                    <button class="viewMailBodyFooterAttachmentBtn" @click="download(attachment.id)">
                        <i class="pi pi-paperclip"></i>
                        {{attachment.name}}
                    </button>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ViewMail',
    props: ['email'],

};
</script>

<style scoped>
.viewMail {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 80vw;
    background-color: rgba(7, 10, 10, 0.821);
    border-left: 0.1vw solid black;
    border-right: 0.1vw solid black;
    border-bottom: 0.1vw solid black;
}
.viewMailHeader {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 5%;
    width: 100%;
    background-color: rgba(7, 10, 10, 0.821);
    border-bottom: 0.1vw solid black;
}
.viewMailHeaderLeft {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 100%;
    width: 50%;
}
.viewMailHeaderSubject {
    font-size: 2.5vh;
    font-weight: bold;
    margin-left: 1vw;
}
.backBtn {
    background-color: transparent;
    border: none;
    color: white;
    font-size: 2.5vh;
    cursor: pointer;
}
.backBtn:hover {
    color: black;
}
.viewMailBody {
    display: flex;
    flex-direction: column;
    height: 95%;
    width: 100%;
    background-color: rgba(7, 10, 10, 0.821);
}
.viewMailBodyHeader {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    height: 10%;
    width: 100%;
    background-color: rgba(7, 10, 10, 0.821);
    border-bottom: 0.1vw solid black;
}
.viewMailBodyHeaderLeft {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 100%;
    width: 50%;
}
.viewMailBodyHeaderFrom {
    font-size: 2vh;
    font-weight: bold;
    margin-left: 1vw;
}
.viewMailBodyHeaderTo {
    font-size: 2vh;
    font-weight: bold;
    margin-left: 1vw;
}
.viewMailBodyHeaderRight {
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    height: 100%;
    width: 50%;
}
.viewMailBodyHeaderDate {
    font-size: 2vh;
    font-weight: bold;
    margin-right: 1vw;
}
.viewMailBodyBody {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    height: 70%;
    width: 100%;
    background-color: rgba(7, 10, 10, 0.821);
    border-bottom: 0.1vw solid black;
    padding-left: 1vw;
    padding-top: 1vh;
    padding-right: 1vw;
    overflow-y: scroll;
}
.viewMailBodyBodyText {
    font-size: 2vh;
    font-weight: bold;
}
.viewMailBodyFooter {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 20%;
    width: 100%;
    background-color: rgba(7, 10, 10, 0.821);
    padding-left: 1vw;
    padding-top: 1vh;
    padding-right: 1vw;
}
.viewMailBodyFooterAttachments {
    font-size: 2vh;
    font-weight: bold;
}
.viewMailBodyFooterAttachmentBtn {
    background-color: transparent;
    border: none;
    color: white;
    font-size: 2vh;
    cursor: pointer;
}
.viewMailBodyFooterAttachmentBtn:hover {
    color: black;
}

</style>
