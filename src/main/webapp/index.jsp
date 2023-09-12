<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>KozmoMusic</title>
  <style>
    header{
      position: sticky;
      top:0;
    }
    .topnav {
      overflow: hidden;
      background-color: #2B2626;
      width: 100%;
    }

    .topnav a {
      float: right;
      display: block;
      color: #FFFDCD;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
      font-size: 17px;
    }

    .topnav a:hover {
      background-color: #7D869C;
      color: #2B2626;
    }
    .topnav .logo-container{
      float:left;
    }

    .topnav .search-container {
      float: left;
      margin-left: 40px;
      width: 50%;
    }

    .topnav input[type=text] {
      float: left;
      padding: 6px;
      margin-top: 8px;
      font-size: 17px;
      border: none;
      width: 60%;
    }

    .topnav .search-container button {
      float: left;
      padding: 6px 10px;
      margin-top: 8px;
      background: #ddd;
      font-size: 17px;
      border: none;
      cursor: pointer;
    }

    .topnav .search-container button:hover {
      background: #ccc;
    }

    @media screen and (max-width: 600px) {
      .topnav .search-container {
        float: none;
        margin-left: 0px;
      }
      .topnav .logo-container{
        float: none;
      }
      .topnav a, .topnav input[type=text], .topnav .search-container button {
        float: none;
        display: block;
        text-align: left;
        width: 100%;
        margin: 0;
        padding: 14px;
      }
      .topnav input[type=text] {
        border: 1px solid #ccc;
      }
    }

    .elenco{
      background: #468C98;
      width: 100%;
      text-align: center;
      height: 25px  ;
    }
    .elenco a{
      color: #FFFDCD;
      text-decoration: none;
      font-size: 15px;
      padding: 10px;
    }
    .elenco a:hover {
      color: #2B2626;
    }

    footer{
      width: 100%;
      background: #468C98;
      text-align: center;
      color: #FFFDCD;
    }
  </style>
</head>
<body>
aaaaa
<%response.sendRedirect("./accounts/home");%>

</body>
</html>