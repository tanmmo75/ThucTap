<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" %>
<%@attribute name="content" fragment="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title }</title>
    <div id="fb-root"></div>
    <script>(function (d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id))
                return;
            js = d.createElement(s);
            js.id = id;
            js.src = 'https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2&appId=1127896927384260&autoLogAppEvents=1';
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content=""> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>	
    <link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
    <link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
    <link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>

    <style type="text/css" id="enject"></style>

</head>
<body>
    <div id="header">
        <div class="container">
            <div id="welcomeLine" class="row">
                <div class="span9">
                    <marquee style="padding-top: 5px; font-weight: bold; color: red; font-family: initial; font-style: italic; font-size: inherit;" >Website tin tức đa phương tiên Đại Học Cần Thơ. Chịu trách nhiệm nội dung: Vương Nhật Tân</marquee>
                </div>
                <div class="span3">
                    <div class="pull-right">
                        <jsp:include page="topmenuIndex.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <div id="logoArea" class="navbar">
                    <a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <div class="navbar-inner">
                        <a class="brand" href="indexClient.htm"><img src="assets/img/logo1.png" alt="Bootsshop" style="height: 50px;"/></a>
                        <form action="initfindClient.htm" class="form-inline navbar-search" method="post">
                            <input id="name" class="srchTxt" type="text" name="name" value="${currentName}" placeholder="Nhập từ khóa cần tìm kiếm..."/>
                        <button type="submit" id="submitButton" class="btn btn-primary">Tìm kiếm</button>
                    </form>
                    <ul id="topMenu" class="nav pull-right">
                        <jsp:include page="mainmenuIndex.jsp"></jsp:include>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                <jsp:invoke fragment="content"></jsp:invoke>
                </div>
            </div>
        </div>
        <div  id="footerSection">
        <jsp:include page="listfooterIndex.jsp"></jsp:include>
    </div>
    <script src="themes/js/jquery.js" type="text/javascript"></script>
    <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="themes/js/google-code-prettify/prettify.js"></script>
    <script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
</body>
</html>