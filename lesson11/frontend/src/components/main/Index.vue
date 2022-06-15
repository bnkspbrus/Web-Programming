<template>
  <Posts :posts="posts"/>
</template>

<script>
import Posts from "./Posts/Posts";

export default {
  name: "Index",
  components: {
    Posts
  },
  props: ["posts"]
}
</script>

<style scoped>

</style>