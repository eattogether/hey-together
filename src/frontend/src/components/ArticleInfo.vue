<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>{{ articleInfo.title }}</v-toolbar-title>
        </v-toolbar>

        <v-card-text>
            <v-list dense>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>받을 위치</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content>
                        TEXT
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-home-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>가게</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content>
                        TEXT
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-currency-krw</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>최소 주문 금액</v-list-item-title>
                    </v-list-item-content>
                    <v-list-item-content>
                        {{ articleInfo.minimumOrderPrice }} 원
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
                        {{ articleInfo.deliveryTip }} 원
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
                        :items="menu"
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
            menu: [],
            selectedMenu: '',
        }),
        created() {
            this.fetchData();
        },
        watch: {
            // call again the method if the route changes
            '$route': 'fetchData'
        },
        computed: {
            calculateShortfall: function () {
                let shortfall = this.articleInfo.minimumOrderPrice - this.articleInfo.totalPrice;
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
        methods: {
            fetchData (){
                console.log(this.articleInfo);
                const articleInfoVue = this;
                axios.get('/api/shops/' + this.articleInfo.shopName)
                    .then(function (response) {
                        articleInfoVue.menu = response.data.menu;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            participateArticle: function () {
                this.$router.push('/articles/' + this.articleInfo.id + '/waiting');
            }
        }
    };
</script>