<template>
  <main>
    <article>
      <a href="#" @click.prevent="showPost(post)" style="text-decoration: none">
        <div class="title">{{ post.title }}</div>
      </a>
      <div class="information">By {{ post.user.login }}, 2 days ago, translation</div>
      <div class="body">{{ post.text }}</div>
      <ul class="attachment">
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
      </ul>
      <div class="footer">
        <div class="left">
          <img src="../../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
          <span class="positive-score">+173</span>
          <img src="../../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
        </div>
        <div class="right">
          <img src="../../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
          2 days ago
          <img src="../../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
          68
        </div>
      </div>
    </article>
  </main>
</template>

<script>
export default {
  name: "PostItem",
  props: ["post"],
  methods: {
    showPost: function(post) {
      this.$root.$emit("onShowPost", post);
    }
  }
}
</script>

<style scoped>

</style>