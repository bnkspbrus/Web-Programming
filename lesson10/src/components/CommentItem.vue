<template>
  <p>{{ users[comment.userId].login }}: {{ comment.text }}</p>
</template>

<script>
export default {
  name: "CommentItem",
  props: ["comment", "users"]
}
</script>

<style scoped>

</style>