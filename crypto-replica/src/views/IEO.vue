<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const active = ref(0);
</script>

<template>
  <div class="ieo bg-gray-50 min-h-screen">
    <van-nav-bar :title="t('ieo.title')" left-arrow @click-left="$router.back()" fixed placeholder />
    
    <van-tabs v-model:active="active" sticky animated color="#4BBD83">
        <van-tab :title="t('ieo.preparing')">
            <van-empty :description="t('ieo.no_preparing')" />
        </van-tab>
        <van-tab :title="t('ieo.ongoing')">
             <van-empty :description="t('ieo.no_ongoing')" />
        </van-tab>
        <van-tab :title="t('ieo.ended')">
             <div class="p-4">
                 <div class="bg-white rounded-lg p-4 shadow-sm mb-4 opacity-70">
                     <div class="flex justify-between mb-2">
                         <div class="font-bold">XYZ Token</div>
                         <van-tag type="default">{{ t('ieo.ended') }}</van-tag>
                     </div>
                     <div class="text-sm text-gray-500">{{ t('ieo.raised') }}: 5,000,000 USDT</div>
                     <van-progress :percentage="100" class="mt-2" color="#999" />
                 </div>
             </div>
        </van-tab>
    </van-tabs>
  </div>
</template>
