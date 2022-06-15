<#-- @ftlvariable name="current" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/user.css">
        <link rel="icon" href="/favicon.ico">
        <link rel="stylesheet" href="/css/users.css">
        <link rel="stylesheet" href="/css/post.css">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "HOME"},
            {"href": "/misc/help", "name": "HELP"},
            {"href": "/contests", "name": "CONTESTS"},
            {"href": "/users", "name": "USERS"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <a <#if current?? && current == item.href>style="border-bottom: 3px solid #3b5998;" </#if>
                           href="${item.href}">${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <section>
                <div class="header">Post ${post.id}</div>
                <div class="body">
                    ${cut(post)}
                </div>
                <div class="footer">
                    <#--                    <a href="/post?post_id=${post.id}">View all</a>-->
                    <@attach "/post?post_id" post.id "View all"/>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user nameOnly>
    <a <#if !nameOnly> style="text-decoration: none; color: ${user.color}" </#if>
            href="/user?handle=${user.handle}">${user.handle}</a>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function getIndex items key value>
    <#list 0..items?size-1 as i>
        <#if items[i][key] == value>
            <#return i>
        </#if>
    </#list>
<#--    <#return -1>-->
</#function>

<#function cut post>
    <#return (250 < post.text?length)?then(post.text[0..*250]+"...", post.text)>
</#function>

<#macro post_page post full>
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By <@userlink findBy(users, "handle", "Vovuh") true/>, 2 days ago, translation</div>
        <div class="body">
            <#if !full>
                ${cut(post)}
            <#else>
                ${post.text}
            </#if>
        </div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro attach uri ref text>
    <a href="${uri}=${ref}">${text}</a>
</#macro>
