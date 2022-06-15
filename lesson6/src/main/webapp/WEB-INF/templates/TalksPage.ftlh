<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import  "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Send Message</div>
        <div class="body">
            <form class="send-box" method="post" action="">
                <input type="hidden" name="action" value="send"/>
                <p>
                    to:
                    <label>
                        <select class="select-box" name="targetUser" required>
                            <#list users as user>
                                <option>${user.login}</option>
                            </#list>
                        </select>
                    </label>
                </p>
                <p>
                    <label>
                        <textarea name="text" rows="10" required></textarea>
                    </label>
                </p>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable">
        <div class="caption">Talk</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as talk>
                <tr>
                    <td>${talk.id}</td>
                    <td>${c.findById(talk.sourceUserId users).login}</td>
                    <td>${c.findById(talk.targetUserId users).login}</td>
                    <td>${talk.text}</td>
                    <td>${talk.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>