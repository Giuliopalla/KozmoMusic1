<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Benvenuti su KozmoMusic"/>
        <jsp:param name="style" value="site,admin"/>
        <jsp:param name="script" value="site"/>
        <jsp:param name="script" value="category"/>
    </jsp:include>
    <script src="/KozmoMusic_war_exploded/js/category.js" defer> </script>
</head>
<body>

<main class="grid-x">

    <%@include file="../partials/site/sitesidebar.jsp" %>
    <section class="content grid-x">
        <%@include file="../partials/site/siteheader.jsp" %>
        <div class="body grid-y justify-center ">

            <%@include file="../partials/site/siteproduct.jsp"%>

            <jsp:include page="../partials/paginatorHome.jsp">
                <jsp:param name="resource" value="prodotti"/>
            </jsp:include>
        </div>
        <%@include file="../partials/site/sitefooter.jsp"%>
    </section>

</main>

</body>

</html>
