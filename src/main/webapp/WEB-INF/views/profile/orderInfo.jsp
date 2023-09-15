<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Profilo Personale"/>
    <jsp:param name="style" value="admin,site"/>
    <jsp:param name="script" value="site"/>
  </jsp:include>
</head>
<body>
<main class="app grid-y">
  <%@include file="../partials/userarea/profilesidebar.jsp" %>
  <section class="content grid-x justify-center">
    <%@include file="../partials/userarea/profileheader.jsp" %>
    <div class="body grid-y justify-center">
      <div class="body tabella">
        <%@include file="../partials/orders/orderinfo.jsp" %>
      </div>
    </div>
    <%@include file="../partials/site/sitefooter.jsp" %>
  </section>
</main>
</body>
</html>
