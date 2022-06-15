<template>
  <footer>
    <p><a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov</p>
    <p>count of posts: {{ countOfPosts }}, count of users: {{ countOfUsers }}</p>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  props: ["countOfPosts", "countOfUsers"]
}
</script>

<style scoped>

</style>