<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Submissions</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Name</th>
                <#--                <th>Text</th>-->
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.handle}</td>
                    <td><@c.userlink user false/></td>
                    <#--                    <#assign text=(250 < post.text?length)?then(post.text[0..*250]+"...", post.text)>-->
                    <#--                    <td>${text}</td>-->
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>