<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>您参与的全部竞价</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<br />
<div align="center">
<h3>您参与的全部竞价</h3>
<h:dataTable width="80%" border="1"
	cellpadding="0"
	cellspacing="1"
	style="border:1px solid black"
	value="#{viewBid.bids}" var="bid"
	rowClasses="odd,even">
	<!-- 定义第一列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="物品名"/>
		</f:facet>
		<h:outputText value="#{bid.bidItem.itemName}"/>
	</h:column>
	<!-- 定义第二列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="竞价价格"/>
		</f:facet>
		<h:outputText value="#{bid.bidPrice}"/>
	</h:column>
	<!-- 定义第三列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="竞价时间"/>
		</f:facet>
		<h:outputText value="#{bid.bidDate}"/>
	</h:column>
	<!-- 定义第四列 -->
	<h:column>
		<f:facet name="header">
			<h:outputText value="竞价人"/>
		</f:facet>
		<h:outputText value="#{bid.bidUser.username}"/>
	</h:column>
</h:dataTable>
</div>
</td>
</tr>
</table>
</body>
</html>
</f:view>