<template>
    <v-app id="inspire">
        <v-content>
            <v-container class="fill-height" fluid>
                <v-row align="center" justify="center">
                    <v-col cols="12" sm="8" md="4">
                        <v-card class="elevation-12">
                            <v-toolbar color="teal" dark flat>
                                <v-toolbar-title>Login form</v-toolbar-title>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-row>
                                        <v-icon class="mr-2 ml-1">mdi-account</v-icon>
                                        <v-text-field label="Login" name="login" type="text" />
                                    </v-row>

                                    <v-row>
                                        <v-icon class="mr-2 ml-1">mdi-lock</v-icon>
                                        <v-text-field
                                                id="password"
                                                label="Password"
                                                name="password"
                                                type="password"
                                        />
                                    </v-row>
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer />
                                <v-btn color="teal">Login</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        props: {
            source: String
        }
    };
</script>