<form method="post" action="/KozmoMusic_war_exploded/orders/add"
      name="orderaddform">
  <fieldset class="grid-y cell product-form">
    <legend>Effettua Ordine</legend>
    <c:if test="${not empty alert}">
      <%@include file="../../partials/alert.jsp" %>
    </c:if>
    <c:choose>
      <c:when test="${not empty utenteSession}">
        <input type="hidden" name="id" value="${utenteSession.id}">
        <input type="hidden" name="nome" value="${utenteSession.nome}">
        <input type="hidden" name="cognome" value="${utenteSession.cognome}">
        <input type="hidden" name="email" value="${utenteSession.email}">
        <label for="Destinazione1" class="field cell w50">
          <b>Destinazione:</b><input id="Destinazione1" name="destinazione" placeholder="Destinazione" type="text" required>
        </label>
        <small class="cell errMsg"></small>
        <button type="submit" class="btn" onclick="validateForm(orderaddform)">Effettua Ordine</button>
      </c:when>
      <c:otherwise>
        <label for="Nome" class="field cell w50">
          <b>Nome:</b><input id="Nome" name="nome" placeholder="Nome" type="text" required minlength="5" maxlength="30">
        </label>
        <small class="cell errMsg"></small>
        <label for="Cognome" class="field cell w50">
          <b>Cognome:</b><input id="Cognome" name="cognome" placeholder="Cognome" type="text" required minlength="5" maxlength="30">
        </label>
        <small class="cell errMsg"></small>
        <label for="Email" class="field cell w50">
          <b>Email:</b><input id="Email" name="email" placeholder="Email" type="text" required>
        </label>
        <small class="cell errMsg"></small>
        <label for="Destinazione" class="field cell w50">
        <b>Destinazione:</b><input id="Destinazione" name="destinazione" placeholder="Destinazione" type="text" required>
        </label>
        <small class="cell errMsg"></small>
        <button type="submit" class="btn" onclick="validateForm(orderaddform)">Effettua Ordine</button>
      </c:otherwise>
    </c:choose>
  </fieldset>
</form>