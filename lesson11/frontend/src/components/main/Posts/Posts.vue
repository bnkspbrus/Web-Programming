<template>
  <div>
    <PostItem v-for="post in posts" :post="post" :key="post.id"/>
  </div>
</template>

<script>
import PostItem from "./PostItem";
export default {
  name: "Posts",
  components: {PostItem},
  props: ["posts"]
}
</script>

<style scoped>

</style>