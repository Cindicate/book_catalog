<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<div>
    <ul class="breadcrumb">
        <c:if test = "${parentCatalog.catalog!=null}">
            <li class="breadcrumb-item"><a href="/catalog/${parentCatalog.catalog.id}">${parentCatalog.catalog.name}</a></li>
        </c:if>
        <li class="breadcrumb-item active">${parentCatalog.name}</li>
    </ul>
</div>