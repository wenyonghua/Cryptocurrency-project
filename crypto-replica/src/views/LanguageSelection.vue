<script setup lang="ts">
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { ref } from 'vue';

const { locale } = useI18n();
const router = useRouter();

const currentLang = ref(locale.value);

const languages = [
    { code: 'en', name: 'English', flag: '🇺🇸' },
    { code: 'jp', name: '日本語', flag: '🇯🇵' },
    { code: 'kr', name: '한국인', flag: '🇰🇷' },
    { code: 'zh', name: '中文（简体）', flag: '🇨🇳' },
    { code: 'vn', name: 'Tiếng Việt', flag: '🇻🇳' },
    { code: 'th', name: 'ไทย', flag: '🇹🇭' },
    { code: 'es', name: 'español', flag: '🇪🇸' },
    { code: 'fr', name: 'Français', flag: '🇫🇷' },
    { code: 'tw', name: '中文（繁體）', flag: '🇹🇼' },
];

const selectLanguage = (code: string) => {
    locale.value = code;
    currentLang.value = code;
    localStorage.setItem('language', code);
    router.back();
}
</script>

<template>
  <div class="language-selection bg-[#F7F8FA] min-h-screen">
      <!-- Header -->
      <van-nav-bar fixed placeholder z-index="50" :border="false" class="bg-white">
          <template #left>
               <van-icon name="arrow-left" size="24" color="#333" @click="$router.back()" />
               <span class="ml-2 text-lg text-[#333]">选择语言</span>
          </template>
          <template #right>
               <van-icon name="service-o" size="24" color="#4BBD83" class="bg-[#E8F8F0] p-1 rounded-full text-[#4BBD83]" />
          </template>
      </van-nav-bar>
      
      <div class="p-4">
          <div class="text-sm text-gray-500 mb-4 px-2">选择语言</div>
          
          <div class="bg-white rounded-lg overflow-hidden shadow-sm">
             <div 
                 v-for="lang in languages" 
                 :key="lang.code" 
                 class="flex items-center justify-between p-4 border-b border-gray-50 last:border-0 hover:bg-gray-50 active:bg-gray-100 cursor-pointer"
                 @click="selectLanguage(lang.code)"
             >
                 <div class="flex items-center gap-4">
                     <span class="text-2xl">{{ lang.flag }}</span>
                     <span class="text-base text-[#333]">{{ lang.name }}</span>
                 </div>
                 <div v-if="currentLang === lang.code">
                     <van-icon name="success" color="#4BBD83" size="20" />
                 </div>
             </div>
          </div>
      </div>
  </div>
</template>

<style scoped>
:deep(.van-nav-bar__content) {
    height: 50px;
}
</style>
