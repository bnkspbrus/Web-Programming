<#import "commons.ftlh" as c>

<@c.page>
    <div class="article-box">
        <div class="header">Add article</div>
        <div class="body">
            <form method="post" action="/article">
                <input type="hidden" name="action" value="addArticle">
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input class ="input-title" id="title" name="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="text-value">
                        <textarea id="text" name="text" rows="20"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
                <div class="error"></div>
            </form>
        </div>
    </div>
    <script>
        $(function() {
            $(".article-box form").submit(function() {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const $error = $(this).find(".error");
                post("", {action: "addArticle", title, text}, function(response) {
                    if (response["error"]) {
                        $error.text(response["error"]);
                    }

                }, "json");
                return false;
            });
        })
    </script>
</@c.page>