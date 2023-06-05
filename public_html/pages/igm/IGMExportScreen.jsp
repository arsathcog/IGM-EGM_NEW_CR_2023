
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
<%@page import="java.util.Date" %>
<%
	boolean[] arrAuthFlags = BaseAction.getAuthFlags(request, "SIGM001");
	boolean blnReadFlag = arrAuthFlags[GlobalConstants.IDX_READ_FLAG];
	boolean blnDelFlag = arrAuthFlags[GlobalConstants.IDX_DEL_FLAG];
	String lstrSysDate = BaseAction.getSysDate();
	String lstrCtxPath1 = request.getContextPath();
%>



<!DOCTYPE html>
<html>
<head>
<tiles:useAttribute id='lstrScreenVersion' name='screenVersion' />
<meta http-equiv="X-UA-Compatible" content="IE=11" />

<title>EGM</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- date picker -->

<link href="<%=lstrCtxPath1%>/3rdjs/jquery-ui.css" rel="stylesheet">

<script src="<%=lstrCtxPath1%>/3rdjs/jquery-1.12.4.js"></script>
<script src="<%=lstrCtxPath1%>/3rdjs/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=lstrCtxPath1%>/js/sweetalert/sweetalert.css">
<script src="<%=lstrCtxPath1%>/js/sweetalert/sweetalert.js"></script>
<script src="<%=lstrCtxPath1%>/js/sweetalert/sweetalert.min.js"></script>

<script>
  $( function() {
    $( "#vessel-ports" ).tabs();
  } );
  </script>
 
<link
	href="<%=lstrCtxPath1%>/3rdjs/jquery-timepicker-master/jquery.timepicker.min.css"
	rel="stylesheet">
<script
	src="<%=lstrCtxPath1%>/3rdjs/jquery-timepicker-master/jquery.timepicker.min.js"></script>

<!-- css  -->

<link rel="stylesheet" href="<%=lstrCtxPath1%>/css/NTL.css" />
<link rel="stylesheet" href="<%=lstrCtxPath1%>/css/RCL.css" />
<link rel="stylesheet" href="<%=lstrCtxPath1%>/css/EZL.css" />
<link rel="stylesheet" href="<%=lstrCtxPath1%>/css/loader.css" />
<style type="text/css">
.table_lable{
	height: 40px;
	text-align: left;
    FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
    font-weight: 500;
    FONT-SIZE: 12px;
	BACKGROUND: #E9E7D7;
	border-right: 1px solid gray;
    border-top: 1px solid gray;
    border-bottom: 1px solid gray;
}
.browse {
    
    height: 20px;
    width: 100px;
   
}
.buttons_box1 {
    height: 20px;
    text-align: right;
    border-bottom: 1px solid #6A7896;
    background-color: #E9E7D7;
    margin: 1px 1px;
    padding-top: 1px;
    clear: both;
}
.scoll-pane {
    width: 100%;
    height: auto;
    overflow: auto;
    outline: none;
    overflow-y: hidden;
    padding-bottom: 15px;
    -ms-overflow-style: scroll;  // IE 10+
    scrollbar-width: none;  // Firefox
}
  .scoll-pane::-webkit-scrollbar { 
  display: none;  // Safari and Chrome
  }
a {
	text-decoration: underline;
}
.bg_textbox2 {
	background-color: yellow;
}
.refreshcolor{
background-color: pink;
}
.validateBL{
background-color: #ADFF2F ;
}
.TableLeftSub {
	height: 20px;
	text-align: left;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
	font-weight: normal;
	FONT-SIZE: 100%;
	COLOR: #003399; /* changed color  from #000000*/
	BACKGROUND: #E9E7D7; /* changed color for R1.2 to 72A2F6*/
	vertical-align: middle;
	PADDING-LEFT: 2px;
	BORDER-BOTTOM: #6A7896 1px solid; /* changed color and px*/
	BORDER-TOP: #6A7896 1px solid; /* changed color */
	border-spacing: 5px;
}
.TableCenterSub {
	height: 20px;
	text-align: center;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
	font-weight: normal;
	FONT-SIZE: 100%;
	COLOR: #003399; /* changed color  from #000000*/
	BACKGROUND: #E9E7D7; /* changed color for R1.2 to 72A2F6*/
	vertical-align: middle;
	PADDING-CENTER: 2px;
	BORDER-BOTTOM: #6A7896 1px solid; /* changed color and px*/
	BORDER-TOP: #6A7896 1px solid; /* changed color */
	border-spacing: 5px;
}
input, select, .must {
	font-family: verdana, arial, helvetica, sans-serif;
	border: 1px solid #3E71CC;
	height: 15px;
	width: 100px;
	font-size: 10px;
	font-family: verdana, arial, helvetica, sans-serif;
	padding: 1px;
	margin: 0;
	required
}
body {
	margin: 1px;
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 15px;
	font-weight: normal;
	background: #efefeb;
}
table {
	border-spacing: 1;
}
.whitebg {
	background: #efefeb !important;
	font-weight: normal;
/*	color: #003399;*/
     background-color: #0000ff;
}
.whitebgs{
    display: inline-table;
    background-color: #CCCCCC;
    font-size: 12px;
    word-spacing: 5px;
    width: 80%;
    font-weight: bold;
    color: black;
    text-align: left;
    }
table.table_search tr td {
	color: #0000ff;
	padding: 0px 0px 2px 2px;
	border-top: 1px solid #efefeb;
}
table.table_search tr td.whitebg {
	background: #fff;
}
div.search_result {
	height: 190px;
}
table.table_results tbody {
	height: 190px;
}
.roundshap1 {
	margin: 1px;
	width: 80px;
	height: 15px;
	border-color: blue;
}
.roundshap2 {
	margin: 5px;
	width: 89%;
	height: 20px;
	border-color: blue;
}
.roundshap3 {
	margin: 1px;
	width: 100px;
	height: 19px;
	border-color: blue;
}
.
roundshap4 {
	margin: 1px;
	width: 100px;
	height: 15px;
	border-color: blue;
}
.roundshap9 {
	margin: 5px;
	width: 70%;
	height: 20px;
	border-color: blue;
}
.seqCss {
	margin: 1px;
	width: 90%;
	height: 22px;
	border-color: blue;
	font-size: 14px !important;
}

.seqCssNd {
	margin: 1px;
	width: 45%;
	height: 22px;
	border-color: blue;
	font-size: 14px !important;
}
.smallDPCss {
	margin: 5px;
	width: 90%;
	height: 25px;
	border-color: blue;
}
.smallInputBox {
	margin: 1px;
	width: 60px;
	height: 15px;
	border-color: blue;
}
.roundshap5 {
	margin: 1px;
	width: 100px;
	height: 19px;
	margin-top: 2px;
	vertical-align: top;
	font-size: 12px;
	color: #333333;
	border: #3E71CC 1px solid;
	letter-spacing: -1px;
	background: #fed url(<%=lstrCtxPath1%>/images/input_bg.gif) top left
		repeat-x;
}
.roundshap6 {
	margin: 1px;
	width: 80px;
	height: 19px;
	border-color: blue;
	}
.loading
{
    text-align: left;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
	font-weight: normal;
	FONT-SIZE: 100%;
	COLOR: #003399; /* changed color  from #000000*/
  
}
@keyframes blink {
    
    0% {
      opacity: .2;
    }
    
    20% {
       opacity: 1;
    }
    
    100% {
      opacity: .2;
    }
}
#loading span {
    
    animation-name: blink;
    animation-duration: 1.4s;
    animation-iteration-count: infinite;
    animation-fill-mode: both;
}
#loading span:nth-child(2) {
    animation-delay: .2s;
}
#loading span:nth-child(3) {
    animation-delay: .4s;
}
.roundshapnormal {
	margin: 1px;
	width: 50px;
	height: 19px;
	margin-top: 2px;
	vertical-align: top;
	font-size: 12px;
	color: #333333;
	border: #3E71CC 1px solid;
	letter-spacing: -1px;
                    }
   .roundshapname {
	margin: 1px;
	width: 80px;
	height: 19px;
	margin-top: 2px;
	vertical-align: top;
	font-size: 12px;
	color: #333333;
	border: #3E71CC 1px solid;
	letter-spacing: -1px;
    }
    .roundshapadress {
	margin: 1px;
	width: 100px;
	height: 19px;
	margin-top: 2px;
	vertical-align: top;
	font-size: 12px;
	color: #333333;
	border: #3E71CC 1px solid;
	letter-spacing: -1px;
    }
    .footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	color: white;
	text-align: center;
	style="font-family: Verdana;
	font-size: 10pt
	valign=middle;
	align=left;
	background-color: #D4D0C8;
    color: white;
    text-align: center;
}
.roundshapError {
	margin: 1px;
	width: 70px;
	height: 19px;
	margin-top: 2px;
	vertical-align: top;
	font-size: 12px;
	color: black;
	border: red 1px solid;
	letter-spacing: -1px;
	background-image: linear-gradient(red, white);
		}
		
		
		.whitebg{
				display: inline-table; 
				background-color: #CCCCCC; 
				font-size: 12px; 
				word-spacing: 5px; 
				width: 100%;
				font-weight: bold;
    			color: black;
    			text-align: left;
    	}
	.containerTableBody {
			background-color:#f7f3f3 !important;		 	     
		    font-size: 15px !important;
		    word-spacing: 5px;		     
		    font-weight: normal;
		    color: black;
		    height: 30px;
	 }
	 .lookUpbtnbutton {
	    margin-top: 6px;
	    vertical-align: top;
	    width: 20px;
	    color: #333333;
	    height: 20px;
	    border: #3E71CC 1px solid;
	    letter-spacing: -1px;
	    background: #fed url(../images/input_bg.gif) top left repeat-x;
	}
	i:hover{
  		filter: brightness(1110%);
	}
	i:active{
  		transform: translate(0,0.3rem);
  		box-shadow: 0 0.1rem #333333;
	}
	hover{
  		filter: brightness(1110%);
	}
</style>
<script language="JavaScript" src="<%=lstrCtxPath1%>/js/validate.js"></script>
<script language="JavaScript"
	src="<%=lstrCtxPath1%>/js/RutMessageBar.js"></script>
<SCRIPT language="javascript" type="text/javascript">
       var PROG_ID          = 'SIGM001';
       var FORM_ID          = 'figm001';
           var ONLOADCONE         = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001", pageContext)%>';
    	   var ONFIND        	  = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001ExportFind", pageContext)%>';
    	   var ONFINDEXPORT        	  = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001ExportFind", pageContext)%>';
    	   var ONREFRESH          = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001refresh", pageContext)%>';
    	   var ONSAVE             = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001save", pageContext)%>' ;
    	   var ONSAVEFILE         = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001savefile", pageContext)%>';
    	   var ONEXCEL            = '<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001excelfile", pageContext)%>';
    	   var ONFILEUPLOAD       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/sigm001edifilegenerate", pageContext)%>';
    	   var ctxPath1           = '<%=lstrCtxPath1%>';
    	   var USERID             = '<%=BaseAction.getUserID(request)%>';
    	   var GETBLDATA       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getBlDataExport", pageContext)%>';
    	   var GETPORTDATA          =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getPortData", pageContext)%>';
    	   var SAVE_DATA       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/VesselVoyagSaveExport", pageContext)%>';
    	   var SAVE_DATA_VESSEL_VOYAGE   =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/VesselVoyageSaveExport", pageContext)%>';
    	   var SAVE_DATA_BL_DATA        =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/blDataSaveExport", pageContext)%>';
    	   var DOWNLOAD_DATA       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/downloadEgm", pageContext)%>';
    	   var UPLOAD_CSV       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/csvFilePrsnOnBordExport", pageContext)%>';
    	   var DELETE_CSV       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/deleteCsvFile", pageContext)%>';
    	   var DOWNLOADCSV       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/downloadCsvFile", pageContext)%>';
    	   var APPLYSAVE          =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/applySave", pageContext)%>';
    	   
    	   var PERSONONBOARDSEARCH         =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getPersonOnBoardSearchExport", pageContext)%>';
    	   var CREWEFFECTSEARCH        =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getCrewEffectSearchExport", pageContext)%>';
    	   var SHIPSTORSEARCH          =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getShipStorSearchExport", pageContext)%>';
    	   var CAROGODETAILSSEARCHHBL      =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getCarogoDetailsHBL", pageContext)%>';
    	   var CAROGODETAILSSEARCH          =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getCarogoDetailsExport", pageContext)%>';
    	   var CONTAINERDETAILSSEARCH       =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getContainerDetailsExport", pageContext)%>';
    	   var GETHBLLIST           =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getHblList", pageContext)%>';
    	   var GETSELECTALLBL           =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getSelectAllOption", pageContext)%>';
    	   var UPLOAD_ACK       		=	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/ackFileUpload", pageContext)%>';
    	   var UPLOAD_SHIPPING      		=	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/shippingFileUpload", pageContext)%>';	
    	   var GETSELECTALLBL           =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getSelectAllOptionExport", pageContext)%>';    	   
    	   var GETSTOWAGEEXPORT		        =	'<%=com.niit.control.web.JSPUtils.getActionMappingURL("/getStowageExport", pageContext)%>';

            <%String strUserFsc = null;
			String userName = null;
			String userId = null;
			UserAccountBean account = (UserAccountBean) session.getAttribute(GlobalConstants.USER_ACCOUNT_BEAN);
			strUserFsc = account.getUserFsc();
			userName = account.getUserName();
			userId = account.getUserId();%>
			
	      var autoCloseTimer;
	      var timeoutObject;
	function promptForClose() {
		alert("Session timeout.")
		autoCloseTimer = setTimeout("definitelyClose()", 1000);
	}
	function resetTimeout() {
		clearTimeout(timeoutObject);
		timeoutObject = setTimeout("promptForClose()", 1000 * 60 * 10);
	}
	function definitelyClose() {
		top.opener = self;
		top.window.close();
		top.popupWindow.window.close();
	}
</SCRIPT>
   <script src="<%=lstrCtxPath1%>/js/ImportGeneralManifestNew.js?id=<%=new Date()%>" ></script>
</head>

