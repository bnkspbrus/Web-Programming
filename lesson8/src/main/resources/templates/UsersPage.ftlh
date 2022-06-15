<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Disable/Enable</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as current_user>
                    <tr>
                        <td class="id">${current_user.id}</td>
                        <td class="login">${current_user.login}</td>
                        <td class="creationTime">${current_user.creationTime}</td>
                        <#if user??>
                            <td>
                                <form method="post" action="<@spring.url '/disableEnableUser'/>">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="userDisableId" value="${current_user.id}">
                                    <#if current_user.disabled>
                                        <input type="hidden" name="disabled" value="false">
                                        <#assign value="Enable">
                                    <#else>
                                        <input type="hidden" name="disabled" value="true">
                                        <#assign value="Disable">
                                    </#if>
                                    <div class="button-field">
                                        <input type="submit" value="${value}">
                                    </div>
                                </form>

                            </td>
                        </#if>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
