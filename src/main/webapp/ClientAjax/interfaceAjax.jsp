<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajax Client</title>
    <link rel="stylesheet" type="text/css" href="../assets/styles.css">
    <script type="text/javascript" src="/assets/js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/assets/js/mustache/mustache.js"></script>
    <script type="text/javascript" src="/assets/js/ajax.js"></script>
    <script>
        window.onload = refresh();
    </script>

</head>
<body id="ajax">

<h1>Welcome to Ajax</h1>

<div id="menuDiv" hidden>
    <h2>Menu</h2>
    <a href="#salonListDiv">Salon List</a>
    <a href="#userListDiv">User List</a>
    <a href="#chatDiv">Chat</a>
    <a href="#messageDiv">Message</a>
    <a href="#disconnectDiv">Log Out</a>
</div>

<br>

<div id="loginDiv">
    <h3>Login</h3>
    <label for="login">Enter your username : </label>
    <input type="text" id="login" name="username" required>
    <button id="loginButton">Login</button>
</div>

<br>

<div id="usernameDiv" hidden></div>
<br>

<div id="salonListDiv" hidden>
    <h3>Salons : </h3>
    <p>These are the available salons : </p>
    <div id="salonList"></div>
    <p>Wanna create a new salon? : </p>
    <label for="salon">Enter the salon's name : </label>
    <input type="text" id="salon" name="salon" required>
    <button id="salonButton">Create</button>
</div>

<br>

<div id="userListDiv" hidden>
    <h3>User List</h3>
    <div id="userList" ></div>
</div>

<br>

<div id="chatDiv" hidden>
    <h3>Salon : </h3>
    <div>This is still in development</div>
    <div id="messagesList"></div>
    <label for="message">Type your message : </label>
    <input type="text" id="message" name="message" required>
    <button id="chatButton">Send</button>
</div>

<br>

<div id="messageDiv" hidden>
    <h3>Message bla from Salon bla</h3>
    <div>This is still in development</div>
</div>

<br>

<div id="logoutDiv" hidden>
    <button id="logoutButton">Log Out</button>
</div>

<br>

<div id="disconnectDiv">
    <a href="${pageContext.request.contextPath}/Deconnection" name="Deconnection">Go back to client selection</a>
</div>

</body>
</html>
