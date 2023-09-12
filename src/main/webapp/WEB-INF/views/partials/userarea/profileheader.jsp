<header class="topbar grid-x align-center">
    <%@include file="../../../../icons/list.svg" %>
    <div class="grid-inline align-center icons">
        <div class="dropdown grid-inline align-center">
            <%@include file="../../../../icons/user.svg" %>
            <div class="dropdown-content">
                <%if(session.getAttribute("utenteSession")==null){%>
                <a href="/KozmoMusic_war_exploded/accounts/signin">Login</a>
                <a href="/KozmoMusic_war_exploded/accounts/register">Register</a>
                <%}else{%>
                <a href="/KozmoMusic_war_exploded/accounts/profile">Area Utente</a>
                <a href="/KozmoMusic_war_exploded/accounts/logout">Logout</a>
                <%}%>
                </span>
            </div>
            <span class="account">
               Area Utente ${utenteSession.nome}
            </span>
        </div>
    </div>
</header>