<template>
  <div class="middle">
    <Sidebar :posts="viewPosts"/>
    <main>
      <Index v-if="page === 'Index'" :posts="sortPosts"/>
      <Enter v-if="page === 'Enter'"/>
      <Register v-if="page === 'Register'"/>
      <Users v-if="page === 'Users'"/>
      <Users v-if="page === 'Users'"/>
      <WritePost v-if="page === 'WritePost'"/>
    </main>
  </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "./main/users/Users";
import WritePost from "./main/WritePost";

export default {
  name: "Middle",
  data: function() {
    return {
      page: "Index"
    }
  },
  components: {
    Register,
    Enter,
    Index,
    Sidebar,
    Users,
    WritePost
  },
  props: ["posts"],
  computed: {
    viewPosts: function() {
      return this.sortPosts.slice(0, 2);
    },
    sortPosts: function() {
      return Object.values(this.posts).sort((a, b) => b.id - a.id);
    }
  }, beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
  }
}
</script>

<style scoped>

</style>
