import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router'

import PrimeVue from 'primevue/config';
import Noir from '@/preset/Noir'

const app = createApp(App)


app.use(createPinia())
app.use(router)
// app.use(ElementPlus);
app.use(PrimeVue, {
    theme: {
        preset: Noir,
        options: {
            prefix: 'p',
            darkModeSelector: '.p-dark',
            cssLayer: false,
        }
    }
});
app.mount('#app')
