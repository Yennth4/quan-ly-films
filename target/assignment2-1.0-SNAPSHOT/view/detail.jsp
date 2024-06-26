<%@ include file="theme/navbar.jsp" %>
<div class="container mt-3">
    <div class="d-flex">
        <h3 style="flex:1">${video.title}</h3>
        <div>
            <c:if test="${not empty sessionScope.username and status eq false}">
                <a class="btn btn-sm border-primary" href="addFavorite?id=${video.id}">
                    Like
                </a>
            </c:if>

            <c:if test="${not empty sessionScope.username and status eq true}">
                <a class="btn btn-sm border-primary"  href="deleteFavorite?id=${video.id}">
                    Unlike
                </a>
            </c:if>
<%--            <a class="btn btn-sm border-primary">Unlike</a>--%>
        </div>
    </div>
    <iframe width="100%" height="600px" src="https://www.youtube.com/embed/${video.poster}" ></iframe>
</div>