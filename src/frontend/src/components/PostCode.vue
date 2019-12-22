<template>
    <VueDaumPostcode @complete=handleAddress />
</template>
<script>
    // {} 하지 않으면 오류
    import {VueDaumPostcode} from 'vue-daum-postcode';

    export default {
        components: {
            VueDaumPostcode
        },
        data: () => ({
            address: null,
        }),
        methods: {
            handleAddress: function(data) {
                let fullAddress = data.address;
                let extraAddress = '';
                if (data.addressType === 'R') {
                    if (data.bname !== '') {
                        extraAddress += data.bname;
                    }
                    if (data.buildingName !== '') {
                        extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName);
                    }
                    fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '');
                }

                console.log(fullAddress);
                this.address = fullAddress;
            }
        }
    };
</script>

