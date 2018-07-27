<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<div>
    <c:choose>
        <c:when test="${book!=null}">
            <div id="cardAlert"></div>
            <div class="card" id="bookCard">
                <h5 class="card-header">${book.name}</h5>
                <div class="card-body">
                    <h5 class="card-title">${book.autor}</h5>
                    <p class="card-text">${book.createDate}</p>
                    <a href="/book/edit/${book.id}" class="btn btn-primary">Редактировать</a>
                    <a href="#" class="btn btn-danger" id="deleteBook">Удалить</a>
                </div>
            </div>
            <script type="text/javascript">
                bookCard = function() {
                    $('#deleteBook').click(function (e) {
                        if(confirm("Удалить книгу?")){
                            var jsonString = JSON.stringify({id: ${book.id}});
                            $.post({
                                url: '/ajax/deletebook',
                                contentType: "application/json; charset=utf-8",
                                type: "POST",
                                data: jsonString,
                                dataType: 'json',
                                success: function (response) {
                                    alert(response.message);
                                    if(response.success) {
                                        $('#bookCard').remove();
                                    }
                                }
                            })
                        };
                    });
                };
                $(document).ready(function(){
                    bookCard();
                });
            </script>
        </c:when>
        <c:when test="${book==null}">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div>
                        <a class="btn btn-primary" href="/book/create/${parentCatalog.id}" role="button">Добавить книгу</a>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="list-group">
                        <c:forEach items="${parentCatalog.contentMap}" var="catalogList">
                            <c:if test = "${catalogList.value.contentType == 'BOOK'}" >
                                <a href="/book/${catalogList.value.id}" class="list-group-item list-group-item-action flex-column align-items-start">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">${catalogList.value.name}</h5>
                                        <small>${catalogList.value.autor}</small>
                                    </div>
                                    <small>${catalogList.value.createDate}</small>
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:when>
    </c:choose>
</div>