<form action="/KozmoMusic_war_exploded/category/add">
    <button class="btn">Add Category</button>
</form>
<table class="table product-table">
    <caption>Lista Utenti</caption>
    <thead>
    <tr>
        <th>Id Categoria</th>
        <th>Tipologia</th>
        <th><%@include file="../../../../icons/modify.svg"%></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categorie}" var="categoria">
        <tr>
            <td data-head="Id Categoria">${categoria.idcategoria}</td>
            <td data-head="Tipologia">${categoria.tipologia}</td>
            <form action="/KozmoMusic_war_exploded/category/update">
                <td data-head="Modifica"><button class="btn" name="categoriaid" value="${categoria.idcategoria}"   type="submit">
                    <%@include file="../../../../icons/modify.svg"%></button></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>

