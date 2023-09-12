<table class="table product-table">
  <caption>Dettagli Ordine </caption>
  <thead>
  <tr>
    <th>Prodotto id</th>
    <th>Prodotto nome</th>
    <th>Quantita'</th>
    <th>Prezzo</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${ordine.prodottoList}" var="prodotto">
    <tr>
      <td data-head="Id">${prodotto.key.idprodotto}</td>
      <td data-head="Nome">${prodotto.key.nome}</td>
      <td data-head="Quantita'">x${prodotto.value}</td>
      <td data-head="Prezzo">${prodotto.key.prezzo}$</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<c:choose>
  <c:when test="${utenteSession.isAdmin()}">
    <form action="/KozmoMusic_war_exploded/orders/showall">
  </c:when>
  <c:otherwise>
    <form action="/KozmoMusic_war_exploded/accounts/orders">
  </c:otherwise>
</c:choose>
  <input type="hidden" value="${ordine.getIdordine()}" name="ordineid">
  <button class="btn" type="submit">Back</button>
</form>