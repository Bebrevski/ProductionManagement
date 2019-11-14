var manufacturyComponent = Vue.component('manufactury', {
   data () {
       return {
           manufacturyDataIsLoaded: false,
       }
   },
    created: function () {
       let vue = this;
       this.message();
    },
    methods: {
        message() {
            console.log('Manufactury working');
        }
    }
});