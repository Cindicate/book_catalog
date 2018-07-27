<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='alert' uri='http://java.sun.com/jsp/jstl/core' %>

<div class="alert ${alert.type}" role="alert">
    <alert:if test = "${alert.showCloseButton}">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
    </alert:if>
    ${alert.text}
</div>