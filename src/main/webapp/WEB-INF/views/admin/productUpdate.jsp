<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Update Product"/>
    <jsp:param name="style" value="site,table"/>
    <jsp:param name="script" value="admin"/>
  </jsp:include>
  </style>
</head>
<body>
<main class="app alt">
  <%@include file="../partials/admin/adminsidebar.jsp" %>
  <section class="content grid-y">
    <%@include file="../partials/admin/adminheader.jsp" %>
    <div class="body grid-y justify-center">
      <div class="body tabella">
        <%@include file="../partials/product/productupdate.jsp"%>
      </div>
    </div>

  </section>
</main>
<%@include file="../partials/admin/adminfooter.jsp"%>
</body>
</html>
