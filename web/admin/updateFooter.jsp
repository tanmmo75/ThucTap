<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Update Footer Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Chân trang </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình cập nhập dữ liệu</h5>
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
                            <spring:form action="updateFooter.htm" class="form-horizontal" commandName="f" method="post">
                                <spring:hidden path="footerID"/>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Tên chân trang</label>
                                    <div class="col-lg-10">
                                        <spring:input path="name" type="text" id="name" name="name" placeholder="" class="form-control"/>
                                        <spring:errors path="name" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Nội dung</label>
                                    <div class="col-lg-10">
                                        <spring:textarea path="content" type="text" rows="12" id="detail" name="content" placeholder="" class="form-control"></spring:textarea>
                                        <spring:errors path="content" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-2">Trạng Thái</label>
                                    <div class="col-lg-10" style="padding-top: 8px;">
                                        <spring:radiobutton path="status" value="false" checked="true"/> Kích hoạt
                                        &nbsp;&nbsp;
                                        <spring:radiobutton path="status" value="true"/> Khóa
                                        <spring:errors path="status" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <a href="listF.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
                                    <input type="submit" class="btn btn-primary" value="Cập nhật" />
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</mt:layout>