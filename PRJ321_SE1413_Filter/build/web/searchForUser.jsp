<%-- 
    Document   : searchForUser
    Created on : Jul 9, 2020, 10:34:06 AM
    Author     : johny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="font-family: sans-serif;">
        <div style="text-align: center; width: 100%; height: 80px; 
             background: aquamarine; float: left; margin-bottom: 25px;">
            <h1>Search Page</h1>
        </div>

        <div style="width: 100%; height: auto; float: left;">
            <div style="text-align: center;">
                <form action="logout">
                    <font style="color:red;">
                    Welcome, ${sessionScope.FULLNAME}
                    </font>
                    <input type="submit" value="Logout" name="btnAction" />
                </form>
            </div>
        </div>

        <div style="width: 100%; height: auto; background: lightslategray; 
             color: beige; text-align: center;  float: left;
             padding-top: 100px; padding-bottom: 10px; margin-top: 50px;">
            <div style="font-size: 18px;"><span style="font-weight: bold;">©</span>May 2020 by QuanBT</div>
        </div>
    </body>
</html>
