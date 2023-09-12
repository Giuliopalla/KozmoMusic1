<table class="table product-table">
    <tbody>
    <c:forEach items="${prodotti}" var="prodotto">

        <tr>
            <td><img src="/KozmoMusic_war_exploded${prodotto.foto}" width="100" height="100"></td>
            <td><a href="/KozmoMusic_war_exploded/products/show?id=${prodotto.idprodotto}" style="text-decoration: none">${prodotto.nome}</a></td>
            <td>${prodotto.descrizione}</td>
            <td>${prodotto.prezzo}$</td>
            <c:choose>
            <c:when test="${prodotto.quantita>0}">
            <form method="post" action="/KozmoMusic_war_exploded/cart/add" >
                <td class="grid-x">
                    <input type="number" min="1" max="${prodotto.quantita}" name="productquantity"
                                          placeholder="MAX ${prodotto.quantita}" >
                </td>

                <td>
                    <input type="hidden" value="${prodotto.idprodotto}" name="idprodotto"/>
                    <button class="btn" type="submit" style="margin-right: 12px">Aggiungi al carrello</button>
                </td>
            </form>
            </c:when>
            <c:otherwise>
                <td>PRODOTTO NON DISPONIBILE</td>
            </c:otherwise>
            </c:choose>
        </tr>

    </c:forEach>
    </tbody>
</table>