<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Lista Ordini"/>
    <jsp:param name="style" value="admin,products,home"/>
    <jsp:param name="script" value="admin"/>
  </jsp:include>
  </style>
</head>
<body>
<main class="app">
  <%@include file="../partials/admin/adminsidebar.jsp" %>
  <section class="content grid-x">
    <%@include file="../partials/admin/adminheader.jsp" %>
    <div class="body grid-y justify-center">
      <div class="body tabella">
        <%@include file="../partials/orders/orderList.jsp"%>
      </div>
      <jsp:include page="../partials/paginator.jsp">
        <jsp:param name="resource" value="ordini"/>
      </jsp:include>
    </div>
    <%@include file="../partials/admin/adminfooter.jsp"%>
  </section>
</main>
</body>
</html>