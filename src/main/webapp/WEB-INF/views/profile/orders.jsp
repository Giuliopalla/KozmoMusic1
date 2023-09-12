<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Profilo Personale"/>
        <jsp:param name="style" value="home,admin,site,products"/>
        <jsp:param name="script" value="site"/>
    </jsp:include>
    <style>

        nav ul {
            height: 40vh;
            overflow: hidden;
            overflow-y: scroll;
        }

        .account {
            margin-left: .5rem;
            margin-right: 1rem;
        }

        fieldset {
            background-color: var(--scritta);
            height: fit-content;
        }
        .table{
            background-color: var(--scritta);
            border: 1px solid var(--primario);
            height: fit-content;
            width: 100%;
        }
        .tabella{
            overflow-y: scroll;
            height: 60vh;
        }

    </style>
</head>
<body>
<main class="app grid-y">
    <%@include file="../partials/userarea/profilesidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/userarea/profileheader.jsp" %>
        <div class="body grid-y justify-center">
            <div class="body tabella">
            <%@include file="../partials/orders/orderList.jsp" %>
            </div>
            <jsp:include page="../partials/paginator.jsp">
                <jsp:param name="resource" value="ordini"/>
            </jsp:include>
        </div>
        <%@include file="../partials/site/sitefooter.jsp" %>
    </section>
</main>
</body>
</html>