<BODY topmargin="0" leftmargin="0" ng-app="myApp">
	<%-- <form  method="POST" action="<%=lstrCtxPath1%>/do/sigm001"> --%>
	<div ng-controller="findResult">
	<div id="container" style="width: 100%;"  >
		<TABLE border="0" width="100%" cellspacing="0" cellpadding="0">
			<TR>
				<TD>
					<div class="page_title">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td class="PageTitle">Export General Manifest(EGM)</td>
								<td class="PageTitleRight">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="middle" align="right"><font size="1"
												face="Verdana" color="#FFFFFF"></font></td>
											<td valign="middle" align="right"><font size="1"
												face="Verdana" color="#FFFFFF"> <%=userName%>(<%=userId%>)-<%=strUserFsc%>&nbsp;&nbsp;-&nbsp;R/*/***&nbsp;<%
															 	Date dNow = new Date();
															 	SimpleDateFormat ft = new SimpleDateFormat(" dd/MM/yyyy ");
															 	out.print(ft.format(dNow));
														 %>
											</font></td>
											<td valign="middle" align="left"><img border="0"
												src="<%=lstrCtxPath1%>/images/imgDivider.gif" height="20"></td>
											<td width="50" valign="middle" align="center"><a href=""><img
													border="0" alt="Help" onClick="openHelp()"
													src="<%=lstrCtxPath1%>/images/btnHelp.jpg" width="40"
													height="16"></a></td>
											<td width="6" valign="middle" align="center"><img
												border="0" src="<%=lstrCtxPath1%>/images/imgDivider.gif"
												width="12" height="20"></td>
											<td width="2%"><a href=""><img border="0"
													src="<%=lstrCtxPath1%>/images/btnClose.gif" alt="Close"
													onClick="closeWindow()" width="16" height="16"></a></td>

										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>

					<div class="page_info">
						<span
							style="font-family: verdana, arial, helvetica, sans-serif; font-size: 10px; font-weight: normal; background: #efefeb;"><%=lstrScreenVersion%>&nbsp;&nbsp;</span>
					</div>
						<div class="text_header">
							<h2>Export General Manifest Search</h2>
						</div>
						<br>
                        <form id="form">
						<table class="table_search" border="0" cellspacing="0"
							cellpadding="0" style="FONT-SIZE: 15px;">
							
							<tr>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">Service</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="igmservice" value="" style="width: 75%"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmservicefield" class="btnbutton"
									onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">Vessel</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="vessel" style="width: 75%" class="must"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmvesselfield" class="btnbutton"
									onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">Voyage</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="voyage" style="width: 75%" class="must"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmvoyagefield" class="btnbutton"
									onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">POD</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="pod"  style="width: 75%" 
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmpodfield" class="btnbutton" onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">DEL</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="del" value="" style="width: 75%" onblur="changeUpper(this)">
									<input type="button" value=". . ." name="igmdelfield"
									class="btnbutton" onclick='' /></td>
							</tr>
							<tr>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">POL</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="pol" value="" style="width: 75%" class="must" onblur="changeUpper(this)">
									<input type="button" value=". . ." name="igmpolfield"
									class="btnbutton" onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">POL
									Terminal</td>

								<td class="whitebg"><input type="text" name="figm001"
									id="polTerminal" value="" style="width: 75%"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmpolterminalfield" class="btnbutton"
									onclick='' /></td>

								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">POD
									Terminal</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="podTerminal" value="" style="width: 75%"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmpodterminalfield" class="btnbutton"
									onclick='' /></td>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">Direction</td>
								<td class="whitebg"><select name="figm001" id="direction"
									style="height: 20px; width: 95%;">
										<option value="10">ALL</option>
								</select></td>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">DEPOT</td>
								<td class="whitebg"><input type="text" name="figm001"
									id="depo" value="" style="width: 75%"
									onblur="changeUpper(this)"> <input type="button"
									value=". . ." name="igmdepofield" class="btnbutton" onclick='' /></td>
							</tr>
							<tr>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal;  background: #efefeb;">BL
									Creation Date From</td>
								<td class="whitebg"><input type="text" name="figm001" readonly="readonly"
									id="blCreationDateFrom" maxlength="10" value="20190120"
									style="width: 70%"> <a href="#"></a> <img
									src="<%=lstrCtxPath1%>/images/btnCalendar.gif"
									onClick="dateFrom()" alt="Calender" class="calender"></td>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal;  background: #efefeb;">BL
									Creation Date To</td>

								<td class="whitebg"><input type="text" name="figm001" readonly="readonly"
									id="blCreationDateTo" maxlength="10" value="20190123"
									style="width: 70%" >
									<a href="#"><img
										src="<%=lstrCtxPath1%>/images/btnCalendar.gif" alt="Calender"
										class="calender" onClick="dateTo()"></a></td>
								<td
									style="font-family: verdana, arial, helvetica, sans-serif; font-size: 12px; font-weight: normal; background: #efefeb;">BL
									Status</td>
								<td class="whitebg"><select name="figm001" id="inStatus"
									style="height: 20px; width: 95%">
										<option value="" selected>ALL</option>
										<option value="1">Entry</option>
										<option value="2">Confirmed</option>
										<option value="3">Printed</option>
										<option value="4">Manifested</option>
										<option value="5">Invoiced</option>
										<option value="6">WAIT LISTED</option>
								</select></td>
							</tr>
						</table>
						</form>
						<br>
						<div class="buttons_box">
							    <input type="button" value="Refresh" id="refreshButton" disabled='true' 
							    name="btnRefresh" class="event_btnbutton" onclick='refreshBtn()' /> 
							    <input type="button" value="Merge Files" name="btnCreateBayPlan" id="btnCreateBayPlan"
								class="event_btnbutton" onclick='mergeFiles()' />
								<input type="button" value="  SEI  " id="seiButton" 
								 name="btnsei"	class="event_btnbutton" onclick='onseiBtn()' />
								<input type="button" value="  SAM  " name="generatemanifest"
								class="event_btnbutton" disabled='true' id="generatetype"
								onclick='return onsamBtn()' />
								<input type="button" value=" Generate Manifest " name="manifestfilegeneratoredifile"
								class="event_btnbutton" disabled='true' id="manifestfilegeneratoredifile"
								onclick='return manifestFileGeneratorEdiFile()' /> 
								<input type="button" value="Find" name="figm001" id="onfindData"
								class="event_btnbutton" ng-click='findDataNew()' /> 
								<input type="button" value="Reset" name="btnReset" id="onResetData"
								class="event_btnbutton" onclick='onResetForm()' />
								<input type="button" value="Submit" name="Submit" id="submitype"
								class="event_btnbutton" disabled='true'
								onclick='return submitData()' />
						</div>
				</TD>
			</TR>
		</TABLE>
	</div>

	
		<div id="vessel&voyage1st" style="display: none" >
				<div class="text_header">
					<h2>Vessel/Voyage Search Details</h2>
					<h2 style="margin-left:39%;font-size: 09px;">Acknowledgment File</h2>
								<table style="margin-left:78%">
									<tr>
										<td><input class="event_btnbutton"  style="margin-top:-17px;"  type="file" 
											name="figm001" id="acknowledgmentFileUpload" size="50px"/></td>
										<td ><input class="event_btnbutton" style="width: 80px;margin-top: -15px;"
											type="button" name="figm001" id="btnacknowledgmentFileUpload" value="Upload"
											onclick="onUploadAcknowledgment()" /></td>
									</tr>
						</table>
		</div>
		<div class="table">
			<table class="whitebg" border="0" style="background-color: #CCCCCC">
				<thead style="font-size: 10px;">
					<tr>
						<th class="TableCenterSub" style="width: 90px; height: 30px;border:1px solid gray;">Option</th>
						<th class="TableCenterSub" style="width: 90px; height: 30px;border:1px solid gray;">Sequence</th>
						<th class="TableCenterSub" style="width: 80px; height: 30px;border:1px solid gray;">Service</th>
						<th class="TableCenterSub" style="width: 80px; height: 30px;border:1px solid gray;">Vessel</th>
						<th class="TableCenterSub" style="width: 80px; height: 30px;border:1px solid gray;">Voyage</th>
						<th class="TableCenterSub" style="width: 85px; height: 30px;border:1px solid gray;">Pol</th>
						<th class="TableCenterSub" style="width: 80px; height: 30px;border:1px solid gray;">From Terminal</th>
					
					</tr>
				</thead>
				<tbody>
					 
					<tr    ng-repeat="item in result track by $index">
						<td >							
							
							<input type="radio" name="rowSelectedVV" id="0rowSelectedForRadioSet" value="0" ng-click="showPop($index);">
						</td>
						<td>{{$index+1}}</td>
						<td>
						
							<input type="text" class="roundshap1" disabled="disabled" value="{{item.service}}" >
						</td>
						<td>
							<input type="text" class="roundshap1" disabled="disabled" value="{{item.vessel}}" >
						</td>
						<td >
							<input type="text" class="roundshap1" disabled="disabled" value="{{item.voyage}}" >
						</td>
						<td>
							<input type="text" class="roundshap1" disabled="disabled" value="{{item.pol}}" >
						
						</td>
						<td>
							<input type="text" class="roundshap1" disabled="disabled" value="{{item.polTerminal}}" >
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		  
</div>
	
	
	 
	</div>
	<br>
	<br>
	<br>
	<br>
	<!--  <div id="container" style="width: 100%;">  -->
		<div class="footer" >
		<table border="0" width="100%" cellspacing="1" cellpadding="4">
			<tr>
				<td width="3%" bgcolor="#D4D0C8">
					<p align="center">
						<img border="0" src="<%=lstrCtxPath1%>/images/imgError.gif"
							width="16" height="16">
							</p>
				</td>
				<td width="97%" bgcolor="#D4D0C8" valign="middle" align="left"
					id="messagetd" style="font-family: Verdana; font-size: 10pt;">
					<p align="left">
					<div id='msg' style="width: 100%; color:black;"></div>
					</p>
				</td>
			</tr>
		</table>
	</div>
	
	
<!-- chandu change end -->
  <div id="dialogBox" style="display:none;">
     <jsp:include page="dialog.jsp"></jsp:include>
  </div>
	
	<!-- </form> -->

</BODY>
<script>
	var igmpage;
	var vvd = [ {
		'type' : 'radio',
		'columnName' : 'Option'
	}, {
		'type' : 'text',
		'columnName' : 'Sequence'
	}, {
		'type' : 'text',
		'columnName' : 'Service',
		"mappedCol" : 'service'
	}, {
		'type' : 'text',
		'columnName' : 'Vessel',
		'mappedCol' : 'vessel'
	}, {
		'type' : 'text',
		'columnName' : 'Voyage',
		'mappedCol' : 'voyage'
	}, {
		'type' : 'text',
		'columnName' : 'Port',
		'mappedCol' : 'pod'
	}, {
		'type' : 'text',
		'columnName' : 'Terminal',
		'mappedCol' : 'terminal'
	}, {
		'type' : 'text',
		'columnName' : 'Custom Terminal Code',
		'mappedCol' : 'customTerminalCode'
	}, {
		'type' : 'text',
		'columnName' : 'From Item No',
		'mappedCol' : 'fromItemNo'
	}, {
		'type' : 'text',
		'columnName' : 'To Item No',
		'mappedCol' : 'toItemNo'
	}, {
		'type' : 'text',
		'columnName' : 'New Voyage',
		'mappedCol' : 'newVoyage'
	}, {
		'type' : 'text',
		'columnName' : 'New Vessel',
		'mappedCol' : 'newVessel'
	}, {
		'type' : 'text',
		'columnName' : 'Road Carr code',
		'mappedCol' : 'roadCarrCodeVVS'
	}, {
		'type' : 'text',
		'columnName' : 'TP Bond No',
		'mappedCol' : 'roadTpBondNoVVSS'
	}, {
		'type' : 'text',
		'columnName' : 'CFS Custom Code',
		'mappedCol' : 'cfsCustomCode'
	}, {
		'type' : 'hidden',
		'columnName' : 'Pol',
		'mappedCol' : 'pol'
	}, {
		'type' : 'hidden',
		'columnName' : 'Pol Terminal',
		'mappedCol' : 'polTerminal'
	}, {
		'type' : 'hidden',
		'columnName' : 'DEL VLS',
		'mappedCol' : 'del'
	}, {
		'type' : 'hidden',
		'columnName' : 'DEPOT VLS',
		'mappedCol' : 'depot'
	}]
var bld = [{
		'type' : 'checkbox',
		'columnName' : 'Option'
	},{
		'type' : 'text',
		'columnName' : 'Sequence'
	},{
		'type' : 'text',
		'columnName' : 'Item Number',
		"mappedCol" : 'itemNumber'
	},{
		'type' : 'text',
		'columnName' : 'BL#',
		'mappedCol' : 'bl'
	},{
		'type' : 'text',
		'columnName' : 'BL Version',
		'mappedCol' : 'blVersion'
	},{
		'type' : 'text',
		'columnName' : 'BL_Date',
		'mappedCol' : 'blDate'
	},{
		'type' : 'hidden',
		'columnName' : 'BL Status',
		'mappedCol' : 'blStatus'
	},{
		'type' : 'text',
		'columnName' : 'CFS-Custom Code',
		'mappedCol' : 'cfsName'
	},{
		'type' : '',
		'columnName' : 'Cargo Nature',
		'mappedCol' : 'cargoNature'
	},{
		'type' : 'text',
		'columnName' : 'Item Type',
		'mappedCol' : 'itemType'
	},{
		'type' : 'text',
		'columnName' : 'Cargo Movement',
		'mappedCol' : 'cargoMovmnt'
	},{
		'type' : 'text',
		'columnName' : 'Cargo Movement Type',
		'mappedCol' : 'cargoMovmntType'
	},{
		'type' : 'text',
		'columnName' : 'Transport Mode',
		'mappedCol' : 'transportMode'
	},{
		'type' : 'text',
		'columnName' : 'Road Carr code',
		'mappedCol' : 'roadCarrCode'
	},{
		'type' : 'text',
		'columnName' : 'TP Bond No',
		'mappedCol' : 'roadTpBondNo'
	},{
		'type' : 'text',
		'columnName' : 'Submit Date Time',
		'mappedCol' : 'submitDateTime'
	},{
		'type' : 'text',
		'columnName' : 'DPD Movement',
		'mappedCol' : 'dpdMovement'
	},{
		'type' : 'text',
		'columnName' : 'DPD Code',
		'mappedCol' : 'dpdCode'
	},{
		'type' : 'text',
		'columnName' : 'Consolidated Indicator',
		'mappedCol' : 'consolidated_indicator'
	},{
		'type' : 'text',
		'columnName' : 'Previous Declaration',
		'mappedCol' : 'previous_declaration'
	},{
		'type' : 'text',
		'columnName' : 'Consolidator PAN',
		'mappedCol' : 'consolidator_pan'
	},{
		'type' : 'text',
		'columnName' : 'CIN TYPE',
		'mappedCol' : 'cin_type'
	},{
		'type' : 'text',
		'columnName' : 'MCIN',
		'mappedCol' : 'mcin'
	},{
		'type' : 'text',
		'columnName' : 'CSN Submitted Type',
		'mappedCol' : 'csn_submitted_type'
	},{
		'type' : 'text',
		'columnName' : 'CSN Submitted by',
		'mappedCol' : 'csn_submitted_type'
	},{
		'type' : 'text',
		'columnName' : 'CSN Reporting Type',
		'mappedCol' : 'csn_reporting_type'
	},{
		'type' : 'text',
		'columnName' : 'CSN Site ID',
		'mappedCol' : 'csn_site_id'
	},{
		'type' : 'text',
		'columnName' : 'CSN Number',
		'mappedCol' : 'csn_number'
	},{
		'type' : 'text',
		'columnName' : 'CSN Date',
		'mappedCol' : 'csn_date'
	},{
		'type' : 'text',
		'columnName' : 'Previous MCIN',
		'mappedCol' : 'previous_mcin'
	},{							
		'type' : 'text',
		'columnName' : 'Split Indicator',
		'mappedCol' : 'split_indicator'
								
	},{
		'type' : 'text',
		'columnName' : 'Number of Packages',
		'mappedCol' : 'packageBLLevel'							
	},{
		'type' : 'text',
		'columnName' : 'Type of Package',
		'mappedCol' : 'type_of_package'
										
	},{
		'type' : 'text',
		'columnName' : 'First Port of Entry/Last Port of Departure',
		'mappedCol' : 'first_port_of_entry_last_port_of_departure'
	},{
		'type' : 'text',
		'columnName' : 'Type Of Cargo',
		'mappedCol' : 'type_of_cargo'
	},{
		'type' : 'text',
		'columnName' : 'Split Indicator List',
		'mappedCol' : 'split_indicator_list'
	},{											
		'type' : 'text',
		'columnName' : 'Port of Acceptance',
		'mappedCol' : 'port_of_acceptance'
	},{											
		'type' : 'text',
		'columnName' : 'Port of Receipt',
		'mappedCol' : 'port_of_receipt'
	},{																
	    'type' : 'text',
	    'columnName' : 'UCR Type',
	    'mappedCol' : 'ucr_type'
	},{																	
	    'type' : 'text',
	    'columnName' : 'UCR Code',
	     'mappedCol' : 'ucr_code'
	},{
		'type' : 'text',
		'columnName' : 'Consignee',
		'mappedCol' : 'consignee'
	},{
		'type' : 'text',
		'columnName' : 'Consigner',
		'mappedCol' : 'consigner'
	},{
		'type' : 'text',
		'columnName' : 'Notify Party',
		'mappedCol' : 'notifyParty'
	},{
		'type' : 'text',
		'columnName' : 'Notify Party Two',
		'mappedCol' : 'notifyPartyTwo'
	},{
		'type' : 'text',
		'columnName' : 'Marks_Number',
		'mappedCol' : 'marksNumber'
	},{
		'type' : 'text',
		'columnName' : 'Container Details',
		'mappedCol' : 'containerDetails'
	},{
		'type' : 'hidden',
		'columnName' : 'Is Present',
		'mappedCol' : 'isPresent'
	},{
		'type' : 'hidden',
		'columnName' : 'Custom ADD1',
		'mappedCol' : 'cusAdd1'
	},{
		'type' : 'hidden',
		'columnName' : 'Custom ADD2',
		'mappedCol' : 'cusAdd2'
	},{
		'type' : 'hidden',
		'columnName' : 'Custom ADD3',
		'mappedCol' : 'cusAdd3'
	},{
		'type' : 'hidden',
		'columnName' : 'Custom ADD4',
		'mappedCol' : 'cusAdd4'
	},{
		'type' : 'hidden',
		'columnName': 'BL Validate Flag',
        'mappedCol'  :  'isValidateBL'
	},{
		'type' : 'hidden',
		'columnName': 'Package BL Level',
	    'mappedCol'  : 'packageBLLevel'
	},{
		'type' : 'hidden',
		'columnName': 'Gross Cargo Weight BL level',
	    'mappedCol' : 'grossCargoWeightBLlevel'
	},{
		/* Hidden field bl  section */
		'type'      : 'hidden',
		'columnName': 'Port of Acceptance Name',
	    'mappedCol' : 'port_of_acceptance_name'
	},{
		'type'      : 'hidden',
		'columnName': 'Port of Receipt Name',
	    'mappedCol' : 'port_of_receipt_name'
	},{
		'type'      : 'hidden',
		'columnName': 'PAN of notified party',
	    'mappedCol' : 'pan_of_notified_party'
	},{
		'type'      : 'hidden',
		'columnName': 'Unit of weight',
	    'mappedCol' : 'unit_of_weight'
	},{
		'type'      : 'hidden',
		'columnName': 'Gross Volume',
	    'mappedCol' : 'gross_volume'
	},{
		'type'      : 'hidden',
		'columnName': 'Unit of Volume',
	    'mappedCol' : 'unit_of_volume'
	},{
		'type'      : 'hidden',
		'columnName': 'Cargo Item Sequence No', 
	    'mappedCol' : 'cargo_item_sequence_no'   
	},{
		'type'      : 'hidden',
		'columnName': 'Cargo Item Description',
	    'mappedCol' : 'cargo_item_description'
	},{
		'type'      : 'hidden',
		'columnName': 'UNO Code',
	    'mappedCol' : 'uno_code'
	},{
		'type'      : 'hidden',
		'columnName': 'IMDG Code',
	    'mappedCol' : 'imdg_code'
	},{
		'type'      : 'hidden',
		'columnName': 'Number of Packages Hidden',
	    'mappedCol' : 'number_of_packages'
	},{
		'type'      : 'hidden',
		'columnName': 'Type of Packages Hidden',
	    'mappedCol' : 'type_of_packages_hidden'
	},{
		'type'      : 'hidden',
		'columnName': 'Container Weight',
	    'mappedCol' : 'container_weight'
	},{
		'type'      : 'hidden',
		'columnName': 'Port of call sequence number',
	    'mappedCol' : 'port_of_call_sequence_number'
	},{
		'type'      : 'hidden',
		'columnName': 'Port of Call Coded',
	    'mappedCol' : 'port_of_call_coded'
	},{
		'type'      : 'hidden',
		'columnName': 'Next port of call coded',
	    'mappedCol' : 'next_port_of_call_coded'
	},{
		'type'      : 'hidden',
		'columnName': 'MC Location Customs',
	    'mappedCol' : 'mc_location_customs'
	}]
	
	
	/* function for date and calendar */
	var counter = 1;
	$(function() {
		$("#blCreationDateFrom").datepicker();
		$("#blCreationDateTo").datepicker();
		$("#igmDate").datepicker();
		$("#cIgm").datepicker();
		$("#smtp").datepicker();
		$("#rotnDate").datepicker();
		$("#igmDateApplyIgmDetails").datepicker();
		$("#arrivalDate").datepicker();
		$("#jobDate").datepicker();
		$("#mBlDate").datepicker();
		$("#blNoDate").datepicker();
		$("#igmDateApplyIgmDetails").datepicker();
		$("#igmDate").datepicker("option", "dateFormat", "dd-mm-yy");
		$("#blCreationDateFrom").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#blCreationDateTo").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#igmDateApplyIgmDetails").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#rotnDate").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#arrivalDate").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#jobDate").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#mBlDate").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#cIgm").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#smtp").datepicker("option", "dateFormat", "dd/mm/yy");
	});
	
	function dateToCommon(id) {
		//	alert(id);
			debugger;
			$("#"+id).datepicker();
			$("#"+id).datepicker("option", "dateFormat", "DD/MM/YYYY"); 
			$("#"+id).datepicker("show");
			
		}
	
	function dateTo() {
		$("#blCreationDateTo").datepicker();
		$("#blCreationDateTo").datepicker("option", "dateFormat", "dd/mm/yy"); 
		$("#blCreationDateTo").datepicker("show");
		
	}
	function dateFrom() {
		$("#blCreationDateFrom").datepicker();
		$("#blCreationDateFrom").datepicker("option", "dateFormat", "dd/mm/yy"); 
		
		$("#blCreationDateFrom").datepicker("show");
		//blCreationDateToFunction();
	}
	/* Button Function For Rest the Page */
	function onResetForm() {
		document.getElementById("inStatus").value = "";
		document.getElementById("pol").value = "";
		document.getElementById("polTerminal").value = "";
		document.getElementById("blCreationDateTo").value = "";
		document.getElementById("blCreationDateFrom").value = "";
		document.getElementById("igmservice").value = "";
		document.getElementById("vessel").value = "";
		document.getElementById("voyage").value = "";
		document.getElementById("podTerminal").value = "";
		document.getElementById("pod").value = "";
	}
	function mergeFiles() {
		var mgsnull=document.getElementById("msg");
		mgsnull.innerHTML = '';
		meregefile = true;
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else
			popupWindow = window
			.open(
					"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManifestMergeFiles.jsp",
					"", "width=360,height=210, top=100, left=500,");
		popupWindow.focus();
	}
	function consigneeInfo(selectObject) {
		var mgsnull=document.getElementById("msg");
		mgsnull.innerHTML = '';
		var popupWindow=""
		consigneeInfor = true;
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else{
			popupWindow=null;
			popupWindow = window
			.open(
					"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManifestConsignee.jsp",
					"_blank", "width=1050,height=350, top=100, left=160,");
		}
		if (window.focus) {popupWindow.focus()}
		
		setTimeout(consigneeMethod, 500, popupWindow, selectObject);
	}
	function ConsignerInfo(selectObject) {
		var mgsnull=document.getElementById("msg");
		mgsnull.innerHTML = '';
		var popupWindow=""
			consignerInfo = true;
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else{
			popupWindow=null;
			popupWindow = window
			.open(
					"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManefestConsigner.jsp",
					"_blank", "width=1050,height=350, top=100, left=160,");
		}
		if (window.focus) {popupWindow.focus()}
		
		setTimeout(consignerMethod, 500, popupWindow, selectObject);
	}
	function notifyInfo(selectObject) {
		var mgsnull = document.getElementById("msg");
		mgsnull.innerHTML = '';
		notifyInfor = true;
		var popupWindow = ""
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else {
			popupWindow = null;
			popupWindow = window
					.open(
							"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManifestNotifyParty.jsp",
					        "_blank", "width=1050,height=350, top=100, left=160,");
		}
		if (window.focus) {popupWindow.focus()}
		
		setTimeout(notifyPartyme, 500, popupWindow, selectObject);
	}
	function marksInfo(selectObject) {
		var mgsnull=document.getElementById("msg");
		mgsnull.innerHTML = '';
		var popupWindow="";
		marksInfor = true;
		if(popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else
		{
			popupWindow=null;
			popupWindow = window
			.open(
					"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManifestMarksNumber.jsp",
					"_blank", "width=800,height=400, top=100, left=300,");
		}
		if (window.focus) {popupWindow.focus()}
		setTimeout(markNumberme, 500, popupWindow, selectObject);
	}
	function containerDetailsInfo(selectObject) {
		var mgsnull=document.getElementById("msg");
		mgsnull.innerHTML = '';
		var popupWindow="";
		containerDetailsInfor = true;
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
		else
		{
			popupWindow=null;
			popupWindow = window
			.open(
					"<%=lstrCtxPath1%>/pages/igm/ImportGeneralManifestContainerDetails.jsp",
					"_blank", "width=1350,height=350, top=200, left=0,");
		}
	if (window.focus) {popupWindow.focus()} 
	setTimeout(containerme, 500, popupWindow, selectObject);
	}
		
</script>

<script>
 
  
$(document).ready(function() {
   
	$( "#dialog-form" ).dialog({
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    maxWidth:1200,
        maxHeight: 800,
        width: 1500,
        height: 625,
     
	    buttons: {
	     
	      Cancel: function() {
	    	  $("#dialog-form").dialog("close");
	      },
	     Save: function() {	    	  
	    	 angular.element(document.getElementById('dialog-tabs')).scope().save();
	    	 
	      }
	    },
	    close: function() {
	     //alert("close");
	    }
	  });

	$("#ackFile").onchange = function(evt){
		alert("ack upload");
		
		  var files = fileEl.files;
		  var file = files[0];
		  var reader = new FileReader();

		  reader.onloadend = function(evt) {
		    if (evt.target.readyState === FileReader.DONE) {
		      $scope.$apply(function () {
		        $scope.something = JSON.parse(evt.target.result);
		      });
		    }
		  };

		  reader.readAsText(file);
		}
	
	$( "#dialog-form-port" ).dialog({
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    maxWidth:500,
        maxHeight: 400,
        width: 500,
        height: 400,
	    buttons: {
	      Cancel: function() {
	    	  $("#dialog-form-port").dialog("close");
	      } 
	    },
	    close: function() {
	     //alert("close");
	    }
	  });

	$( "#saveDataModal" ).dialog({
	    autoOpen: false,
	    modal: true,
	    resizable: true,
	    maxWidth:500,
        maxHeight: 400,
        width: 500,
        height: 400,
	    buttons: {
	      Cancel: function() {
	    	  $("#saveDataModal").dialog("close");
	      } 
	    },
	    close: function() {
	     //alert("close");
	    }
	  });
   
	});
	
var jsonData = {};
function showDialg(index){
	$("#dialog-form").dialog("open");
}

function showDialgPort(index){
	debugger;
	$("#dialog-form-port").dialog("open");
}
function showDialgSaveLoadingDtls(index){
	debugger;
	$("#saveDataModal").dialog("open");
} 
  
		$(function () {
		    $("#dialog-tabs").tabs();
		    $("#dialog-tabs").tabs({ active: 0 });
		    $("#blDetailsTabs").tabs();
		});
		
		
		

		function genrateFile() {
			debugger;
			 delete jsonData.result[0].service.$$hashKey; 
			delete prsnOnBordTable.$$hashKey;

			if ($("#fileType").val() == "SAA" || $("#fileType").val() == "SEI"
					|| $("#fileType").val() == "SDN"
					|| $("#fileType").val() == "SEI"
					|| $("#fileType").val() == "SCC"
					|| $("#fileType").val() == "SCD"
					|| $("#fileType").val() == "SCU"
					|| $("#fileType").val() == "SCA") {
				swal("Message", $("#fileType").val() + " under development.",
						"info");
				return false;
			}

			var blNoSaved = "";
			var blNoUnSaved = "";
			for (var x = 0; x < jsonData.result[0]["BLS"].length; x++) {
				if (jsonData.result[0]["BLS"][x].isBlSave == true
						&& jsonData.result[0]["BLS"][x].itemNumber != ""
						&& jsonData.result[0]["BLS"][x].itemNumber != null) {
					if (jsonData.result[0]["BLS"][x].fetch == false) {
						if (blNoSaved == "") {
							blNoSaved = blNoSaved + "'"
									+ jsonData.result[0]["BLS"][x].bl + "'";
						} else {
							blNoSaved = blNoSaved + ",'"
									+ jsonData.result[0]["BLS"][x].bl + "'";
						}
					}
				} else {
					if (jsonData.result[0]["BLS"][x].fetch == false) {
						if (blNoUnSaved == "") {
							blNoUnSaved = blNoUnSaved + "'"
									+ jsonData.result[0]["BLS"][x].bl + "'";
						} else {
							blNoUnSaved = blNoUnSaved + ",'"
									+ jsonData.result[0]["BLS"][x].bl + "'";
						}
					}
				}
			}

			$("body").append('<div class="loading"></div>');

	/* 		var requestParam = JSON.stringify(jsonData.result[0]);
			$("#requestParam").val(requestParam);
			console.log(requestParam, "requestParam");
			
			$("#crewEfctMod").val(JSON.stringify(crewEffetTable));
			$("#shipStore").val(JSON.stringify(shipStoreTable));
			$("#crewEfctMod").val(JSON.stringify(crewEffetTable));
			var fileType = ("#fileType").val();
			$("#fileType").val(fileType);
	 */
			$.ajax({
				method : "POST",
				async : true,
				url : DOWNLOAD_DATA,
				data : {
					fileType : $("#fileType").val(),
					requestParam :(JSON.stringify(jsonData.result[0])),
					personOnBoardMod : (JSON.stringify(jsonData.result[0].service.personOnBoardMod)),
					crewEfctMod  :  (JSON.stringify(jsonData.result[0].service.crewEfctMods)),
					shipStoresMod:  (JSON.stringify(jsonData.result[0].service.igmShipStoresMods)),
					unSavedBlList:  blNoUnSaved,
					savedBlList  :  blNoSaved
				},
				success : function(result) {
					debugger;
					$("body").find('.loading').remove();
					debugger;
					var mgsnull=document.getElementById("msg");
					mgsnull.innerHTML = '';
					swal("Message","Manifest File generated successfully.","info");
					var StringResult=JSON.parse(result);
					var downloadfilename="";
					var one="";
					var two="";
					var three="";
					var fore="";
					var five="";
					if(fileNme=='SAM'){one='F'}else if(fileNme=='SAA'){one='A'}else{one='F'}
					fileNme = $("#fileType").val();
						
					if($("#fileType").val()=="SCE" || $("#fileType").val()=="SEX"){
						two=StringResult.jsonFile['headerField']['senderID'];
						/* three=StringResult.jsonFile['master']['decRef'][0]['jobNo'];
						fore=StringResult.jsonFile['master']['decRef']['jobDt']; */
					}else{
						two=StringResult.jsonFile['headerField']['senderID'];
						/* three=StringResult.jsonFile['master']['decRef']['jobNo'];
						fore=StringResult.jsonFile['master']['decRef']['jobDt']; */
					}
					downloadfilename=one+'_'+'SACHM23'+'_'+fileNme+'_'+two+'_'+three+'_'+fore+'_'+'DEC'+'.json';
					
					var sampleBytes = new String(JSON.stringify(StringResult.jsonFile,null,"\t"));

					var saveByteArray = (function() {
						var a = document.createElement("a");
						document.body.appendChild(a);
						a.style = "display: none";
						
						return function(data, name) {
							if (navigator.msSaveBlob) {
								blobObject = new Blob(data);
								window.navigator.msSaveOrOpenBlob(blobObject,
										downloadfilename);
							} else {
								var blob = new Blob(data, {
									type : "octet/stream"
								}), url = window.URL.createObjectURL(blob);
								a.href = url;
								a.download = name;
								a.click();
								window.URL.revokeObjectURL(url);
							}
						};
					}());
					saveByteArray([ sampleBytes ], downloadfilename);
				},
			});

		}

</script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>

<script>
var selectedIndex=0;
var jsonData={};
var prsnOnBordTable ={};
var crewEffetTable ={};
var shipStoreTable ={};
var result1={}
var app = angular.module('myApp', []);
var totalIteamNo=0;
app.controller('findResult', function($scope,$window,$rootScope,$http) {
	
	$scope.payload="";
	$scope.result=[];
	
	$scope.dropDown="";
	
	$scope.showPop=function showPop(index){
		$window.selectedIndex =index;
		
		debugger;
		$("#dialog-tabs").tabs({ active: 0 });
		 angular.element(document.getElementById('dialog-tabs')).scope().send();
		 
	}
	$scope.findDataNew = function (){
		debugger;
		/* checking searchSection Validation Started */
		var service1 = document.getElementById("igmservice").value;
		var vessel1 = document.getElementById("vessel").value;
		var voyage1 = document.getElementById("voyage").value;
		var pod1 = document.getElementById("pod").value;
		var pol = document.getElementById("pol").value;
		var mgsnull=document.getElementById("msg");
		$('#0rowSelectedForRadioSet').attr('checked', false);	
		mgsnull.innerHTML = '';
		if (vessel1 == "") {
			swal("Message","Vessel is required.","info");
			return false;
		} else if (voyage1 == "") {
			swal("Message","Voyage is required.","info");
			return false;
		} else if (pol == "") {
			swal("Message","POL is required","info");
			return false;
		}
		/* checking searchSection Validation Ened */
		else {
			 
			/* Restore the vessel/voyage section and BL section 
			 * refresh all the edited field in igm screen  */
			//document.getElementById("selectall").checked = false;
			//document.getElementById("selectall").disabled = true;
			 
			//document.getElementById("checkIgmInfo").disabled = true;
			//document.getElementById("checkBLInfo").disabled = true;
			//document.getElementById("checkIgmInfo").checked = false;
			//document.getElementById("checkBLInfo").checked  = false;
			checkSubmit = false;
			$("#customCode").val('');
			$("#callSign").val('');
			$("#imoCode").val('');
			$("#agentCode").val('');
			$("#lineCode").val('');
			$("#portOrigin").val('');
			$("#prt1").val('');
			$("#File1").val('');
			$("#prt2").val('');
			$("#prt3").val('');
			$("#portOfArrival").val('');
			$("#nov").val('');
			$("#mn").val('');
			$("#igmNo").val('');
			$("#aDate").val('');
			$("#aTime").val('');
			$("#ataAd").val('');
			$("#ataAt").val('');
			$("#totalItem").val('');
			$("#lhd").val('');
			$("#gwv").val('');
			$("#nwv").val('');
			$("#serialnumber").val('');
			$("#blSearchNo").val('');
			$("#fileUpload").val('');
			 
			  
			 
			document.getElementById("submitype").disabled = true;
			document.getElementById("generatetype").disabled = true;
			document.getElementById("manifestfilegeneratoredifile").disabled = true;
			checkHandlerArray=[];
			submitvalidationaftersave=true;
			 
			var blDetails = [];
			newResult = [];
			saveddataForcheckHandler=[];
			/*Ended .....*/
			/*for generate file*/
			 generatFalg="";
		     fileNme="";
			/* Get the data from search section */
			var inStatus = document.getElementById("inStatus").value;
			var blCreationDateFrom = document.getElementById("blCreationDateFrom").value;
			var blCreationDateTo = document.getElementById("blCreationDateTo").value;
			var del = document.getElementById("del").value;
			var igmservice = document.getElementById("igmservice").value;
			var vessel = document.getElementById("vessel").value;
			var voyage = document.getElementById("voyage").value;
			var direction = document.getElementById("direction").value;
			var pol = document.getElementById("pol").value;
			var depot = document.getElementById("depo").value;
			var polTerminal = document.getElementById("polTerminal").value;
			$scope.polTerminal = document.getElementById("polTerminal").value;
			var pod = document.getElementById("pod").value;
			var podTerminal = document.getElementById("podTerminal").value;
			
			/**disable all button search section*/
			disableButton();
			/*send data to backend*/
			$scope.payload="inStatus=" + inStatus+
			"&blCreationDateFrom=" +  blCreationDateFrom+
			"&blCreationDateTo=" +  blCreationDateTo+
			"&del=" + del+
			"&igmservic =" +  igmservice+
			"&vessel=" +  vessel+
			"&voyage=" +  voyage+
			"&direction=" +  direction+
			"&pol=" +  pol+
			"&polTerminal=" +  polTerminal+
			"&pod=" +  pod+
			"&podTerminal=" +  podTerminal+
			"&depot=" + depot;		
			$( "body" ).append('<div class="loading"></div>');
			$http({
				method : "POST",
				async : true,
				url : $window.ONFINDEXPORT,
				dataType: 'json',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},				
				data : $scope.payload
				 
			}).then(function(data, status, headers, config) {			 
				$("body").find('.loading').remove();
				/**enable all button search section*/
				EnableButton();
				$("body").find('.loading').remove();
				/* Get the Data From backend 
				 * And convert the string to json*/
				 $window.result1 = data.data;
				 $scope.dropDown = data.data;
				/* if combination is wrong */
				if (result1.result.length == 0) {
					var mgsnull=document.getElementById("msg");
					mgsnull.innerHTML = '';
					swal("Message","No data found for this combination","error");
				} else {
					
					document.getElementById("vessel&voyage1st").style.display = "block";
					$scope.getFindResult();
					return ;
				}
			}, function(response){
	            // else\\\\
	            debugger;
				$("body").find('.loading').remove();
            	EnableButton();
            	var mgsnull=document.getElementById("msg");
				mgsnull.innerHTML = '';
				swal("Message","error : "+error.responseText,"error");
			 });
		}
	}
	
	$scope.getFindResult = function (){
		if($window.result1 && $window.result1.result){
			$scope.result= $window.result1.result;
		}
	}
});
app.controller('myCtrl', function($scope,$window,$rootScope,$http) {
	debugger;
	$scope.consignee={"xyx":1};
	$scope.consigner={"xyx":1};
	$scope.notifyParty={"xyx":1};
	$scope.notifyPartyTwo={"xyx":1};
	$scope.blIndex=0;
	$scope.vesselVoyageIndex=0;
	$scope.selectedBL={};
	$scope.selectedContainer={};
	$scope.selectedServcies={vesselType:""};
	$scope.prsnOnBordTable=[];
	$scope.crewEffetTable=[];
	$scope.shipStoreTable=[];
    $scope.SealTypeObj = ["BTSL", "ESEAL","OTHER"];
    $scope.ISOCodeObj=["2000","4200","4000"]
	$scope.pageType='EGM';
	$scope.socFlagObj=["N","Y"];
	$scope.containerStatusArray=["FCL","LCL","EMP"];
	$scope.unitArray=["KGS","MTS","MBT"];
	$scope.cargoNatureArray=["C","CP","DB","LB","P"];
	$scope.cargoMovmentArray=["LC","TI","TC","DT","FT"];
	$scope.currencyArray=["INR","USD"];
	$scope.modeOfTpFeeArray=["P","C","R"];
	$scope.previousDeclarationArray=["S","Y","N","C"];
	$scope.consolIdatedIndicatorArray = ["S", "C" , "H" , "R"];
	$scope.tpBondNoArr=[];
	$scope.msg = "";
	$scope.selectAllFetch = false;
	$rootScope.pupUpName = '' ;
	$rootScope.curPage = 0;
	$rootScope.pageSize = 10;
	$rootScope.portLookUpJson=[];
	$scope.TPVALIDATION = true; 
	$scope.isBlSelecteSave = 'false';
	$scope.init = function () {
	  // alert(2)
	}
	
	$scope.roadCarrCodeHandle=function()
	{ 
		debugger;
		if($scope.selectedBL.roadCarrCodeVVS==""){
			$scope.TPVALIDATION = true;
			$scope.tpBondNoArr = [{"descriptiondrw": "Select one",
				"partnerValuedre": "",
					"podValue": "","partnerviews":"Select one"
			}];
			for(var i=0; i<$scope.BLS.length;i++){
				$scope.BLS[i].carrierNo = $scope.selectedBL.roadCarrCodeVVS;
			}
			return;
		}
		$scope.tpBondNoArr = $window.jsonData.DropDown.TPBondNoDropdown[$scope.selectedBL.roadCarrCodeVVS];
		$scope.TPVALIDATION = false;
		
		for(var i=0; i<$scope.BLS.length;i++){
			$scope.BLS[i].carrierNo = $scope.selectedBL.roadCarrCodeVVS;
		}
	}
	
	$scope.roadTpBondNoHandler=function(selectObject)
	{
		debugger;
		for(var i=0; i<$scope.BLS.length;i++){
			$scope.BLS[i].tpBondNo = $scope.selectedBL.tpbondnoVVS;
		}
	}
	
	$scope.changefromItemNo= function(){
		//alert(2)
		$scope.selectedServcies.toItemNo=0;
		if($scope.selectedServcies.fromItemNo){
			$scope.selectedServcies.toItemNo=parseInt($scope.selectedServcies.fromItemNo)+ $window.jsonData.result[0].BLS.length;
			return parseInt($scope.selectedServcies.fromItemNo)+ $window.jsonData.result[0].BLS.length;
		}
		console.log($scope.selectedServcies.fromItemNo)
	}
	
	$scope.igmDateUpdate=function()
	{ 
		debugger;
		var igmVal=$scope.selectedServcies.igmNumber;
		if(igmVal==null || igmVal==''){
			$scope.selectedServcies.igmYearVal="";
			$scope.selectedServcies.igmDateVal="";
		}else{		
			var d = new Date();
			$scope.selectedServcies.igmYearVal=d.getFullYear();
			$scope.selectedServcies.igmDateVal=d.getDate()+'-'+d.getMonth()+"-"+d.getFullYear();
		}
		
	}
	
	$scope.applySave=function() {
		
		if($scope.selectedServcies.igmNumber!=null && $scope.selectedServcies.igmNumber!='' && $scope.selectedServcies.igmDate !=null && $scope.selectedServcies.igmDate!='')
		{
			var dateResult = $scope.selectedServcies.igmDate.split("/");
			$scope.selectedServcies.igmYearVal=dateResult[2];
			$scope.selectedServcies.igmDateVal=dateResult[0]+'/'+ dateResult[1] +"/"+dateResult[2];
			$("#blNoDate").datepicker()=$scope.selectedServcies.igmDateVal;
		}else{
			$scope.selectedServcies.igmYearVal="";
			$scope.selectedServcies.igmDateVal="";
		}
	}
	
	$scope.weightBtn=function() {
		var Numberofbox = $scope.selectedBL.containerDetailes.length;
		var Grossweight = $scope.selectedBL.grossCargoWeightBLlevel;
		var avarageWeight = (Number(Grossweight) / Number(Numberofbox));
		for(var i=0;i<$scope.selectedBL.containerDetailes.length;i++){
			$scope.selectedBL.containerDetailes[i].container_weight	=avarageWeight;
		}
	}
	
	
	$scope.packages = function() {
		var reminderVlaue = 0;
		var lastValue = 0;
		var packageBL =    $scope.selectedBL.packageBLLevel;                     
		var Numberofbox =  $scope.selectedBL.containerDetailes.length;                   
		var avarageValue = packageBL / Numberofbox;
		var getValue = getIntValue(avarageValue);
		if ((packageBL - ((Number(getValue)) * (Number(Numberofbox)))) != 0) {
			reminderVlaue = (packageBL - ((Number(getValue)) * (Number(Numberofbox))));
			lastValue = Number(getValue) + reminderVlaue;
		} else {
			lastValue = getValue;
		}
		for (var i = 0; i < $scope.selectedBL.containerDetailes.length; i++) {
			if (i == ($scope.selectedBL.containerDetailes.length - 1)) {
				$scope.selectedBL.containerDetailes[i].totalNumberOfPackagesInContainer = lastValue;
			} else {
				$scope.selectedBL.containerDetailes[i].totalNumberOfPackagesInContainer = getValue;
			}
		}
	}
	$scope.defaultcontainerDtl = function() {
		debugger;
		var listOfcontainerDetailsInformation = $scope.selectedBL.containerDetailes
		var result1 = window.opener.result1;
	
		var cantainerDtlpath = $scope.selectedBL.containerDetailes;
		
		for (i = 0; i < $scope.selectedBL.containerDetailes.length; i++) {
			$scope.selectedBL.containerDetailes[i].containerNumber = cantainerDtlpath[i].containerNumber;
			$scope.selectedBL.containerDetailes[i].containerSealNumber = cantainerDtlpath[i].containerSealNumber;
			$scope.selectedBL.containerDetailes[i].containerAgentCode = cantainerDtlpath[i].containerAgentCode;
			$scope.selectedBL.containerDetailes[i].status = cantainerDtlpath[i].status;
			$scope.selectedBL.containerDetailes[i].totalNumberOfPackagesInContainer = cantainerDtlpath[i].totalNumberOfPackagesInContainer;
			$scope.selectedBL.containerDetailes[i].containerWeight = cantainerDtlpath[i].containerWeight;
            if(cantainerDtlpath[i].equipmentLoadStatus==""){
            	$scope.selectedBL.containerDetailes[i].equipmentLoadStatus ="FCL";
                }else{
                	$scope.selectedBL.containerDetailes[i].equipmentLoadStatus = cantainerDtlpath[i].equipmentLoadStatus;       
                    }
            if(cantainerDtlpath[i].soc_flag==""){
            	$scope.selectedBL.containerDetailes[i].soc_flag ="N" ;
                }else{
                	$scope.selectedBL.containerDetailes[i].soc_flag = cantainerDtlpath[i].soc_flag;    
                    }
            if(cantainerDtlpath[i].equipment_seal_type==""){
            	$scope.selectedBL.containerDetailes[i].equipment_seal_type ="BTSL";
            }else{
             	$scope.selectedBL.containerDetailes[i].equipment_seal_type = cantainerDtlpath[i].equipment_seal_type;       
            }
			
	        var ConSize=cantainerDtlpath[i].containerSize;
            var ConType=cantainerDtlpath[i].containerType;
            if(ConSize==""){
				var isoCode="";}
			else if(ConSize=="20"){
				var isoCode= 2000;}
			else if(ConSize="40"){
				if(ConType=="HC"){
					var isoCode= 4200;}
				}
			else{
				var isoCode=4000;}
			
            $scope.selectedBL.containerDetailes[i].isoCode = isoCode;
		}
		
	}
	
	$scope.getDataMoveToNextTab = function () {
		   debugger;
		   $scope.getConsinee();
		   /* 	var selectedIndex = obj.blIndex; */
		   $("body").append('<div class="loading"></div>');

		   if ( ($scope.consignee.consigneeName != "BANK" || $scope.consignee.consigneeName != "TO THE ORDER OF" ||
		      $scope.consignee.consigneeName != "TO ORDER" || $scope.consignee.consigneeName != "TO ORDER OF")&&
		       $scope.notifyParty.notifyName == "SAME AS CONSIGNEE" ) {
		      $scope.BLS[$scope.blIndex].notifyParty[0].costumerCode = $scope.BLS[$scope.blIndex].consignee[0].customerCode;
		      $scope.BLS[$scope.blIndex].notifyParty[0].costumerName = $scope.BLS[$scope.blIndex].consignee[0].customerName;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine1 = $scope.BLS[$scope.blIndex].consignee[0].addressLine1;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine2 = $scope.BLS[$scope.blIndex].consignee[0].addressLine2;

		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine3 = $scope.BLS[$scope.blIndex].consignee[0].addressLine3;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine4 = $scope.BLS[$scope.blIndex].consignee[0].addressLine4;
		      $scope.BLS[$scope.blIndex].notifyParty[0].city = $scope.BLS[$scope.blIndex].consignee[0].city;
		      $scope.BLS[$scope.blIndex].notifyParty[0].state = $scope.BLS[$scope.blIndex].consignee[0].state;

		      $scope.BLS[$scope.blIndex].notifyParty[0].countryCode = $scope.BLS[$scope.blIndex].consignee[0].countryCode;
		      $scope.BLS[$scope.blIndex].notifyParty[0].zip = $scope.BLS[$scope.blIndex].consignee[0].zip;

		      $scope.notifyIec = $scope.consignee.consigneIec;
		      $scope.notifyPan = $scope.consignee.consignePan;
		      
		      swal("Message", "Consignee Data copy to Notify Party.", "info");
		      $("body").find('.loading').remove();

		     
		   } else if (($scope.consignee.consigneeName != "BANK" || $scope.consignee.consigneeName != "TO THE ORDER OF" ||
		      $scope.consignee.consigneeName != "TO ORDER" || $scope.consignee.consigneeName != "TO ORDER OF" )&&
		      $scope.notifyParty.forwader == "FWR") {

		      $scope.consigneIec = $scope.consignee.consigneIec;
		      $scope.notifyPan = $scope.notifyParty.notifyPan;


		   } else if (($scope.notifyParty.countryCode != "IN" && $scope.consignee.portOfDischarge.substring(0, 2) == "IN")&&
		     ( $scope.notifyParty.notifyName != "SAME AS CONSIGNEE" || $scope.notifyParty.forwader != "FWR")) {

		      $scope.BLS[$scope.blIndex].notifyParty[0].costumerCode = $scope.BLS[$scope.blIndex].consignee[0].customerCode;
		      $scope.BLS[$scope.blIndex].notifyParty[0].costumerName = $scope.BLS[$scope.blIndex].consignee[0].customerName;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine1 = $scope.BLS[$scope.blIndex].consignee[0].addressLine1;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine2 = $scope.BLS[$scope.blIndex].consignee[0].addressLine2;

		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine3 = $scope.BLS[$scope.blIndex].consignee[0].addressLine3;
		      $scope.BLS[$scope.blIndex].notifyParty[0].addressLine4 = $scope.BLS[$scope.blIndex].consignee[0].addressLine4;
		      $scope.BLS[$scope.blIndex].notifyParty[0].city = $scope.BLS[$scope.blIndex].consignee[0].city;
		      $scope.BLS[$scope.blIndex].notifyParty[0].state = $scope.BLS[$scope.blIndex].consignee[0].state;

		      $scope.BLS[$scope.blIndex].notifyParty[0].countryCode = $scope.BLS[$scope.blIndex].consignee[0].countryCode;
		      $scope.BLS[$scope.blIndex].notifyParty[0].zip = $scope.BLS[$scope.blIndex].consignee[0].zip;

		      $scope.notifyIec = $scope.consignee.consigneIec;
		      $scope.notifyPan = $scope.consignee.consignePan;
		      swal("Message", "Consignee Data copy to Notify Party.", "info");
		      $("body").find('.loading').remove();

		   } else if (($scope.consignee.consigneeName == "BANK" || $scope.consignee.consigneeName == "TO THE ORDER OF" ||
		      $scope.consignee.consigneeName == "TO ORDER" || $scope.consignee.consigneeName == "TO ORDER OF" )&&
		      ($scope.notifyParty.notifyName != "SAME AS CONSIGNEE" || $scope.notifyParty.forwader != "FWR")) {
		      $scope.BLS[$scope.blIndex].consignee[0].customerCode = $scope.BLS[$scope.blIndex].notifyParty[0].costumerCode;
		      $scope.BLS[$scope.blIndex].consignee[0].costumerName = $scope.BLS[$scope.blIndex].notifyParty[0].costumerName;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine1 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine1;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine2 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine2;

		      $scope.BLS[$scope.blIndex].consignee[0].addressLine3 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine3;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine4 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine4;
		      $scope.BLS[$scope.blIndex].consignee[0].city = $scope.BLS[$scope.blIndex].notifyParty[0].city;
		      $scope.BLS[$scope.blIndex].consignee[0].state = $scope.BLS[$scope.blIndex].notifyParty[0].state;

		      $scope.BLS[$scope.blIndex].consignee[0].countryCode = $scope.BLS[$scope.blIndex].notifyParty[0].countryCode;
		      $scope.BLS[$scope.blIndex].consignee[0].zip = $scope.BLS[$scope.blIndex].notifyParty[0].zip;

		      $scope.consigneIec = $scope.notifyParty.notifyIec;
		      $scope.consignePan = $scope.notifyParty.notifyPan;


		      swal("Message", "Notify Party Data copy to Consignee.", "info");
		      $("body").find('.loading').remove();
		   } else if (($scope.consignee.consigneeName != "BANK" || $scope.consignee.consigneeName != "TO THE ORDER OF" ||
		      $scope.consignee.consigneeName != "TO ORDER" || $scope.consignee.consigneeName != "TO ORDER OF" )&&
		      $scope.notifyParty.countryCode != "IN" && $scope.consignee.portOfDischarge.substring(0, 2) == "IN") {
		      $scope.BLS[$scope.blIndex].consignee[0].customerCode = $scope.BLS[$scope.blIndex].notifyParty[0].costumerCode;
		      $scope.BLS[$scope.blIndex].consignee[0].costumerName = $scope.BLS[$scope.blIndex].notifyParty[0].costumerName;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine1 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine1;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine2 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine2;

		      $scope.BLS[$scope.blIndex].consignee[0].addressLine3 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine3;
		      $scope.BLS[$scope.blIndex].consignee[0].addressLine4 = $scope.BLS[$scope.blIndex].notifyParty[0].addressLine4;
		      $scope.BLS[$scope.blIndex].consignee[0].city = $scope.BLS[$scope.blIndex].notifyParty[0].city;
		      $scope.BLS[$scope.blIndex].consignee[0].state = $scope.BLS[$scope.blIndex].notifyParty[0].state;

		      $scope.BLS[$scope.blIndex].consignee[0].countryCode = $scope.BLS[$scope.blIndex].notifyParty[0].countryCode;
		      $scope.BLS[$scope.blIndex].consignee[0].zip = $scope.BLS[$scope.blIndex].notifyParty[0].zip;

		      $scope.consigneIec = $scope.notifyParty.notifyIec;
		      $scope.consignePan = $scope.notifyParty.notifyPan;
		      swal("Message", "Notify Party Data copy to Consignee.", "info");
		      $("body").find('.loading').remove();


		   } else if ($scope.consignee.consigneFwr == "FWR") {
		      $scope.consignePan = $scope.consignee.consignePan;
		      $("body").find('.loading').remove();
		   } else if ($scope.notifyParty.notifyFwr == "FWR") {
		      $scope.notifyPan = $scope.notifyParty.notifyPan;
		      $("body").find('.loading').remove();
		   } else {
			   $scope.consigneIec = $scope.consignee.consigneIec;
			   $scope.consignePan = $scope.consignee.consignePan;
			   $scope.notifyIec = $scope.notifyParty.notifyIec;
			   $scope.notifyPan = $scope.notifyParty.notifyPan;
			   $("body").find('.loading').remove();
			   
			   
		   }
		}
	$scope.movingDataToConsignee=function(){
		debugger;
		var selectedIndex = obj.blIndex;
		if($scope.notifyParty.consigneCheckBox == "N"){
		if($scope.consignee.consigneeName == "BANK" || $scope.consignee.consigneeName == "TO THE ORDER OF" || 
			$scope.consignee.consigneeName == "TO ORDER" || $scope.consignee.consigneeName == "TO ORDER OF" ||
			$scope.notifyParty.countryCode != "IN"&& $scope.consignee.portOfDischarge.substring(0,2) == "IN"){
			obj.BLS[selectedIndex].consignee[0].customerCode=obj.BLS[selectedIndex].notifyParty[0].costumerCode;
			obj.BLS[selectedIndex].consignee[0].costumerName=obj.BLS[selectedIndex].notifyParty[0].costumerName;
			obj.BLS[selectedIndex].consignee[0].addressLine1=obj.BLS[selectedIndex].notifyParty[0].addressLine1;
			obj.BLS[selectedIndex].consignee[0].addressLine2=obj.BLS[selectedIndex].notifyParty[0].addressLine2;
			
			obj.BLS[selectedIndex].consignee[0].addressLine3=obj.BLS[selectedIndex].notifyParty[0].addressLine3;
			obj.BLS[selectedIndex].consignee[0].addressLine4=obj.BLS[selectedIndex].notifyParty[0].addressLine4;
			obj.BLS[selectedIndex].consignee[0].city=obj.BLS[selectedIndex].notifyParty[0].city;
			obj.BLS[selectedIndex].consignee[0].state=obj.BLS[selectedIndex].notifyParty[0].state;
			
			obj.BLS[selectedIndex].consignee[0].countryCode=obj.BLS[selectedIndex].notifyParty[0].countryCode;
			obj.BLS[selectedIndex].consignee[0].zip=obj.BLS[selectedIndex].notifyParty[0].zip;

			$scope.notifyIec  = $scope.notifyParty.notifyIec;
			$scope.notifyPan  =  $scope.notifyParty.notifyPan;

		}
		}else{
			$scope.notifyIec  = $scope.notifyParty.notifyIec;
			$scope.notifyPan  =  $scope.notifyParty.notifyPan;
	
			}
		
	}
	
	$scope.movingDataToNotify=function(){
		debugger;
		var selectedIndex = obj.blIndex;
		if($scope.consignee.consigneFwr=="FWR" && $scope.consignee.consigneeName == "SAME AS CONSIGNEE"){
			obj.BLS[selectedIndex].notifyParty[0].costumerCode=obj.BLS[selectedIndex].consignee[0].customerCode;
			obj.BLS[selectedIndex].notifyParty[0].costumerName=obj.BLS[selectedIndex].consignee[0].customerName;
			obj.BLS[selectedIndex].notifyParty[0].addressLine1=obj.BLS[selectedIndex].consignee[0].addressLine1;
			obj.BLS[selectedIndex].notifyParty[0].addressLine2=obj.BLS[selectedIndex].consignee[0].addressLine2;
			
			obj.BLS[selectedIndex].notifyParty[0].addressLine3=obj.BLS[selectedIndex].consignee[0].addressLine3;
			obj.BLS[selectedIndex].notifyParty[0].addressLine4=obj.BLS[selectedIndex].consignee[0].addressLine4;
			obj.BLS[selectedIndex].notifyParty[0].city=obj.BLS[selectedIndex].consignee[0].city;
			obj.BLS[selectedIndex].notifyParty[0].state=obj.BLS[selectedIndex].consignee[0].state;
			
			obj.BLS[selectedIndex].notifyParty[0].countryCode=obj.BLS[selectedIndex].consignee[0].countryCode;
			obj.BLS[selectedIndex].notifyParty[0].zip=obj.BLS[selectedIndex].consignee[0].zip;
			
			$scope.notifyPan  = $scope.consignee.consigneIec;
			$scope.notifyPan  =  $scope.consignee.consignePan;

		}else if ($scope.consignee.consigneeName != "BANK" || $scope.consignee.consigneeName != "TO THE ORDER OF" || 
				$scope.consignee.consigneeName != "TO ORDER" || $scope.consignee.consigneeName != "TO ORDER OF" && $scope.notifyParty.notifyName == "SAME AS CONSIGNEE"){
			obj.BLS[selectedIndex].notifyParty[0].costumerCode=obj.BLS[selectedIndex].consignee[0].customerCode;
			obj.BLS[selectedIndex].notifyParty[0].costumerName=obj.BLS[selectedIndex].consignee[0].customerName;
			obj.BLS[selectedIndex].notifyParty[0].addressLine1=obj.BLS[selectedIndex].consignee[0].addressLine1;
			obj.BLS[selectedIndex].notifyParty[0].addressLine2=obj.BLS[selectedIndex].consignee[0].addressLine2;
			
			obj.BLS[selectedIndex].notifyParty[0].addressLine3=obj.BLS[selectedIndex].consignee[0].addressLine3;
			obj.BLS[selectedIndex].notifyParty[0].addressLine4=obj.BLS[selectedIndex].consignee[0].addressLine4;
			obj.BLS[selectedIndex].notifyParty[0].city=obj.BLS[selectedIndex].consignee[0].city;
			obj.BLS[selectedIndex].notifyParty[0].state=obj.BLS[selectedIndex].consignee[0].state;
			
			obj.BLS[selectedIndex].notifyParty[0].countryCode=obj.BLS[selectedIndex].consignee[0].countryCode;
			obj.BLS[selectedIndex].notifyParty[0].zip=obj.BLS[selectedIndex].consignee[0].zip;
			
			$scope.notifyIec  = $scope.consignee.consigneIec;
			$scope.notifyPan  =  $scope.consignee.consignePan;
			
			}else{
				$scope.consigneIec  = $scope.consignee.consigneIec;
				$scope.consignePan  =  $scope.consignee.consignePan;
				}
		
	}

	
	$scope.igmDateUpdate=function()
	{ 
		debugger;
		var igmVal=$scope.selectedServcies.igmNumber;
		if(igmVal==null || igmVal==''){
			$scope.selectedServcies.igmYearVal="";
			$scope.selectedServcies.igmDateVal="";
		}else{		
			var d = new Date();
			$scope.selectedServcies.igmYearVal=d.getFullYear();
			$scope.selectedServcies.igmDateVal=d.getDate()+'-'+d.getMonth()+"-"+d.getFullYear();
		}
		
	}
	
	$scope.applySave=function() {
		
		if($scope.selectedServcies.igmNumber!=null && $scope.selectedServcies.igmNumber!='' && $scope.selectedServcies.igmDate !=null && $scope.selectedServcies.igmDate!='')
		{
			var dateResult = $scope.selectedServcies.igmDate.split("/");
			$scope.selectedServcies.igmYearVal=dateResult[2];
			$scope.selectedServcies.igmDateVal=dateResult[0]+'/'+ dateResult[1] +"/"+dateResult[2];
			$("#blNoDate").datepicker()=$scope.selectedServcies.igmDateVal;
		}else{
			$scope.selectedServcies.igmYearVal="";
			$scope.selectedServcies.igmDateVal="";
		}
	}

	$scope.getHBLNo=function(obj,index)
	{
		debugger;
		if($("#"+obj.item.bl).css('display') == 'block'){
			$("#"+obj.item.bl).css("display", "none");
		}else{
			$("#"+obj.item.bl).css("display", "block");
		}
		if(obj.item.hblArr.length>0){
			return false;
		}
		
		$scope.blIndex=index;
		$scope.selectedBL= $scope.BLS[$scope.blIndex];
		$scope.getCarogoDetails();
		//$scope.onUploadAck();
		
		/* $scope.getExtraDetails(); */
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
		$scope.getConsinee();
		$scope.containerValue();
		//$("#dialog-tabs").tabs({ active: 4 });
		
		sendData ="?masterBl='"+obj.item.bl+"'&itemNumber='"+obj.item.itemNumber+"'";
		$( "body" ).append('<div class="loading"></div>');
		$http({
			method : "POST",
			async : true,
			url : $window.GETHBLLIST+sendData,
			dataType: 'json',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			beforeSend:function()
			{
				loadingfun();
			},
		}).then(function(data, status, headers, config) {
			$("body").find('.loading').remove();
			debugger;
			
			for(var k=0;k<$scope.BLS.length;k++){
				if($scope.BLS[k].bl==obj.item.bl){
					$scope.BLS[k].hblArr = data.data.hblDetails;
				}	
			}
		});
	}

	$scope.blcheckTotalIteamSelectAll=function(obj)
	{
		debugger;
			var payloadSaved = "";
			var payloadUnSaved = "";
			var count = 0;
			if($("#selectAllCheckBox")[0].checked == true){
				for(var i=0; i<$scope.BLS.length;i++){
				var iteam = $scope.BLS[i];
				
				if(iteam.isBlSave==false || iteam.isBlSave=="false"){
					iteam.isBlSave=true;
				}
				
				iteam.isBlSave=true
				    count++;
					if($scope.BLS[i].isBlSave == true && $scope.BLS[i].itemNumber != "true" && $scope.BLS[i].itemNumber != null){
						if(payloadSaved == "" && $scope.selectAllFetch == false ||  $scope.selectAllFetch == "false"){
							payloadSaved =  $scope.BLS[i].bl;
						}else{
							payloadSaved = payloadSaved + "," + $scope.BLS[i].bl;
						}
					}else{
						if(payloadUnSaved == "" && $scope.selectAllFetch == false || $scope.selectAllFetch == "false"){
							payloadUnSaved =  $scope.BLS[i].bl;
						}else{
							payloadUnSaved = payloadUnSaved + "," + $scope.BLS[i].bl;
						}
					}
				}
			}else{
				for(var i=0; i<$scope.BLS.length;i++){
					var iteam = $scope.BLS[i];
					iteam.isBlSave=false;
					if(iteam.saveFlags= 'N' && iteam.itemNumber!=null && iteam.itemNumber!=""){
						iteam.saveFlags='D';
					}else{
						iteam.saveFlags='N';
					}
					
				}
				count=0;
				payloadSaved = "";
				payloadUnSaved = "";
		}
			 
			$scope.selectedServcies.totalItem=count;
			totalIteamNo=count;
			if(payloadUnSaved == "" && payloadSaved == "" ){
				return false;
			}
			
			if( $scope.selectAllFetch == true){
				return false;
			}
		/* 	$( "body" ).append('<div class="loading"></div>');
			setTimeout(function() {
				  $(".loading").remove();
				}, 1000);
 */

	}
	
	$scope.hblcheckTotalIteam=function(obj)
	{
			debugger;
			var payloadSaved = "";
			var payloadUnSaved = "";
			var count = 0;
			if($("#selectAllCheckBox")[0].checked == true){
				for(var i=0; i<$scope.BLS.length;i++){
					var iteam = $scope.BLS[i];
					iteam.isBlSave=true
				    count++;
					if($scope.BLS[i].isBlSave == true && $scope.BLS[i].itemNumber != "" && $scope.BLS[i].itemNumber != null){
						if(payloadSaved == "" && $scope.selectAllFetch == false){
							payloadSaved =  $scope.BLS[i].bl;
						}else{
							payloadSaved = payloadSaved + "," + $scope.BLS[i].bl;
						}
					}else{
						if(payloadUnSaved == "" && $scope.selectAllFetch == false){
							payloadUnSaved =  $scope.BLS[i].bl;
						}else{
							payloadUnSaved = payloadUnSaved + "," + $scope.BLS[i].bl;
						}
					}
				}
			}else{
				for(var i=0; i<$scope.BLS.length;i++){
					var iteam = $scope.BLS[i];
					iteam.isBlSave=false;
					if(iteam.saveFlags= 'N' && iteam.itemNumber!=null && iteam.itemNumber!=""){
						iteam.saveFlags='D';
					}else{
						iteam.saveFlags='N';
					}
					
				}
				count=0;
				payloadSaved = "";
				payloadUnSaved = "";
			}
			 
			$scope.selectedServcies.totalItem=count;
			totalIteamNo=count;
			
			if(payloadUnSaved == "" && payloadSaved == "" ){
				return false;
			}
			
			if( $scope.selectAllFetch == true){
				return false;
			}
			 
			sendData ="?savedBlList="+payloadSaved+"&unSavedBlList="+payloadUnSaved+"&vessel="+$scope.selectedServcies.vessel+"&voyage="+$scope.selectedServcies.voyage+"&pod="+$scope.selectedServcies.pod;
			$( "body" ).append('<div class="loading"></div>');
			$http({
				method : "POST",
				async : true,
				url : $window.GETSELECTALLBL+sendData,
				dataType: 'json',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				beforeSend:function()
				{
					loadingfun();
				},
			}).then(function(data, status, headers, config) {
				$("body").find('.loading').remove();
				debugger;
				jsonData.result[0].BLS = data.data.blDetails;
				$scope.BLS = data.data.blDetails;
				$scope.selectAllFetch = true;
				document.getElementById("subCheckBox").checked = true;
				 /* $scope.getExtraDetails(); */ 
			});
			
	}
	
	$scope.blcheckTotalIteam=function(obj)
	{
		debugger;
		var count = 0;
		var totalNmbrOfLinesCount = 0;
		var containerMsBlCount = 0;
		var countMsBl = 0 ;
		console.log(this);
		//$scope.selectedServcies.totalItems=
			if($scope.BLS.length==0){
				
				count=$scope.selectedServcies.totalItem;
			}else{
				if(obj.item.saveFlags=="N" && (obj.item.itemNumber == "" || obj.item.itemNumber == null)){
					obj.item.saveFlags="I";
				}else if(obj.item.saveFlags=="N" && (obj.item.itemNumber != "" || obj.item.itemNumber != null)){
					obj.item.saveFlags="D";
				}else if((obj.item.saveFlags=="N" ||obj.item.saveFlags=="D") && (obj.item.itemNumber != "" || obj.item.itemNumber != null)) {
					obj.item.saveFlags="U";
				}else if((obj.item.saveFlags=="N" || obj.item.saveFlags=="I")&& (obj.item.itemNumber == "" || obj.item.itemNumber == null)){
					obj.item.saveFlags="N";
				}
				for(var i=0; i<$scope.BLS.length;i++){
				var iteam = $scope.BLS[i];
				if(iteam.isBlSave==true || iteam.isBlSave=="true"){
					count++;
					totalNmbrOfLinesCount = totalNmbrOfLinesCount + iteam.containerDetailes.length;
							if(iteam.ishbl != true){
								containerMsBlCount = containerMsBlCount + iteam.containerDetailes.length;
								 countMsBl++;
								}
							
						} 
			}
				if($('[name=chk]')[obj.$index].checked==true){
					$( "body" ).append('<div class="loading"></div>');
					$scope.blIndex=obj.$index;
					$scope.selectedBL= $scope.BLS[$scope.blIndex]
					$scope.getContainerDetails();
				}
				}
			$scope.selectedServcies.totalItem = count;
			$scope.selectedServcies.totalNmbrOfLines = totalNmbrOfLinesCount ;
			$scope.selectedServcies.containerMsBl = containerMsBlCount;
			totalIteamNo=count;
	}
	
	$scope.setTwoNumberDecimal= function(obj,index,firestNo,secNo,type){
	 		debugger;
	 		
	 		if(!isNaN(obj.BLS[index].grosWeight) && obj.BLS[index].grosWeight!=""){
		 			var grosWeightArray=obj.BLS[index].grosWeight.split('.');
		 			if(grosWeightArray.length>2){
		 				obj.BLS[index].grosWeight = 0;
		 			}else if(grosWeightArray.length<2){
		 				obj.BLS[index].grosWeight=obj.BLS[index].grosWeight.substring(0,firestNo)+".00"
		 			}else{
		 				
		 				obj.BLS[index].grosWeight=grosWeightArray[0].substring(0,firestNo)+"."+grosWeightArray[1].substring(0,secNo);
		 			}
		 			 
	 		  } else{
	 			 obj.BLS[index].grosWeight='';
	 		  } 
	 		 
	}
	
	$scope.setTwoNumberDecimalContainer= function(selectedContainer,firestNo,secNo,type){
 		debugger;
 		
 		if(!isNaN(selectedContainer.containerWeight) && selectedContainer.containerWeight!=""){
 			var containerWeightArray=selectedContainer.containerWeight.split('.');
 			if(containerWeightArray.length>2){
 				selectedContainer.containerWeight = 0;
 			}else if(containerWeightArray.length<2){
 				selectedContainer.containerWeight=selectedContainer.containerWeight.substring(0,firestNo)+".00"
 			}else{
 				
 				selectedContainer.containerWeight=containerWeightArray[0].substring(0,firestNo)+"."+containerWeightArray[1].substring(0,secNo);
 			}
 			 
		  } else{
			  selectedContainer.containerWeight='';
		  } 
 		
 	 
 	}
	
	$scope.checkNumericNoPackage=function(selectedContainer,totalNumberOfPackagesInContainer){
 		debugger;
 		var len=selectedContainer.totalNumberOfPackagesInContainer.length;
 		if(isNaN(selectedContainer.totalNumberOfPackagesInContainer[len-1]))
 			{
 			selectedContainer.totalNumberOfPackagesInContainer=selectedContainer.totalNumberOfPackagesInContainer.substring(0,len-1)
 			}else{
 				if(isNaN(selectedContainer.totalNumberOfPackagesInContainer) ){
 					  selectedContainer.totalNumberOfPackagesInContainer='';
 				  } 
 			}
 		
 	}
	
	
	
	$scope.setTwoNumberDecimalContainercbm= function(selectedContainer,firestNo,secNo,type){
 		debugger;
 		
 		if(!isNaN(selectedContainer.cbm) && selectedContainer.cbm!=""){
 			var cbmArray=selectedContainer.cbm.split('.');
 			if(cbmArray.length>2){
 				selectedContainer.cbm = 0;
 			}else if(cbmArray.length<2){
 				selectedContainer.cbm=selectedContainer.cbm.substring(0,firestNo)+".00"
 			}else{
 				
 				selectedContainer.cbm=cbmArray[0].substring(0,firestNo)+"."+cbmArray[1].substring(0,secNo);
 			}
 			 
		  } else{
			  selectedContainer.cbm='';
		  } 
 		
 	}
	
	$scope.setContainer= function(item){
		$scope.selectedContainer = item;
	}
	
	$scope.portPopUp = function (type){
		debugger;
		var payload={}
		$rootScope.pupUpName = type;
		if($rootScope.portLookUpJson.length > 0){
			showDialgPort();
			return;
		}
		payload["vessel"]=result1.result[ $window.selectedIndex].vessel;
		$( "body" ).append('<div class="loading"></div>');
		$http({
			method : "POST",
			async : true,
			url : $window.GETPORTDATA,
			dataType: 'json',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			beforeSend:function()
			{
				loadingfun();
			},
			data : "requestParam="+encodeURIComponent(JSON.stringify(payload)),
			 
			 
		}).then(function(data, status, headers, config) {
			$("body").find('.loading').remove();
			$rootScope.portLookUpJson=data.data.result;
			showDialgPort();
		});
	}
	
	$rootScope.setPortCode = function (port,portName){
		
		debugger;
		var type = $rootScope.pupUpName ;
		if(type=='Last Port -1'){
			$scope.selectedServcies.lastPort3 = port;
			$scope.selectedServcies.port_of_call_name_last3 = portName;
		}else if(type=='Last Port -2'){
			$scope.selectedServcies.lastPort2 = port;
			$scope.selectedServcies.port_of_call_name_last2 = portName;
		}else if(type=='Last Port -3'){
			$scope.selectedServcies.lastPort1 = port;
			$scope.selectedServcies.port_of_call_name_last1 = portName;
		}else if(type=='Next Port 1'){
			$scope.selectedServcies.nextport1 = port;
			$scope.selectedServcies.port_of_call_name_nextport1 = portName;
		}else if(type=='Next Port 2'){
			$scope.selectedServcies.nextport2 = port;
			$scope.selectedServcies.port_of_call_name_nextport2 = portName;
		}else if(type=='Next Port 3'){
			$scope.selectedServcies.nextport3 = port;
			$scope.selectedServcies.port_of_call_name_nextport3 = portName;
		}else if(type=='Port Of Origin'){
			$scope.selectedServcies.portOrigin = port;
			$scope.selectedServcies.port_of_call_name_portOrigin = portName;
		}else if(type=='Port Of Arrival'){
			$scope.selectedServcies.portArrival = port;
			$scope.selectedServcies.port_of_call_name_portArrival = portName;
		} 
		$("#dialog-form-port").dialog("close");
	}	
	 
	$scope.send = function (){
		debugger;
		var payload={}
		payload["vessel"]=result1.result[ $window.selectedIndex].vessel;
		payload["voyage"]=result1.result[ $window.selectedIndex].voyage
		payload["igmservice"]=result1.result[ $window.selectedIndex].service
		payload["pol"]=result1.result[ $window.selectedIndex].pol
		payload["polTerminal"]=result1.result[ $window.selectedIndex].polTerminal
		$( "body" ).append('<div class="loading"></div>');
		$http({
			method : "POST",
			async : true,
			url : $window.GETBLDATA,
			dataType: 'json',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			beforeSend:function()
			{
				loadingfun();
			},
			data : "requestParam="+encodeURIComponent(JSON.stringify(payload)),
			 
			 
		}).then(function(data, status, headers, config) {			 
			showDialg();
			$("body").find('.loading').remove();
			var blCountCheck = 0;
			$window.jsonData=data.data;			
			$scope.getData();
			$scope.getConsinee();
			$scope.prsnOnBordTable=data.data.personOnBoardMod;
			$scope.shipStoreTable=data.data.shipStoresMod;
			$scope.crewEffetTable=data.data.crewEfctMod;
			
			$scope.roadCarrCode=data.data.DropDown.roadCarrCoadDropdown;
			$scope.tpBondNo=data.data.DropDown.TPBondNoDropdown;
			$scope.cfsCustomCode=data.data.DropDown.cfsCustomCodeDropdown;
			 
			$window.prsnOnBordTable=data.data.personOnBoardMod;
			if(selectedServcies.noOfCrew==0){
				$scope.selectedServcies.noOfCrew = Object.keys($window.prsnOnBordTable).length;
			}
			$window.shipStoreTable=data.data.shipStoresMod;
			$window.crewEffetTable=data.data.crewEfctMod;
			
			/* $scope.selectedBL.roadCarrCodeVVS = "";
			$scope.selectedBL.roadTpBondNoVVSS = $scope.tpBondNo[1].podValue; */
			debugger
			for(var i=0; i<$scope.BLS.length;i++){
				var iteam = $scope.BLS[i];
				if(iteam.isBlSave=="true"){
					blCountCheck++;
				}
			}
			if($scope.BLS.length == blCountCheck){
				$scope.isBlSelecteSave = 'true';
			}
			if($scope.isBlSelecteSave == 'true' && $scope.BLS.length > blCountCheck){
				document.getElementById("selectAllCheckBox").checked = false;
				$scope.selectAllFetch = false;
			}
		});
	}
	
	
	/* $scope.save = function (){
		 
		var totalNoValidation=$scope.selectedServcies.totalItem;
		if(totalNoValidation ==null || totalNoValidation == 0){
			swal("Message","!Need to select BL.","info");
			 return false;
		}
		for(var d=0;d<$scope.BLS.length;d++){
			if(($scope.BLS[d].isBlSave=="true" || $scope.BLS[d].isBlSave==true) && ($scope.BLS[d].saveFlags == "U" || $scope.BLS[d].saveFlags == "I" )){
				if(($scope.BLS[d].fetch == "false" || $scope.BLS[d].fetch == false) ||(document.getElementById("selectAllCheckBox").checked = false && ($scope.BLS[d].containerDetailes == undefined  || $scope.BLS[d].containerDetailes.length==0))){
					swal("Message","Please Check Carogo Data : "+$scope.BLS[d].bl,"info");
					return false;
				}
		}
		}
		
		$( "body" ).append('<div class="loading"></div>');
		//delete jsonData.result[0].service.$$hashKey;
		
		$http({
			method : "POST",
			async : true,
			url : $window.SAVE_DATA,
			dataType: 'json',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			beforeSend:function()
			{
				loadingfun();
			},
			data : "requestParam="+encodeURIComponent(JSON.stringify(jsonData.result[0])),
			 
			 
		}).then(function(data, status, headers, config) {			 
			debugger;
			for(var o=0;o<data['data']['resultSave'].length;o++){
				if(data['data']['resultSave'][o]['saveFlags']=="I" || data['data']['resultSave'][o]['saveFlags']=="U"){
					for(var x=0;x<$scope.BLS.length;x++){
						if($scope.BLS[x].bl==data['data']['resultSave'][o]['bl']){
							$scope.BLS[x]['itemNumber']=data['data']['resultSave'][o]['itemNumber'];
						}
					}
				}
			}
			for(var o=0;o<data['data']['resultDelete'].length;o++){
				if(data['data']['resultDelete'][o]['saveFlags']=="D"){
					for(var x=0;x<$scope.BLS.length;x++){
						if($scope.BLS[x].bl==data['data']['resultDelete'][o]['bl']){
							$scope.BLS[x]['itemNumber']="";
						}
					}
				}
			}
			var blCountCheck = 0;

			for(var i=0; i<$scope.BLS.length;i++){
				var iteam = $scope.BLS[i];
				if(iteam.isBlSave=="true"){
					blCountCheck++;
				}
			}
			debugger;
			if($scope.BLS.length == blCountCheck){
				$scope.isBlSelecteSave = 'true';
				document.getElementById("selectAllCheckBox").checked = true;
			}
			if($scope.isBlSelecteSave == 'true' && $scope.BLS.length > blCountCheck){
				document.getElementById("selectAllCheckBox").checked = false;
				$scope.selectAllFetch = false;
			}
			$("body").find('.loading').remove();
			swal("Message","Saved Successfully..!","info");
			//$scope.msg="Saved Successfully..!";
			// $("#dialog-form").dialog("close");
		});
	}
	 */
	 
	 

		$scope.save = function (){
			debugger;
			var totalNoValidation=$scope.selectedServcies.totalItem;
			if(totalNoValidation ==null || totalNoValidation == 0){
				swal("Message","!Need to select BL.","info");
				 return false;
			}
			for(var d=0;d<$scope.BLS.length;d++){
				if(($scope.BLS[d].isBlSave=="true" || $scope.BLS[d].isBlSave==true) && ($scope.BLS[d].saveFlags == "U" || $scope.BLS[d].saveFlags == "I" )){
						if(($scope.BLS[d].fetch == "false" || $scope.BLS[d].fetch == false) && (document.getElementById("selectAllCheckBox").checked = false && ($scope.BLS[d].containerDetailes == undefined  || $scope.BLS[d].containerDetailes.length==0))){
							swal("Message","Please Check Carogo Data : "+$scope.BLS[d].bl,"info");
							return false;
						}
				}
			}
			
			blModule =  ($scope.BLS.length/200|0);
			blExtraModule = $scope.BLS.length%200;
			if(blExtraModule>0){
				blModule=blModule+1;
			}
			
			savePhaseCountObj = [];
			for(var i=0;i<blModule;i++){
				savePhaseCountObj.push(i);
			}
			$rootScope.blSavePhases = savePhaseCountObj;
			showDialgSaveLoadingDtls(blModule);
				$http({
					method : "POST",
					async : true,
					url : $window.SAVE_DATA_VESSEL_VOYAGE,
					dataType: 'json',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					beforeSend:function()
					{
						loadingfun();
					},
					data : "requestParam="+encodeURIComponent(JSON.stringify(jsonData.result[0].service)),
				}).then(function(data, status, headers, config) { 
					$('#phaseVsslVoyg').removeClass('active');
					$('#phaseVsslVoyg').html('Completed');
					maxIteamNo = parseInt(jsonData.result[0].service.fromItemNo);
					fstBL = 0;
					blModuleTemp = blModule;
					for(var d=0;d<blModule;d++){
							debugger;
							startingIndex = (d * 1) * 200;
							lastIndex = (d * 1 + 1) * 200;
							for(var p=startingIndex;p<lastIndex;p++){
								if ($scope.BLS[p] != null && (true==$scope.BLS[p].isBlSave || "true"==$scope.BLS[p].isBlSave)) {
									if ($scope.BLS[p].itemNumber == null || $scope.BLS[p].itemNumber == "") {
										$scope.BLS[p].itemNumber=(maxIteamNo+ "");
										if($scope.BLS[p].saveFlags=='N'){
											$scope.BLS[p].saveFlags='I';
										}
										maxIteamNo++;
									}else {
										if (parseInt($scope.BLS[p].itemNumber) > maxIteamNo || maxIteamNo == parseInt(jsonData.result[0].service.fromItemNo)) {
											if(fstBL==0) {
												$scope.BLS[p].itemNumber = maxIteamNo;
											}else {
												maxIteamNo = parseInt($scope.BLS[p].itemNumber);
											}
											if($scope.BLS[p].saveFlags=='N' && ($scope.BLS[p].fetch=='false' || $scope.BLS[p].fetch==false)){
												$scope.BLS[p].saveFlags='N';
											}else if(($scope.BLS[p].saveFlags=='N' || $scope.BLS[p].saveFlags=='U') && ($scope.BLS[p].fetch=='true' || $scope.BLS[p].fetch==true)){
												$scope.BLS[p].saveFlags='U';
											}
											maxIteamNo++;
										}else {
											$scope.BLS[p].itemNumber = maxIteamNo;
											maxIteamNo++;
											if($scope.BLS[p].saveFlags=='N' && ($scope.BLS[p].fetch=='false' || $scope.BLS[p].fetch==false)){
												$scope.BLS[p].saveFlags='N';
											}else if(($scope.BLS[p].saveFlags=='N' || $scope.BLS[p].saveFlags=='U') && ($scope.BLS[p].fetch=='true' || $scope.BLS[p].fetch==true)){
												$scope.BLS[p].saveFlags='U';
											}
										}
									}
									fstBL++;
								} else if ($scope.BLS[p] != null && (true==$scope.BLS[p].isBlSave  || "true"==$scope.BLS[p].isBlSave)
														&&  $scope.BLS[p].itemNumber != "" && $scope.BLS[p].itemNumber != null) {
									if (parseInt($scope.BLS[p].itemNumber) >= maxIteamNo) {
										maxIteamNo = parseInt($scope.BLS[p].itemNumber);
										$scope.BLS[p].saveFlags='U';
									}
								}else if ($scope.BLS[p] != null && (false==$scope.BLS[p].isBlSave || "false"==$scope.BLS[p].isBlSave) && $scope.BLS[p] != null 
														&&  $scope.BLS[p].itemNumber != "" && $scope.BLS[p].itemNumber != null) {
									if (parseInt($scope.BLS[p].itemNumber) >= maxIteamNo) {
										maxIteamNo = parseInt($scope.BLS[p].itemNumber);
										$scope.BLS[p].saveFlags='D';
									}
								}
							} 
							blSaveJson = {};	
							blSaveJson['bls'] = $scope.BLS.slice(startingIndex,lastIndex);	
							blSaveJson['service'] = (jsonData.result[0].service);
							blSaveJson['sequence'] = (jsonData.result[0].sequence);
							blSaveJson['saveBlPhase'] = d;
							
							$http({
							method : "POST",
							async : true,
							url : $window.SAVE_DATA_BL_DATA,
							dataType: 'json',
							headers: {'Content-Type': 'application/x-www-form-urlencoded'},
							beforeSend:function()
							{
								loadingfun();
							},
							data : "requestParam="+encodeURIComponent(JSON.stringify(blSaveJson)),
						}).then(function(data, status, headers, config) {
							debugger;
							$('#phase'+data.data.result).removeClass('active'); 
							$('#phase'+data.data.result).html('Completed'); 
							 
						}); 
							 
					 }
				});
		}
		
	 var resultLength ;
	$rootScope.getData = function () {  
		debugger;
		if($window.jsonData.result){
		$scope.BLS = $window.jsonData.result[0].BLS;
        $scope.selectedServcies= $window.jsonData.result[0].service;
        $scope.selectedBL= $scope.BLS[$scope.blIndex]
         resultLength = $window.jsonData.result[0].BLS.length;
		}
        return $scope.BLS;
    }
	
	   	
	$scope.getConsinee = function () {	
		
		$scope.consignee =$scope.selectedBL.consignee[0];
		$scope.consigner =$scope.selectedBL.consigner[0];
		$scope.notifyParty =$scope.selectedBL.notifyParty[0];
		$scope.notifyPartyTwo =$scope.selectedBL.notifyPartyTwo[0];
		 
    }
	
    $rootScope.getVesselVoyageData = function () {         
        $scope.selectedServcies= $window.jsonData.result[$window.selectedIndex].service;
        $scope.selectedBL= $scope.BLS[$scope.blIndex]
        return $scope.selectedServcies;
    }
	$rootScope.setIndex = function (index) {  
		$scope.blIndex=index;
		$scope.selectedBL= $scope.BLS[$scope.blIndex]
		$scope.getCarogoDetails();
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
		$scope.getConsinee();
		$scope.containerValue();
		$("#dialog-tabs").tabs({ active: 4 });
    }
	$rootScope.setIndexHBL = function (index,obj) {
		debugger;
		var perentRow = 0;
		for(var s=0;s<$scope.BLS.length;s++){
			if($scope.BLS[s].bl==obj.item1.bl){
				perentRow = s;
			}
		}
		$scope.selectedHBLRow = index;
		$scope.blIndex=perentRow;
		$scope.selectedBL = $scope.BLS[$scope.blIndex];
		$scope.getCarogoDetailsHBL();
		//$scope.onUploadAck();
		
		/* $scope.getExtraDetails(); */
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
		$scope.getConsinee();
		$scope.containerValue();
		$("#dialog-tabs").tabs({ active: 4 });
    }
	$rootScope.setNext = function () {  
		if(resultLength-1>$scope.blIndex){
		$scope.blIndex = $scope.blIndex+1;
		$scope.selectedBL= $scope.BLS[$scope.blIndex]
		$scope.getCarogoDetails();
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
		$scope.getConsinee();
		$scope.getContainerDetails();
		$("#dialog-tabs").tabs({ active: 4 });}
    }  
	$rootScope.setPrevious = function () {  
    	if($scope.blIndex>0){
    	$scope.blIndex = $scope.blIndex-1;
		$scope.selectedBL= $scope.BLS[$scope.blIndex]
		$scope.getCarogoDetails();
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
		$scope.getConsinee();
		$scope.getCarogoDetails();
		$("#dialog-tabs").tabs({ active: 4 });}
    }
	
	$rootScope.setIndexForVesselVoyage = function (index) {  
		$scope.vesselVoyageIndex=index;
		$scope.selectedServcies= $scope.selectedServcies[index]
    }
	
	$scope.getprscrwshipSearch=function(val) {
		debugger;
			
		var vessel = $scope.selectedServcies.vessel;
		var voyage = $scope.selectedServcies.voyage;
		var pod = $scope.selectedServcies.pol;
		
		var url  = "";
		if (val === 'P'){
			url = PERSONONBOARDSEARCH+"?vessel="+vessel+"&voyage="+voyage+"&pod="+pod
		}else if (val === 'C'){
			 
			url = CREWEFFECTSEARCH+"?vessel="+vessel+"&voyage="+voyage+"&pod="+pod;
		}else{
			url = SHIPSTORSEARCH+"?vessel="+vessel+"&voyage="+voyage+"&pod="+pod;
		}	
		$( "body" ).append('<div class="loading"></div>');
		$http({
				method : "POST",
				async : true,
				url : url,
			  }).then(function(result, status, headers, config) {			 
				  $("body").find('.loading').remove();
				  if (val === 'P'){
						$scope.prsnOnBordTable=result.data.personOnBoardMod;
						$scope.selectedServcies.noOfCrew = Object.keys($scope.prsnOnBordTable).length;
						jsonData.result[0].service.personOnBoardMod = result.data.personOnBoardMod;
						$scope.personData();	
					}
					if (val === 'S'){
						$scope.shipStoreTable=result.data.shipStoresMod;
						jsonData.result[0].service.igmShipStoresMods = result.data.shipStoresMod;
					}
					if (val === 'C'){
						$scope.crewEffetTable=result.data.crewEfctMod;
						jsonData.result[0].service.crewEfctMods = result.data.crewEfctMod;
					}
					$scope.personData();
								
					$("body").find('.loading').remove();
			}); 
	}
	$scope.personData = function(){
		debugger;
		$scope.prsonData = $scope.prsnOnBordTable;
		$scope.shipStore = $scope.shipStoreTable;
		$scope.crewEffet= $scope.crewEffetTable;
		}
	
	$scope.importCVSFileAjax=function(val) {
		var form = null;
		debugger;
		if (val === 'P')
			form = document.getElementById('filePersonOnBoard');
		else if (val === 'C')
			form = document.getElementById('fileCrewEffect');
		else
			form = document.getElementById('fileShipStore');
			
		var fileData = new FormData(form);
		$http({
				method : "POST",
				async : true,
				url : $window.UPLOAD_CSV,
				headers: {'Content-Type': undefined},
						processData : false,
						data : fileData,
			  }).then(function(result, status, headers, config) {			 
							if (val === 'P'){
								$scope.prsnOnBordTable=result.data.result;
								$scope.selectedServcies.noOfCrew = Object.keys($scope.prsnOnBordTable).length;
								jsonData.result[0].service.personOnBoardMod = result.data.result;
							}
							if (val === 'S'){
								$scope.shipStoreTable=result.data.result;
								jsonData.result[0].service.igmShipStoresMods = result.data.result;
							}
							if (val === 'C'){
								$scope.crewEffetTable=result.data.result;
								jsonData.result[0].service.crewEfctMods = result.data.result;
							}
							
			});
	}

	/*====================================Start of Generate file JSON===================================================================== */

		$scope.genrateFileJson = function() {
		
		    delete jsonData.result[0].service.$$hashKey;
		    delete prsnOnBordTable.$$hashKey;
		    $scope.personData();
		    $scope.getprscrwshipSearch();
		    if ($("#fileType").val() == "SAA" || $("#fileType").val() == "SEI" || $("#fileType").val() == "SDN" || $("#fileType").val() == "SEI" ||
		        $("#fileType").val() == "SCC" || $("#fileType").val() == "SCD" || $("#fileType").val() == "SCU" || $("#fileType").val() == "SCA") {
		        swal("Message", $("#fileType").val() + " under development.", "info");
		        return false;
		    }
		
		    var blNoSaved = "";
		    var blNoUnSaved = "";
		    for (var x = 0; x < jsonData.result[0]["BLS"].length; x++) {
		        if (jsonData.result[0]["BLS"][x].isBlSave == 'true' && jsonData.result[0]["BLS"][x].itemNumber != "" && jsonData.result[0]["BLS"][x].itemNumber != null) {
		            if (jsonData.result[0]["BLS"][x].fetch == false) {
		                if (blNoSaved == "") {
		                    blNoSaved = blNoSaved + "'" + jsonData.result[0]["BLS"][x].bl + "'";
		                } else {
		                    blNoSaved = blNoSaved + ",'" + jsonData.result[0]["BLS"][x].bl + "'";
		                }
		            }
		        } else {
		            if (jsonData.result[0]["BLS"][x].fetch == false) {
		                if (blNoUnSaved == "") {
		                    blNoUnSaved = blNoUnSaved + "'" + jsonData.result[0]["BLS"][x].bl + "'";
		                } else {
		                    blNoUnSaved = blNoUnSaved + ",'" + jsonData.result[0]["BLS"][x].bl + "'";
		                }
		            }
		        }
		    }
		
		    var requestParam = JSON.stringify(jsonData.result[0]);
		    $("#requestParam").val(requestParam);
		
		    var prsnOnBordTables = JSON.stringify($scope.prsonData);
		    $("#personOnBoardMod").val(prsnOnBordTables);
		
		    var crewEffetTables = JSON.stringify($scope.crewEffet);
		    $("#crewEfctMod").val(crewEffetTables);
		
		    var shipStoreTables = JSON.stringify($scope.shipStore);
		    $("#shipStoresMod").val(shipStoreTables);
		
		    var fileType = $("#fileTypeEx").val();
		    $("#fileTypeEgm").val(fileType);
		
		    $("#blNoSaved").val(blNoSaved);
		    $("#blNoUnSaved").val(blNoUnSaved);
		
		}
/* =======================End of Generate file JSON ===================================================================*/
	
		$scope.onUploadAck = function() {
	    debugger;
	
	    var form = null;
	
	    form = document.getElementById('ackFileForm');
	    console.log(form);
	    var fileData = new FormData(form);
	    console.log(fileData, "ackfileeeeeeee")
	
	    $http({
	        method: "POST",
	        async: true,
	        url: $window.UPLOAD_ACK,
	        headers: {
	            'Content-Type': undefined
	        },
	        processData: false,
	        data: fileData,
	    }).then(function(result, status, headers, config) {
	
	
	        console.log(result);
	        $scope.ackFileResponse = result.data.result.master.mastrCnsgmtDec;
	        console.log($scope.ackFileResponse);
	
	        for (var i = 0; i < $scope.ackFileResponse.length; i++) {
	            console.log($scope.ackFileResponse[i].MCRef.lineNo);
	            console.log($scope.ackFileResponse[i].mcResponse.cinType);
	            console.log($scope.ackFileResponse[i].mcResponse.mcinPcin);
	            if ($scope.ackFileResponse[i].MCRef.lineNo == $scope.selectedBL.itemNumber && $scope.ackFileResponse[i].mcResponse.cinType == "PCIN") {
	                $scope.selectedBL.pcin = $scope.ackFileResponse[i].mcResponse.mcin;
	            } else {
	                if ($scope.ackFileResponse[i].MCRef.lineNo == $scope.selectedBL.itemNumber && $scope.ackFileResponse[i].mcResponse.cinType == "MCIN")
	                    $scope.selectedBL.mcin = $scope.ackFileResponse[i].mcResponse.mcin;
	            }
	
	        }
	
	
	        /* 	if($scope.ackFileResponse.master.mastrCnsgmtDec.lineNo=== $scope.selectedBL.itemNumber &&  $scope.ackFileResponse.master.mcResponse.cinType =="PCIN" ){
	        		$scope.selectedBL.pcin = $scope.ackFileResponse.master.mcResponse.mcinPcin;
	        		
	        		}else if($scope.ackFileResponse.master.mastrCnsgmtDec.lineNo=== $scope.selectedBL.itemNumber &&  $scope.ackFileResponse.master.mcResponse.cinType =="MCIN"){
	        			$scope.selectedBL.mcin = $scope.ackFileResponse.master.mcResponse.mcinPcin;
	        			} */
	
	        /* 	console.log($scope.ackFileResponse.headerField); */
	
	    });
	
	
	}
		/* uploading Shipping Bill Excel File  */
		
	$scope.onUploadShippingBill = function() {
	    debugger;
	
	    var form = null;
	    var pcinVal = null ;
	    form = document.getElementById('shippingFileForm');
	    console.log(form);
	    var fileData = new FormData(form);
	    console.log(fileData, "shipping Bill")
	
	    $http({
	        method: "POST",
	        async: true,
	        url: $window.UPLOAD_SHIPPING,
	        headers: {
	            'Content-Type': undefined
	        },
	        processData: false,
	        data: fileData,
	    }).then(function(result, status, headers, config) {

	    	$scope.shipipngResponse = result.data.result
	        for (var i = 1; i < $scope.shipipngResponse.length; i++) {
	        	console.log( $scope.selectedBL.bl);
	        	 console.log($scope.shipipngResponse[i]);
	        	if($scope.shipipngResponse[i][0] ==  $scope.selectedBL.bl  ){
		        	
		        	
		        	if(pcinVal == null){
		        		$scope.selectedBL.pcin = $scope.shipipngResponse[i][2];
		        		pcinVal = $scope.shipipngResponse[i][2];
			        	}else {
			        		$scope.selectedBL.pcin = pcinVal ,"+",$scope.selectedBL.pcin; 
			        	}
	        		
	        }
	        }  
	    });
	
	
	}

	$scope.getCarogoDetails=function() {
 	    debugger;
 	    if($scope.BLS[$scope.blIndex].fetch==true){
 	    	return false;
 	    }
 	   if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null && $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
 	    var bl= $scope.BLS[$scope.blIndex].bl;
 	    isBlSaved = $scope.BLS[$scope.blIndex].isBlSave
 	    itemNumber = $scope.selectedBL.itemNumber
 	    var vessel = $scope.selectedServcies.vessel;
		var voyage = $scope.selectedServcies.voyage;
		var pod = $scope.selectedServcies.pod;
		var service     = $scope.selectedServcies.service;
		var url = CAROGODETAILSSEARCH+"?vessel="+vessel+"&voyage="+voyage+"&pod="+pod+"&bl="+bl+"&isBlSave="+isBlSaved+"&itemNumber="+itemNumber+"&service="+service;
 	    $( "body" ).append('<div class="loading"></div>');
		$http({
				method : "POST",
				async : true,
				url : url,
			  }).then(function(result, status, headers, config) {			 
						$scope.BLS[$scope.blIndex].agencyType  = result.data.blDetails[0].agencyType
						$scope.BLS[$scope.blIndex].agentCode  =  result.data.blDetails[0].agentCode
						$scope.BLS[$scope.blIndex].arrivalDate  =  result.data.blDetails[0].arrivalDate
						$scope.BLS[$scope.blIndex].arrivalTime  =  result.data.blDetails[0].arrivalTime
						$scope.BLS[$scope.blIndex].arrival_departure_details  =  result.data.blDetails[0].arrival_departure_details
						$scope.BLS[$scope.blIndex].ataarrivalDate  =  result.data.blDetails[0].ataarrivalDate
						$scope.BLS[$scope.blIndex].ataarrivalTime  =  result.data.blDetails[0].ataarrivalTime
						$scope.BLS[$scope.blIndex].authRepCd  =  result.data.blDetails[0].authRepCd
						$scope.BLS[$scope.blIndex].authReprsntvCd  =  result.data.blDetails[0].authReprsntvCd
						$scope.BLS[$scope.blIndex].authoriz_rep_code  =  result.data.blDetails[0].authoriz_rep_code
						$scope.BLS[$scope.blIndex].authorized_sea_carrier_code  =  result.data.blDetails[0].authorized_sea_carrier_code
						$scope.BLS[$scope.blIndex].blStatus  =  result.data.blDetails[0].blStatus
						$scope.BLS[$scope.blIndex].blVersion  =  result.data.blDetails[0].blVersion
						$scope.BLS[$scope.blIndex].brief_cargo_des  =  result.data.blDetails[0].brief_cargo_des
						$scope.BLS[$scope.blIndex].callSing  =  result.data.blDetails[0].callSing
						$scope.BLS[$scope.blIndex].cargoDeclaration  =  result.data.blDetails[0].cargoDeclaration
						$scope.BLS[$scope.blIndex].cargoMovmntType  =  result.data.blDetails[0].cargoMovmntType
						$scope.BLS[$scope.blIndex].cargoNature  =  result.data.blDetails[0].cargoNature
						$scope.BLS[$scope.blIndex].cargo_description  =  result.data.blDetails[0].cargo_description
						$scope.BLS[$scope.blIndex].cargo_item_description  =  result.data.blDetails[0].cargo_item_description
						$scope.BLS[$scope.blIndex].cargo_item_sequence_no  =  result.data.blDetails[0].cargo_item_sequence_no
						$scope.BLS[$scope.blIndex].carrierNo  =  result.data.blDetails[0].carrierNo
						$scope.BLS[$scope.blIndex].cbm  =  result.data.blDetails[0].cbm
						$scope.BLS[$scope.blIndex].cfsName  =  result.data.blDetails[0].cfsName
						$scope.BLS[$scope.blIndex].cigmDate  =  result.data.blDetails[0].cigmDate
						$scope.BLS[$scope.blIndex].cigmNo  =  result.data.blDetails[0].cigmNo
						$scope.BLS[$scope.blIndex].cin_type  =  result.data.blDetails[0].cin_type
						$scope.BLS[$scope.blIndex].codeCode  =  result.data.blDetails[0].codeCode
						$scope.BLS[$scope.blIndex].consolidatedIndicator  =  result.data.blDetails[0].consolidatedIndicator
						$scope.BLS[$scope.blIndex].consolidated_indicator  =  result.data.blDetails[0].consolidated_indicator
						$scope.BLS[$scope.blIndex].consolidator_pan  =  result.data.blDetails[0].consolidator_pan
						$scope.BLS[$scope.blIndex].container_weight  =  result.data.blDetails[0].container_weight
						$scope.BLS[$scope.blIndex].conveyance_reference_no  =  result.data.blDetails[0].conveyance_reference_no
						$scope.BLS[$scope.blIndex].crewEffect  =  result.data.blDetails[0].crewEffect
						$scope.BLS[$scope.blIndex].crewListDeclaration  =  result.data.blDetails[0].crewListDeclaration
						$scope.BLS[$scope.blIndex].csn_date  =  result.data.blDetails[0].csn_date
						$scope.BLS[$scope.blIndex].csn_number  =  result.data.blDetails[0].csn_number
						$scope.BLS[$scope.blIndex].csn_reporting_type  =  result.data.blDetails[0].csn_reporting_type
						$scope.BLS[$scope.blIndex].csn_site_id  =  result.data.blDetails[0].csn_reporting_type
						$scope.BLS[$scope.blIndex].csn_submitted_by  =  result.data.blDetails[0].csn_submitted_by
						$scope.BLS[$scope.blIndex].csn_submitted_type  =  result.data.blDetails[0].csn_submitted_type
						$scope.BLS[$scope.blIndex].currency  =  result.data.blDetails[0].currency
						$scope.BLS[$scope.blIndex].cusAdd1  =  result.data.blDetails[0].cusAdd1
						$scope.BLS[$scope.blIndex].cusAdd2  =  result.data.blDetails[0].cusAdd2
						$scope.BLS[$scope.blIndex].cusAdd3  =  result.data.blDetails[0].cusAdd3
						$scope.BLS[$scope.blIndex].cusAdd4  =  result.data.blDetails[0].cusAdd4
						$scope.BLS[$scope.blIndex].customCode  =  result.data.blDetails[0].customCode
						$scope.BLS[$scope.blIndex].customTerminalCode  =  result.data.blDetails[0].customTerminalCode
						$scope.BLS[$scope.blIndex].del  =  result.data.blDetails[0].del
						$scope.BLS[$scope.blIndex].dep_manif_no  =  result.data.blDetails[0].dep_manif_no
						$scope.BLS[$scope.blIndex].dep_manifest_date  =  result.data.blDetails[0].dep_manifest_date
						$scope.BLS[$scope.blIndex].departureDate  =  result.data.blDetails[0].departureDate
						$scope.BLS[$scope.blIndex].departureTime  =  result.data.blDetails[0].departureTime
						$scope.BLS[$scope.blIndex].depot  =  result.data.blDetails[0].depot
						$scope.BLS[$scope.blIndex].dpdCode  =  result.data.blDetails[0].dpdCode
						$scope.BLS[$scope.blIndex].dpdMovement  =  result.data.blDetails[0].dpdMovement
						$scope.BLS[$scope.blIndex].dutyInr  =  result.data.blDetails[0].dutyInr
						$scope.BLS[$scope.blIndex].edi  =  result.data.blDetails[0].edi
						$scope.BLS[$scope.blIndex].enblockMovement  =  result.data.blDetails[0].enblockMovement
						$scope.BLS[$scope.blIndex].equimentType  =  result.data.blDetails[0].equimentType
						$scope.BLS[$scope.blIndex].equipment_load_status  =  result.data.blDetails[0].equipment_load_status
						$scope.BLS[$scope.blIndex].equipment_seal_type  =  result.data.blDetails[0].equipment_seal_type
						$scope.BLS[$scope.blIndex].exchangeRate  =  result.data.blDetails[0].exchangeRate
						$scope.BLS[$scope.blIndex].expected_date  =  result.data.blDetails[0].expected_date
						$scope.BLS[$scope.blIndex].fetch  =  result.data.blDetails[0].fetch
						$scope.BLS[$scope.blIndex].finalPlaceDelivery  =  result.data.blDetails[0].finalPlaceDelivery
						$scope.BLS[$scope.blIndex].first_port_of_entry_last_port_of_departure  =  result.data.blDetails[0].first_port_of_entry_last_port_of_departure
						$scope.BLS[$scope.blIndex].fromItemNo  =  result.data.blDetails[0].fromItemNo
						$scope.BLS[$scope.blIndex].genDesc  =  result.data.blDetails[0].genDesc
						$scope.BLS[$scope.blIndex].grosWeight  =  result.data.blDetails[0].grosWeight
						$scope.BLS[$scope.blIndex].grossCargoWeightBLlevel  =  result.data.blDetails[0].grossCargoWeightBLlevel
						$scope.BLS[$scope.blIndex].grossWeight  =  result.data.blDetails[0].grossWeight
						$scope.BLS[$scope.blIndex].gross_volume  =  result.data.blDetails[0].gross_volume
						$scope.BLS[$scope.blIndex].hblNo  =  result.data.blDetails[0].hblNo
						$scope.BLS[$scope.blIndex].hightValue  =  result.data.blDetails[0].hightValue
						$scope.BLS[$scope.blIndex].igmDate  =  result.data.blDetails[0].igmDate
						$scope.BLS[$scope.blIndex].igmDateVal  =  result.data.blDetails[0].igmDateVal
						$scope.BLS[$scope.blIndex].igmNumber  =  result.data.blDetails[0].igmNumber
						$scope.BLS[$scope.blIndex].igmYear  =  result.data.blDetails[0].igmYear
						$scope.BLS[$scope.blIndex].igmYearVal  =  result.data.blDetails[0].igmYearVal
						$scope.BLS[$scope.blIndex].imdg_code  =  result.data.blDetails[0].imdg_code
						$scope.BLS[$scope.blIndex].imoCode  =  result.data.blDetails[0].imoCode
						$scope.BLS[$scope.blIndex].invoiceItems  =  result.data.blDetails[0].invoiceItems
						$scope.BLS[$scope.blIndex].invoiceValueFc  =  result.data.blDetails[0].invoiceValueFc
						$scope.BLS[$scope.blIndex].invoiceValueInr  =  result.data.blDetails[0].invoiceValueInr
						$scope.BLS[$scope.blIndex].isValidateBL  =  result.data.blDetails[0].isValidateBL
						$scope.BLS[$scope.blIndex].itemType  =  result.data.blDetails[0].itemType
						$scope.BLS[$scope.blIndex].jobDate  =  result.data.blDetails[0].jobDate
						$scope.BLS[$scope.blIndex].jobNo  =  result.data.blDetails[0].jobNo
						$scope.BLS[$scope.blIndex].job_date  =  result.data.blDetails[0].job_date
						$scope.BLS[$scope.blIndex].job_number  =  result.data.blDetails[0].job_number
						$scope.BLS[$scope.blIndex].lastPort1  =  result.data.blDetails[0].lastPort1
						$scope.BLS[$scope.blIndex].lastPort2  =  result.data.blDetails[0].lastPort2
						$scope.BLS[$scope.blIndex].lastPort3  =  result.data.blDetails[0].lastPort3
						$scope.BLS[$scope.blIndex].lightDue  =  result.data.blDetails[0].lightDue
						$scope.BLS[$scope.blIndex].lineCode  =  result.data.blDetails[0].lineCode
						$scope.BLS[$scope.blIndex].manifest_date_csn_date  =  result.data.blDetails[0].manifest_date_csn_date
						$scope.BLS[$scope.blIndex].manifest_no_csn_no  =  result.data.blDetails[0].manifest_no_csn_no
						$scope.BLS[$scope.blIndex].mariTimeDecl  =  result.data.blDetails[0].mariTimeDecl
						$scope.BLS[$scope.blIndex].masterBl  =  result.data.blDetails[0].masterBl
						$scope.BLS[$scope.blIndex].masterBlDate  =  result.data.blDetails[0].masterBlDate
						$scope.BLS[$scope.blIndex].masterName  =  result.data.blDetails[0].masterName
						$scope.BLS[$scope.blIndex].mblNo  =  result.data.blDetails[0].mblNo
						$scope.BLS[$scope.blIndex].mc_item_details  =  result.data.blDetails[0].mc_item_details
						$scope.BLS[$scope.blIndex].mc_location_customs  =  result.data.blDetails[0].mc_location_customs
						$scope.BLS[$scope.blIndex].mcin  =  result.data.blDetails[0].mcin
						$scope.BLS[$scope.blIndex].message_type  =  result.data.blDetails[0].message_type
						$scope.BLS[$scope.blIndex].modeOfTpFee  =  result.data.blDetails[0].modeOfTpFee
						$scope.BLS[$scope.blIndex].mode_of_transport  =  result.data.blDetails[0].mode_of_transport
						$scope.BLS[$scope.blIndex].multipalPakages  =  result.data.blDetails[0].multipalPakages
						$scope.BLS[$scope.blIndex].neCargoMovmnt  =  result.data.blDetails[0].neCargoMovmnt
						$scope.BLS[$scope.blIndex].netWeight  =  result.data.blDetails[0].netWeight
						$scope.BLS[$scope.blIndex].newVessel  =  result.data.blDetails[0].newVessel
						$scope.BLS[$scope.blIndex].newVoyage  =  result.data.blDetails[0].newVoyage
						$scope.BLS[$scope.blIndex].next_port_of_call_coded  =  result.data.blDetails[0].next_port_of_call_coded
						$scope.BLS[$scope.blIndex].nextport1  =  result.data.blDetails[0].nextport1
						$scope.BLS[$scope.blIndex].nextport2  =  result.data.blDetails[0].nextport2
						$scope.BLS[$scope.blIndex].nextport3  =  result.data.blDetails[0].nextport3
						$scope.BLS[$scope.blIndex].nhavaShevaEta  =  result.data.blDetails[0].nhavaShevaEta
						$scope.BLS[$scope.blIndex].noOfCrew  =  result.data.blDetails[0].noOfCrew
						$scope.BLS[$scope.blIndex].noOfItemInFil  =  result.data.blDetails[0].noOfItemInFil
						$scope.BLS[$scope.blIndex].noOfItemInPrior  =  result.data.blDetails[0].noOfItemInPrior
						$scope.BLS[$scope.blIndex].noOfItemInSupplimentary  =  result.data.blDetails[0].noOfItemInSupplimentary
						$scope.BLS[$scope.blIndex].noOfPassenger  =  result.data.blDetails[0].noOfPassenger
						$scope.BLS[$scope.blIndex].no_of_crew_manif  =  result.data.blDetails[0].no_of_crew_manif
						$scope.BLS[$scope.blIndex].nonEdi  =  result.data.blDetails[0].nonEdi
						$scope.BLS[$scope.blIndex].notifyPartyCode  =  result.data.blDetails[0].notifyPartyCode
						$scope.BLS[$scope.blIndex].number_of_crew  =  result.data.blDetails[0].number_of_crew
						$scope.BLS[$scope.blIndex].number_of_packages  =  result.data.blDetails[0].number_of_packages
						$scope.BLS[$scope.blIndex].number_of_packages_hidden  =  result.data.blDetails[0].number_of_packages_hidden
						$scope.BLS[$scope.blIndex].packageBLLevel  =  result.data.blDetails[0].packageBLLevel
						$scope.BLS[$scope.blIndex].packages  =  result.data.blDetails[0].packages
						$scope.BLS[$scope.blIndex].pan_of_notified_party  =  result.data.blDetails[0].pan_of_notified_party
						$scope.BLS[$scope.blIndex].parentVoy  =  result.data.blDetails[0].parentVoy
						$scope.BLS[$scope.blIndex].passengerList  =  result.data.blDetails[0].passengerList
						$scope.BLS[$scope.blIndex].podTerminal  =  result.data.blDetails[0].podTerminal
						$scope.BLS[$scope.blIndex].podTerminalPort  =  result.data.blDetails[0].podTerminalPort
						$scope.BLS[$scope.blIndex].pol  =  result.data.blDetails[0].pol
						$scope.BLS[$scope.blIndex].polTerminal  =  result.data.blDetails[0].polTerminal
						$scope.BLS[$scope.blIndex].polTerminalPort  =  result.data.blDetails[0].polTerminalPort
						$scope.BLS[$scope.blIndex].portArrival  =  result.data.blDetails[0].portArrival
						$scope.BLS[$scope.blIndex].portOfDeschargedCfs  =  result.data.blDetails[0].portOfDeschargedCfs
						$scope.BLS[$scope.blIndex].portOfDestination  =  result.data.blDetails[0].portOfDestination
						$scope.BLS[$scope.blIndex].portOrigin  =  result.data.blDetails[0].portOrigin
						$scope.BLS[$scope.blIndex].port_of_acceptance  =  result.data.blDetails[0].port_of_acceptance
						$scope.BLS[$scope.blIndex].acceptanceName  =  result.data.blDetails[0].acceptanceName
						$scope.BLS[$scope.blIndex].port_of_call_cod  =  result.data.blDetails[0].port_of_call_cod
						$scope.BLS[$scope.blIndex].port_of_call_coded  =  result.data.blDetails[0].port_of_call_coded
						$scope.BLS[$scope.blIndex].port_of_call_sequence_number  =  result.data.blDetails[0].port_of_call_sequence_number
						$scope.BLS[$scope.blIndex].port_of_receipt  =  result.data.blDetails[0].port_of_receipt
						$scope.BLS[$scope.blIndex].recieptName  =  result.data.blDetails[0].recieptName
						$scope.BLS[$scope.blIndex].port_of_registry  =  result.data.blDetails[0].port_of_registry
						$scope.BLS[$scope.blIndex].port_of_reporting  =  result.data.blDetails[0].port_of_reporting
						$scope.BLS[$scope.blIndex].position  =  result.data.blDetails[0].position
						$scope.BLS[$scope.blIndex].previous_mcin  =  result.data.blDetails[0].previous_mcin
						$scope.BLS[$scope.blIndex].previous_pcin  =  result.data.blDetails[0].previous_pcin
						$scope.BLS[$scope.blIndex].recieverId  =  result.data.blDetails[0].recieverId
						$scope.BLS[$scope.blIndex].registry_date  =  result.data.blDetails[0].registry_date
						$scope.BLS[$scope.blIndex].remark  =  result.data.blDetails[0].remark
						$scope.BLS[$scope.blIndex].remarkVessel  =  result.data.blDetails[0].remarkVessel
						$scope.BLS[$scope.blIndex].reporting_event  =  result.data.blDetails[0].reporting_event
						$scope.BLS[$scope.blIndex].roadCarrCode  =  result.data.blDetails[0].roadCarrCode
						$scope.BLS[$scope.blIndex].roadCarrCodeVVS  =  result.data.blDetails[0].roadCarrCodeVVS
						$scope.BLS[$scope.blIndex].roadTPBondNo  =  result.data.blDetails[0].roadTPBondNo
						$scope.BLS[$scope.blIndex].roadTpBondNo  =  result.data.blDetails[0].roadTpBondNo
						$scope.BLS[$scope.blIndex].rotnDate  =  result.data.blDetails[0].rotnDate
						$scope.BLS[$scope.blIndex].rotnNo  =  result.data.blDetails[0].rotnNo
						$scope.BLS[$scope.blIndex].senderId  =  result.data.blDetails[0].senderId
						$scope.BLS[$scope.blIndex].serialNumber  =  result.data.blDetails[0].serialNumber
						$scope.BLS[$scope.blIndex].service  =  result.data.blDetails[0].service
						$scope.BLS[$scope.blIndex].shipStrDect  =  result.data.blDetails[0].shipStrDect
						$scope.BLS[$scope.blIndex].ship_itinerary  =  result.data.blDetails[0].ship_itinerary
						$scope.BLS[$scope.blIndex].ship_itinerary_sequence  =  result.data.blDetails[0].ship_itinerary_sequence
						$scope.BLS[$scope.blIndex].ship_type  =  result.data.blDetails[0].ship_type
						$scope.BLS[$scope.blIndex].shipping_line_bond_no_r  =  result.data.blDetails[0].shipping_line_bond_no_r
						$scope.BLS[$scope.blIndex].shipping_line_code  =  result.data.blDetails[0].shipping_line_code
						$scope.BLS[$scope.blIndex].shpngLineCd  =  result.data.blDetails[0].shpngLineCd
						$scope.BLS[$scope.blIndex].smBtCargo  =  result.data.blDetails[0].smBtCargo
						$scope.BLS[$scope.blIndex].smtpDate  =  result.data.blDetails[0].smtpDate
						$scope.BLS[$scope.blIndex].smtpNo  =  result.data.blDetails[0].smtpNo
						$scope.BLS[$scope.blIndex].soc_flag  =  result.data.blDetails[0].soc_flag
						$scope.BLS[$scope.blIndex].split_indicator  =  result.data.blDetails[0].split_indicator
						$scope.BLS[$scope.blIndex].split_indicator_list  =  result.data.blDetails[0].split_indicator_list
						$scope.BLS[$scope.blIndex].subLineNumber  =  result.data.blDetails[0].subLineNumber
						$scope.BLS[$scope.blIndex].subTermil  =  result.data.blDetails[0].subTermil
						$scope.BLS[$scope.blIndex].submitDateTime  =  result.data.blDetails[0].submitDateTime
						$scope.BLS[$scope.blIndex].submitter_code  =  result.data.blDetails[0].submitter_code
						$scope.BLS[$scope.blIndex].submitter_type  =  result.data.blDetails[0].submitter_type
						$scope.BLS[$scope.blIndex].terminal  =  result.data.blDetails[0].terminal
						$scope.BLS[$scope.blIndex].time_of_dept  =  result.data.blDetails[0].time_of_dept
						$scope.BLS[$scope.blIndex].toItemNo  =  result.data.blDetails[0].toItemNo
						$scope.BLS[$scope.blIndex].tol_no_of_trans_equ_manif  =  result.data.blDetails[0].tol_no_of_trans_equ_manif
						$scope.BLS[$scope.blIndex].totalBls  =  result.data.blDetails[0].totalBls
						$scope.BLS[$scope.blIndex].totalItem  =  result.data.blDetails[0].totalItem
						$scope.BLS[$scope.blIndex].totalItems  =  result.data.blDetails[0].totalItems
						$scope.BLS[$scope.blIndex].totalNmbrOfLines  =  result.data.blDetails[0].totalNmbrOfLines
						$scope.BLS[$scope.blIndex].totalWeight  =  result.data.blDetails[0].totalWeight
						$scope.BLS[$scope.blIndex].total_no_of_tran_s_cont_repo_on_ari_dep  =  result.data.blDetails[0].total_no_of_tran_s_cont_repo_on_ari_dep
						$scope.BLS[$scope.blIndex].total_no_of_transport_equipment_reported_on_arrival_departure  =  result.data.blDetails[0].total_no_of_transport_equipment_reported_on_arrival_departure
						$scope.BLS[$scope.blIndex].total_number_of_packages  =  result.data.blDetails[0].total_number_of_packages
						$scope.BLS[$scope.blIndex].tpBondNo  =  result.data.blDetails[0].tpBondNo
						$scope.BLS[$scope.blIndex].tpbondnoVVS  =  result.data.blDetails[0].tpbondnoVVS
						$scope.BLS[$scope.blIndex].transportMode  =  result.data.blDetails[0].transportMode
						$scope.BLS[$scope.blIndex].typeTransportMeans  =  result.data.blDetails[0].typeTransportMeans
						$scope.BLS[$scope.blIndex].type_of_cargo  =  result.data.blDetails[0].type_of_cargo
						$scope.BLS[$scope.blIndex].type_of_package  =  result.data.blDetails[0].type_of_package
						$scope.BLS[$scope.blIndex].type_of_packages_hidden  =  result.data.blDetails[0].type_of_packages_hidden
						$scope.BLS[$scope.blIndex].ucr_code  =  result.data.blDetails[0].ucr_code
						$scope.BLS[$scope.blIndex].ucr_type  =  result.data.blDetails[0].ucr_type
						$scope.BLS[$scope.blIndex].unit  =  result.data.blDetails[0].unit
						$scope.BLS[$scope.blIndex].unit_of_volume  =  result.data.blDetails[0].unit_of_volume
						$scope.BLS[$scope.blIndex].unit_of_weight  =  result.data.blDetails[0].unit_of_weight
						$scope.BLS[$scope.blIndex].uno_code  =  result.data.blDetails[0].uno_code
						$scope.BLS[$scope.blIndex].vasselCode  =  result.data.blDetails[0].vasselCode
						$scope.BLS[$scope.blIndex].vesselName  =  result.data.blDetails[0].vesselName
						$scope.BLS[$scope.blIndex].vesselNation  =  result.data.blDetails[0].vesselNation
						$scope.BLS[$scope.blIndex].vesselType  =  result.data.blDetails[0].vesselType
						$scope.BLS[$scope.blIndex].vessel_type_movement  =  result.data.blDetails[0].vessel_type_movement
						$scope.BLS[$scope.blIndex].viaVcn  =  result.data.blDetails[0].viaVcn
						$scope.BLS[$scope.blIndex].volume  =  result.data.blDetails[0].volume
						$scope.BLS[$scope.blIndex].voyage_details_movement  =  result.data.blDetails[0].voyage_details_movement
						$scope.BLS[$scope.blIndex].weigh  =  result.data.blDetails[0].weigh
						$scope.BLS[$scope.blIndex].weight  =  result.data.blDetails[0].weight
						$scope.BLS[$scope.blIndex].package_kind  =  result.data.blDetails[0].package_kind
						$scope.BLS[$scope.blIndex].commdity_code  =  result.data.blDetails[0].commdity_code
						$scope.BLS[$scope.blIndex].commodity_seq  =  result.data.blDetails[0].commodity_seq
						$scope.BLS[$scope.blIndex].gstStateCode  =  result.data.blDetails[0].gstStateCode
						$scope.BLS[$scope.blIndex].stowagePosition  =  result.data.blDetails[0].stowagePosition
						$scope.BLS[$scope.blIndex].port_of_call_name  =  result.data.blDetails[0].port_of_call_name
						$scope.BLS[$scope.blIndex].next_port_of_call_name  =  result.data.blDetails[0].next_port_of_call_name
						debugger;
			
						$scope.BLS[$scope.blIndex].consignee = result.data.blDetails[0].consignee
						$scope.BLS[$scope.blIndex].consigner = result.data.blDetails[0].consigner
						$scope.BLS[$scope.blIndex].marksNumber = result.data.blDetails[0].marksNumber
						$scope.BLS[$scope.blIndex].notifyParty = result.data.blDetails[0].notifyParty
						$scope.BLS[$scope.blIndex].notifyPartyTwo = result.data.blDetails[0].notifyPartyTwo
						$scope.BLS[$scope.blIndex].previousDeclaration = result.data.blDetails[0].previousDeclaration

						    $scope.getConsinee();
							$scope.getDataMoveToNextTab();
							
						
						$("body").find('.loading').remove();
			});
		
		 
	}
	
	$scope.getCarogoDetailsHBL=function() {
 	    debugger;
 	    if($scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].fetch==true){
 	    	return false;
 	    }
 	   if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null && $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
 	 
 	    var 		bl			= $scope.BLS[$scope.blIndex].bl;
 	    var 		hblNo		= $scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].hblNo;
 	    isBlSaved 				= $scope.BLS[$scope.blIndex].isBlSave;
 	    itemNumber 				= $scope.selectedBL.itemNumber
 	    var 		vessel 		= $scope.selectedServcies.vessel;
		var 		voyage 		= $scope.selectedServcies.voyage;
		var 		pod 		= $scope.selectedServcies.pod;
		var 		service     = $scope.selectedServcies.service;
		
		var 		url 		= CAROGODETAILSSEARCHHBL+"?vessel="+vessel+"&voyage="+voyage+"&pod="+pod+"&bl="+bl+"&isBlSave="+isBlSaved+"&itemNumber="+itemNumber+"&service="+service;
 	    $( "body" ).append('<div class="loading"></div>');
		$http({
				method : "POST",
				async : true,
				url : url,
			  }).then(function(result, status, headers, config) {			 
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].agencyType  =  result.data.blDetails[0].agencyType
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].agentCode  =  result.data.blDetails[0].agentCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].arrivalDate  =  result.data.blDetails[0].arrivalDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].arrivalTime  =  result.data.blDetails[0].arrivalTime
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].arrival_departure_details  =  result.data.blDetails[0].arrival_departure_details
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ataarrivalDate  =  result.data.blDetails[0].ataarrivalDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ataarrivalTime  =  result.data.blDetails[0].ataarrivalTime
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].authRepCd  =  result.data.blDetails[0].authRepCd
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].authReprsntvCd  =  result.data.blDetails[0].authReprsntvCd
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].authoriz_rep_code  =  result.data.blDetails[0].authoriz_rep_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].authorized_sea_carrier_code  =  result.data.blDetails[0].authorized_sea_carrier_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].blStatus  =  result.data.blDetails[0].blStatus
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].blVersion  =  result.data.blDetails[0].blVersion
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].brief_cargo_des  =  result.data.blDetails[0].brief_cargo_des
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].callSing  =  result.data.blDetails[0].callSing
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargoDeclaration  =  result.data.blDetails[0].cargoDeclaration
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargoMovmntType  =  result.data.blDetails[0].cargoMovmntType
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargoNature  =  result.data.blDetails[0].cargoNature
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargo_description  =  result.data.blDetails[0].cargo_description
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargo_item_description  =  result.data.blDetails[0].cargo_item_description
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cargo_item_sequence_no  =  result.data.blDetails[0].cargo_item_sequence_no
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].carrierNo  =  result.data.blDetails[0].carrierNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cbm  =  result.data.blDetails[0].cbm
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cfsName  =  result.data.blDetails[0].cfsName
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cigmDate  =  result.data.blDetails[0].cigmDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cigmNo  =  result.data.blDetails[0].cigmNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cin_type  =  result.data.blDetails[0].cin_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].codeCode  =  result.data.blDetails[0].codeCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].consolidatedIndicator  =  result.data.blDetails[0].consolidatedIndicator
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].consolidated_indicator  =  result.data.blDetails[0].consolidated_indicator
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].consolidator_pan  =  result.data.blDetails[0].consolidator_pan
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].container_weight  =  result.data.blDetails[0].container_weight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].conveyance_reference_no  =  result.data.blDetails[0].conveyance_reference_no
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].crewEffect  =  result.data.blDetails[0].crewEffect
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].crewListDeclaration  =  result.data.blDetails[0].crewListDeclaration
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_date  =  result.data.blDetails[0].csn_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_number  =  result.data.blDetails[0].csn_number
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_reporting_type  =  result.data.blDetails[0].csn_reporting_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_site_id  =  result.data.blDetails[0].csn_reporting_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_submitted_by  =  result.data.blDetails[0].csn_submitted_by
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].csn_submitted_type  =  result.data.blDetails[0].csn_submitted_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].currency  =  result.data.blDetails[0].currency
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cusAdd1  =  result.data.blDetails[0].cusAdd1
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cusAdd2  =  result.data.blDetails[0].cusAdd2
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cusAdd3  =  result.data.blDetails[0].cusAdd3
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].cusAdd4  =  result.data.blDetails[0].cusAdd4
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].customCode  =  result.data.blDetails[0].customCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].customTerminalCode  =  result.data.blDetails[0].customTerminalCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].del  =  result.data.blDetails[0].del
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].dep_manif_no  =  result.data.blDetails[0].dep_manif_no
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].dep_manifest_date  =  result.data.blDetails[0].dep_manifest_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].departureDate  =  result.data.blDetails[0].departureDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].departureTime  =  result.data.blDetails[0].departureTime
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].depot  =  result.data.blDetails[0].depot
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].dpdCode  =  result.data.blDetails[0].dpdCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].dpdMovement  =  result.data.blDetails[0].dpdMovement
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].dutyInr  =  result.data.blDetails[0].dutyInr
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].edi  =  result.data.blDetails[0].edi
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].enblockMovement  =  result.data.blDetails[0].enblockMovement
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].equimentType  =  result.data.blDetails[0].equimentType
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].equipment_load_status  =  result.data.blDetails[0].equipment_load_status
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].equipment_seal_type  =  result.data.blDetails[0].equipment_seal_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].exchangeRate  =  result.data.blDetails[0].exchangeRate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].expected_date  =  result.data.blDetails[0].expected_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].fetch  =  result.data.blDetails[0].fetch
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].finalPlaceDelivery  =  result.data.blDetails[0].finalPlaceDelivery
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].first_port_of_entry_last_port_of_departure  =  result.data.blDetails[0].first_port_of_entry_last_port_of_departure
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].fromItemNo  =  result.data.blDetails[0].fromItemNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].genDesc  =  result.data.blDetails[0].genDesc
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].grosWeight  =  result.data.blDetails[0].grosWeight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].grossCargoWeightBLlevel  =  result.data.blDetails[0].grossCargoWeightBLlevel
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].grossWeight  =  result.data.blDetails[0].grossWeight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].gross_volume  =  result.data.blDetails[0].gross_volume
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].hblNo  =  result.data.blDetails[0].hblNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].hightValue  =  result.data.blDetails[0].hightValue
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].igmDate  =  result.data.blDetails[0].igmDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].igmDateVal  =  result.data.blDetails[0].igmDateVal
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].igmNumber  =  result.data.blDetails[0].igmNumber
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].igmYear  =  result.data.blDetails[0].igmYear
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].igmYearVal  =  result.data.blDetails[0].igmYearVal
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].imdg_code  =  result.data.blDetails[0].imdg_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].imoCode  =  result.data.blDetails[0].imoCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].invoiceItems  =  result.data.blDetails[0].invoiceItems
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].invoiceValueFc  =  result.data.blDetails[0].invoiceValueFc
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].invoiceValueInr  =  result.data.blDetails[0].invoiceValueInr
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].isValidateBL  =  result.data.blDetails[0].isValidateBL
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].itemType  =  result.data.blDetails[0].itemType
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].jobDate  =  result.data.blDetails[0].jobDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].jobNo  =  result.data.blDetails[0].jobNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].job_date  =  result.data.blDetails[0].job_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].job_number  =  result.data.blDetails[0].job_number
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].lastPort1  =  result.data.blDetails[0].lastPort1
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].lastPort2  =  result.data.blDetails[0].lastPort2
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].lastPort3  =  result.data.blDetails[0].lastPort3
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].lightDue  =  result.data.blDetails[0].lightDue
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].lineCode  =  result.data.blDetails[0].lineCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].manifest_date_csn_date  =  result.data.blDetails[0].manifest_date_csn_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].manifest_no_csn_no  =  result.data.blDetails[0].manifest_no_csn_no
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mariTimeDecl  =  result.data.blDetails[0].mariTimeDecl
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].masterBl  =  result.data.blDetails[0].masterBl
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].masterBlDate  =  result.data.blDetails[0].masterBlDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].masterName  =  result.data.blDetails[0].masterName
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mblNo  =  result.data.blDetails[0].mblNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mc_item_details  =  result.data.blDetails[0].mc_item_details
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mc_location_customs  =  result.data.blDetails[0].mc_location_customs
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mcin  =  result.data.blDetails[0].mcin
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].message_type  =  result.data.blDetails[0].message_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].modeOfTpFee  =  result.data.blDetails[0].modeOfTpFee
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].mode_of_transport  =  result.data.blDetails[0].mode_of_transport
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].multipalPakages  =  result.data.blDetails[0].multipalPakages
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].neCargoMovmnt  =  result.data.blDetails[0].neCargoMovmnt
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].netWeight  =  result.data.blDetails[0].netWeight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].newVessel  =  result.data.blDetails[0].newVessel
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].newVoyage  =  result.data.blDetails[0].newVoyage
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].next_port_of_call_coded  =  result.data.blDetails[0].next_port_of_call_coded
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].nextport1  =  result.data.blDetails[0].nextport1
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].nextport2  =  result.data.blDetails[0].nextport2
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].nextport3  =  result.data.blDetails[0].nextport3
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].nhavaShevaEta  =  result.data.blDetails[0].nhavaShevaEta
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].noOfCrew  =  result.data.blDetails[0].noOfCrew
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].noOfItemInFil  =  result.data.blDetails[0].noOfItemInFil
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].noOfItemInPrior  =  result.data.blDetails[0].noOfItemInPrior
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].noOfItemInSupplimentary  =  result.data.blDetails[0].noOfItemInSupplimentary
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].noOfPassenger  =  result.data.blDetails[0].noOfPassenger
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].no_of_crew_manif  =  result.data.blDetails[0].no_of_crew_manif
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].nonEdi  =  result.data.blDetails[0].nonEdi
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].notifyPartyCode  =  result.data.blDetails[0].notifyPartyCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].number_of_crew  =  result.data.blDetails[0].number_of_crew
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].number_of_packages  =  result.data.blDetails[0].number_of_packages
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].number_of_packages_hidden  =  result.data.blDetails[0].number_of_packages_hidden
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].packageBLLevel  =  result.data.blDetails[0].packageBLLevel
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].packages  =  result.data.blDetails[0].packages
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].pan_of_notified_party  =  result.data.blDetails[0].pan_of_notified_party
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].parentVoy  =  result.data.blDetails[0].parentVoy
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].passengerList  =  result.data.blDetails[0].passengerList
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].podTerminal  =  result.data.blDetails[0].podTerminal
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].podTerminalPort  =  result.data.blDetails[0].podTerminalPort
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].pol  =  result.data.blDetails[0].pol
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].polTerminal  =  result.data.blDetails[0].polTerminal
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].polTerminalPort  =  result.data.blDetails[0].polTerminalPort
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].portArrival  =  result.data.blDetails[0].portArrival
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].portOfDeschargedCfs  =  result.data.blDetails[0].portOfDeschargedCfs
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].portOfDestination  =  result.data.blDetails[0].portOfDestination
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].portOrigin  =  result.data.blDetails[0].portOrigin
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_acceptance  =  result.data.blDetails[0].port_of_acceptance
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_acceptance_name  =  result.data.blDetails[0].port_of_acceptance_name
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_call_cod  =  result.data.blDetails[0].port_of_call_cod
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_call_coded  =  result.data.blDetails[0].port_of_call_coded
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_call_name  =  result.data.blDetails[0].port_of_call_name
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_call_sequence_number  =  result.data.blDetails[0].port_of_call_sequence_number
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_receipt  =  result.data.blDetails[0].port_of_receipt
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_receipt_name  =  result.data.blDetails[0].port_of_receipt_name
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_registry  =  result.data.blDetails[0].port_of_registry
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].port_of_reporting  =  result.data.blDetails[0].port_of_reporting
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].position  =  result.data.blDetails[0].position
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].previous_mcin  =  result.data.blDetails[0].previous_mcin
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].previous_pcin  =  result.data.blDetails[0].previous_pcin
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].recieverId  =  result.data.blDetails[0].recieverId
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].registry_date  =  result.data.blDetails[0].registry_date
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].remark  =  result.data.blDetails[0].remark
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].remarkVessel  =  result.data.blDetails[0].remarkVessel
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].reporting_event  =  result.data.blDetails[0].reporting_event
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].roadCarrCode  =  result.data.blDetails[0].roadCarrCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].roadCarrCodeVVS  =  result.data.blDetails[0].roadCarrCodeVVS
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].roadTPBondNo  =  result.data.blDetails[0].roadTPBondNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].roadTpBondNo  =  result.data.blDetails[0].roadTpBondNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].rotnDate  =  result.data.blDetails[0].rotnDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].rotnNo  =  result.data.blDetails[0].rotnNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].senderId  =  result.data.blDetails[0].senderId
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].serialNumber  =  result.data.blDetails[0].serialNumber
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].service  =  result.data.blDetails[0].service
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].shipStrDect  =  result.data.blDetails[0].shipStrDect
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ship_itinerary  =  result.data.blDetails[0].ship_itinerary
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ship_itinerary_sequence  =  result.data.blDetails[0].ship_itinerary_sequence
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ship_type  =  result.data.blDetails[0].ship_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].shipping_line_bond_no_r  =  result.data.blDetails[0].shipping_line_bond_no_r
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].shipping_line_code  =  result.data.blDetails[0].shipping_line_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].shpngLineCd  =  result.data.blDetails[0].shpngLineCd
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].smBtCargo  =  result.data.blDetails[0].smBtCargo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].smtpDate  =  result.data.blDetails[0].smtpDate
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].smtpNo  =  result.data.blDetails[0].smtpNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].soc_flag  =  result.data.blDetails[0].soc_flag
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].split_indicator  =  result.data.blDetails[0].split_indicator
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].split_indicator_list  =  result.data.blDetails[0].split_indicator_list
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].subLineNumber  =  result.data.blDetails[0].subLineNumber
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].subTermil  =  result.data.blDetails[0].subTermil
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].submitDateTime  =  result.data.blDetails[0].submitDateTime
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].submitter_code  =  result.data.blDetails[0].submitter_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].submitter_type  =  result.data.blDetails[0].submitter_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].terminal  =  result.data.blDetails[0].terminal
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].time_of_dept  =  result.data.blDetails[0].time_of_dept
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].toItemNo  =  result.data.blDetails[0].toItemNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].tol_no_of_trans_equ_manif  =  result.data.blDetails[0].tol_no_of_trans_equ_manif
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].totalBls  =  result.data.blDetails[0].totalBls
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].totalItem  =  result.data.blDetails[0].totalItem
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].totalItems  =  result.data.blDetails[0].totalItems
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].totalNmbrOfLines  =  result.data.blDetails[0].totalNmbrOfLines
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].totalWeight  =  result.data.blDetails[0].totalWeight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].total_no_of_tran_s_cont_repo_on_ari_dep  =  result.data.blDetails[0].total_no_of_tran_s_cont_repo_on_ari_dep
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].total_no_of_transport_equipment_reported_on_arrival_departure  =  result.data.blDetails[0].total_no_of_transport_equipment_reported_on_arrival_departure
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].total_number_of_packages  =  result.data.blDetails[0].total_number_of_packages
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].tpBondNo  =  result.data.blDetails[0].tpBondNo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].tpbondnoVVS  =  result.data.blDetails[0].tpbondnoVVS
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].transportMode  =  result.data.blDetails[0].transportMode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].typeTransportMeans  =  result.data.blDetails[0].typeTransportMeans
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].type_of_cargo  =  result.data.blDetails[0].type_of_cargo
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].type_of_package  =  result.data.blDetails[0].type_of_package
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].type_of_packages_hidden  =  result.data.blDetails[0].type_of_packages_hidden
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ucr_code  =  result.data.blDetails[0].ucr_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].ucr_type  =  result.data.blDetails[0].ucr_type
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].unit  =  result.data.blDetails[0].unit
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].unit_of_volume  =  result.data.blDetails[0].unit_of_volume
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].unit_of_weight  =  result.data.blDetails[0].unit_of_weight
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].uno_code  =  result.data.blDetails[0].uno_code
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].vasselCode  =  result.data.blDetails[0].vasselCode
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].vesselName  =  result.data.blDetails[0].vesselName
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].vesselNation  =  result.data.blDetails[0].vesselNation
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].vesselType  =  result.data.blDetails[0].vesselType
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].vessel_type_movement  =  result.data.blDetails[0].vessel_type_movement
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].viaVcn  =  result.data.blDetails[0].viaVcn
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].volume  =  result.data.blDetails[0].volume
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].voyage_details_movement  =  result.data.blDetails[0].voyage_details_movement
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].weigh  =  result.data.blDetails[0].weigh
						$scope.BLS[$scope.blIndex].hblArr[$scope.selectedHBLRow].weight  =  result.data.blDetails[0].weight

					
						
						$scope.getDataMoveToNextTab();
					 
						$("body").find('.loading').remove();
			});
		
		 
	}
	
	$scope.getContainerDetails=function(val) {
		debugger;
		if($scope.BLS[$scope.blIndex].containerDetailes != undefined  && $scope.BLS[$scope.blIndex].containerDetailes.length>0){
			return false;
		}
		if(($scope.selectedBL.isBlSave == 'true' || $scope.selectedBL.isBlSave == true) && ($scope.selectedBL.itemNumber !=null || $scope.selectedBL.itemNumber !="")){
			$scope.selectedBL.saveFlags="U"
		}
 	    var val=  $scope.BLS[$scope.blIndex].bl;
 	    var isSaveBl=  $scope.BLS[$scope.blIndex].isBlSave;
 	    itemNumber = $scope.selectedBL.itemNumber
		var	form = val;
		$( "body" ).append('<div class="loading"></div>');
		$http({
				method : "POST",
				async : true,
				url : CONTAINERDETAILSSEARCH+"?bl="+form+"&isBlSave="+isSaveBl+"&itemNumber="+itemNumber,
			  }).then(function(result, status, headers, config) {			 
				$scope.BLS[$scope.blIndex].containerDetailes=result.data.containerList

				$scope.containerValue();
			/* 	$scope.stowageExport(); */
				
				$("body").find('.loading').remove();
			});
	}

	$scope.containerValue = function(){
		debugger;
		$scope.container = $scope.BLS[$scope.blIndex].containerDetailes[0];
		
		}

	$scope.stowageExport = function(){

 	 	debugger;
 	 	
 	    $scope.getCarogoDetails();
 	 	$scope.containerValue(); /* getting contaner details */
 	 	
 		 var 		bl			= $scope.BLS[$scope.blIndex].bl;
 		 var        container   = $scope.container.containerNumber;
 		 var   		polTerminal = result1.result[ $window.selectedIndex].polTerminal
 		 var 		pol  		= $scope.selectedBL.portOfLoading;
 		 var 		vessel 		= $scope.selectedServcies.vessel;
 		 var 		voyage 		= $scope.selectedServcies.voyage; 

 		 var 		url 		=  GETSTOWAGEEXPORT+"?bl="+bl+"&container="+container+"&pol="+pol+"&polTerminal="+polTerminal+
		  						   "&vessel="+vessel+"&voyage="+voyage;

 		 $http({
 			async : true,
 			url : url,
 		  }).then(function(result, status, headers, config) {	

 			 console.log( result.data.blDetails ,"export........................");	
/* 
 			  $scope.selectedBL.stowageExport = result.data.blDetails[0].stowageExport;
 */
 		  }); 
 			  
	
		}

	
	$scope.cVSFileDeleteAjex = function (val){
		
		sendData = {"bl":$("#bl").val(),"checkCSV" : $("#checkCSV").val(),"vessel":$("#vessel").val(),"voyage":$("#voyage").val(),"pod":$("#pod").val()}
		
		$http({
			method : "POST",
			async : true,
			url : $window.DELETE_CSV,
			dataType: 'json',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			beforeSend:function()
			{
				loadingfun();
			},
			data : "requestParam="+encodeURIComponent(JSON.stringify(sendData)),
			 
			 
		}).then(function(data, status, headers, config) {			 
			$("body").find('.loading').remove();
			if (val === 'P')
				$scope.prsnOnBordTable=[];
				$scope.selectedServcies.noOfCrew = 0;
			
			if (val === 'S')
				$scope.shipStoreTable=[];
			if (val === 'C')
				$scope.crewEffetTable=[];
		});
	}
	
	$scope.selectedItemChanged = function(equipmentLoadStatus) {
	    $scope.equipmentLoadStatus = equipmentLoadStatus;
	}
	  	
});

   
</script>

</html>
