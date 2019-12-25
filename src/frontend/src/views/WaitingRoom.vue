<template>
    <v-app id="inspire">
        <v-navigation-drawer v-model="drawer" app>
            <v-list dense>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <router-link to="/" exact>Home</router-link>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-account</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-dialog v-model="loginDialog" persistent max-width="600px">
                            <template v-slot:activator="{ on }">
                                <v-btn v-if="loginUser === null" color="primary" dark v-on="on">Login</v-btn>
                                <v-btn v-else color="teal" dark>{{ loginUser.userId }}</v-btn>
                            </template>
                            <LoginModal
                                    v-on:closeLoginModal="closeLoginModal"
                                    v-on:passLoginInfo="getLoginInfo"
                            />
                        </v-dialog>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar app color="teal" dark>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"/>
            <v-toolbar-title>마! 같이 묵자</v-toolbar-title>

            <v-spacer/>

            <v-btn icon>
                <v-icon>mdi-bell-outline</v-icon>
            </v-btn>
        </v-app-bar>

        <v-content>
            <v-container>
                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <div class="text-center"
                                 v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }">
                                {{ article.title }}
                            </div>
                        </v-card>
                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col cols="12">
                        <v-card
                                class="mx-auto mb-5"
                                max-width="90%"
                                outlined
                        >
                            <v-list dense>
<!--                                <v-list-item>-->
<!--                                    <v-list-item-action>-->
<!--                                        <v-icon>mdi-map-marker</v-icon>-->
<!--                                    </v-list-item-action>-->
<!--                                    <v-list-item-content>-->
<!--                                        <v-list-item-title>받을 위치</v-list-item-title>-->
<!--                                    </v-list-item-content>-->
<!--                                    <v-list-item-content>-->
<!--                                        TEXT-->
<!--                                    </v-list-item-content>-->
<!--                                </v-list-item>-->
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
                                        {{ orders[0].minimumOrderPrice }} 원
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
                                        {{ orders[0].deliveryTip }} 원
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
                        </v-card>
                    </v-col>
                </v-row>

            </v-container>

        </v-content>
        <v-footer color="teal" app>
            <span class="white--text">&copy; 2019</span>
        </v-footer>
    </v-app>
</template>

<script>
    import axios from 'axios';
    import LoginModal from '../components/Login.vue';

    export default {
        components: {
            LoginModal,
        },
        props: {
            source: String,
        },
        data: () => ({
            activeColor: 'black',
            fontSize: 40,
            drawer: null,
            loginDialog: false,
            article: null,
            orders: [],
        }),
        created() {
            this.fetchData();
        },
        watch: {
            // call again the method if the route changes
            '$route': 'fetchData'
        },
        methods: {
            fetchData () {
                const waitingRoomVue = this;
                const articleId = this.$route.params.articleId;
                axios.get('/api/articles/' + articleId)
                    .then(function (response) {
                        waitingRoomVue.article = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

                axios.get('/api/articles/' + articleId + '/orders')
                    .then(function (response) {
                        waitingRoomVue.orders = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            closeLoginModal: function () {
                this.loginDialog = false;
            },
            getLoginInfo: function (user) {
                // 로그인 요청 보내기
                console.log('home: ', user);
            },
        },
        computed: {
            calculateShortfall: function() {
                let totalOrderPrice = 0;
                this.orders.forEach(order => totalOrderPrice += order.totalPrice);
                let shortfall = this.orders[0].minimumOrderPrice - totalOrderPrice;
                if (shortfall < 0) {
                    shortfall = 0;
                }
                return shortfall;
            },
            convertTimeForm: function () {
                let time = this.article.deadLine.split('T');
                let timeForm = time[1].split(':');
                return timeForm[0] + '시' + timeForm[1] + '분';
            }
        }
    };
</script>