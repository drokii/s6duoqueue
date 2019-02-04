<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 2/4/2019
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <title>Insert title here</title>
</head>
<body>

Welcome ${requestScope['user'].username}.

</body>
</html>