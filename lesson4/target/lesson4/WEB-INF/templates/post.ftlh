<#-- @ftlvariable name="post_id" type="long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign post=c.findBy(posts, "id", post_id)!/>
    <#if post?has_content>
        <@c.post_page post true/>
    <#else>
        No such post
    </#if>
</@c.page>