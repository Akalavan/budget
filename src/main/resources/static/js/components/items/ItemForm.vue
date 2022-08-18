<template>
    <div>
        <input type="text" placeholder="Write name" v-model="name"/>
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    import axios from 'axios'

    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return id;
            }
        }

        return -1;
    }

    export default {
        props: ['items', 'itemAttr'],

        data() {
            return {
                name: '',
                id: ''
            }
        },

        watch: {
            itemAttr(newVal, oldVal) {
                this.name = newVal.name
                this.id = newVal.id
            }
        },

        methods: {
            save() {
                const item = {
                    name: this.name,
                    id: this.id
                };

                if (this.id) {
                    axios.put('/item/', item).then(result => {
                        const data = result.data;
                        const index = getIndex(this.items, data.id)
                        this.items.splice(index, 1) // here
                        console.log(data)
                        console.log(index)
                        console.log(this.items)
                        this.name = ''
                        this.id = ''
                    })

                } else {
                    axios.post('/item/' + item.id, item).then(result => {
                        this.items.push(result.data)
                        this.name = ''
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>