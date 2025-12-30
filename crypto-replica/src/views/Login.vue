<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const router = useRouter();
const { t } = useI18n();
const username = ref('');
const password = ref('');

const onSubmit = (values: any) => {
  console.log('submit', values);
  // Mock login - In real app, call store/api
  router.push('/');
};
</script>

<template>
  <div class="login-page min-h-screen bg-white p-6 flex flex-col justify-center max-w-[480px] mx-auto">
    <div class="logo mb-10 text-center">
      <van-icon name="gem-o" size="64" color="#4BBD83" />
      <h1 class="text-3xl font-bold text-gray-900 mt-4">CryptoEx</h1>
      <p class="text-gray-500 mt-2">{{ t('login.welcome') }}</p>
    </div>

    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field
          v-model="username"
          name="username"
          :label="t('login.account')"
          :placeholder="t('login.username')"
          :rules="[{ required: true, message: t('login.username_req') }]"
        />
        <van-field
          v-model="password"
          type="password"
          name="password"
          :label="t('login.password')"
          :placeholder="t('login.password')"
          :rules="[{ required: true, message: t('login.password_req') }]"
        />
      </van-cell-group>
      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit" color="#4BBD83">
          {{ t('login.btn') }}
        </van-button>
      </div>
    </van-form>
    
    <div class="text-center mt-4 flex justify-between px-4 text-sm">
        <span class="text-gray-500">{{ t('login.forgot') }}</span>
        <span class="text-blue-500 font-semibold">{{ t('login.register') }}</span>
    </div>
  </div>
</template>
