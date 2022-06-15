<#import "commons.ftlh" as c>

<@c.page>
    <div class="article-box">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
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
        </template>
        <div class="noData">
            <article>No data</article>
        </div>
    </div>

    <script>
        $(function() {
            const $noData = $(".article-box .noData");
            $noData.find("article").text("Loading...");
            post("", {"action": "findAll"}, function(response) {
                $noData.hide()
                if (response["articles"].length > 0) {
                    response["articles"].forEach(function(article) {
                        const $article = $($(".article-box template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".information").text("By " + article["userLogin"] + ", 2 days ago, translation")
                        $article.find(".body").text(article["text"]);
                        $(".article-box").append($article);
                    });
                } else {
                    $noData.find("article").text("No Data");
                }
            }, "json");
        })
    </script>
</@c.page>
