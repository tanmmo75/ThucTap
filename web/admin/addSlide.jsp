<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Insert Slide Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Trình chiếu </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <div class="box">
                        <header>
                            <div class="icons"><i class="icon-th-large"></i></div>
                            <h5>Màn hình nhập dữ liệu</h5>
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
                            <spring:form action="addSlide.htm" class="form-horizontal" commandName="s" method="post">
                                <div class="form-group">
                                    <label class="control-label col-lg-2">Hình Ảnh</label>
                                    <div class="col-lg-10">
                                        <spring:input path="image" type="text" id="productmodel_image" name="productmodel_image" placeholder="" readonly="true" class="form-control" style="width:50%; display:unset;" />
                                        <input type="button" id="Browse" value="Select Image" class="btn btn-outline-secondary" style="border: 1px solid #ced4da;"/><br>
                                        <spring:errors path="image" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Thứ Tự Hiển Thị</label>
                                    <div class="col-lg-10">
                                        <spring:input path="displayOrder" type="text" id="displayOrder" name="displayOrder" placeholder="" class="form-control"/>
                                        <spring:errors path="displayOrder" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Ngày Tạo</label>
                                    <div class="col-lg-10">
                                        <spring:input path="createDate" type="date" id="today" name="createDate" readonly="true" placeholder="" class="form-control"/>
                                        <spring:errors path="createDate" cssStyle="color:red;"/>
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
                                    <a href="listS.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
                                    <input type="submit" class="btn btn-primary" value="Lưu" />
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
    document.querySelector("#today").valueAsDate = new Date();
    $('#Browse').on('click', function (e) {
        e.preventDefault();
        var finder = new CKFinder();
        finder.selectActionFunction = function (url) {
            $('#productmodel_image').val(url);
        };
        finder.popup();
    });
</script>