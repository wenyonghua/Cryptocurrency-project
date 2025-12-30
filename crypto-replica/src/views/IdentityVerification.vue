<script setup lang="ts">
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const { t } = useI18n();
const router = useRouter();

const activeTab = ref(0);

// Primary Form
const realName = ref('');
const idNumber = ref('');

// Advanced Form
const idFront = ref([]);
const idBack = ref([]);
const idHandheld = ref([]);

const onSubmitPrimary = () => {
    // Logic
};

const onSubmitAdvanced = () => {
    // Logic
};
</script>

<template>
  <div class="identity-verification bg-gray-50 min-h-screen">
      <van-nav-bar 
        :title="t('mine.identity')" 
        left-arrow 
        @click-left="router.back()" 
        fixed 
        placeholder 
    />
    
    <van-tabs v-model:active="activeTab" color="#4BBD83" block>
        <van-tab :title="t('drawer.primary_cert')">
            <div class="p-4">
                <div class="bg-white rounded p-4 mb-4">
                     <div class="text-sm text-gray-500 mb-2">{{ t('login.account') }}</div>
                     <van-field v-model="realName" :placeholder="'Real Name'" class="mb-2 border-b" />
                     <div class="text-sm text-gray-500 mb-2 mt-2">ID Number</div>
                     <van-field v-model="idNumber" :placeholder="'ID Card Number'" class="border-b" />
                </div>
                <van-button block color="#4BBD83" @click="onSubmitPrimary">{{ t('common.submit') }}</van-button>
            </div>
        </van-tab>
        
        <van-tab :title="t('drawer.advanced_cert')">
             <div class="p-4">
                 <div class="bg-white rounded p-4 mb-4 text-center">
                     <div class="text-sm text-gray-500 mb-4">Please upload your ID photos</div>
                     <div class="grid grid-cols-2 gap-4">
                         <van-uploader v-model="idFront" :max-count="1">
                             <div class="w-full h-24 bg-gray-100 flex flex-col items-center justify-center rounded border border-dashed border-gray-300">
                                 <van-icon name="photograph" size="24" class="text-gray-400" />
                                 <span class="text-xs text-gray-400 mt-1">Front</span>
                             </div>
                         </van-uploader>
                         <van-uploader v-model="idBack" :max-count="1">
                              <div class="w-full h-24 bg-gray-100 flex flex-col items-center justify-center rounded border border-dashed border-gray-300">
                                 <van-icon name="photograph" size="24" class="text-gray-400" />
                                 <span class="text-xs text-gray-400 mt-1">Back</span>
                             </div>
                         </van-uploader>
                     </div>
                 </div>
                 <van-button block color="#4BBD83" @click="onSubmitAdvanced">{{ t('common.submit') }}</van-button>
             </div>
        </van-tab>
    </van-tabs>
  </div>
</template>
