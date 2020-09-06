<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Review Feedback Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Phản hồi </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình xem dữ liệu</h5>
                            <div class="toolbar">
                                <ul class="nav">
                                    <li>
                                        <div class="btn-group">
                                            <a class="accordion-toggle btn btn-xs minimize-box" data-toggle="collapse" href="#collapseOne">
                                                <i class="icon-chevron-up"></i>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </header>
                        <div id="collapseOne" class="accordion-body collapse in body">
                            <spring:form action="updateFeedBack.htm" commandName="fb" class="form-horizontal" method="post">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div id="collapseOne" class="accordion-body collapse in body">
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Họ và tên :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${fb.name}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Số điện thoại :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${fb.phone}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Địa chỉ :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${fb.address}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div id="collapseOne" class="accordion-body collapse in body">
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Phản hồi số :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${fb.feedBackID}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Email :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label">${fb.email}</label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-4">Ngày tạo :</label>
                                                    <div class="col-lg-8">
                                                        <label for="text1" class="control-label"><fmt:formatDate pattern="dd/MM/yyyy" value="${fb.createDate}"/></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row"> 
                                    <div class="col-lg-12">
                                        <div class="box">
                                            <header>
                                                <div class="icons"><i class="icon-bolt"></i></div>
                                                <h5>Thông tin</h5>
                                            </header>
                                            <div id="collapseOne" class="accordion-body collapse in body">
                                                <div class="form-group">
                                                    <label for="text1" class="control-label col-lg-2">Nội dung :</label>
                                                    <div class="col-lg-10">
                                                        <label for="text1" class="control-label">${fb.content}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" hidden=""> 
                                    <spring:input path="feedBackID" type="text" id="feedBackID" name="feedBackID" placeholder="" class="form-control"/><br>
                                    <spring:input path="name" type="text" id="name" name="name" placeholder="" class="form-control"/><br>
                                    <spring:input path="phone" type="text" id="phone" name="phone" placeholder="" class="form-control"/><br>
                                    <spring:input path="email" type="text" id="email" name="email" placeholder="" class="form-control"/><br>
                                    <spring:input path="address" type="text" id="address" name="address" placeholder="" class="form-control"/><br>
                                    <spring:input path="content" type="text" id="content" name="content" placeholder="" class="form-control"/><br>
                                    <spring:radiobutton path="status" value="false" checked="true"/><br>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <input type="submit" class="btn btn-primary" value="Xong" />
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>