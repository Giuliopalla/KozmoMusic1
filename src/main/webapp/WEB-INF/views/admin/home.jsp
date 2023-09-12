<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Home Admin"/>
        <jsp:param name="style" value="admin,home"/>
        <jsp:param name="script" value="admin"/>
    </jsp:include>
</head>
<body>
<main class="app">
    <%@include file="../partials/admin/adminsidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/admin/adminheader.jsp" %>
        <div class="body grid-x ">
            <div class="body grid-x justify-center">
            <jsp:include page="../partials/admin/adminstatictable.jsp">
                <jsp:param name="title" value="Utenti Totali"/>
                <jsp:param name="value" value="${totaleUtenti}"/>
            </jsp:include>
            <jsp:include page="../partials/admin/adminstatictable.jsp">
                <jsp:param name="title" value="Ordini Totali"/>
                <jsp:param name="value" value="${totaleOrdini}"/>
            </jsp:include>
            <jsp:include page="../partials/admin/adminstatictable.jsp">
                <jsp:param name="title" value="Prodotti Totali"/>
                <jsp:param name="value" value="${totaleProdotti}"/>
            </jsp:include>
            <jsp:include page="../partials/admin/adminstatictable.jsp">
                <jsp:param name="title" value="Guadagno Totale"/>
                <jsp:param name="value" value="${totaleGuadagno}$"/>
            </jsp:include>
            </div>
        </div>
    </section>
</main>
</body>
</html>
