<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
</head>

<body>
<main>
    <form method="get">
        <label for="attempt_field">Your attempt:</label>
        <input name="attempt" id="attempt_field">
    </form>
    <img src="data:image/png;base64, ${requestScope.captcha}" alt="captcha">
</main>
</body>
</html>
