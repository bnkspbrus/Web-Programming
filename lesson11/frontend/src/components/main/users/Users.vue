<template>
  <div class="users datatable">
    <div class="caption">Users</div>
    <table>
      <thead>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
      <UserItem v-for="user in users" :user="user" :key="user.id"/>
      </tbody>
    </table>
  </div>
</template>

<script>
import UserItem from "./UserItem";
import axios from "axios";

export default {
  name: "Users",
  components: {UserItem},
  data: function() {
    return {
      users: []
    };
  },
  beforeMount() {
    axios.get("/api/1/users").then(response => {
      this.users = response.data;
    });
  }
}
</script>

<style scoped>

</style>