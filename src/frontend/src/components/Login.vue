<template>
    <v-card class="elevation-12">
        <v-toolbar color="teal" dark flat>
            <v-toolbar-title>Login</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
            <v-form>
                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-account</v-icon>
                    <v-text-field
                            id="username"
                            label="Login"
                            name="login"
                            type="text"
                            v-model="username"
                    />
                </v-row>

                <v-row>
                    <v-icon class="mr-2 ml-1">mdi-lock</v-icon>
                    <v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            type="password"
                            v-model="password"
                    />
                </v-row>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer/>
            <v-btn color="teal" v-on:click="closeEvent">Close</v-btn>
            <v-btn color="teal" v-on:click="loginEvent">Login</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import axios from 'axios';

    export default {
        props: {
            source: String
        },
        data: () => ({
            username: '',
            password: '',
        }),
        methods: {
            loginEvent: function () {
                const loginVue = this;
                const loginUser = {
                    userId: this.username,
                    password: this.password
                };
                console.log(loginUser);

                // let loginResults;
                axios.post("/login", loginUser)
                .then(function(response) {
                    console.log(response);
                    console.log(response.headers['Set-Cookie']);
                    // loginResults = {
                    //     jwt:
                    // }
                    loginVue.$emit("passLoginInfo", loginUser);
                }).catch(function(error) {
                    console.log(error);

                });
            },
            closeEvent: function () {
                this.$emit('closeLoginModal');
            }
        }
    };
</script>