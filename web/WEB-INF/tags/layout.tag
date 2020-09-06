<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" %>
<%@attribute name="content" fragment="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title }</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/css/main.css" />
        <link rel="stylesheet" href="assets/css/theme.css" />
        <link rel="stylesheet" href="assets/css/MoneAdmin.css" />
        <link rel="stylesheet" href="assets/plugins/Font-Awesome/css/font-awesome.css" />
        <link href="assets/css/layout2.css" rel="stylesheet" />
        <link href="assets/plugins/flot/examples/examples.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets/plugins/timeline/timeline.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-fileupload.min.css" />
        <link rel="stylesheet" href="assets/plugins/wysihtml5/dist/bootstrap-wysihtml5-0.0.2.css" />
        <link rel="stylesheet" href="assets/css/Markdown.Editor.hack.css" />
        <link rel="stylesheet" href="assets/plugins/CLEditor1_4_3/jquery.cleditor.css" />
        <link rel="stylesheet" href="assets/css/jquery.cleditor-hack.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-wysihtml5-hack.css" />
        <link rel="stylesheet" href="assets/css/countdown.css" />
    </head>
    <body style="font-family: initial;">
        <!--Kiểm tra session đã có dữ liệu đăng nhập hay chưa -->
        <c:if test="${empty username}">
            <div id="main"> 
                <div class="container">
                    <div class="clearfix">
                    </div>
                    <div id="counter"></div>
                    <div id="counter-default" class="row">
                        <div class="col-lg-12 col-sm-12" style="text-align: center;">
                            <iframe scrolling="no" frameborder="no" clocktype="html5" style="overflow:hidden;border:0;margin:0;padding:0;width:450px;height:210px;"src="https://www.clocklink.com/html5embed.php?clock=048&timezone=ICT&color=red&size=450&Title=&Message=&Target=&From=2018,1,1,0,0,0&Color=red"></iframe>
                        </div>
                    </div>
                    <div class="col-lg-12 title">
                        <h2>
                            <span class="">
                                <script type="text/javascript">
                                    farbbibliothek = new Array();
                                            farbbibliothek[0] = new Array("#FF0000", "#FF1100", "#FF2200", "#FF3300", "#FF4400", "#FF5500", "#FF6600", "#FF7700", "#FF8800", "#FF9900", "#FFaa00", "#FFbb00", "#FFcc00", "#FFdd00", "#FFee00", "#FFff00", "#FFee00", "#FFdd00", "#FFcc00", "#FFbb00", "#FFaa00", "#FF9900", "#FF8800", "#FF7700", "#FF6600", "#FF5500", "#FF4400", "#FF3300", "#FF2200", "#FF1100");
                                            farbbibliothek[1] = new Array("#00FF00", "#000000", "#00FF00", "#00FF00");
                                            farbbibliothek[2] = new Array("#00FF00", "#FF0000", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00", "#00FF00");
                                            farbbibliothek[3] = new Array("#FF0000", "#FF4000", "#FF8000", "#FFC000", "#FFFF00", "#C0FF00", "#80FF00", "#40FF00", "#00FF00", "#00FF40", "#00FF80", "#00FFC0", "#00FFFF", "#00C0FF", "#0080FF", "#0040FF", "#0000FF", "#4000FF", "#8000FF", "#C000FF", "#FF00FF", "#FF00C0", "#FF0080", "#FF0040");
                                            farbbibliothek[4] = new Array("#FF0000", "#EE0000", "#DD0000", "#CC0000", "#BB0000", "#AA0000", "#990000", "#880000", "#770000", "#660000", "#550000", "#440000", "#330000", "#220000", "#110000", "#000000", "#110000", "#220000", "#330000", "#440000", "#550000", "#660000", "#770000", "#880000", "#990000", "#AA0000", "#BB0000", "#CC0000", "#DD0000", "#EE0000");
                                            farbbibliothek[5] = new Array("#000000", "#000000", "#000000", "#FFFFFF", "#FFFFFF", "#FFFFFF");
                                            farbbibliothek[6] = new Array("#0000FF", "#FFFF00");
                                            farben = farbbibliothek[4];
                                            function farbschrift(){for (var b = 0; b < Buchstabe.length; b++){document.all["a" + b].style.color = farben[b]}farbverlauf()}function string2array(b){Buchstabe = new Array(); while (farben.length < b.length){farben = farben.concat(farben)}k = 0; while (k <= b.length){Buchstabe[k] = b.charAt(k); k++}}function divserzeugen(){for (var b = 0; b < Buchstabe.length; b++){document.write("<span id='a" + b + "' class='a" + b + "'>" + Buchstabe[b] + "</span>")}farbschrift()}var a = 1; function farbverlauf(){for (var b = 0; b < farben.length; b++){farben[b - 1] = farben[b]}farben[farben.length - 1] = farben[ - 1]; setTimeout("farbschrift()", 30)}var farbsatz = 1; function farbtauscher(){farben = farbbibliothek[farbsatz]; while (farben.length < text.length){farben = farben.concat(farben)}farbsatz = Math.floor(Math.random() * (farbbibliothek.length - 0.0001))}setInterval("farbtauscher()", 10000);
                                            text = "WEBSITE TIN TỨC ĐA PHƯƠNG TIỆN ĐẠI HỌC CẦN THƠ";
                                            string2array(text);
                                            divserzeugen();</script>
                            </span>
                        </h2>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-lg-12">
                            <div style="height: 25px;" class="progress progress-striped active" rel="tooltip" data-placement="bottom" data-original-title="Total Progress">
                                <div id="total-bar" style="padding-top: 5px;" class="progress-bar progress-bar-primary"></div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col-lg-6">
                            <form action="#" method="post" accept-charset="utf-8" id="emailForm" style="padding-top: 12px;">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="form-group col-lg-6 col-sm-6 col-6">
                                            <a href="indexClient.htm" target="_blank" class="btn btn-primary btn-block">Chuyển đến trang clien</a>
                                        </div>
                                        <div class="form-group col-lg-6 col-sm-6 col-6">
                                            <a href="indexloginAdmin.htm" class="btn btn-block btn-success">Đăng nhập trang quản trị</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-6 contact">
                            <marquee behavior="alternate"><span class="">Bạn Cần Đăng Nhập Trước</span></marquee>

                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
    <body class="padTop53">       
        <c:if test="${not empty username}">
            <div id="wrap" >
                <div id="top">
                    <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                        <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                            <i class="icon-align-justify"></i>
                        </a>
                        <header class="navbar-header">
                            <a href="index.htm" class="navbar-brand" style="margin-top: -9px;">
                                <img src="assets/img/logo1.png" alt="" height="45" />
                            </a>
                        </header>
                        <ul class="nav navbar-top-links navbar-right" style="width: 83%;">
                            <li class="dropdown" style="width: 92%;">
                            <marquee style="padding-top: 5px; font-weight: bold; color: red; font-family: initial; font-style: italic; font-size: inherit;" > Website tin tức đa phương tiên Đại Học Cần Thơ. Chịu trách nhiệm nội dung: Vương Nhật Tân.</marquee>
                            </li>
                            <li class="dropdown" style="float: right;">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="icon-user "></i>&nbsp; <i class="icon-chevron-down "></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="initUpdateUser.htm?userID=${userid}&&profile=1"><i class="icon-user"></i> Thông tin cá nhân </a>
                                    </li>
                                    <li><a href="#"><i class="icon-gear"></i> Cài đặt </a>
                                    </li>
                                    <li class="divider"></li>
                                    <li><a href="logoutAdmin.htm"><i class="icon-signout"></i> Đăng xuất </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div id="left" >
                    <div class="media user-media well-small">
                        <a class="user-link" href="#">
                            <img class="media-object img-thumbnail user-img" style="width: 64px; height: 64px;" alt="${sessionScope.username}" title="${sessionScope.username}" src="assets/img/1231.jpg" />
                        </a>
                        <br />
                        <div class="media-body">
                            <h5 class="media-heading"> ${username}</h5>
                            <ul class="list-unstyled user-info">
                                <li>
                                    <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> Online
                                </li>
                            </ul>
                        </div>
                        <br />
                    </div>
                    <ul id="menu" class="collapse">
                        <li class="panel">
                            <a href="index.htm" >
                                <i class="icon-dashboard"></i> Home
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listA.htm?page=1" >
                                <i class="icon-tablet"></i> Giới thiệu
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listCate.htm?page=1" >
                                <i class="icon-th-list"></i> Loại tin tức
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listP.htm?page=1" >
                                <i class="icon-trophy"></i> Tin tức
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listPro.htm?page=1" >
                                <i class="icon-indent-left"></i> Nguồn
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listU.htm?page=1" >
                                <i class="icon-user"></i> Tài khoản
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listUG.htm?page=1" >
                                <i class="icon-user-md"></i> Phân quyền
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listS.htm?page=1" >
                                <i class="icon-picture"></i> Trình chiếu
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listC.htm?page=1" >
                                <i class="icon-info-sign"></i> Liên hệ
                            </a>                   
                        </li>
 
                        <li class="panel">
                            <a href="listFB.htm?page=1" >
                                <i class="icon-thumbs-up"></i> Phản hồi
                            </a>                   
                        </li>
                        <li class="panel">
                            <a href="listF.htm?page=1" >
                                <i class="icon-tint"></i> Chân trang
                            </a>                   
                        </li>
                        <li class="panel">
                            <a id="BrowseAdmin" href="#">
                                <input type="text" id="BrowseAdminImage" hidden="true"/>
                                <i class="icon-picture"></i> 
                                Tải ảnh
                            </a>                   
                        </li>
                    </ul>
                </div>
                <div id="content">
                    <jsp:invoke fragment="content"></jsp:invoke>
                    </div>
                </div>
                <div id="footer">
                    <p>Tanb1507157@student.ctu.edu.vn &nbsp;2020</p>
                </div>
        </c:if>
        <script src="assets/plugins/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="assets/plugins/countdown/jquery.countdown.min.js"></script>
        <script type="text/javascript" src="assets/plugins/jquery-validation-1.11.1/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/js/countdown.js"></script>
        <script>
                                            $(function () {
                                            $('#AlertBox').removeClass('hide');
                                                    $('#AlertBox').delay(2000).slideUp(1000);
                                            });
                                            $(function () {
                                            $('#AlertBox2').removeClass('hide');
                                                    $('#AlertBox2').delay(2000).slideUp(1000);
                                            });</script>
        <script>
                    $('#BrowseAdmin').on('click', function (e) {
            e.preventDefault();
                    var finder = new CKFinder();
                    finder.selectActionFunction = function (url) {
                    $('#BrowseAdminImage').val(url);
                    };
                    finder.popup();
            });</script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <script src="assets/plugins/flot/jquery.flot.js"></script>
        <script src="assets/plugins/flot/jquery.flot.resize.js"></script>
        <script src="assets/plugins/flot/jquery.flot.time.js"></script>
        <script  src="assets/plugins/flot/jquery.flot.stack.js"></script>
        <script src="assets/js/for_index.js"></script>
        <script src="assets/plugins/jasny/js/bootstrap-fileupload.js"></script>
        <script src="assets/plugins/wysihtml5/lib/js/wysihtml5-0.3.0.js"></script>
        <script src="assets/plugins/bootstrap-wysihtml5-hack.js"></script>
        <script src="assets/plugins/CLEditor1_4_3/jquery.cleditor.min.js"></script>
        <script src="assets/plugins/pagedown/Markdown.Converter.js"></script>
        <script src="assets/plugins/pagedown/Markdown.Sanitizer.js"></script>
        <script src="assets/plugins/Markdown.Editor-hack.js"></script>
        <script src="assets/js/editorInit.js"></script>        
        <script src="<%=request.getContextPath()%>/assets/ckeditor/ckeditor.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/ckfinder/ckfinder.js" type="text/javascript"></script>
        <script>
                    $(function () {
                    formWysiwyg();
                    });
        </script>
    </body>
</html>