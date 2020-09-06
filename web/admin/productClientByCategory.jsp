<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="categoies" tagdir="/WEB-INF/tags" %>
<categoies:layoutclientcategory title="Producy By Category Page">   
    <jsp:attribute name="content">
        <!--Pagination edited by Hoang-->
        <style type="text/css">
            .pagination {
                display: inline-block;
                padding-left: 0;
                margin: 20px 0;
                border-radius: 4px;
            }
            .pagination > li {

                display: inline;
            }
            .pagination > li > a,
            .pagination > li > span {
                position: relative;
                float: left;
                padding: 6px 12px;
                margin-left: -1px;
                line-height: 1.428571429;
                text-decoration: none;
                background-color: #ffffff;
                border: 1px solid #dddddd;
            }
            .pagination > li:first-child > a,
            .pagination > li:first-child > span {
                margin-left: 0;
                border-bottom-left-radius: 4px;
                border-top-left-radius: 4px;
            }
            .pagination > li:last-child > a,

            .pagination > li:last-child > span {
                border-top-right-radius: 4px;
                border-bottom-right-radius: 4px;
            }
            .pagination > li > a:hover,
            .pagination > li > span:hover,
            .pagination > li > a:focus,
            .pagination > li > span:focus {
                background-color: #eeeeee;
            }
            .pagination > .active > a,
            .pagination > .active > span,
            .pagination > .active > a:hover,
            .pagination > .active > span:hover,
            .pagination > .active > a:focus,
            .pagination > .active > span:focus {
                z-index: 2;
                color: #ffffff;
                cursor: default;
                background-color: #428bca;
                border-color: #428bca;
            }
            .pagination > .disabled > span,
            .pagination > .disabled > span:hover,
            .pagination > .disabled > span:focus,
            .pagination > .disabled > a,
            .pagination > .disabled > a:hover,
            .pagination > .disabled > a:focus {
                color: #999999;
                cursor: not-allowed;
                background-color: #ffffff;
                border-color: #dddddd;
            }
            .pagination-lg > li > a,
            .pagination-lg > li > span {
                padding: 10px 16px;
                font-size: 18px;
            }
            .pagination-lg > li:first-child > a,
            .pagination-lg > li:first-child > span {
                border-bottom-left-radius: 6px;
                border-top-left-radius: 6px;
            }
            .pagination-lg > li:last-child > a,
            .pagination-lg > li:last-child > span {
                border-top-right-radius: 6px;
                border-bottom-right-radius: 6px;
            }
            .pagination-sm > li > a,
            .pagination-sm > li > span {
                padding: 5px 10px;
                font-size: 12px;
            }
            .pagination-sm > li:first-child > a,
            .pagination-sm > li:first-child > span {
                border-bottom-left-radius: 3px;
                border-top-left-radius: 3px;
            }
            .pagination-sm > li:last-child > a,
            .pagination-sm > li:last-child > span {
                border-top-right-radius: 3px;
                border-bottom-right-radius: 3px;
            }
        </style>
        <div id="sidebar" class="span3">
                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                <c:forEach items="${listC}" var="listC">
                    <li>
                        <a href="productClientByCategory.htm?categoryID=${listC.categoryID}&&page=1">${listC.name}</a>
                    </li>
                </c:forEach>
            </ul>
            <br/>
            <jsp:include page="topviewIndex.jsp"></jsp:include>
            </div>
            <div class="span9">
            <c:if test="${not empty listP || listP.size()!=0}">
                <ul class="breadcrumb">
                    <li><a href="indexClient.htm">Home</a> <span class="divider">/</span></li>
                    <li class="active">Danh mục</li>
                </ul>
                <h3> ${listProductByCategory.categoryName} </h3>	
            </c:if>
            <c:if test="${empty listP}">
                <div class="alert alert-block alert-error fade in">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <strong>Không tồn tại tin tức trong danh mục này</strong>
                </div>
            </c:if>
            <c:if test="${not empty listP || listP.size()!=0}">
                

                <div id="myTab" class="pull-right">
                    <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
                    <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
                </div>
                <br class="clr"/>
                <div class="tab-content">
                    <div class="tab-pane" id="listView"> 
                        <c:forEach items="${listP}" var="p">
                            <div class="row">	  
                                <div class="span2">
                                    <img src="${p.image}" alt="${p.name}" style="width: 170px; height: 120px;"/>
                                </div>
                                <div class="span4">
                                    <h3><h5>${p.name} </h5></h3>				
                                    <hr class="soft"/>
                                    
                                    <p>
                                        ${p.description}
                                    </p>
                                    
                                    <br class="clr"/>
                                </div>
                                <div class="span3 alignR">
                                    <form class="form-horizontal qtyFrm">
                               
                                        <label class="checkbox"></label><br/>
                                        <a href="productClientByDetail.htm?productID=${p.productID}&&categoryID=${p.categoryID.categoryID}" class="btn btn-large btn-primary"> Xem chi tiết </a>
                                        

                                    </form>
                                </div>
                            </div>
                            <hr class="soft"/>
                        </c:forEach>
                    </div>

                    <div class="tab-pane  active" id="blockView">
                        <ul class="thumbnails">
                            <c:forEach items="${listP}" var="p">
                                <li class="span3">
                                    <div class="thumbnail">
                                        <a href="productClientByDetail.htm?productID=${p.productID}&&categoryID=${p.categoryID.categoryID}"><img src="${p.image}" style="width: 230px; height: 160px" alt=""/></a>
                                        <div class="caption">
                                            <h5>${p.name}</h5>
                                            <h4 style="text-align:center">
                                              
                                                <a  class="btn" href="productClientByDetail.htm?productID=${p.productID}&&categoryID=${p.categoryID.categoryID}">Xem
                                                   
                                                </a> 
                                                
                                            </h4>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                        <hr class="soft"/>
                    </div>
                    <!--Pagination-->
                                <div style="float:right">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <c:if test="${currentPage != 1}">
                                                <li class="paginate_button previous">
                                                    <a href="productClientByCategory.htm?categoryID=${id}&&page=${currentPage-1}">Previous</a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="paginate_button active">
                                                            <a>${i} <span class="sr-only"></span></a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="paginate_button">
                                                            <a href="productClientByCategory.htm?categoryID=${id}&&page=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage lt noOfPages}">
                                                <li class="paginate_button next">
                                                    <a href="productClientByCategory.htm?categoryID=${id}&&page=${currentPage+1}">Next</a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                                <!--End Pagination-->
                </div>
                <div class="">

                    </ul>
                </div>
            </c:if>
        </div>
    </jsp:attribute>
</categoies:layoutclientcategory>
