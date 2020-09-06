<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach items="${listView}" var="listView">
    <div class="thumbnail">
        <a href="productClientByDetail.htm?productID=${listView.productID}&&categoryID=${listView.categoryID.categoryID}"><img src="${listView.image}" style="width: 230px; height: 160px" alt="${listView.name}"></a>
        <div class="caption">
            <h5>${listView.name}</h5>
            <h4 style="text-align:center">
                <a class="btn" href="productClientByDetail.htm?productID=${listView.productID}&&categoryID=${listView.categoryID.categoryID}">Xem</a> 
            </h4>
        </div>
    </div><br/>
</c:forEach>
