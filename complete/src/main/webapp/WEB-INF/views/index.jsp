<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Static content -->
<script type="text/javascript" src="/resources/js/app.js"></script>

<title>Spring Boot</title>
</head>
<body>
  <h1>Gubee Tecnologia</h1>
  <hr>
  <div class="container">  
	  <div class="form">
	  	<form action="/" method="post">
	      <table>
	        <tr>
	          <td><b>Mercado</b></td>
	        </tr>
	        <tr>
	          <td>
			      <input type="checkbox" name="market" value="Digital Onboarding" />Digital Onboarding
			      <input type="checkbox" name="market" value="Ecommerce" />Ecommerce
			      <input type="checkbox" name="market" value="ERP" />ERP
			      <input type="checkbox" name="market" value="Loja fisica" />Loja fisica
			      <input type="checkbox" name="market" value="Lojista que não desejam possuir ecommerce" />Lojista que não desejam possuir ecommerce
			      <input type="checkbox" name="market" value="Mobile First" />Mobile First
			      <input type="checkbox" name="market" value="Telecom" />Telecom
			      <input type="checkbox" name="market" value="Venda direta" />Venda direta
	          </td>
	        </tr>
	      
	        <tr>
	          <td><b>Tecnologias</b></td>
	        </tr>
	        <tr>
	          <td>
			      <input type="checkbox" name="stack" value="Big Data Analytics" />Big Data Analytics
			      <input type="checkbox" name="stack" value="Cassandra" />Cassandra
			      <input type="checkbox" name="stack" value="Event Stream" />Event Stream
			      <input type="checkbox" name="stack" value="Hadoop" />Hadoop
			      <input type="checkbox" name="stack" value="Java 10" />Java 10
			      <input type="checkbox" name="stack" value="Kafka" />Kafka
			      <input type="checkbox" name="stack" value="Kotlin" />Kotlin      
			      <input type="checkbox" name="stack" value="MongoDB" />MongoDB
			      <input type="checkbox" name="stack" value="NodeJS" />NodeJS
			      <input type="checkbox" name="stack" value="Pig" />Pig
			      <input type="checkbox" name="stack" value="Redis" />Redis
	          </td>
	        </tr>
	        
	        <tr>
	          <td><input type="submit" value="Submit"></td>
	        </tr>      
	    </form>
	  </div>  
	    
	  <div class="list">
	  	<c:forEach items="${products}" var="product">
	    	<b>${product.productName}</b><br />
	    	${product.description}<br />
	    	<b>Mercado alvo</b> 
	    	<c:forEach items="${product.targetMarket}" var="mkt">
	    		[${mkt}]
	    	</c:forEach><br />    	
	    	<b>Tecnologias</b>
	    	<c:forEach items="${product.stack}" var="stk">
	    		[${stk}]
	    	</c:forEach><br />
	    	<br />
	    </c:forEach>
	  </div>
  </div>    
    
</body>
</html>