<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="cl" tagdir="/WEB-INF/tags" %>
<cl:layoutclient title="Home Page">   
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
                <div class="well well-small">
                    <h4>TIN TỨC NỔI BẬC </h4>
                    <div class="row-fluid">
                        <div id="featured" class="carousel slide">
                            <div class="carousel-inner">
                                <div class="item active">
                                    <ul class="thumbnails">
                                    <c:forEach items="${listHot}" var="listHot">
                                        <li class="span3">
                                            <div class="thumbnail">
                                                <i class="tag"></i>
                                                <a href="productClientByDetail.htm?productID=${listHot.productID}&&categoryID=${listHot.categoryID.categoryID}"><img src="${listHot.image}" style="width: 230px; height: 130px" alt=""></a>
                                                <div class="caption">
                                                    <h5>${listHot.name}</h5>
                                                    <h4 style="text-align:center" ><a class="btn" href="productClientByDetail.htm?productID=${listHot.productID}&&categoryID=${listHot.categoryID.categoryID}">Xem</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="item">
                                <ul class="thumbnails">
                                    <c:forEach items="${listHot}" var="listHot">
                                        <li class="span3">
                                            <div class="thumbnail">
                                                <i class="tag"></i>
                                                <a href="productClientByDetail.htm?productID=${listHot.productID}&&categoryID=${listHot.categoryID.categoryID}"><img src="${listHot.image}" style="width: 230px; height: 130px" alt=""></a>
                                                <div class="caption">
                                                    <h5>${listHot.name}</h5>
                                                    <h4 style="text-align:center" ><a class="btn" href="productClientByDetail.htm?productID=${listHot.productID}&&categoryID=${listHot.categoryID.categoryID}">Xem</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h4>TIN TỨC MỚI NHẤT </h4>
            <ul class="thumbnails">
                <c:forEach items="${listP}" var="p">
                    <li class="span3">
                        <div class="thumbnail">
                            <a href="productClientByDetail.htm?productID=${p.productID}&&categoryID=${p.categoryID.categoryID}"><img src="${p.image}" style="width: 230px; height: 160px" alt=""/></a>
                            <div class="caption">
                                <h5>${p.name}</h5>
                                <h4 style="text-align:center">
                                    <a class="btn" href="productClientByDetail.htm?productID=${p.productID}&&categoryID=${p.categoryID.categoryID}">Xem</a> 
                                </h4>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </jsp:attribute>
</cl:layoutclient>
