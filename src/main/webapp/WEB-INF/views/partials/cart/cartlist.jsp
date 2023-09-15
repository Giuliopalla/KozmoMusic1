<table class="table product-table">
    <c:choose>
        <c:when test="${carrello.quantita()==0 or  empty carrello}">
            <caption>Il carrello e' vuoto</caption>
        </c:when>
        <c:otherwise>
            <caption>Prodotti Carrello</caption>
            <thead>
            <tr>
                <th>Foto</th>
                <th>Prodotto</th>
                <th>Prezzo Totale:${carrello.totale()}$</th>
                <th>Quantita':${carrello.quantita()}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${carrello.items}" var="cartItems">
                <tr>
                    <td><img src="/KozmoMusic_war_exploded${cartItems.prodotto.foto}" width="80" height="80"></td>
                    <td data-head="Nome">${cartItems.prodotto.nome}</td>
                    <td data-head="Prezzo">${cartItems.prodotto.prezzo}$</td>
                    <td data-head="Quantita">${cartItems.quantita}</td>
                    <form method="post" action="/KozmoMusic_war_exploded/cart/remove">
                        <input type="hidden" value="${cartItems.prodotto.idprodotto}" name="idprodotto">
                        <td>
                            <button class="btn" type="submit">Elimina</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
            <form method="post" action="/KozmoMusic_war_exploded/orders/make">
                <button class="btn" type="submit">Effettua Ordine</button>
            </form>
        </c:otherwise>
    </c:choose>
</table>
