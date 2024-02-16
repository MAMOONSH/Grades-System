<html>
<head>
<title>Login</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/login" method="POST">
        <label>UserID     :</label><input name="userID" type="text" required="required"/><br>
        <label>Password   : </label><input name="password" type="password" required="required"/><br>
        <input type="submit" />
    </form>
</body>
</html>