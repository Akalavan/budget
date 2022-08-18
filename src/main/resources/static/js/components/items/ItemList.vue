<template>
    <div style="position: relative; width: 300px;">
        <item-form :items="items" :itemAttr="item"/>
        <item-row v-for="item in items"
                  :key="item.id"
                  :item="item"
                  :editItem="editItem"
                  :deleteItem="deleteItem"
                  :items="items"/>
    </div>
</template>

<script>
    import axios from 'axios'
    import ItemRow from "./ItemRow.vue";
    import ItemForm from "./ItemForm.vue";

    export default {
        props: ['items'],

        components: {
            ItemRow,
            ItemForm
        },

        data() {
            return {
                item: null
            }
        },

        methods: {
            editItem(item) {
                this.item = item;
            },
            deleteItem(item) {
                axios.delete('/item/'+item.id).then(result => {
                    console.log(result)
                    if (200 === result.status) {
                        this.items.splice(this.items.indexOf(this.item), 1)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>