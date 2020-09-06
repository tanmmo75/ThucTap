<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:layout title="Insert Contact Page">   
    <jsp:attribute name="content">
        <div class="inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3> Liên hệ </h3>
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
                            <spring:form action="addContact.htm" class="form-horizontal" commandName="c" method="post">
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Nội Dung</label>
                                    <div class="col-lg-10">
                                        <spring:textarea path="content" type="text" rows="16" id="detail" name="content" placeholder="" class="form-control"></spring:textarea>
                                        <spring:errors path="content" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="text1" class="control-label col-lg-2">Trạng Thái</label>
                                    <div class="col-lg-10" style="padding-top: 8px;">
                                        <spring:radiobutton path="status" value="false" checked="true"/> Kích hoạt
                                        &nbsp;&nbsp;
                                        <spring:radiobutton path="status" value="true"/> Khóa
                                        <spring:errors path="status" cssStyle="color:red;"/>
                                    </div>
                                </div>
                                <div class="form-actions no-margin-bottom" style="text-align:center;">
                                    <a href="listC.htm?page=1" type="button" class="btn btn-default">Quay lại</a>
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
    var ck = CKEDITOR.replace('detail');
    CKEDITOR.config.extraPlugins='html5video';
</script>