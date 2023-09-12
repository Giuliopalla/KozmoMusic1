<header class="topbar grid-x align-center">
    <%@include file="../../../../icons/list.svg" %>
    <div class="grid-inline align-center icons">
        <div class="dropdown grid-inline align-center">
            <%@include file="../../../../icons/user.svg" %>
            <div class="dropdown-content">
                <%if (session.getAttribute("utenteSession") == null) {%>
                <a href="/KozmoMusic_war_exploded/accounts/signin">Login</a>
                <a href="/KozmoMusic_war_exploded/accounts/register">Register</a>
                <%} else {%>
                <a href="/KozmoMusic_war_exploded/accounts/profile">Area Utente</a>
                <a href="/KozmoMusic_war_exploded/accounts/logout">Logout</a>
                <%}%>
                </span>
            </div>
            <span class="account">
                Benvenuto ${utenteSession.nome} ${utenteSession.cognome}
            </span>
        </div>
        <a class="shopping-cart" href="/KozmoMusic_war_exploded/cart/show">
            <%@include file="../../../../icons/carrello.svg" %>
            <c:choose>
                <c:when test="${not empty carrello}">
                    <span class="badge">${carrello.quantita()}</span>
                </c:when>
                <c:otherwise>
                    <span class="badge">0</span>
                </c:otherwise>
            </c:choose>
        </a>
    </div>
</header>