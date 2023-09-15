<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Benvenuti su KozmoMusic"/>
    <jsp:param name="style" value="admin,site"/>
    <jsp:param name="script" value="site"/>
    <jsp:param name="script" value="category"/>
  </jsp:include>
  <script src="/KozmoMusic_war_exploded/js/category.js" defer> </script>
</head>
<body>
<main class="grid-x alt" >
  <%@include file="../partials/site/sitesidebar.jsp" %>
  <section class="content grid-y">
    <%@include file="../partials/site/siteheader.jsp" %>
    <div class="body grid-y justify-center ">
<div class="body">
        <%@include file="../partials/product/productShow.jsp"%>

    </div>
    <%@include file="../partials/site/sitefooter.jsp"%>
      </div>
  </section>
</main>
</body>
</html>