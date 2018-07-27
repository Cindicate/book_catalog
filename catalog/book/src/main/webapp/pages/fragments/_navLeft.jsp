<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<ul class="list-group">
    <c:forEach items="${parentCatalog.contentMap}" var="catalogList">
        <c:choose>
            <c:when test = "${catalogList.value.contentType == 'CATALOG'}">
                <a class="list-group-item d-flex justify-content-between align-items-center" href="/catalog/${catalogList.key}">
                        ${catalogList.value.name}
                    <span class="badge badge-primary badge-pill">${catalogList.value.contentMap.size()}</span>
                </a>
            </c:when>
            <c:when test = "${catalogList.value.contentType == 'BOOK'}">
                <a class="list-group-item d-flex justify-content-between align-items-center" href="/book/${catalogList.key}">
                        ${catalogList.value.name} (${catalogList.value.autor})
                </a>
            </c:when>
        </c:choose>
    </c:forEach>
</ul>