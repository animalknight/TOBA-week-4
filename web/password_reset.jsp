<%@include file="/head and foot/header.html" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <h1>Password Reset</h1>
        <form action="NewCustomerServlet" method="post">
            <label>What would you like your new password to be?  </label>
            <input type="text" name="password" value="${user.password}"> </br>
        
            <input type="submit" value="Reset Password" id="reset">
        </form>
        
<%@include file="/head and foot/footer.jsp" %>