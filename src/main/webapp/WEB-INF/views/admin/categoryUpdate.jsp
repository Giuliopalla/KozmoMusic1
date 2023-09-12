<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Update Category"/>
    <jsp:param name="style" value="admin,products"/>
    <jsp:param name="script" value="admin"/>
  </jsp:include>
</head>
<body>
<main class="app">
  <%@include file="../partials/admin/adminsidebar.jsp" %>
  <section class="content grid-y">
    <%@include file="../partials/admin/adminheader.jsp" %>
    <div class="body grid-y justify-center">
      <div class="body ">
        <%@include file="../partials/categories/categoryupdate.jsp"%>
      </div>
    </div>
    <%@include file="../partials/admin/adminfooter.jsp"%>
  </section>
</main>
</body>
</html>