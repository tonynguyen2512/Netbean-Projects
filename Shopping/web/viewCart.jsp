
<%@page import="dto.TeaDTO"%>
<%@page import="dto.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>
    <body>
        <h1>Your selected tea!</h1>
        <%
            double total = 0.0;
            CartDTO cart = (CartDTO) session.getAttribute("CART");
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
                        total += tea.getPrice() * tea.getQuantity();
                %>
            <form action="MainController" >
                <tr>
                    <td><%= count++%></td>                    
                    <td>
                        <input type="text" name="id" value="<%=tea.getId()%>" readonly="true"/>
                    </td>
                    <td><%= tea.getName()%></td>
                    <td><%= tea.getPrice()%></td>
                    <td>                        
                        <input type="number" name="quantity" value="<%= tea.getQuantity()%>" required="true"/>
                    </td>
                    <td><%= tea.getPrice() * tea.getQuantity()%></td>
                    <td>
                        <!--<input type="hidden" name="id" value="<%= tea.getId()%>"/>-->
                        <input type="submit" name="action" value="Delete"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>

        </tbody>
    </table>

    <%
        }
    %>
    Total: <%= total%></br>
    <a href="MainController?action=add_more" >add more</a>
</body>
</html>
