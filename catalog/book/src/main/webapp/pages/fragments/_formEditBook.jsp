<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib prefix="book" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="bookEditForm">
    <div id="editFormAlert"></div>
    <form name="book">
        <input type="hidden" name="contentType" value="${book.contentType}">
        <input type="hidden" name="id" value="${book.id}">
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Название</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Название" name="name" value="${book.name}">
            </div>
        </div>
        <div class="form-group row">
            <label for="autor" class="col-sm-2 col-form-label">Автор</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="autor" placeholder="Автор" name="autor" value="${book.autor}">
            </div>
        </div>
        <div class="form-group row">
            <label for="autor" class="col-sm-2 col-form-label">Дата выхода</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" data-date="22.01.2015" data-date-format="dd.mm.yyyy" id="datepicker" name="createDate" value="${book.createDate}">
            </div>
        </div>
        <div class="form-group row">
            <label for="catalogID" class="col-sm-2 col-form-label">Каталог</label>
            <div class="col-sm-10">
                <select class="form-control" id="catalogID" name="catalogID">
                    <c:forEach items="${catalogMap}" var="catalogItem">
                        <c:if test = "${catalogItem.value.contentType == 'CATALOG'}">
                            <option  <c:if test = "${catalogItem.key == book.catalogID}">selected</c:if> value ="${catalogItem.key}">${catalogItem.value.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
                <a class="btn btn-primary" href="/book/${book.id}" role="button">Отмена</a>
                <button type="button" class="btn btn-success" id="saveBook">Сохранить</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    loginForm = function() {
        $('#saveBook').click(function(e) {

            $.post({
                url : '/ajax/savebook',
                //contentType: "application/json; charset=utf-8",
                type:     "POST",
                data : $('form[name=book]').serialize(),
                success : function(response) {
                    console.log(response);
                    $('#editFormAlert').html(response);
                }
            })
        });
    };
    $(document).ready(function(){
        console.log('${book.createDate}');
        $('#datepicker').datepicker();
        loginForm();
    });
</script>