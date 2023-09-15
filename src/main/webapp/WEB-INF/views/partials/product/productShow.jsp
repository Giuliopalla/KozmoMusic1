
<fieldset class="grid-x product-form cell justify-center">
    <legend>Informazioni Prodotto</legend>
            <figure class="cell w25 align-center grid-y">
                <img src="/KozmoMusic_war_exploded${prodotto.foto}" class="img">
                <figcaption>
                        <c:choose>
                            <c:when test="${prodotto.quantita>0}">
                            <form method="post" action="/KozmoMusic_war_exploded/cart/add">
                                <input type="number" min="1" max="${prodotto.quantita}" name="productquantity"
                                       placeholder="MAX ${prodotto.quantita}">
                                <input type="hidden" name="info" value="1"/>
                                <input type="hidden" value="${prodotto.idprodotto}" name="idprodotto"/>
                                <button class="btn amp" type="submit"><a>Aggiungi al carrello</a></button>
                            </form>
                            </c:when>
                            <c:otherwise>
                                PRODOTTO NON DISPONIBILE
                            </c:otherwise>
                        </c:choose>
                </figcaption>
            </figure>

            <dl>
                <dd>Nome:</dd>
                <dd>${prodotto.nome}</dd>
                <dd>Descrizione:</dd>
                <dd>${prodotto.descrizione}</dd>
                <dd>Prezzo:${prodotto.prezzo}$ </dd>
                <dd> <button class="btn amp">
                    <a href="/KozmoMusic_war_exploded/accounts/home" style="text-decoration: none">Torna alla home</a></button></dd>
            </dl>



</fieldset>

