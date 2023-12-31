<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Benvenuti su KozmoMusic"/>
    <jsp:param name="style" value="site,table"/>
    <jsp:param name="script" value="site"/>
  </jsp:include>

</head>
<body>
<main class="app alt">
  <section class="content grid-y">
    <%@include file="../partials/site/cartheader.jsp" %>
    <div class="body grid-y justify-center ">
      <div class="tabella body">
        <%@include file="../partials/orders/newOrder.jsp" %>
      </div>
    </div>

  </section>
</main>
<%@include file="../partials/site/sitefooter.jsp"%>
</body>
</html>
