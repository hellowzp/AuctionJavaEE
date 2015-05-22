
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Categories</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<table width="80%" align="center" cellpadding="0"
	cellspacing="1" style="border:1px solid black">
<tr>
<td>
<div align="center">
<h3>请选择需要浏览的物品种类</h3>
<h:dataTable width="80%" border="1"
	cellpadding="0"
	cellspacing="1"
	style="border:1px solid black"
	value="#{mgrKind.kinds}" var="kind"
	rowClasses="odd,even">
	
	<h:column>
		<f:facet name="header">
			<h:outputText value="种类名"/>
		</f:facet>
		<h:outputLink value="viewItem.jsp?kindId=#{kind.id}">
		<h:outputText value="#{kind.kindName}"/>
		</h:outputLink>
	</h:column>
	
	<h:column>
		<f:facet name="header">
			<h:outputText value="种类描述"/>
		</f:facet>
		<h:outputText value="#{kind.kindDesc}"/>
	</h:column>
</h:dataTable>
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>