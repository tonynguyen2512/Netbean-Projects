<%-- 
    Document   : ViewCart
    Created on : Feb 25, 2021, 1:24:13 PM
    Author     : ASUS
--%>

<%@page import="thien.dto.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>

    <h1>Your selected tea!</h1>
    <%
        CartDTO cart = (CartDTO) session.getAttribute("CART");
        double total = 0.0;
        if (cart != null) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (TeaDTO tea : cart.getCart().values()) {
                    total += tea.getQuantity() * tea.getPrice();
            %>
        <form action="MainController">
            <tr>
                <td><%=count++%></td>
                <td>
                    <%=tea.getId()%>update
                    <input type="text" name="id" value="<%=tea.getId()%>" readonly=""/>
                </td>
                <td><%=tea.getName()%></td>
                <td><%=tea.getPrice()%></td>
                <td>
                    <input type="text" name="id" value="<%=tea.getQuantity()%>" required="true"/>  
                </td>
                <td><%=tea.getQuantity() * tea.getPrice()%></td>
                <td>
                    <!--<input type="hidden" name="id" value="<%=tea.getId()%>"/>-->
                    <input type="submit" name="action" value="Delete"/>
                </td>
            </tr>
        </form>
        <%}%>
    </tbody>
</table>
<%
    }
%>
Total: <%= total%>
<a href="MainController?action=add_more">add more</a>
</html>
