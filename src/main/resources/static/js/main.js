function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return id;
        }
    }

    return -1;
}

var itemApi = Vue.resource('v1/item{/id}')

Vue.component('item-form', {
    props: ['items', 'itemAttr'],

    data: function () {
        return {
            name: '',
            id: ''
        }
    },

    watch: {
        itemAttr: function (newVal, oldVal) {
            this.name = newVal.name
            this.id = newVal.id
        }
    },

    template:
        '<div>' +
            '<input type="text" placeholder="Write name" v-model="name" />' +
            '<input type="button" value="Save"  @click="save" />' +
        '</div>',

    methods: {
        save: function () {
            var item = {
                name: this.name,
                id: this.id
            };

            if (this.id) {
                itemApi.update({}, item).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.items, data.id) - 1
                        this.items.splice(index, 1, data);
                        console.log(this.items)
                        this.name = '';
                        this.id = '';
                    })
                )
            } else {
                itemApi.save({}, item).then(result =>
                    result.json().then(data => {
                        this.items.push(data);
                        this.name = '';
                    })
                )
            }
        }
    }
});

Vue.component('item-row', {
    props: ['item', 'editMethod', 'items'],
    template:
        '<div>' +
            '<i>({{ item.id }}) </i>{{ item.name }}' +
            '<span style="position: absolute; right: 0;">' +
                '<input type="button" value="Edit" @click="edit">' +
                '<input type="button" value="X" @click="del">' +
            '</span>' +
        '</div>',

    methods: {
        edit: function () {
            this.editMethod(this.item)
        },
        del: function () {
            itemApi.remove({id: this.item.id}).then(result => {
                if (result.ok) {
                    this.items.splice(this.items.indexOf(this.item), 1)
                }
            })
        }
    }
})

Vue.component('items-list', {
    props: ['items'],

    data: function() {
        return {
            item: null
        }
    },

    template:
        '<div style="position: relative; width: 300px;">' +
        '<item-form :items="items" :itemAttr="item"/>' +
        '<item-row v-for="item in items" :key="item.id" :item="item" :editMethod="editMethod" :items="items" />' +
        '</div>',

    created: function () {
        itemApi.get().then(result =>
            result.json().then(data =>
                data.forEach(m => this.items.push(m))
            )
        )
    },

    methods: {
        editMethod: function (item) {
            this.item = item;
        }
    }
})


var app = new Vue({
    el: '#app',
    template: '<items-list :items="items"/>',
    data: {
        items: []
    }
})
