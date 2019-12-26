<template>
    <v-app id="inspire">
        <v-navigation-drawer v-model="drawer" app>
            <v-list dense>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <router-link to="/">
                            <v-btn min-width="167px" color="primary" dark>Home</v-btn>
                        </router-link>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-account</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-dialog v-model="loginDialog" persistent max-width="600px">
                            <template v-slot:activator="{ on }">
                                <v-btn v-if="$store.state.loginUser === null" color="primary" dark v-on="on">Login</v-btn>
                                <v-btn v-else color="teal" dark v-click="requestLogout">Logout</v-btn>
                            </template>
                            <LoginModal
                                    v-on:closeLoginModal="closeLoginModal"
                                    v-on:passLoginInfo="getLoginInfo"
                            />
                        </v-dialog>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <v-card-text v-if="$store.state.loginUser !== null" class=".title .font-weight-bold">
                환영합니다. {{ $store.state.loginUser }} 님
            </v-card-text>
            <!--            <v-img-->
            <!--                    :src="require('../assets/logo.svg')"-->
            <!--                    class="my-lg-5"-->
            <!--                    contain-->
            <!--                    height="200"-->
            <!--            />-->
        </v-navigation-drawer>

        <v-app-bar app color="#85BECA" dark>
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
                            <div v-if="article" class="text-center"
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
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-icon>mdi-home-map-marker</v-icon>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>가게</v-list-item-title>
                                    </v-list-item-content>
                                    <v-list-item-content v-if="shopName">
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
                                    <v-list-item-content v-if="orders">
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
                                    <v-list-item-content v-if="orders">
                                        {{ orders[0].deliveryTip }} 원
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-icon>mdi-currency-krw</v-icon>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>현재 주문 금액</v-list-item-title>
                                    </v-list-item-content>
                                    <v-list-item-content>
                                        {{ calculateTotalOrderPrice }} 원
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

<!--                <v-list dense>-->
<!--                    <v-list-item>-->
<!--                        <v-list-item-action>-->
<!--                            <v-icon>mdi-food-fork-drink</v-icon>-->
<!--                        </v-list-item-action>-->
<!--                        <v-list-item-content>-->
<!--                            <v-list-item-title>-->
<!--                                {{ article.title }}-->
<!--                            </v-list-item-title>-->
<!--                            <v-list-item-content>-->
<!--                                마감 기한: {{ convertTimeForm(article.deadLine) }} 까지!-->
<!--                            </v-list-item-content>-->
<!--                        </v-list-item-content>-->
<!--                    </v-list-item>-->
<!--                </v-list>-->

            </v-container>

        </v-content>
        <v-footer color="#85BECA" app>
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
            loading: false,
            activeColor: 'black',
            fontSize: 40,
            drawer: null,
            loginDialog: false,
            article: null,
            orders: [],
            shopName: '',
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
                this.article = this.shopName = null;
                this.orders = [];
                const waitingRoomVue = this;
                const articleId = this.$route.params.articleId;
                axios.get('/api/articles/' + articleId)
                    .then(function (response) {
                        waitingRoomVue.article = response.data;
                        axios.get('/api/shops/' + waitingRoomVue.article.shopId)
                            .then(function(response) {
                                waitingRoomVue.shopName = response.data.shopName;
                            })
                            .catch(function(error) {
                                console.log(error);
                            });
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

                axios.get('/api/articles/' + articleId + '/orders')
                    .then(function (response) {
                        waitingRoomVue.orders = response.data;
                        console.log('order ', waitingRoomVue.orders);
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
                const homeVue = this;
                this.$store.state.loginUser = user.userId;
                if (this.$store.state.loginUser !== null) {
                    // cookie.set("remember_me", this.loginUser.token, "expiring time");
                    this.loginDialog = false;
                    axios.get("/api/articles")
                        .then(function (response) {
                            homeVue.articles = response.data.articleInfosDtoList;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
            },
            requestLogout: function () {
                axios.get('/logout')
                    .then(function (response) {
                        this.$store.state.loginUser = null;
                        console.log('logout: ', response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },
        computed: {
            calculateTotalOrderPrice: function() {
                let totalOrderPrice = 0;
                this.orders.forEach(order => totalOrderPrice += order.totalPrice);
                return totalOrderPrice;
            },
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