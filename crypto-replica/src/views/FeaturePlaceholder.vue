<script setup lang="ts">
import { useRoute } from 'vue-router';
const route = useRoute();
const title = route.query.title as string || 'Feature';
</script>

<template>
  <div class="placeholder-page min-h-screen bg-gray-50 flex flex-col items-center justify-center">
      <van-nav-bar :title="title" fixed placeholder left-arrow @click-left="$router.back()" />
      <van-icon name="warning-o" size="64" color="#FF976A" class="mb-4" />
      <h2 class="text-xl font-bold text-gray-700">{{ title }}</h2>
      <p class="text-gray-500 mt-2">This feature is under development.</p>
  </div>
</template>
