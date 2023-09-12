<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Profilo Personale"/>
        <jsp:param name="style" value="home,admin,site"/>
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
            width: 100%;
        }
        .product-form>*{
            margin-bottom: .5rem;
        }
    </style>
</head>
<body>
<main class="app">
    <%@include file="../partials/userarea/profilesidebar.jsp" %>
    <section class="content grid-y">
        <%@include file="../partials/userarea/profileheader.jsp" %>
        <div class="body grid-y justify-center">
            <div class="body">
                <%@include file="../partials/profile/profileupdate.jsp" %>
            </div>
        </div>
        <%@include file="../partials/userarea/profilefooter.jsp" %>
    </section>
</main>
</body>
</html>