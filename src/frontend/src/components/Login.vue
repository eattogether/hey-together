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
            <v-btn color="teal" v-on:click="passEvent">Login</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: {
            source: String
        },
        data: () => ({
            username: '',
            password: '',
        }),
        methods: {
            passEvent: function () {
                const loginUser = {
                    username: this.username,
                    password: this.password
                };
                console.log(loginUser);
                this.$emit("passLoginInfo", loginUser);
            },
            closeEvent: function () {
                this.$emit('closeLoginModal');
            }
        }
    };
</script>