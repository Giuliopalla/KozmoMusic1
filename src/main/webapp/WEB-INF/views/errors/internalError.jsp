<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Internal Error"/>
        <jsp:param name="style" value="admin,site"/>
        <jsp:param name="script" value="site"/>
    </jsp:include>
</head>
<body>
<main class="app ">
    <section class="content grid-y">
        <%@include file="../partials/userarea/profileheader.jsp" %>
        <div class="body grid-y justify-center">
                <div class="body">
                    <fieldset class="grid-y product-form cell">
                        <legend>ERROR PAGE</legend>
                        <h3>ERROR 500:Internal Error</h3>
                        <h4>Error Message:<%= request.getAttribute("javax.servlet.error.message") %></h4>
                        <a href="/KozmoMusic_war_exploded/accounts/home">Torna alla home</a>
                    </fieldset>
                </div>
            </div>
            <%@include file="../partials/site/sitefooter.jsp" %>
    </section>
</main>
</body>
</html>
