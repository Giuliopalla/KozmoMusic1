<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Benvenuti su KozmoMusic"/>
    <jsp:param name="style" value="site,admin"/>
    <jsp:param name="script" value="site"/>
  </jsp:include>

</head>
<body>
<main class="app">
  <%@include file="../partials/site/sitesidebar.jsp" %>
  <section class="content grid-y">
    <%@include file="../partials/site/siteheader.jsp" %>
    <div class="body grid-y justify-center ">
      <div class="tabella body">
        <%@include file="../partials/product/productShow.jsp"%>
      </div>
      <jsp:include page="../partials/paginatorHome.jsp">
        <jsp:param name="resource" value="prodotti"/>
      </jsp:include>
    </div>
    <%@include file="../partials/site/sitefooter.jsp"%>
  </section>
</main>
</body>
</html>