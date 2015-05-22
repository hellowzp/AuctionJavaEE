
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>My Bids</title>
        </head>
        <body>
            <table width="780" align="center" cellspacing="0"
                   background="images/bodybg.jpg">
                <tr>
                    <td>
                        <br />
                        <div align="center">
                            <h3>您已经竞得的物品</h3>
                            <h:dataTable width="80%" border="1"
                                         cellpadding="0"
                                         cellspacing="1"
                                         style="border:1px solid black"
                                         value="#{viewSuccess.items}" var="item"
                                         rowClasses="odd,even">

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="物品名"/>
                                    </f:facet>
                                    <h:outputText value="#{item.itemName}"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="物品种类"/>
                                    </f:facet>
                                    <h:outputText value="#{item.kind.kindName}"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="赢取价格"/>
                                    </f:facet>
                                    <h:outputText value="#{item.maxPrice}"/>
                                </h:column>

                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="物品备注"/>
                                    </f:facet>
                                    <h:outputText value="#{item.itemRemark}"/>
                                </h:column>
                            </h:dataTable>
                        </div>
                    </td>
                </tr>
            </table>
        </body>
    </html>
</f:view>