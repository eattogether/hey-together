<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>주문 참여하기</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
            <v-form>
                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-silverware-variant</v-icon>
                    <v-text-field label="메뉴 선택" name="menu" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-currency-krw</v-icon>
                    <v-text-field label="주문 금액" name="orderPrice" type="text"/>
                </v-row>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer/>
            <v-btn color="teal" v-on:click="closeEvent">Close</v-btn>
            <v-btn color="teal">참여하기</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: {
            source: String
        },
        methods: {
            closeEvent: function() {
                this.$emit('closeJoiningRoomModal');
            }
        }
    };
</script>