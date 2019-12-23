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

        </v-content>
        <v-footer color="teal" app>
            <span class="white--text">&copy; 2019</span>
        </v-footer>
    </v-app>
</template>

<script>
    import LoginModal from '../components/Login.vue';

    export default {
        components: {
            LoginModal,
        },
        props: {
            source: String,
        },
        data: () => ({
            drawer: null,
            loginDialog: false,
        }),
        methods: {
            closeLoginModal: function () {
                this.loginDialog = false;
            },
            getLoginInfo: function(user) {
                // 로그인 요청 보내기
                console.log('home: ', user);
            },
        }
    };
</script>