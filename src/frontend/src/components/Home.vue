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
                        <v-dialog>
                            <template v-slot:activator="{ on }">
                                <v-btn color="primary" dark v-on="on">Login</v-btn>
                            </template>
                            <Modal></Modal>
                        </v-dialog>
                        <!--                            <router-link to="/login" exact>Login</router-link>-->
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar app color="teal" dark>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
            <v-toolbar-title>마! 같이 묵자</v-toolbar-title>
        </v-app-bar>


        <v-content>
            <v-container>
                <v-row align="center">
                    <v-col>
<!--                        <v-card class="mx-auto mb-5" max-width="90%" outlined>-->
                            <!--                            <v-btn large width="100%" color="primary" dark>위치 선택</v-btn>-->
<!--                        </v-card>-->
                        <VueDaumPostcode style="height: 200px; overflow: scroll;" />

                    </v-col>
                </v-row>

                <v-row align="center">
                    <v-col>
                        <v-card class="mx-auto mb-5" max-width="90%" outlined>
                            <router-link to="/write" exact>
                                <v-btn large width="100%" color="primary" dark>같이 묵자 등록하기</v-btn>
                            </router-link>
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
    // {} 하지 않으면 오류
    import {VueDaumPostcode} from 'vue-daum-postcode';

    export default {
        components: {
            VueDaumPostcode,
        },
        props: {
            source: String,
        },
        data: () => ({
            drawer: null,
            categories: ["치킨", "피자", "떡볶이", "중식"],
            articles: [{ id: 1 }, { id: 2 }, { id: 3 }],
            dialog: false,
        }),
    };
</script>