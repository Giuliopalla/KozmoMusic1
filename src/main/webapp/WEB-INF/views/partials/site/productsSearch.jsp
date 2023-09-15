<fieldset class="search">
    <legend>Cerca Prodotto</legend>
<form class="w25 grid-y field" action="/KozmoMusic_war_exploded/products/home">
    <span>Nome:</span>
    <label for="searchname" class="field cell">
        <input id="searchname" name="searchname" placeholder="Nome" type="text">
    </label>
    <span>Categoria:</span>
    <label for="searchcategoria" class="field cell">
        <select name="searchcategoria" id="searchcategoria" class="select">
            <option value="0"></option>
        </select>
    </label>
    <label class="cell field">
        <input type="radio" id="CD" name="searchtype" value="1">CD
        <input type="radio" id="Vinile" name="searchtype" value="0">Vinile
    </label>
    <span>Prezzo:</span>
    <label class=" field cell">
            Min<input type="number" placeholder="Da" name="pricemin">
            Max<input type="number" placeholder="A" name="pricemax">
    </label>
    <button class="btn2" type="submit">Cerca</button>
</form>
</fieldset>