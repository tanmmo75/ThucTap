<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Update Producer Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Nhà sản xuất </h3>
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
                            <spring:form action="updateProducer.htm" class="form-horizontal" commandName="pro" method="post">
                                <spring:hidden path="producerID"/>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Nguồn tin</label>
                                    <div class="col-lg-10">
                                        <spring:input path="name" type="text" id="name" name="name" placeholder="" class="form-control"/>
                                        <spring:errors path="name" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-2">Logo nguồn tin</label>
                                    <div class="col-lg-10">
                                        <div class="col-md-4">
                                            <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-left: -15px;">
                                                <div class="fileupload-new thumbnail"><img src="${pro.logo}" width="220px;" height="175px;" alt=""></div>
                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 10px;"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-8" style="margin-top: 40px;">
                                            <spring:input path="logo" type="text" id="productmodel_image" name="productmodel_image" placeholder="" readonly="true" class="form-control" style="width:50%; display:unset;" />
                                            <input type="button" id="Browse" value="Select Image" class="btn btn-outline-secondary" style="border: 1px solid #ced4da;"/><br>
                                        <spring:errors path="logo" cssStyle="color:red;"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Email </label>
                                    <div class="col-lg-10">
                                        <spring:input path="email" type="text" id="email" name="email" placeholder="" class="form-control"/>
                                        <spring:errors path="email" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Điện Thoại Liên Hệ</label>
                                    <div class="col-lg-10">
                                        <spring:input path="phone" type="number" id="phone" name="phone" placeholder="" class="form-control"/>
                                        <spring:errors path="phone" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <a href="listPro.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
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
<script>
    $('#Browse').on('click', function (e) {
        e.preventDefault();
        var finder = new CKFinder();
        finder.selectActionFunction = function (url) {
            $('#productmodel_image').val(url);
        };
        finder.popup();
    });
</script>