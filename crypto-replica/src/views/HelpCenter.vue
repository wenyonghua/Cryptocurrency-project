<script setup lang="ts">
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
</script>
<template>
  <div class="p-4">
      <h1 class="text-xl font-bold mb-4">{{ t('drawer.help') }}</h1>
      <van-empty description="Help Center Content" />
  </div>
</template>
