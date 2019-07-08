<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2019/6/25
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
    <json:property name="status" value="${cart.itemCount}"/>
    <json:property name="message" value="${cart.subtotal}"/>
</json:object>

