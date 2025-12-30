<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { computed } from 'vue';

const router = useRouter();
const { t, locale } = useI18n();

const logout = () => {
    router.replace('/login');
}

// Map locale codes to display names
const currentLanguageName = computed(() => {
    const map: Record<string, string> = {
        'en': 'English',
        'zh': '中文简体',
        'jp': '日本語',
        'kr': '한국인',
        'vn': 'Tiếng Việt',
        'th': 'ไทย',
        'es': 'Español',
        'fr': 'Français',
        'tw': '中文繁體'
    };
    return map[locale.value] || 'English';
});
</script>

<template>
  <div class="mine bg-gray-50 min-h-screen pb-20">
      <!-- User Info Header -->
      <div class="bg-gradient-to-r from-[#4BBD83] to-[#2E8B57] min-h-[160px] p-6 text-white rounded-b-[24px] shadow-lg relative">
          <div class="flex items-center mt-4">
               <!-- Avatar -->
               <div class="w-16 h-16 rounded-full bg-white/20 border-2 border-white/40 flex items-center justify-center text-3xl font-bold mr-4">
                   A
               </div>
               
               <!-- Info -->
               <div class="flex-1">
                   <div class="text-xl font-bold flex items-center">
                       aa123456
                       <van-icon name="arrow" class="ml-2 text-white/70 text-sm" />
                   </div>
                   <div class="text-xs opacity-80 mt-1">UID: 8829103</div>
                   <div class="mt-2 inline-flex items-center bg-[#D4AF37]/20 border border-[#D4AF37]/50 rounded px-2 py-0.5">
                       <span class="text-[#FFD700] text-xs font-bold">VIP 1 Member</span>
                   </div>
               </div>
          </div>
      </div>
      
      <!-- List Group 1 -->
      <div class="px-4 -mt-6">
          <div class="bg-white rounded-xl shadow-sm overflow-hidden mb-4">
              <van-cell :title="t('mine.security')" is-link icon="shield-o" class="items-center" />
              <van-cell :title="t('mine.identity')" is-link icon="idcard" :value="t('mine.verified')" class="items-center" />
              <van-cell :title="t('mine.payment')" is-link icon="card" class="items-center" />
          </div>
          
           <!-- List Group 2 -->
          <div class="bg-white rounded-xl shadow-sm overflow-hidden mb-6">
              <van-cell :title="t('mine.language')" is-link icon="globe-o" :value="currentLanguageName" class="items-center" @click="$router.push('/language')" />
              <van-cell :title="t('mine.help')" is-link icon="question-o" class="items-center" />
              <van-cell :title="t('mine.about')" is-link icon="info-o" class="items-center" />
          </div>
          
          <!-- Logout Button -->
          <van-button block color="#D62548" round class="shadow-lg font-bold" @click="logout">
              {{ t('mine.logout') }}
          </van-button>
      </div>
  </div>
</template>

<style scoped>
:deep(.van-cell__left-icon) {
    margin-right: 12px;
    font-size: 18px;
    color: #333;
}
:deep(.van-cell__title) {
    font-weight: 500;
    color: #333;
}
</style>
