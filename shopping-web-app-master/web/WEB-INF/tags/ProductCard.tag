<%-- 
    Document   : ProductCard
    Created on : Apr 4, 2018, 4:26:21 PM
    Author     : bas
--%>
<%@taglib uri="/WEB-INF/tlds/MyTagLib.tld" prefix="mf" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<!--rtexprvalue คือ run-time application value-->
<%@attribute name="p" type="sit.int303.demo.model.Product" required="true" rtexprvalue="true"%>

<%-- any content can be specified here e.g.: --%>
<div class="col-md-3">
    <div class="thumbnail">
        <a href="ProductManager?productCode=${p.productcode}">
            <img class="img-thumbnail"
                 src="${mf:getImageFileName(p.productline.productline, p.productcode)}" title="${p.productcode}">
        </a>
        <a href="AddItemToCart?item=${p.productcode}">
            <img src="https://scraperking.com/wp-content/uploads/2015/01/Amazon_logo-9.gif" width="20px"/>
        </a>

        <div class="caption">
            <p>${p.productname}  | Scale:  ${p.productscale}  | Price: ${p.msrp} $US </p>

        </div>
    </div>
</div>