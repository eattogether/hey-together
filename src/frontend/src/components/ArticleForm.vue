<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>같이 묵자!</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
            <v-form>
                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-map-marker</v-icon>
                    <v-text-field label="배달 위치" name="location" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-home-map-marker</v-icon>
                    <v-text-field label="주문 가게" name="shop" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-currency-krw</v-icon>
                    <v-text-field label="최소 주문 금액" name="minOrderPrice" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-motorbike</v-icon>
                    <v-text-field label="배달팁" name="deliveryTip" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-silverware-variant</v-icon>
                    <v-text-field label="메뉴 선택" name="menu" type="text"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-timer</v-icon>
                    <v-text-field label="시간 제한" name="time" type="text"/>
                </v-row>

            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer/>
            <v-btn color="teal" v-on:click="closeEvent">Close</v-btn>
            <v-btn color="teal">등록하기</v-btn>
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
                this.$emit('closeWriteModal');
            }
        }
    };
</script>