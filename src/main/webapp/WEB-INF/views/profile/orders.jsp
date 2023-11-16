<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Profilo Personale"/>
        <jsp:param name="style" value="site,table"/>
        <jsp:param name="script" value="site"/>
    </jsp:include>
</head>
<body>
<main class="app alt">
    <%@include file="../partials/userarea/profilesidebar.jsp" %>
    <section class="content grid-x">
        <%@include file="../partials/userarea/profileheader.jsp" %>
        <div class="body grid-y justify-center">
            <div class="body tabella">
                <%@include file="../partials/orders/orderList.jsp" %>
            </div>
            <jsp:include page="../partials/paginator.jsp">
                <jsp:param name="resource" value="ordini"/>
            </jsp:include>
        </div>

    </section>
</main>
<%@include file="../partials/site/sitefooter.jsp" %>
</body>
</html>