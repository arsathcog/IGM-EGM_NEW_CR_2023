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
   width: 100%;
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

   .inputCargo{
    margin: 4px;
    width: 50%;
    height: 22px;
    border-color: blue;
    font-size: 14px !important;
   }

   .seqCssNew {
    margin: 9px;
    width: 50%;
    height: 22px;
    border-color: blue;
    font-size: 14px !important;
}
   .seqCss {
   }
   .cargoCss{
    margin: 4px;
    width: 87%;
    height: 22px;
    border-color: blue;
    font-size: 14px !important;
    
   }
 
  .dialog-form{
  height: 552px; 
  width: 1506px;
  }
   .tableVessel-width {
   width: 120px;
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
 /*   border-right: 1px solid gray; * /
   width: 150px;
   font-size: 12px;
   font-weight: 500;
   color: black;
   }
    .bl_detail_Two {
   border-top: 1px solid gray;
   border-left: 1px solid gray;
   width: 120px;
   border-right: 1px solid gray;
   width: 75px;
   font-size: 12px;
   font-weight: 500;
   color: black;
   }
   .tab-title {
   font-weight: 500;
   font-size: 20px;
   margin: 5px 0px 5px;
   }
   <!--
   Vessel divs-->.vessel-div1, .vessel-div2, .vessel-div3, .vessel-div4 {
   display: inline;
   float: left;
   width: 300px;
   height: 100%;
   }
   .vessel-div1 {
   top: 0;
   left: 50vh;
   }
   .vessel-div2 {
   top: 0;
   left: 0;
   }
   .vessel-div3 {
   top: 0;
   left: 0;
   }
   .vessel-div4 {
   top: 50vw;
   left: 50vh;
   }
   
   .bl_detail_Store {
   border: 1px solid #dddddd;
	height			:	20px;
	text-align		:	left;
	FONT-FAMILY		:	Verdana,Arial,Helvetica,sans-serif;
	vertical-align	:	middle;
	PADDING-LEFT	: 	2px;
 	BORDER-BOTTOM 	:	#6A7896 1px solid; /*changed color and px*/
	BORDER-TOP 		:	#6A7896 1px solid; /* changed color */
}
</style>
<script>
$(document).ready(function(){
	  $("#ui-id-7").click(function(){     alert(1);
	    $('#blHeader').hide();
	    $("#vesselHeader").show();
	  });
	});                      
	$(document).ready(function(){
		  $("#ui-id-9").click(function(){   alert(2);
		    $('#blHeader').hide();
		    $("#vesselHeader").show();
		  });
		});
	$(document).ready(function(){
	  $("#ui-id-10").click(function(){       alert(3);
	    $("#vesselHeader").hide();
	     $("#blHeader").show();
	  });
	  $(document).ready(function(){
		  $("#ui-id-8").click(function(){     alert(4);
		    $("#vesselHeader").hide();
		     $("#blHeader").show();
		  });
	});
</script>
<div id="dialog-form" title="BL Details" class="dialog-form">
<div id="dialog-tabs" class="ml-4 " ng-controller="myCtrl" ng-init="init();">
   <div>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
         <tr>
            <td>
               <h2 style="height: 20px; width: 70px;"></h2>
            </td>
            <td style="float: right">
               <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
								<td>
								<select class="smallDPCss" ng-model="seletedFile"  ng-init="seletedFile = 'ackFile'">
										<option value="excelFile" >Excel</option>
										<option value="ackFile" >Ack</option>
								</select></td>

								<td>
									<form enctype="multipart/form-data" id="exlFileForm">
										<input class="event_btnbutton"
											ng-if="seletedFile == 'excelFile'" type="file" name="fileExl"
											id="fileUpload" size="50px" />
											
											 <input ng-if="seletedFile == 'excelFile'" class="event_btnbutton"
											style="width: 80px" type="button" name="figm001"
											id="btnUpload" value="Upload" onclick="onUpload()" />
									</form>
								</td>
								
								<td>
								<form  enctype="multipart/form-data" id="ackFileForm" ng-if="seletedFile == 'ackFile'">
								 <input class="event_btnbutton"
											 accept="application/json" type="file" name="ackJson"
											id="fileUpload" size="50px" />
											
									<!-- 	<input class="event_btnbutton"
											ng-if="seletedFile == 'ackFile'" name="fileAck"
											 id="ackUpload" class="event_btnbutton" style="width: 80px" type="button" 
											 value="ackUpload"  /> -->
									<button type="button"  class="btn btn-info event_btnbutton" ng-click="onUploadAck()">Upload</button>
								</form>
									
									
								</td>

								<td class="" ng-if="pageType=='EGM'">
                        <select class="smallDPCss" name="fileType"  id="fileType" >
					                           <option value="SCX">SCX</option>
					                           <option value="SDM" selected>SDM</option>
					                           <option value="SDN">SDN</option>
					                         <!--   <option value="SCC">SCC</option>
				 							   <option value="SDM">SCD</option>
					                           <option value="SCU">SCU</option>
					                           <option value="SCA">SCA</option> -->
					                         
                        </select>
                        </td>
                  
                     <td class="" ng-if="pageType=='IGM'">
                        <select class="smallDPCss" name="fileType"  id="fileType" >
                        					   <option value="SAM" selected>SAM</option>
                          	                   <option value="SCE">SCE</option>
					                           <option value="SEI">SEI</option>
					                       <!--<option value="SDN">SDN</option>
					                           <option value="SEI">SEI</option>
					                           <option value="SAA" >SAA</option>
					                           <option value="SCC">SCC</option>
					                           <option value="SCD">SCD</option>
					                           <option value="SCU">SCU</option>
					                           <option value="SCA">SCA</option> -->
                        </select>
                     </td>
                         <td class=""><input type="button" value="Genrate Data"
                        class="event_btnbutton" onclick="genrateFile()"></td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
   </div>
   <div  id="vesselHeader"> <jsp:include page="vesselHeader.jsp"></jsp:include> </div>
   <div id="blHeader" style="display:none" > <jsp:include page="blHeader.jsp"></jsp:include> </div>
   <div id="portLookupId" style="display:none" > <jsp:include page="ports.jsp"></jsp:include> </div>     
   <div id="saveDataModalId" style="display:none" > <jsp:include page="savePageDialog.jsp"></jsp:include> </div> 
   <ul>
       <li><a href="#vesselSection"> <span>Vessel/Voyage Search Details</span></a> </li>
       <li><a href="#prsnOnBord" ng-click = "getprscrwshipSearch('P')" ><span>Person On Board</span></a>   </li>
       <li><a href="#crwEffect"  ng-click = "getprscrwshipSearch('C')"> <span>Crew Effect</span></a></li>
       <li><a href="#shipStors"	 ng-click = "getprscrwshipSearch('S')"> <span>Ship Stors</span></a></li>
       <li><a href="#blContainer" ng-click = "getCarogoDetails()"><span>Cargo Details {{BLS[blIndex].bl}}</span></a></li>
       <li><a href="#consigneeContainer" ng-click = "getContainerDetails()"> <span>Container Details</span></a></li>
       <li><a href="#bl-New"> <span>BL Details</span></a></li>
       
   </ul>
    <div id="vesselSection"><jsp:include page="vesselSection.jsp"></jsp:include></div>
    <div id="prsnOnBord"><jsp:include page="personOnBoard.jsp"></jsp:include></div>
    <div id="crwEffect"><jsp:include page="crewEffect.jsp"></jsp:include></div>
    <div id="shipStors"><jsp:include page="shipStors.jsp"></jsp:include></div>
    <div id="blContainer"><jsp:include page="CargoDetail.jsp"></jsp:include> </div>
    <div id="consigneeContainer"> <jsp:include page="container.jsp"></jsp:include> </div>
    <div  id="bl-New"><jsp:include page="blDetailNew.jsp"></jsp:include></div>
     
         
   
      <br>
      <div id="container">
         <table  id="blTable">
            <tr id="row3">
               <td id="marksNumberinfo"></td>
            </tr>
         </table>
      </div>
   <h2 style="height: 20px; width: 70px;">{{msg}}</h2>
</div>
</div>
<script>
function applySave(){
	sendData = {"bl":$("#bl").val(),"igmDate" : $("#igmDate").val(),"igmNo" : $("#igmNumber").val()}   
	/*send data to backend*/
	$.ajax({
		method : "POST",
		async : true,
		url : APPLYSAVE,
		data : sendData,
		success : function(result) {},
        error: function(error){
			showBarMessages("error : "+error.responseText,1);
        }
	  });
	}
	
function downloadCVSFileAjax(val){
	
    var type=val;
	sendData = {"bl":$("#bl").val(),"checkCSV" : type ,"vessel":$("#vessel").val(),"voyage":$("#voyage").val(),"pod":$("#pod").val()}
		/*send data to backend*/
	$.ajax({
		method : "POST",
		async : true,
		url : DOWNLOADCSV,
		data : sendData,
		success : function(result) {
			
			var filename = "";
			if (val === 'P')
				filename = "personOnBoard.csv";
			if (val === 'S')
				filename = "shipStore.csv";
			if (val === 'C')
				filename = "crewEffect.csv";
			var resultToJson = JSON.parse(result);
			var res= [];
			res = resultToJson.result;
			downloadCSVFromJson(filename, res)

			
		},
        error: function(error){
			showBarMessages("error : "+error.responseText,1);
        }
	  });
	}

	downloadCSVFromJson = (filename, arrayOfJson) => {
		  // convert JSON to CSV
		  debugger;
		  const replacer = (key, value) => value === null ? '' : value // specify how you want to handle null values here
		  const header = Object.keys(arrayOfJson[0])
		  let csv = arrayOfJson.map(row => header.map(fieldName => 
		  JSON.stringify(row[fieldName], replacer)).join(','))
		  csv.unshift(header.join(','))
		  csv = csv.join('\r\n')

		  // Create link and download
		  var link = document.createElement('a');
		  link.setAttribute('href', 'data:text/csv;charset=utf-8,%EF%BB%BF' + encodeURIComponent(csv));
		  link.setAttribute('download', filename);
		  link.style.visibility = 'hidden';
		  document.body.appendChild(link);
		  link.click();
		  document.body.removeChild(link);
		  swal("Message",filename+ " downloaded successfully.","info");
		}; 

</script>