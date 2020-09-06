<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="User Group Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Phân quyền </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div id="div-5" class="accordion-body collapse in body">
                        <div class="form-group row">
                            <div class="col-lg-4"></div>
                            <div class="col-lg-5" style="margin-bottom:-8px;">
                                <div class="form-group input-group">
                                    <input type="text" class="form-control" name="searchString" value="" placeholder="" disabled="" />
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit" disabled=""><i class="icon-search"></i></button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div id="AlertBox" class="a" style="margin-top: 6px; font-size: large; color: #468847;">
                                    ${notificationsSuccessfully}
                                </div>
                                <div id="AlertBox2" class="b" style="margin-top: 6px; font-size: large; color: #b94a48;">
                                    ${notificationsError}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Màn hình hiển thị dữ liệu
                            <div style="float:right">
                                <a href="initAddUserGroup.htm" class="btn btn-default btn-sm" style="margin-top:-5px">
                                    Thêm Mới
                                </a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width: 150px;">Tên Quyền Người Dùng</th>
                                            <th style="width: 150px;">Mô tả</th>
                                            <th style="width: 150px;">Thao Tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listUserGroup}" var="u">
                                            <tr>
                                                <td>${u.name}</td>
                                                <td>${u.description}</td>
                                                <td>
                                                    <a href="initUpdateUserGroup.htm?userGroupID=${u.userGroupID}" class="btn btn-primary"><i class="icon-pencil icon-white"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
<!--                                <div style="float:right">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
                                                <a href="#">Previous</a>
                                            </li>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0">
                                                <a href="#">1</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                                <a href="#">2</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
                                                <a href="#">3</a>
                                            </li>
                                            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0"><a href="#">4</a></li>
                                            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0"><a href="#">5</a></li>
                                            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0"><a href="#">6</a></li>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="#">Next</a></li>
                                        </ul>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>
