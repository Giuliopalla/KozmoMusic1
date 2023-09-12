<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="grid-inline paginator">
  <c:forEach var="page" begin="1" end="${pages}">
    <li>



                <a href="./home?page=${page}">${page}</a>



    </li>
  </c:forEach>
</ul>
