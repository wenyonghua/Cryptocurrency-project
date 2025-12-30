<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
</script>

<template>
  <div class="promotion bg-gray-50 min-h-screen">
    <van-nav-bar :title="t('promotion.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <div class="bg-[#4BBD83] text-white p-8 text-center rounded-b-3xl shadow-lg">
        <div class="text-sm opacity-80 mb-2">{{ t('promotion.my_commission') }}</div>
        <div class="text-4xl font-bold mb-6">0.0000</div>
        
        <div class="bg-white/10 rounded-lg p-4 flex justify-between items-center backdrop-blur-sm">
            <div class="text-left">
                <div class="text-xs opacity-60">{{ t('promotion.code') }}</div>
                <div class="font-mono font-bold text-lg">8829103</div>
            </div>
            <van-button size="small" round color="white" class="!text-[#4BBD83] font-bold">{{ t('promotion.copy') }}</van-button>
        </div>
    </div>
    
    <div class="p-4 -mt-4">
        <div class="bg-white rounded-xl shadow-sm p-4">
            <div class="font-bold mb-4 border-b pb-2">{{ t('promotion.team_data') }}</div>
            <div class="grid grid-cols-3 gap-4 text-center">
                <div>
                    <div class="text-lg font-bold text-gray-800">0</div>
                    <div class="text-xs text-gray-400">{{ t('promotion.direct') }}</div>
                </div>
                 <div>
                    <div class="text-lg font-bold text-gray-800">0</div>
                    <div class="text-xs text-gray-400">{{ t('promotion.second') }}</div>
                </div>
                 <div>
                    <div class="text-lg font-bold text-gray-800">0</div>
                    <div class="text-xs text-gray-400">{{ t('promotion.third') }}</div>
                </div>
            </div>
        </div>
        
         <div class="bg-white rounded-xl shadow-sm p-4 mt-4 min-h-[200px] flex flex-col items-center justify-center text-gray-400">
             <van-icon name="friends-o" size="40" class="mb-2 opacity-30" />
             <div class="text-sm">{{ t('promotion.no_members') }}</div>
         </div>
    </div>
  </div>
</template>
