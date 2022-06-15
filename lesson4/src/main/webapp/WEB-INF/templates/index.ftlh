<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?reverse as post>
        <@c.post_page post false/>
    </#list>
</@c.page>
