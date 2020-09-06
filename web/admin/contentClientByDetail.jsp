<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="detail" tagdir="/WEB-INF/tags" %>
<detail:layoutclientdetail title="Detail Page">   
    <jsp:attribute name="content">
        <div id="sidebar" class="span3">
                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                <c:forEach items="${listC}" var="listC">
                    <li>
                        <a href="productClientByCategory.htm?categoryID=${listC.categoryID}">${listC.name}</a>
                    </li>
                </c:forEach>
            </ul>
            <br/>
            <jsp:include page="topviewIndex.jsp"></jsp:include>
                
            </div>
            <div class="span9">
                <ul class="breadcrumb">
                    <li><a href="indexClient.htm">Home</a> <span class="divider">/</span></li>
                    <li>Tin tức<span class="divider">/</span></li>
                    <li class="active">${listIndexContentDetail.title}</li>
            </ul>
            <h4> ${listIndexContentDetail.description}</h4>
            <p>
                Bởi <span style="color: red;">${listIndexContentDetail.contenSource}</span>
                <span>
                    <a style="cursor: pointer;">
                        <i class="icon-star"></i>
                        <i class="icon-star"></i>
                        <i class="icon-star"></i>
                        <i class="icon-star"></i>
                        <i class="icon-star"></i>
                    </a>
                </span>
                <span> - </span>
                <span> Ngày  <fmt:formatDate pattern="dd/MM/yyyy" value="${listIndexContentDetail.createDate}"/></span>
            </p>
            <hr class="soft">
            <p>
                ${listIndexContentDetail.detail}
            </p>
        </div>
    </jsp:attribute>
</detail:layoutclientdetail>
