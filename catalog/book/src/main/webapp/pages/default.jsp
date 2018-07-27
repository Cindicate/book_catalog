<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 22.06.2018
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="catalogType" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="parentCatalog" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="book" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="edit" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head>
        <title>Book Catalog</title>
        <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.css'>
        <link rel='stylesheet' href='/webjars/bootstrap/datepicker/datepicker.css'>

        <script type="text/javascript" src="/webjars/bootstrap/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="/webjars/bootstrap/jquery/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/webjars/js/actions.js"></script>
        <script type="text/javascript" src="/webjars/bootstrap/datepicker/bootstrap-datepicker.js"></script>

    </head>

    <body>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Каталог книг</h2>
            </div>
            <div class="row">
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link <c:if test = "${catalogType eq 'public_catalog'}">active</c:if>" href="/">Публичный каталог</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test = "${catalogType eq 'private_catalog'}">active</c:if>" href="/private_catalog">Закрытый каталог</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">&nbsp;</div>
            </div>
            <div class = "row">
                <div class="col-md-3">
                    <jsp:include page="/pages/fragments/_navLeft.jsp"/>
                </div>
                <div class="col-md-9">
                    <jsp:include page="/pages/fragments/_breadCrumbs.jsp"/>
                    <c:choose>
                        <c:when test="${edit eq true}">
                            <jsp:include page="/pages/fragments/_formEditBook.jsp"/>
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="/pages/fragments/_wrapContent.jsp"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    </body>
    <!-- PAGE SIDEBAR -->
    <%--jsp:include page="/pages/fragments/_head.jsp"/>

    <!-- PAGE SIDEBAR -->
    <jsp:include page="/pages/fragments/_body.jsp"/--%>

</html>