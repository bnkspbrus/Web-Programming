<#-- @ftlvariable name="myArticles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>
<@c.page>
    <div class="datatable">
        <div class="caption">Article</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list myArticles as article>
                <tr>
                    <td class="article-id">${article.id}</td>
                    <td class="article-title">${article.title}</td>
                    <#if article.hidden == "HIDE">
                        <#assign invert = "SHOW">
                    <#else>
                        <#assign invert = "HIDE">
                    </#if>
                    <td><input type="button" class="article-hidden" name="${article.id}" value="${invert}"></td>
                </tr>
            </#list>
            </tbody>
        </table>
        <div class="error">
            ${error!}
        </div>
    </div>

    <script>
        $(function() {
            const $datatable = $(".datatable");
            const $error = $datatable.find(".error");
            $datatable.find(".article-hidden").click(function() {
                const $article = $(this)
                post("", {
                    action: "invertStatus",
                    articleId: $article.attr("name"),
                    status: $article.val()
                }, function(response) {
                    if (response["error"]) {
                        $error.text(response["error"])
                    } else {
                        if ($article.val() === "SHOW") {
                            $article.val("HIDE")
                        } else {
                            $article.val("SHOW")
                        }
                    }
                }, "json")
            })
        })
    </script>
</@c.page>