
  <c:forEach items="${prodotti}" var="prodotto">
    <div class="grid-y cell w40 align-center siteproducto">

      <h4>${prodotto.nome}</h4>
      <img src="/KozmoMusic_war_exploded${prodotto.foto}" width="150" height="150">



      Prezzo:${prodotto.prezzo}$<br>
      <c:choose>
        <c:when test="${prodotto.quantita>0}">
          <form method="post" action="/KozmoMusic_war_exploded/cart/add" class="grid-y align-center" style="width: 100%;">

              <input type="number" min="1" max="${prodotto.quantita}" name="productquantity"
                     placeholder="MAX ${prodotto.quantita}" >



              <input type="hidden" value="${prodotto.idprodotto}" name="idprodotto"/>
            <button class="btn amp" type="submit"><a>Aggiungi al carrello</a></button>


          </form>
          <button class="btn amp" ><a href="/KozmoMusic_war_exploded/products/show?id=${prodotto.idprodotto}" style="text-decoration: none;">Dettagli</a>
          </button>
        </c:when>
        <c:otherwise>
          PRODOTTO NON DISPONIBILE
        </c:otherwise>
      </c:choose>

    </div>
  </c:forEach>
