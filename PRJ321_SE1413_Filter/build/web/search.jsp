<%-- 
    Document   : search
    Created on : Jun 5, 2020, 11:17:56 AM
    Author     : johny
--%>

<%@page import="quanbt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body style="font-family: sans-serif;">
        <div style="text-align: center; width: 100%; height: 80px; 
             background: aquamarine; float: left; margin-bottom: 25px;">
            <h1>Search Page</h1>
        </div>
        <c:if test="${sessionScope.ROLE == false}">
            <c:redirect url="userPage"/>
        </c:if>
        <c:if test="${empty sessionScope}">
            <c:redirect url="try"/>
        </c:if>
        
        <div style="width: 100%; height: auto; float: left; text-align: center;">
            <center>
                <form action="logout">
                    <font style="color:red;">
                    Welcome, ${sessionScope.FULLNAME}
                    </font>
                    <input type="submit" value="Logout" name="btnAction" />
                </form>
                <form action="search" method="POST" 
                      style="border-style: solid; border-width: initial; 
                      width: 350px; padding: 10px;">
                    Search value: <input type="text" name="txtSearchValue" 
                                         value="${param.txtSearchValue}" />
                    <input type="submit" value="Search" name="btnAction" />
                </form>

                <br/>
                <c:set var="searchValue" value="${param.txtSearchValue}"/>
                <c:if test="${not empty searchValue}">
                    <c:set var="searchResult" value="${requestScope.SEARCH_RESULT}"/>
                    <c:if test="${empty searchResult}">
                        <h2 style="color:darkred;">
                            No record is matched!!!
                        </h2>
                    </c:if>
                    <c:if test="${not empty searchResult}">
                        <table border="1" style="text-align: center;">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Full Name</th>
                                    <th>Role</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="result" value="${requestScope.UPDATE_ERRORS}"/>
                                <c:set var="usernameResult" value="${requestScope.CURRENT_USER}"/>
                                <c:set var="cannotDelete" value="${requestScope.CANNOT_DELETE_CURRENT_USER}"/>
                                <c:forEach var="dto" items="${searchResult}" varStatus="counter">
                                <form action="update">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                            .</td>
                                        <td>
                                            ${dto.username}
                                            <input type="hidden" name="txtUsername" 
                                                   value="${dto.username}" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" 
                                                   value="${dto.password}" />
                                            <c:if test="${not empty result.passwordLengthErr}">
                                                <c:if test="${dto.username eq usernameResult}">
                                                    <br/>
                                                    <font style="color: red;">
                                                    ${result.passwordLengthErr}
                                                    </font>
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${dto.fullname}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" value="ON"
                                                   <c:if test="${dto.isAdmin}">
                                                       checked="checked"
                                                   </c:if>
                                                   />
                                        </td>
                                        <td>
                                            <c:url var="deleteLink" value="delete">
                                                <c:param name="btnAction" value="Delete"/>
                                                <c:param name="pk" value="${dto.username}"/>
                                                <c:param name="lastSearchValue" value="${searchValue}"/>
                                            </c:url>
                                            <a href="${deleteLink}">Delete</a>
                                            <c:if test="${not empty cannotDelete.cannotDeleteCurrentUser}">
                                                <c:if test="${dto.username eq usernameResult}">
                                                    <br/>
                                                    <font style="color: red;">
                                                    ${cannotDelete.cannotDeleteCurrentUser}
                                                    </font>
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" name="btnAction" />
                                            <input type="hidden" name="lastSearchValue" 
                                                   value="${searchValue}" />
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>
            </center>
        </div>
                
        <div style="width: 100%; height: auto; background: lightslategray; 
             color: beige; text-align: center;  float: left;
             padding-top: 100px; padding-bottom: 10px; margin-top: 50px;">
            <div style="font-size: 18px;"><span style="font-weight: bold;">©</span>May 2020 by QuanBT</div>
        </div>
    </body>
</html>
