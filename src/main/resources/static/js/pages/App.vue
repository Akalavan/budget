<template>
    <items-list :items="items"/>
    <div>
        <h3>App.vue connected</h3>
    </div>
</template>

<script>

    import ItemsList from "../components/items/ItemList.vue";
    export default {
        components: {ItemsList},
        data() {
            return {
                items: frontendData
            }
        }
    }

</script>

<style scoped>

</style>