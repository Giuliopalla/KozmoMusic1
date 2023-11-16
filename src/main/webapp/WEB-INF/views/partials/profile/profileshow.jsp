<table class="table product-table">
    <caption>Profilo Personale</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>E-mail</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td data-head="id">${utente1.id}</td>
            <td data-head="nome">${utente1.nome}</td>
            <td data-head="cognome">${utente1.cognome}</td>
            <td data-head="e-mail">${utente1.email}</td>
    </tbody>
</table>
<form action="/KozmoMusic_war_exploded/accounts/update">
    <button class="btn" type="submit">Aggiorna Dati</button>
</form>
