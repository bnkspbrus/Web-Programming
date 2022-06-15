<template>
  <div>
    <div>
      <PostItem :post="post" :user="users[post.userId]" :count-of-comments="Object.values(comments).length"/>
    </div>
    <div>
      <CommentItem v-for="comment in comments" :comment="comment" :users="users" :key="comment.id"/>
    </div>
  </div>
</template>

<script>
import CommentItem from "./CommentItem";
import PostItem from "./PostItem";

export default {
  name: "FullPost",
  components: {
    CommentItem,
    PostItem
  },
  props: ["post", "users", "comments"]
}
</script>

<style scoped>

</style>