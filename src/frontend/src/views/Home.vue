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
        </v-app-bar>

        <v-content>
            <v-container>
                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <v-dialog v-model="postCodeDialog" persistent max-width="600px">
                                <template v-slot:activator="{ on }">
                                    <v-btn large width="100%" color="primary" dark v-on="on">위치 검색</v-btn>
                                </template>
                                <PostCodeModal/>
                            </v-dialog>
                        </v-card>
                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <v-dialog v-model="writeDialog" persistent max-width="600px">
                                <template v-slot:activator="{ on }">
                                    <v-btn large width="100%" color="primary" dark v-on="on">같이 묵자 등록하기</v-btn>
                                </template>
                                <WriteModal v-on:closeWriteModal="closeWriteModal"/>
                            </v-dialog>
                        </v-card>
                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col cols="12">
                        <v-card
                                v-for="article in articles"
                                :key="article.id"
                                class="mx-auto mb-5"
                                max-width="90%"
                                outlined
                        >
                            <v-list dense>
                                <v-list-item link>
                                    <v-list-item-action>
                                        <v-icon>mdi-food-fork-drink</v-icon>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>같이 묵자 {{ article.id }}...</v-list-item-title>
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
    import LoginModal from '../components/Login.vue';
    import PostCodeModal from '../components/PostCode.vue';
    import WriteModal from '../components/ArticleForm';

    export default {
        components: {
            LoginModal,
            PostCodeModal,
            WriteModal,
        },
        props: {
            source: String,
        },
        data: () => ({
            drawer: null,
            categories: ["치킨", "피자", "떡볶이", "중식"],
            articles: [{id: 1}, {id: 2}, {id: 3}],
            loginDialog: false,
            postCodeDialog: false,
            writeDialog: false,
        }),
        methods: {
            closeLoginModal: function () {
                this.loginDialog = false;
            },
            getLoginInfo: function(user) {
                // 로그인 요청 보내기
                console.log('home: ', user);
            },
            closeWriteModal: function () {
                this.writeDialog = false;
            },
        }
    };
</script>