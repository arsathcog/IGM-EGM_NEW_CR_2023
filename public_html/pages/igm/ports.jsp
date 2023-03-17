<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.niit.control.common.GlobalConstants"%>
<%@page import="com.niit.control.web.action.BaseAction"%>
<%@ page import="com.niit.control.web.UserAccountBean"%>
<%@page import="com.niit.control.common.StringUtil"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%@page import="com.niit.control.web.*"%>
<%@page import="com.niit.control.common.*"%>
<%@page import="java.util.Date"%>
<%
   boolean[] arrAuthFlags = BaseAction.getAuthFlags(request, "SIGM001");
   boolean blnReadFlag = arrAuthFlags[GlobalConstants.IDX_READ_FLAG];
   boolean blnDelFlag = arrAuthFlags[GlobalConstants.IDX_DEL_FLAG];
   String lstrSysDate = BaseAction.getSysDate();
   String lstrCtxPath1 = request.getContextPath();
   %>

<style>

	.roundshap9 {
		margin: 5px;
		width: 70%;
		height: 20px;
		border-color: blue;
	}
	
	.bl_detail_Newl{
	border: 1px solid #dddddd;
	height			:	20px;
	text-align		:	left;
	FONT-FAMILY		:	Verdana,Arial,Helvetica,sans-serif;
	vertical-align	:	middle;
	PADDING-LEFT	: 	2px;
 	BORDER-BOTTOM 	:	#6A7896 1px solid; /*changed color and px*/
	BORDER-TOP 		:	#6A7896 1px solid; /* changed color */
	
	}
	.veseelRemoveBorder {
    width: 120px;
    font-weight: 500;
    
   }
   .underLine {
   height: 1px;
   border-top: 1px solid #ccc !important;
   }
   .underLineSelected {
   border-top: 1px solid #ccc !important;
   }
   .flex-container {
   width: 101%;
   min-height: 300px;
   display: -webkit-flex;
   /* Safari */
   display: flex;
   /* Standard syntax */
   border: 1px solid #808080;
   }
   .flex-bl-list {
   background: #dbdfe5;
   width: 320px;
   }
   .flex-bl-detail {
   background: #dbdfe5;
   -webkit-flex: 1;
   /* Safari */
   -ms-flex: 1;
   /* IE 10 */
   flex: 1;
   /* Standard syntax */
   }
   .seqCssNew {
    margin: 10px;
    width: 40%;
    height: 22px;
    border-color: blue;
    font-size: 14px !important;
}
   .seqCss {
   }
   .tableVessels-width {
   width: auto;
   border: 1px solid gray;
   width: 150px;
   font-size: 12px;
   font-weight: 500;
   color: black;
   }
   .bl_detail {
   border-top: 1px solid gray;
   border-left: 1px solid gray;
   width: 120px;
  
}
</style>
<script>
    
</script>
<div id="dialog-form-port"  title="Port Details">
<div id="dialog-port" ng-init="init();">
   	  <td style="font-weight: bold;" > Port Code : </td>
   	  <td>
  	  <input type="search" ng-model="q" placeholder="Port Code." aria-label="filter portLookUpJson" class="roundshap9" />
  	  </td>
  	  <br>
      <table border="0"   cellspacing="0" cellpadding="0">
			<thead>
						<tr>
							<th class="TableLeftSub tableVessel-width" style="font-weight: bold;"><span>Seq</span></th>
							<th class="TableLeftSub tableVessel-width" style="font-weight: bold;"><span>Port code</span></th>
							<th class="TableLeftSub tableVessel-width" style="font-weight: bold;"><span>Port Name</span></th>
						</tr>
			 </thead>
			 <tbody>			
						<tr class="paginationclass"
							ng-repeat="datalist in portLookUpJson | filter:q as results track by datalist.port " ng-dblclick="setPortCode(datalist.port,datalist.portName);">
							<td class="bl_detail_Newl" ><span> {{$index + 1}} </span></td>
							<td class="bl_detail_Newl" ><span> {{ datalist.port}} </span></td>
							<td class="bl_detail_Newl" ><span> {{datalist.portName}} </span></td>
						</tr>
						
			 </tbody>
      </table>
</div>
</div>
<script>
 
</script>