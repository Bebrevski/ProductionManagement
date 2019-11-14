var inventoryComponent = Vue.component('inventory', {
   data () {
       return {
           inventoryDataIsLoaded: false,
       }
   },
    created: function () {
       let vue = this;
       this.message();
    },
    methods: {
        message() {
            console.log('inventory working');
        }
    }
});