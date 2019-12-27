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
                                <v-btn v-if="$store.state.loginUser === null" color="primary" dark v-on="on">Login
                                </v-btn>
                                <v-btn v-else color="teal" dark v-on:click="requestLogout">Logout</v-btn>
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
                            <v-dialog v-model="postCodeDialog" persistent max-width="600px">
                                <template v-slot:activator="{ on }">
                                    <v-btn v-if="address" large width="100%" v-on="on">{{ address }}</v-btn>
                                    <v-btn v-else large width="100%" color="primary" dark v-on="on">위치 검색</v-btn>
                                </template>
                                <PostCodeModal
                                        v-on:passPostCodeData="getPostCode"
                                />
                            </v-dialog>
                        </v-card>
                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <v-dialog v-model="writeDialog" persistent max-width="600px">
                                <template v-slot:activator="{ on }">
                                    <v-btn large width="100%"
                                           color="primary"
                                           dark
                                           v-on="on"
                                    >같이 묵자 등록하기
                                    </v-btn>
                                </template>
                                <WriteModal v-on:closeWriteModal="closeWriteModal"
                                            v-on:passSaveArticle="requestSaveArticle"/>
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
                            <v-list-item three-line link @click.stop="articleInfoDialog = true"
                                         v-on:click="requestArticle(article)">
                                <v-list-item-avatar
                                        size="80"
                                        class="mt-6"
                                >
                                    <v-img :src="articlesImage[article.id - 1]"/>
                                </v-list-item-avatar>
                                <v-list-item-content class="py-0">
                                    <v-divider/>
                                    <v-list-item-title>{{ article.title }}</v-list-item-title>
                                    <v-list-item-subtitle>{{ article.userName }}</v-list-item-subtitle>
                                    <v-list-item-content>마감 기한: {{ convertTimeForm(article.deadLine) }} 까지!
                                    </v-list-item-content>
                                </v-list-item-content>
                            </v-list-item>
                        </v-card>
                        <v-dialog
                                v-model="articleInfoDialog"
                                max-width="90%"
                                outlined
                        >
                            <ArticleInfo v-bind:articleInfo="article"/>
                        </v-dialog>
                    </v-col>
                </v-row>
            </v-container>

        </v-content>

        <v-bottom-navigation
                v-model="activeBtn"
                :input-value="showNav"
                color="indigo"
        >
            <v-btn v-on:click="goHome">
                <span>Home</span>
                <v-icon>mdi-home</v-icon>
            </v-btn>

            <v-dialog v-model="loginDialog">
                <template v-slot:activator="{ on }">
                    <v-btn v-if="$store.state.loginUser === null" v-on="on">
                        <span>로그인</span>
                        <v-icon>mdi-account</v-icon>
                    </v-btn>
                    <v-btn v-else v-on:click="requestLogout">
                        <span>로그아웃</span>
                        <v-icon>mdi-account</v-icon>
                    </v-btn>
                </template>
                <LoginModal
                        v-on:closeLoginModal="closeLoginModal"
                        v-on:passLoginInfo="getLoginInfo"
                />
            </v-dialog>

            <v-dialog v-model="postCodeDialog">
                <template v-slot:activator="{ on }">
                    <v-btn v-if="address" v-on="on">{{ address }}</v-btn>
                    <v-btn v-else v-on="on">
                        <span>위치 검색</span>
                        <v-icon>mdi-map-marker</v-icon>
                    </v-btn>
                </template>
                <PostCodeModal
                        v-on:passPostCodeData="getPostCode"
                />
            </v-dialog>

            <v-dialog v-model="writeDialog">
                <template v-slot:activator="{ on }">
                    <v-btn v-on="on">
                        <span>글쓰기</span>
                        <v-icon>mdi-map-marker</v-icon>
                    </v-btn>
                </template>
                <WriteModal v-on:closeWriteModal="closeWriteModal"
                            v-on:passSaveArticle="requestSaveArticle"/>
            </v-dialog>
        </v-bottom-navigation>
    </v-app>
</template>

<script>
    import axios from 'axios';
    import LoginModal from '../components/Login.vue';
    import PostCodeModal from '../components/PostCode.vue';
    import WriteModal from '../components/ArticleForm.vue';
    import ArticleInfo from '../components/ArticleInfo.vue';

    export default {
        components: {
            LoginModal,
            PostCodeModal,
            WriteModal,
            ArticleInfo,
        },
        props: [{
            source: String,
        }],
        data: () => ({
            drawer: null,
            activeBtn: 1,
            showNav: true,
            articles: [],
            articlesImage: [],
            article: null,
            articleId: null,
            loginDialog: false,
            postCodeDialog: false,
            address: '',
            writeDialog: false,
            articleInfoDialog: false,
        }),
        created() {
            const homeVue = this;
            axios.get("/api/articles")
                .then(function (response) {
                    homeVue.articles = response.data.articleInfosDtoList;
                    for (let i = 0; i < homeVue.articles.length; ++i) {
                        const idx = (i % 4) + 1;
                        homeVue.articlesImage.push(require('../assets/eat' + idx + '.png'));
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        methods: {
            goHome: function() {
              this.$router.push('/');
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
                    this.loginDialog = false;
                    axios.get("/api/articles")
                        .then(function (response) {
                            homeVue.articles = response.data.articleInfosDtoList;
                            for (let i = 0; i < homeVue.articles.length; ++i) {
                                const idx = (i % 4) + 1;
                                homeVue.articlesImage.push(require('../assets/eat' + idx + '.png'));
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
            },
            requestLogout: function () {
                const homeVue = this;
                axios.get('/logout')
                    .then(function (response) {
                        console.log('logout: ', response);
                        if (response.status === 200) {
                            homeVue.$store.state.loginUser = null;
                            axios.defaults.headers.common['Authorization'] = '';
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getPostCode: function (data) {
                this.postCodeDialog = data.modal;
                this.address = data.address;
                // 위치로 필터해서 게시글 보여주기
                console.log(data);
            },
            closeWriteModal: function () {
                this.writeDialog = false;
            },
            requestArticle: function (currentArticle) {
                this.article = currentArticle;
                if (this.$store.state.loginUser === this.article.userName) {
                    this.$router.push('/articles/' + this.article.id + '/waiting');
                }
            },
            requestSaveArticle: function (article) {
                console.log(article);
                const homeVue = this;
                axios.post('/api/articles', article)
                    .then(function (response) {
                        console.log(response);
                        if (response.status === 200) {
                            homeVue.writeDialog = false;
                            homeVue.$router.push('/articles/' + response.data.id + '/waiting');
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            convertTimeForm: function (deadLine) {
                let time = deadLine.split('T');
                let timeForm = time[1].split(':');
                return timeForm[0] + '시' + timeForm[1] + '분';
            },
        },
    };
</script>

<style lang="scss">
    #inspire {
        background-image: url('../assets/background.png');
    }
</style>