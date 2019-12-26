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
                                <v-btn color="primary" dark v-on="on">Login</v-btn>
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

            <v-spacer></v-spacer>

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
                                {{ articleTitle }}
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
                                <router-link tag="div" to="/articles">
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
                                            TEXT
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
                                            TEXT
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
                                            TEXT
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
                                            {{ article.deadLine }}
                                        </v-list-item-content>
                                    </v-list-item>
                                </router-link>
                            </v-list>
                        </v-card>
                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <v-dialog v-model="joiningRoomDialog" persistent max-width="600px">
                                <template v-slot:activator="{ on }">
                                    <v-btn large width="100%" color="primary" dark v-on="on">참여하기</v-btn>
                                </template>
                                <JoiningRoomModal v-on:closeJoiningRoomModal="closeJoiningRoomModal"/>
                            </v-dialog>
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
    import JoiningRoomModal from '../components/JoiningRoom.vue';

    export default {
        components: {
            LoginModal,
            JoiningRoomModal,
        },
        props: ['articleId'],
        data: () => ({
            article: null,
            activeColor: 'black',
            fontSize: 40,
            drawer: null,
            loginDialog: false,
            joiningRoomDialog: false,
        }),
        created() {
            console.log(this.articleId);
            const showArticleVue = this;
            const getArticleUri = '/api/articles/' + this.articleId;
            axios.get(getArticleUri)
                .then(function(response) {
                    showArticleVue.aticle = response.data;
                    console.log(response.data);
                    console.log(showArticleVue.aticle);
                    console.log(showArticleVue.aticle.title);
                })
                .catch(function(error){
                    console.log(error);
                });
        },
        methods: {
            closeLoginModal: function () {
                this.loginDialog = false;
            },
            getLoginInfo: function (user) {
                // 로그인 요청 보내기
                console.log('home: ', user);
            },
            closeJoiningRoomModal: function () {
                this.joiningRoomDialog = false;
            },
            articleTitle: function() {
                return this.article.title;
            }
        }
    };
</script>