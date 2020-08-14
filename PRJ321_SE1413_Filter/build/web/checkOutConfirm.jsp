<%-- 
    Document   : checkOutConfirm
    Created on : Jul 14, 2020, 11:56:17 AM
    Author     : johny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body style="font-family: sans-serif;">
        <div style="text-align: center; width: 100%; height: auto; 
             background: aquamarine; float: left; margin-bottom: 25px;">
            <h1>Check Out Page</h1>
        </div>

        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}" />
                <c:if test="${not empty items}">
                    <div style="width: 100%; height: auto; float: left; text-align: center;">
                        <center>
                            <form action="removeSelectedBook" method="POST" 
                                  style="border-style: solid; border-width: initial; 
                                  width: 600px; padding: 10px;">
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Title</th>
                                            <th>Quantity</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody style="text-align: center;">
                                        <c:forEach var="title" items="${items}" varStatus="counter">

                                            <tr>
                                                <td>
                                                    ${counter.count}
                                                    .</td>
                                                <td>
                                                    ${title.key}
                                                </td>
                                                <td>
                                                    ${title.value}
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="chkItem" value="${title.key}" />
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        <tr>
                                            <td colspan="2">
                                                <a href="onlineStore">Add more book to your Cart</a>
                                            </td>
                                            <td>

                                            </td>
                                            <td>
                                                <input type="submit" value="Remove Selected Book" name="btnAction" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                            <br/>
                            <form action="checkOut" method="POST" 
                                  style="border-style: solid; border-width: initial; 
                                  width: 600px; padding: 10px;">
                                <c:set var="info" value="${requestScope.INFO_CHECKOUT_ERRORS}"/>
                                Your name: <input type="text" name="txtName" value="${param.txtName}" />
                                
                                <br/>
                                <c:if test="${not empty info.nameLengthErr}">
                                    <font style="color: red;">
                                    ${info.nameLengthErr}
                                    </font><br/>
                                </c:if>
                                <br/>
                                Address: <input type="text" name="txtAddress" value="${param.txtAddress}" /><br/>
                                <c:if test="${not empty info.addressLengthErr}">
                                    <font style="color: red;">
                                    ${info.addressLengthErr}
                                    </font><br/>
                                </c:if>
                                <input type="submit" value="Pay" name="btnAction" style="background: greenyellow;"/>
                            </form>
                        </center>
                        <br/><br/>
                        <a href="try"><-- Back to Login page</a>
                    </div>
                </c:if>
            </c:if>
        </c:if>

        <div style="width: 100%; height: auto; background: lightslategray;
             color: beige; text-align: center;  float: left;
             padding-top: 100px; padding-bottom: 10px; margin-top: 50px;">
            <div style="font-size: 18px;"><span style="font-weight: bold;">©</span>May 2020 by QuanBT</div>
        </div>
    </body>
</html>
