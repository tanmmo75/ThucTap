<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="contentt" tagdir="/WEB-INF/tags" %>
<contentt:layoutclientcategory title="Content Page">   
    <jsp:attribute name="content">
        <!--Pagination edited by Tan-->
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
                    <li class="active">Tin tức</li>
                </ul>
                <h3> Tin tức </h3>	
                <hr class="soft"/>
                <div class="tab-content">
                    <div class="tab-pane  active" id="blockView">
                        <ul class="thumbnails">
                        <c:forEach items="${listContent}" var="p">
                            <li class="span3">
                                <div class="thumbnail">
                                    <a href="contentClientByDetail.htm?contentID=${p.contentID}"><img src="${p.image}" style="width: 100%; height: 160px" alt=""/></a>
                                    <div class="caption">
                                        <a href="contentClientByDetail.htm?contentID=${p.contentID}"><h5 style="text-align: -webkit-left;">${p.description}</h5></a>
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
                                    <a href="contentClient.htm?page=${currentPage-1}">Previous</a>
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
                                            <a href="contentClient.htm?page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${currentPage lt noOfPages}">
                                <li class="paginate_button next">
                                    <a href="contentClient.htm?page=${currentPage+1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!--End Pagination-->
            </div>
        </div>
    </jsp:attribute>
</contentt:layoutclientcategory>
