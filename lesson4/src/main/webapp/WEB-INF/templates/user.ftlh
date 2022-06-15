<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <#assign index = c.getIndex(users, "id", user_id)!-1>
    <#else>
        <#assign index = c.getIndex(users, "handle", handle)!-1>
    </#if>
    <#if index != -1>
        <#assign u = users[index]>
    </#if>
    <div class="user_data">
        <div class="text_data">
            <#if u??>
                <@c.userlink u false/>
                <ul>
                    <li>id: ${u.id}</li>
                    <li>handle: ${u.handle}</li>
                    <li>name: ${u.name}</li>
                    <li>posts: ${countPosts()}</li>
                    <li>
                        <@c.attach "/posts?user_id" u.id "View all"/>
                    </li>
                    <#--                    <li>posts: ${countPosts()}</li>-->
                </ul>
            <#else>
                No such user
            </#if>
        </div>
        <img class="user_image" src="https://userpic.codeforces.com/no-title.jpg" alt="no-title">
    </div>
    <div class="arrows">
        <#assign rarrow="→" larrow="←" handle_uri="/user?handle">
        <#if u??>
            <#if 0 < index>
                <@c.attach handle_uri users[index - 1].handle larrow/>
            <#else>
                ${larrow}
            </#if>
            <#if index < users?size-1>
                <@c.attach handle_uri users[index + 1].handle rarrow/>
            <#else>
                ${rarrow}
            </#if>
        </#if>
    </div>
</@c.page>

<#function countPosts>
    <#assign count = 0>
    <#list posts as post>
        <#if post.user_id == u.id>
            <#assign count++>
        </#if>
    </#list>
    <#return count>
</#function>
