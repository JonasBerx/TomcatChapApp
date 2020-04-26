<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body id="body">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if> <c:choose>
    <c:when test="${user!=null}">
        <b>Welcome </b><b id="currentuser">${user.getFirstName()}</b>
        <div id="status">Online</div>
        <form method="post" action="Controller?action=LogOut">
            <p>
                <input type="submit" id="logoutbutton" value="Log Out">
            </p>
        </form>
        <br/>
        <form id="bgform">
            <input type="color" id="colorChooser" value="#456456">
            <label for="colorChooser">Color</label>
            <button type="submit">Change Background</button>
        </form>

        <div class="chat-popup" id="chatdiv" style="border: 1px black">
            <form id="chatform" method="post" class="form-container">
                <h1>Chat</h1>
                <div id="chatbox"></div>
                <div id="chatRecipient"></div>
                <label  for="msg"></label>
                <textarea placeholder="Type message.." id="msg" name="msg" required></textarea>

                <button id="chatsubmit" type="button" class="btn">Send</button>
                <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
            </form>
        </div>

        <br/>
        <form id="statusForm">
            <p><label for="statusInput">Change Status</label></p>

            <input type="text" id="statusInput">
            <button type="submit">Change Status</button>
        </form>
        <br/>
        <table id="friends">
            <tr>
                <th>Name</th>
                <th>Status</th>
                <th>Chat?</th>
            </tr>
        </table>
        <br/>
        <form id="addFriend">
            <label for="friendName"></label>
            <input type="text" id="friendName">
            <button id="friendsubmit" type="submit">Add Friend</button>
        </form>
        <br/>
        <section id="blog">
            <form id="1">
                <p>Yes yes this is a post</p>
                <div id="comments1"></div>
                <input type="text" id="username1" value="${user.getFirstName()}">
                <input type="text" id="comment1">
                <input type="number" min="0" max="10" id="rating1">
                <button type="submit">Add comment</button>
            </form>

            <form id="2">
                <p>Yes yes this is a post</p>
                <div id="comments2"></div>
                <input type="text" id="username2" value="${user.getFirstName()}">
                <input type="text" id="comment2">
                <input type="number" min="0" max="10" id="rating2">
                <button type="submit">Add comment</button>
            </form>

            <form id="3">
                <p>Yes yes this is a post</p>
                <div id="comments3"></div>
                <input type="text" id="username3" value="${user.getFirstName()}">
                <input type="text" id="comment3">
                <input type="number" min="0" max="10" id="rating3">
                <button type="submit">Add comment</button>
            </form>

            <form id="4">
                <p>Yes yes this is a post</p>
                <div id="comments4"></div>
                <input type="text" id="username4" value="${user.getFirstName()}">
                <input type="text" id="comment4">
                <input type="number" min="0" max="10" id="rating4">
                <button type="submit">Add comment</button>
            </form>

            <form id="5">
                <p>Yes yes this is a post</p>
                <div id="comments5"></div>
                <input type="text" id="username5" value="${user.getFirstName()}">
                <input type="text" id="comment5">
                <input type="number" min="0" max="10" id="rating5">
                <button type="submit">Add comment</button>
            </form>

        </section>
        <br/>
        <%--		<div>--%>
        <%--			<input type="text" id="messageinput"/>--%>
        <%--		</div>--%>
        <%--		<div>--%>
        <%--		<button type="button" onclick="openSocket();" >Open</button>--%>
        <%--		<button type="button" onclick="send();" >Send</button>--%>

        <%--	</div>--%>
        <%--		<div id="messages"></div>--%>


    </c:when>
    <c:otherwise>
        <form method="post" action="Controller?action=LogIn">
            <p>
                <label for="email">Your email </label>
                <input type="text" id="email" name="email" value="jan@ucll.be">
            </p>
            <p>
                <label for="password">Your password</label>
                <input type="password" id="password" name="password" value="t">
            </p>
            <p>
                <input type="submit" id="loginbutton" value="Log in">
            </p>
        </form>
    </c:otherwise>
</c:choose></main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" type="text/javascript">
</script>
<script src="js/main.js"></script>
</body>
</html>
