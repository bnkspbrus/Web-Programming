<template>
  <div class="middle">
    <Sidebar :posts="viewPosts"/>
    <main>
      <Index v-if="page === 'Index'" :posts="sortPosts" :users="users" :count-of-comments="countOfComments"/>
      <Enter v-if="page === 'Enter'"/>
      <WritePost v-if="page === 'WritePost'"/>
      <EditPost v-if="page === 'EditPost'"/>
      <Register v-if="page === 'Register'"/>
      <FullPost v-if="page === 'FullPost'" :post="post" :users="users" :comments="viewComments"/>
      <Users v-if="page === 'Users'" :users="users"/>
    </main>
  </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "./page/Register";
import Users from "./page/Users";
import FullPost from "./FullPost";

export default {
  name: "Middle",
  data: function() {
    return {
      page: "Index",
      post: 0
    }
  },
  components: {
    Register,
    WritePost,
    Enter,
    Index,
    Sidebar,
    EditPost,
    Users,
    FullPost
  },
  props: ["posts", "users", "comments"],
  computed: {
    viewComments: function() {
      return Object.values(this.comments).filter((comment) => comment.postId === this.post.id);
    },
    viewPosts: function() {
      return this.sortPosts.slice(0, 2);
    },
    sortPosts: function() {
      return Object.values(this.posts).sort((a, b) => b.id - a.id)
    },
    countOfComments: function() {
      let result = {}
      Object.values(this.comments).forEach((comment) => {
        if (result[comment.postId]) {
          result[comment.postId]++
        } else {
          result[comment.postId] = 1
        }
      })
      return result
    }
  }, beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
    this.$root.$on("onShowPost", (post) => {
      this.post = post;
      this.page = 'FullPost';
    })
  }
}
</script>

<style scoped>

</style>
