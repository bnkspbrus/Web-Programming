<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user-login"></td>
                    <#if user?? && user.admin>
                        <td class="user-admin">
                            <span class="status"></span>
                            <a href="#" class="change"></a>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function() {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            post("", {action: "findAll"}, function(response) {
                // $noData.hide();
                response["users"].forEach(function(user) {
                    $noData.hide();
                    const $tr = $($(".users template").prop("content")).clone();
                    $tr.find(".user-id").text(user["id"]);
                    $tr.find(".user-login").text(user["login"]);
                    $tr.find("tr").attr("data-userId", user["id"]);
                    $tr.find(".user-admin .status").text(user["admin"] ? "YES" : "NO");
                    $tr.find(".user-admin .change").text(user["admin"] ? "Disable" : "Enable");
                    $tr.find(".user-admin .change").attr("data-userId", user["id"]);
                    $(".users tbody").append($tr);
                });
                $(".users .user-admin .change").click(function() {
                    const $status = $(this).closest(".user-admin").find(".status");
                    const $change = $(this)
                    post("", {
                        action: "changeAdmin",
                        userId: $change.attr("data-userId"),
                        status: $change.text()
                    }, function(response) {
                        // alert($change.text() + " " + $status.text())
                        if ($change.text() === "Enable") {
                            $change.text("Disable")
                            $status.text("YES")
                        } else {
                            $change.text("Enable")
                            $status.text("NO")
                        }
                    }, "json")
                    return false;
                })
                $(".users tr").click(function() {
                    post("", {action: "findUser", userId: $(this).attr("data-userId")},
                        function(response) {
                            notify(response["user"].creationTime);
                        }, "json"
                    )
                });
            }, "json");
        })
    </script>
</@c.page>
