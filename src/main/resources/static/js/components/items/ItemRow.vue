<template>
    <div>
        <i>({{ item.id }}) </i>{{ item.name }}
        <span style="position: absolute; right: 0;">
                <input type="button" value="Edit" @click="edit">
                <input type="button" value="X" @click="del">
            </span>
    </div>
</template>

<script>
    export default {
        props: ['item', 'editItem', 'deleteItem', 'items'],

        methods: {
            edit() {
                this.editItem(this.item)
            },
            del() {
                this.deleteItem(this.item)
            }
        }
    }
</script>

<style scoped>

</style>