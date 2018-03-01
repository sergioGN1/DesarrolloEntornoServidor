<%-- 
    Document   : lista
    Created on : 01-mar-2018, 13:18:12
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" value ="${usuarios}" id="d">
        <script>
            var f = document.getElementById("d");
            console.log(f);
        </script>
        <c:if test="${usuarios!= null}" var="mal">
              <table border="1">
                  <c:forEach items="${usuarios}" var="usuario">  
                      <tr>
                          <td>${usuario}</td>
                      </tr>
                  </c:forEach>
              </table>
        </c:if>
        <c:if test="${usuarios == null}" var="alumnos">
            <div>No hay usuarios conectados</div>
        </c:if>
        
            <div>${error}</div>
        
    </body>
</html>
