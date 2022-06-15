<#import "commons.ftlh" as c>
<#import "spring.ftl" as spring>

<@c.page>
    <@c.post post/>
    <#if user??>
        <div class="form">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="text">Add comment</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text">${comment.text!}</textarea>
                    </div>
                    <@c.error "comment.text"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </#if>
    <div class="comments">
        <#list post.comments as comment>
            <section>
                <div class="header">
                    ${comment.creationTime}
                </div>
                <div class="body">
                    ${comment.text}
                </div>
                <div class="author">
                    by ${comment.user.login}
                </div>
            </section>
        </#list>
    </div>
</@c.page>