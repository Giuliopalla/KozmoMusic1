<table class="table product-table">
    <caption>Lista Utenti</caption>
<thead>
<tr>
    <th>Utente id</th>
    <th>Nome</th>
    <th>Cognome</th>
    <th>Email</th>
    <th>Admin</th>
    <th><%@include file="../../../../icons/modify.svg"%></th>
</tr>
</thead>
    <tbody>
    <c:forEach items="${utenti}" var="utente">
        <tr>
            <td data-head="Id">${utente.id}</td>
            <td data-head="Nome">${utente.nome}</td>
            <td data-head="Cognome">${utente.cognome}</td>
            <td data-head="Email">${utente.email}</td>
            <td data-head="Admin">${utente.admin}</td>
            <form action="/KozmoMusic_war_exploded/accounts/update">
            <td data-head="Modifica"><button class="btn" name="utenteid" value="${utente.id}"   type="submit">
                <%@include file="../../../../icons/modify.svg"%></button></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
