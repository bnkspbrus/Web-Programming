<template>
  <Posts :posts="posts" :users="users" :count-of-comments="countOfComments"/>
</template>

<script>
import Posts from "../Posts";

export default {
  name: "Index",
  components: {
    Posts
  },
  props: ["posts", "users", "countOfComments"]
}
</script>

<style scoped>

</style>
