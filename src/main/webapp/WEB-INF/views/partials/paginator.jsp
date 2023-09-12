<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="grid-inline paginator">
    <c:forEach var="page" begin="1" end="${pages}">
        <li>
            <c:choose>
                <c:when test="${utenteSession.isAdmin()}">
                    <a href="./showall?page=${page}">${page}</a>
                </c:when>
                <c:otherwise>
                    <a href="./orders?page=${page}">${page}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
