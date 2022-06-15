<template>
  <div>
    <PostItem v-for="post in posts" :post="post" :user="users[post.userId]"
              :count-of-comments="countOfComments[post.id]? countOfComments[post.id]: 0" :key="post.id"/>
  </div>
</template>

<script>
import PostItem from "./PostItem";

export default {
  name: "Posts",
  components: {
    PostItem
  },
  props: ["posts", "users", "countOfComments"]
}
</script>

<style scoped>

</style>