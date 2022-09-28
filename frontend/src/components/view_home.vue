<template>
    <v-container>
        <v-row>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                1
            </v-col>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                빈 공간
            </v-col>
        </v-row>
        <v-row>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                <v-textarea
                clearable
                clear-icon="mdi-close-circle"
                v-model="message"
                >메세지 입력</v-textarea>
            </v-col>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                <v-btn @click="sendMessage()">전송</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                {{ recvList }}
            </v-col>
            <v-col class="bg-red-lighten-2 ma-2 text-center">
                여분
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import SockJS from 'sockjs-client'
const Stomp = require('stompjs');

export default {
    data() {
        return {
            message: undefined,
            stompClient: undefined,
            recvList: [],
        }
    },
    methods: {
        funcSub(res) {
            console.log("res.body: " + res.body);
            this.recvList.push(res.body);
        },

        connect() {
            var socket = new SockJS(this.$backendUrl + '/ws');
            this.stompClient = Stomp.over(socket);
            this.stompClient.connect({}, () => {
                this.stompClient.subscribe('/sub/rooms/5', this.funcSub);
            });
        },

        clearText() {
            this.message = "";
        },

        sendMessage() {
            this.stompClient.send("/pub/messages", {}, JSON.stringify({
                'message': this.message,
                'senderId': 7,
                'receiverId': 14,
                'roomId': 5
            }));
            this.clearText();
        }
    },
    created() {
        this.connect();
    },
}
</script>
