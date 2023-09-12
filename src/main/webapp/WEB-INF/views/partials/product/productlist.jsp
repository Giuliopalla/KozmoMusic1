<form action="/KozmoMusic_war_exploded/products/add">
    <button class="btn">Add Product</button>
</form>
<table class="table product-table">
    <caption> Lista Prodotti</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>Quantita'</th>
        <th><%@include file="../../../../icons/modify.svg"%></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${prodotti}" var="prodotto">

        <tr>
            <td data-head="Id">${prodotto.idprodotto}</td>
            <td data-head="Nome">${prodotto.nome}</td>
            <td data-head="Descrizione">${prodotto.descrizione}</td>
            <td data-head="Prezzo">${prodotto.prezzo}$</td>
            <td data-head="Quantita'">${prodotto.quantita}</td>
            <form action="/KozmoMusic_war_exploded/products/update">
                <td data-head="Modifica"><button class="btn" name="prodottoid" value="${prodotto.idprodotto}"   type="submit">
                    <%@include file="../../../../icons/modify.svg"%></button></td>
            </form>
        </tr>

    </c:forEach>
    </tbody>
</table>