<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lardi" tagdir="/WEB-INF/tags" %>
<html>
<jsp:include page="headTag.jsp"/>
<head>
    <title>Title</title>
</head>
<body>
<div class="jumbotron">
<div class="container">
    <div class="shadow">
        <ul class="nav navbar-nav navbar-left">
            <li>
                <h4>Please, set your login and password to continue!</h4>
                <br/>
                <c:url value="/j_spring_security_check" var="loginUrl"/>
                <form:form class="form-horizontal" role="form" action="${loginUrl}"
                           method="post">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="username"> Login: </label>
                        <div class="col-sm-10">
                            <input type="text" placeholder="Login" class="form-control" name='username' id="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="password"> Password: </label>
                        <div class="col-sm-10">
                            <input type="password" placeholder="Password" class="form-control" name='password' id="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-3">
                            <button type="submit" class="btn btn-info">Sign in</button>
                        </div>
                    </div>
                </form:form>
            </li>
        </ul>
    </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="nav navbar-nav navbar-left">
            <h4>Please, fulfill the next form:</h4>
            <br/>
            <div class="view-box">
                <form:form modelAttribute="userDTO" class="form-horizontal" method="post"
                           action="register" charset="utf-8"
                           accept-charset="UTF-8">

                    <lardi:inputField label="Full name" name="fullName"/>
                    <lardi:inputField label="Login" name="login"/>
                    <lardi:inputField label="Password" name="password" inputType="password"/>

                    <div class="form-group">
                        <div class="col-sm-9">
                            <button type="submit" class="btn btn-info">Add new User</button>
                        </div>
                    </div>
                </form:form>
            </div>
                </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>