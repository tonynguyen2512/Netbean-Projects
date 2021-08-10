<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Model List</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </head>

    <body>
        <center>
            <h2>Model List</h2>
            <a href="${pageContext.request.contextPath }/ShoppingCart.jsp" >View Cart</a>

            <table width="35%" border="1">
                <tr><td>
                        <form method="POST" action="CartController">
                            
                            Description:  Tech-Freaks imaginary model 1.<input type="hidden" name="description" value="Tech-Freaks imaginary model 1."><br/>
                            Quantity:<input type="number" name="quantity" value="1"><br/> 
                            Price: $10.00<input type="hidden" name="price" value="10"><br/>
                            <input type="submit" name="action" value="Add To Cart">
                            
                        </form>
                    </td>
                </tr>
            </table>

        </center>
    </body>
</html>