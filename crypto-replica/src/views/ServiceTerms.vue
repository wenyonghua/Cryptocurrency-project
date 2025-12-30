<script setup lang="ts">
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
</script>
<template>
  <div class="p-4">
      <h1 class="text-xl font-bold mb-4">{{ t('drawer.terms') }}</h1>
      <p class="text-gray-500">Lorem ipsum dolor sit amet...</p>
  </div>
</template>
