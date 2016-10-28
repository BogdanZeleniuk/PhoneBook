<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
<jsp:include page="bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <br/><br/>
            <h1>The list of contacts</h1>
            <br/>
            <div class="view-box">
                <form:form method="post" class="form-horizontal" role="form" id="filter">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="fName">First name:</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="fName" id="fName" placeholder="Gates">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="lName">Last name:</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="lName" id="lName" placeholder="Bill">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="mPhone">Mobile phone:</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="mPhone" id="mPhone" placeholder="+380(66)1234567">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button type="submit" class="btn btn-primary pull-right">Filter</button>
                        </div>
                    </div>
                </form:form>
                <a class="btn btn-sm btn-info" onclick="add()">Add new contact</a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last Name</th>
                        <th>Patronymic</th>
                        <th>Mobile phone</th>
                        <th>Home phone</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Edit your contact</h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="firstName" class="control-label col-xs-3">First name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Gates">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="control-label col-xs-3">Last name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="Bill">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patronymic" class="control-label col-xs-3">Patronymic</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="patronymic" name="patronymic"
                                   placeholder="patronymic">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobilePhone" class="control-label col-xs-3">Mobile Phone</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="mobilePhone" name="mobilePhone"
                                   placeholder="+380(66)1234567">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="homePhone" class="control-label col-xs-3">Home Phone</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="homePhone" name="homePhone"
                                   placeholder="+380(44)1234567">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="control-label col-xs-3">Address</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="Ukraine, Kiev">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">Amount</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="office@gmail.com">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="js/datatablesUtil.js"></script>
<script type="text/javascript" src="js/contactDataTables.js"></script>
</html>
