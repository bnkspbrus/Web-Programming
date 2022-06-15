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
import UserItem from "../UserItem";

export default {
  name: "UsersPage",
  components: {
    UserItem
  },
  props: ["users"]
}
</script>

<style scoped>

</style>