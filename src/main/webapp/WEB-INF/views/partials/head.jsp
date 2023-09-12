<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Ecommerce per cd e vinili">
<link rel="icon" type="image/png" href="/images/logo.png">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-title" content="KozmoMusic">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="apple-touch-icon" href="/images/logo.png">
<link rel="apple-touch-startup-image" href="/images/logo.png">
<meta name="theme-color" content="#2B2626">
<link href="${context}/css/reset.css" rel="stylesheet">
<link href="${context}/css/library.css" rel="stylesheet">
<c:if test="${not empty param.style}">
  <c:forTokens items="${param.style}" delims="," var="style">
    <link rel="stylesheet" href="${context}/css/${style}.css">
  </c:forTokens>
</c:if>
<script src="${context}/js/library.js" defer></script>
<c:if test="${not empty param.script}">
  <c:forTokens items="${param.script}" delims="," var="script">
   <script src="${context}/js/${script}.js" defer></script>
  </c:forTokens>
</c:if>
