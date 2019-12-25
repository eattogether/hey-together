<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>같이 묵자!</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
            <v-form>
                <!--                <v-row>-->
                <!--                    <v-icon class="mr-2 ml-1">mdi-map-marker</v-icon>-->
                <!--                    <v-text-field label="배달 위치" name="location" type="text"/>-->
                <!--                </v-row>-->

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-pencil</v-icon>
                    <v-text-field label="제목" name="title" type="text" v-model="title"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-home-map-marker</v-icon>
                    <v-text-field label="주문 가게" name="shop" type="text" v-model="shopName"/>
                    <v-btn v-on:click="registerShop()">등록</v-btn>
                </v-row>

                <v-list dense>
                    <v-list-item>
                        <v-list-item-action>
                            <v-icon>mdi-currency-krw</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>최소 주문 금액</v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-content>
                            {{ minimumOrderPrice }}
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-action>
                            <v-icon>mdi-motorbike</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>배달 팁</v-list-item-title>
                        </v-list-item-content>
                        <v-list-item-content>
                            {{ deliveryTip }}
                        </v-list-item-content>
                    </v-list-item>
                </v-list>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-silverware-variant</v-icon>
                    <v-overflow-btn
                            :items="menu"
                            v-model="selectedMenu"
                            label="메뉴 선택"
                            hide-details
                            class="pa-0"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-timer</v-icon>
                    <v-overflow-btn
                            v-on:click="calculateTime"
                            :items="time"
                            v-model="selectedTime"
                            label="제한 시간 선택"
                            hide-details
                            class="pa-0"/>
                </v-row>

            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer/>
            <v-btn color="teal" v-on:click="closeEvent">닫기</v-btn>
            <v-btn color="teal" v-on:click="saveArticleEvent">등록하기</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import axios from 'axios';

    export default {
        props: {
            source: String,
        },
        data: () => ({
            deliveryTip: '-',
            minimumOrderPrice: '-',
            title: '',
            shopName: '',
            menu: [
                {text: 'BBQ'},
                {text: '교촌치킨'},
                {text: 'BHC'},
                {text: '네네치'},
            ],
            selectedMenu: '',
            time: [],
            selectedTime: '',
        }),
        methods: {
            closeEvent: function () {
                this.$emit('closeWriteModal');
            },
            registerShop: function () {
                // 해당 shop 메뉴(가격), 배달팁, 최소 금액 가져오기
                // data: minimumOrderPrice, deliveryTip, List<menu(메뉴 이름, 가격)>
                console.log(this.shopName);
                const articleFormVue = this;
                axios.get('/api/shops/' + this.shopName)
                    .then(function(response) {
                        articleFormVue.deliveryTip = response.data.deliveryTip;
                        articleFormVue.minimumOrderPrice = response.data.minimumOrderPrice;
                        articleFormVue.menu = response.data.menu;
                    })
                    .catch(function(error){
                        console.log(error);
                    });
            },
            calculateTime: function() {
                this.time = [];
                let currentTime = this.$moment(new Date()).add(10, 'minutes');
                const startMinutes = Math.floor((currentTime.minutes()) / 10) * 10;
                currentTime.minutes(startMinutes);
                const limitTime = this.$moment(currentTime).add(2, 'hours');
                while (!limitTime.isSame(currentTime, 'hour')
                    || !limitTime.isSame(currentTime, 'minute')) {
                    this.time.push(currentTime.format('HH:mm'));
                    // console.log(currentTime.format('YYYYMMDDTHHmm'));
                    currentTime = this.$moment(currentTime).add(10, 'minutes');
                }
            },
            saveArticleEvent: function() {
                // const serverTimeFormat = this.$moment(this.selectedTime)
                const saveArticle = {
                    title: this.title,
                    deadLine: this.selectedTime,
                    shopName: this.shopName,
                    place: {latitude: 1, longitude: 1},
                    menu: [
                        {name: this.selectedMenu, count: 1}
                    ]
                };
                this.$emit('passSaveArticle', saveArticle);
            }
        },
    };
</script>