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
                            {{ minimumOrderPrice }} 원
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
                            {{ deliveryTip }} 원
                        </v-list-item-content>
                    </v-list-item>
                </v-list>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-silverware-variant</v-icon>
                    <v-overflow-btn
                            v-on:click="convertMenuInfo"
                            :items="menusInfo"
                            v-model="selectedMenu"
                            label="메뉴 선택"
                            hide-details
                            class="pa-0"/>
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-timer</v-icon>
                    <v-overflow-btn
                            v-on:click="calculateTime"
                            :items="times"
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
            shopId: null,
            shopName: '',
            menus: [],
            menusInfo: [],
            selectedMenu: '',
            times: [],
            selectedTime: '',
        }),
        methods: {
            closeEvent: function () {
                this.$emit('closeWriteModal');
            },
            registerShop: function () {
                const articleFormVue = this;
                axios.get('/api/shops/' + this.shopName + '/menus')
                    .then(function(response) {
                        console.log(response.data);
                        articleFormVue.shopId = response.data.shopId;
                        articleFormVue.deliveryTip = response.data.deliveryTip;
                        articleFormVue.minimumOrderPrice = response.data.minimumOrderPrice;
                        articleFormVue.menus = response.data.menus;
                    })
                    .catch(function(error){
                        console.log(error);
                    });
            },
            convertMenuInfo: function() {
                let index = 1;
                this.menus.forEach(menu => {
                    this.menusInfo.push(index + '. ' + menu.name + ', ' + menu.price + '원');
                    index++;
                });
            },
            calculateTime: function() {
                this.times = [];
                let currentTime = this.$moment(new Date()).add(10, 'minutes');
                const startMinutes = Math.floor((currentTime.minutes()) / 10) * 10;
                currentTime.minutes(startMinutes);
                const limitTime = this.$moment(currentTime).add(2, 'hours');
                while (!limitTime.isSame(currentTime, 'hour')
                    || !limitTime.isSame(currentTime, 'minute')) {
                    this.times.push(currentTime.format('HH:mm'));
                    currentTime = this.$moment(currentTime).add(10, 'minutes');
                }
            },
            saveArticleEvent: function() {
                const timeToken = this.selectedTime.toString().split(':');
                const serverTimeFormat = this.$moment(new Date());
                serverTimeFormat.set({'hour': timeToken[0], 'minute': timeToken[1]});
                const menuInfoToken = this.selectedMenu.toString().split('.');
                const menuIndex = menuInfoToken[0] - 1;
                const saveArticle = {
                    title: this.title,
                    deadLine: serverTimeFormat.format('YYYY-MM-DD HH:mm'),
                    shopId: this.shopId,
                    place: {latitude: 1, longitude: 1},
                    items: [
                        { menuId: this.menus[menuIndex].menuId, count: 1 }
                    ]
                };
                console.log(saveArticle);
                this.$emit('passSaveArticle', saveArticle);
            }
        },
    };
</script>