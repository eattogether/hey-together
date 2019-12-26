<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>{{ articleInfo.title }}</v-toolbar-title>
        </v-toolbar>

        <v-card-text>
            <v-list dense>
<!--                <v-list-item>-->
<!--                    <v-list-item-action>-->
<!--                        <v-icon>mdi-map-marker</v-icon>-->
<!--                    </v-list-item-action>-->
<!--                    <v-list-item-content>-->
<!--                        <v-list-item-title>받을 위치</v-list-item-title>-->
<!--                    </v-list-item-content>-->
<!--                    <v-list-item-content>-->
<!--                        TEXT-->
<!--                    </v-list-item-content>-->
<!--                </v-list-item>-->
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-home-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>가게</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content v-model="shopName">
                        {{ shopName }}
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-currency-krw</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>최소 주문 금액</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content v-model="minimumOrderPrice">
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
                    <v-list-item-content v-model="deliveryTip">
                        {{ deliveryTip }} 원
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-currency-krw</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>부족한 금액</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content>
                        {{ calculateShortfall }} 원
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-timer</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>시간 제한</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content>
                        {{ convertTimeForm }} 까지!
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
        </v-card-text>
        <v-card-actions>
            <v-spacer/>
            <v-btn color="teal" v-on:click="participateArticle">참여하기</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import axios from "axios";

    export default {
        props: ['articleInfo'],
        data: () => ({
            activeColor: 'black',
            fontSize: 40,
            shopName: '',
            deliveryTip: '',
            minimumOrderPrice: '',
            totalPrice: '',
            menus: [],
            menusInfo: [],
            selectedMenu: '',
        }),
        created() {
            this.fetchData();
        },
        watch: {
            // call again the method if the route changes
            '$route': 'fetchData'
        },
        methods: {
            fetchData (){
                console.log('ArticleInfo articleInfo: ', this.articleInfo);
                const articleInfoVue = this;
                const orderUri = '/api/articles/' + this.articleInfo.id + '/orders';
                const shopUri = '/api/shops/' + this.articleInfo.shopId;
                const requestOrder = axios.get(orderUri);
                const requestShop = axios.get(shopUri);

                axios.all([requestOrder, requestShop])
                    .then(axios.spread((...responses) => {
                        const responseOrderData = responses[0].data[0];
                        const responseShopData = responses[1].data;
                        console.log(responseOrderData);
                        console.log(responseShopData);
                        articleInfoVue.shopName = responseShopData.shopName;
                        articleInfoVue.menus = responseShopData.menus;
                        articleInfoVue.deliveryTip = responseOrderData.deliveryTip;
                        articleInfoVue.minimumOrderPrice = responseOrderData.minimumOrderPrice;
                        articleInfoVue.totalPrice = responseOrderData.totalPrice;
                    })).catch(errors => {
                    console.log(errors);
                });
            },
            convertMenuInfo: function() {
                let index = 1;
                this.menus.forEach(menu => {
                    this.menusInfo.push(index + '. ' + menu.name + ', ' + menu.price + '원');
                    index++;
                });
            },
            participateArticle: function () {
                this.$router.push('/articles/' + this.articleInfo.id + '/waiting');
            }
        },
        computed: {
            calculateShortfall: function () {
                let shortfall = this.minimumOrderPrice - this.totalPrice;
                if (shortfall < 0) {
                    shortfall = 0;
                }
                return shortfall;
            },
            convertTimeForm: function () {
                console.log(this.articleInfo.deadLine);
                let time = this.articleInfo.deadLine.split('T');
                let timeForm = time[1].split(':');
                return timeForm[0] + '시' + timeForm[1] + '분';
            }
        },
    };
</script>