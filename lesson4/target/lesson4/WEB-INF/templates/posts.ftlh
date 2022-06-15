<#import "./common.ftlh" as c>

<@c.page>
    <#if c.findBy(users, "id", user_id)!?has_content>
        <#list posts?reverse as post>
            <#assign has_content=false/>
            <#if post.user_id == user_id>
                <@c.post_page post true/>
                <#assign has_content=true>
            </#if>
        </#list>
        <#if !has_content>
            Posts not found
        </#if>
        <#else>
        No such user
    </#if>
</@c.page>