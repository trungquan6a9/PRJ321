<%-- 
    Document   : createNewAccount
    Created on : Jun 26, 2020, 11:46:10 AM
    Author     : johny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up page JSP</title>
    </head>
    <body style="font-family: sans-serif;">
        <div style="text-align: center; width: 100%; height: auto; 
             background: aquamarine; float: left; margin-bottom: 25px;">
            <h1>Sign Up Page</h1>
        </div>
        <div style="float: left; width: 100%; height: auto;">
            <div style="text-align: center;">
                <center>
                    <form action="signUp" method="POST" 
                          style="border-style: solid; border-width: initial; 
                          width: 205px; padding: 10px; text-align: left;">
                        <c:set var="result" value="${requestScope.CREATE_ERRORS}"/>

                        <b>Username: </b><br/>
                        <input type="text" name="txtUsername" value="${param.txtUsername}" 
                               style="width: 200px;"/><br/>
                        <c:if test="${not empty result.usernameLengthErr}">
                            <font style="color: red;">
                            ${result.usernameLengthErr}
                            </font><br/>
                        </c:if>

                        <b>Password: </b><br/>
                        <input type="password" name="txtPassword" value="" 
                               style="width: 200px;" /><br/>
                        <c:if test="${not empty result.passwordLengthErr}">
                            <font style="color: red;">
                            ${result.passwordLengthErr}
                            </font><br/>
                        </c:if>

                        <b>Confirm: </b><br/>
                        <input type="password" name="txtConfirm" value="" 
                               style="width: 200px;" /><br/>
                        <c:if test="${not empty result.confirmNotMatched}">
                            <font style="color: red;">
                            ${result.confirmNotMatched}
                            </font><br/>
                        </c:if>

                        <b>Full name: </b><br/>
                        <input type="text" name="txtFullname" value="${param.txtFullname}" 
                               style="width: 200px;" />
                        <c:if test="${not empty result.fullnameLengthErr}">
                            <font style="color: red;">
                            ${result.fullnameLengthErr}
                            </font><br/>
                        </c:if>
                        <br/><br/>
                        <input type="submit" value="Sign Up" name="btnAction" 
                               style="width: 205px; background: sandybrown;"/>

                        <c:if test="${not empty result.usernameIsExisted}">
                            <font style="color: red;">
                            ${result.usernameIsExisted}
                            </font><br/>
                        </c:if>
                        <a href="login.html"><-- Back to Login page</a>
                    </form><br/>
                </center>
            </div>
        </div>
        <div style="width: 100%; height: auto; background: lightslategray; 
             color: beige; text-align: center;  float: left;
             padding-top: 100px; padding-bottom: 10px; margin-top: 50px;">
            <div style="font-size: 18px;"><span style="font-weight: bold;">©</span>May 2020 by QuanBT</div>
        </div>
    </body>
</html>
