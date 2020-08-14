<%-- 
    Document   : onlineStore
    Created on : Jul 13, 2020, 10:07:02 AM
    Author     : johny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fake Fahasa</title>
    </head>
    <body style="font-family: sans-serif;">

        <div style="text-align: center; width: 100%; height: auto; 
             background: aquamarine; float: left; margin-bottom: 25px;">
            <h1>Online Shopping Cart</h1>
        </div>
        <div style="width: 100%; height: auto; float: left;">
            <div style="text-align: center;">
                <center>
                    <c:set var="bookList" value="${requestScope.BOOK_LIST}"/>
                    <c:if test="${empty bookList}">
                        <h2 style="color:darkred;">
                            No book in DB!!!
                        </h2>
                    </c:if>
                    <c:if test="${not empty bookList}">
                        <form action="cart" method="POST" 
                              style="border-style: solid; border-width: initial; 
                              width: 400px; padding: 10px;">
                            Select the book you want to buy: <br/>
                            <select name="cboBook">
                                <c:forEach var="dto" items="${bookList}" varStatus="counter">
                                    <option>${dto.proName}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Add To Cart" name="btnAction" 
                                   style="background: lightcyan;"/>
                            <input type="submit" value="View Your Cart" name="btnAction"
                                   style="background: cornsilk;"/>
                        </form>
                    </c:if>
                </center>
                <br/><br/>
                <a href="login.html"><-- Back to Login page</a>
            </div>
        </div>

        <div style="width: 100%; height: auto; background: lightslategray; 
             color: beige; text-align: center;  float: left;
             padding-top: 100px; padding-bottom: 10px; margin-top: 50px;">
            <div style="font-size: 18px;"><span style="font-weight: bold;">©</span>May 2020 by QuanBT</div>
        </div>
    </body>
</html>
