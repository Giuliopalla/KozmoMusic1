<table class="table product-table">
    <caption>Lista Ordini</caption>
    <thead>
    <tr>
        <th>Ordine id</th>
        <th>Destinazione</th>
        <th>Totale</th>
        <th><%@include file="../../../../icons/info.svg"%></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ordini}" var="ordine">
        <tr>
            <td data-head="Id">${ordine.idordine}</td>
            <td data-head="Destinazione">${ordine.destinazione}</td>
            <td data-head="Totale">${ordine.totale}$</td>
            <c:choose>
            <c:when test="${utenteSession.isAdmin()}">
                <form action="/KozmoMusic_war_exploded/orders/infoadmin">
                    <td data-head="Info"><button class="btn" name="ordineid" value="${ordine.idordine}"   type="submit">
                        <%@include file="../../../../icons/info.svg"%></button></td>
                </form>
                </c:when>
                <c:otherwise>
                    <form action="/KozmoMusic_war_exploded/orders/info">
                        <td data-head="Info"><button class="btn" name="ordineid" value="${ordine.idordine}"   type="submit">
                            <%@include file="../../../../icons/info.svg"%></button></td>
                    </form>
                    </c:otherwise>
                    </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>