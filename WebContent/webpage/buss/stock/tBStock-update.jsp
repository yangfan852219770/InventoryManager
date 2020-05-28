<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>货品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBStockController.do?doUpdate" tiptype="4">
					<input id="id" name="id" type="hidden" value="${tBStockPage.id }">
		<table style="width: 300px;height:100px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								货品名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="goodsname" readonly="readonly" name="goodsname" type="text" style="width: 150px" datatype="*1-16" class="inputxt"  
									               
									                 value='${tBStockPage.goodsname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货品名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						     	 <input name="totalcount" type="text" style="width: 150px" datatype="n1-10" class="inputxt"  
									               
									                 value='${tBStockPage.totalcount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/buss/goods/tBGoods.js"></script>		