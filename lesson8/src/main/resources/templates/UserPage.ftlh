<#import "commons.ftlh" as c>

<@c.page>
    <#if user_to_profile??>
        <div class="roundbox">
            <div class="user-info">
                <span class="user-name"><a href="/user/${user_to_profile.id}">${user_to_profile.login}</a></span>
                <ul>
                    <li>id: ${user_to_profile.id}</li>
                    <li>login: ${user_to_profile.login}</li>
                    <li>creation time: ${user_to_profile.creationTime}</li>
                </ul>
            </div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>