 <div class="flex-container">
         <div class="flex-bl-list">

		<div style="height: 200px;">
			<table>
				<tbody>
					<tr>
						  <td>Line No.</td>
						
						   <td><input type="text" class="cargoCss" value="{{BLS[blIndex].itemNumber}}" ng-model="selectedBL.itemNumber" disabled></td>
						    <td>Sub Line No</td>
						    <td><input type="text" class="cargoCss" value="{{BLS[blIndex].subLineNumber}}" ng-model="selectedBL.subLineNumber"></td>
					</tr>
				</tbody>
			</table>
			<br>
			<fieldset>
				<legend>Bill's of Landing</legend>
				<table>
					<tbody>
						<tr>
							<td>Master B/L No & Date(House B/L Merging)</td>
						</tr>
						<tr>
							<td><input type="text" class="seqCssNew" value="{{BLS[blIndex].masterBl}}" ng-model="selectedBL.masterBl"> 
							    <input type="text"  class="seqCssNew" value="{{BLS[blIndex].masterBlDate}}" ng-model="selectedBL.masterBlDate"> </td>
						</tr>
						<tr>
							<td>B/L NO & Date</td>
							<td>
						</tr>
						<tr>
							<td><input type="text"  class="seqCssNew" value="{{BLS[blIndex].bl}}" ng-model="selectedBL.bl" maxlength="20"> 
							<input type="text"  class="seqCssNew" value="{{BLS[blIndex].blDate}}" ng-model="selectedBL.blDate" id="blNoDate" readonly="true" onclick="dateToCommon(this)" ng-change="setTwoNumberDecimal(this,blIndex,12,3,'MasterBl');"> </td>
						</tr>
					</tbody>
				</table>
			</fieldset>
<br>
			<fieldset>
				<legend>NON-EDI</legend>
				<table>
					<tbody>
						<tr>
							<td>Cargo Movement</td>    </tr>{{selectedBL.neCargoMovmnt}}
						<tr>	<td> 
						    <select class="selectCss"
								ng-model="selectedBL.neCargoMovmnt"  >            
									<option value="FCL">FCL</option> 
									<option value="LCL">LCL</option>
									<option value="BKL">BKL</option>
									<option value="TSC">TSC</option>
							</select>
						</td>    </tr>

						 <tr>	<td>Item Type</td>    </tr>  
						 <tr>	<td>
									  <select class="smallDPCss" ng-model="selectedBL.itemType" ng-options="x for x in itemTypeArray">
									  </select>
								</td>   
						 </tr>	
					</tbody>
				</table>
			</fieldset>
		</div>

	</div>
         <div class="flex-bl-detail">

		<div style="float:left; width: 725px; height: 100%; background: #efefeb;">
           <table class="whitebg" border="0" style="display: inline-table; background-color: #CCCCCC;">
           
           
             <tr style="line-height: .7rem">
             <th class="veseelRemoveBorder" >Place of Receipt</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].port_of_receipt}}" ng-model="selectedBL.port_of_receipt" >
              	</td>
               
              <!-- chandu -->
                <td> <input type="text" class="cargoCss"   value="">
              </td>
              <td>
              </td>
             </tr>
             <tr style="line-height: .7rem">
             <th class="veseelRemoveBorder" >Port of Loading</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].portOfLoading}}" ng-model="selectedBL.portOfLoading">
              	</td>
               <th class="veseelRemoveBorder" >Consolidated indicator</th>   
               <td ng-if="pageType=='IGM'">
                 <select class="selectCss" ng-model="selectedBL.consolidatedIndicator" ng-options="x for x in consolIdatedIndicatorArray">
                  </select>
              	</td>
              	<td ng-if="pageType=='EGM'">
  						<input type="text" class="cargoCss" ng-model="selectedBL.consolidatedIndicator" value="{{BLS[blIndex].consolidatedIndicator}}" > 
               </td>
             </tr>
                <tr style="line-height: .7rem">
                <th class="veseelRemoveBorder" >  Port of Destination</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].portOfDestination}}" ng-model="selectedBL.portOfDestination" >
              	</td>
              	  </td> 
              	    <th class="veseelRemoveBorder">POD</th>
                <td>
                 <input type="text" class="cargoCss" ng-model="selectedBL.pod" value=""> 
                </td>
             <!-- chandu -->
                <td> <input type="text" class="cargoCss"  value="">
              </td>
              <td>
              </td>
             </tr>
                
             <tr style="line-height: .7rem">
                
               <th class="veseelRemoveBorder" ng-if="pageType=='EGM'" >Final place of delivery(FPD)</th>
               <th class="veseelRemoveBorder" ng-if="pageType=='IGM'" >CFS</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].type_of_package}}" ng-model="selectedBL.portOfDeschargedCfs">
             
              <th class="veseelRemoveBorder">BL Type</th> 
              <td ng-if="pageType=='IGM'">
                  <select class="selectCss" ng-model="selectedBL.blType" ng-options="x for x in blTypeArray">
                  </select>
               </td>
                <td ng-if="pageType=='EGM'">
  						<input type="text" class="cargoCss" ng-model="selectedBL.blType" value="{{BLS[blIndex].blType}}" > 
               </td>
             </tr>
               
               <tr style="line-height: .7rem"> 
               <th class="veseelRemoveBorder">DPD Movement</th>
               <td ng-if="pageType=='IGM'" >
               			 <!-- <select class="smallDPCss" ng-model="selectedBL.dpdMovement" ng-options="x for x in socFlagObj">
						 </select> -->
                  		<input type="text" class="cargoCss" ng-model="selectedBL.dpdMovement" value="{{BLS[blIndex].dpdMovement}}" > 
               </td>
               <td ng-if="pageType=='EGM'" >
               		     <input type="text" class="cargoCss" ng-model="selectedBL.dpdMovement" value="{{BLS[blIndex].dpdMovement}}" > 
               </td> 
               <th class="veseelRemoveBorder">DPD Code</th>
               <td>
                 <input type="text" class="cargoCss" ng-model="selectedBL.dpdCode" value="{{BLS[blIndex].dpdCode}}">
               </td>
               </tr>
               
               <tr style="line-height: .7rem"> 
               <th class="veseelRemoveBorder"  >Total Packages</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].total_number_of_packages}}" ng-model="BLS[blIndex].total_number_of_packages" >
               </td>
                <th class="veseelRemoveBorder"  >Type</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].type_of_package}}" ng-model="selectedBL.type_of_package"  >
               </td>
              </tr>
               
               <tr style="line-height: .7rem">

               <th class="veseelRemoveBorder" >Multiple packages
              
               </th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].multipalPakages}}" ng-model="selectedBL.multipalPakages"  >
               </td>

               <th class="veseelRemoveBorder" >Cargo Nature</th>
               <td>
               			<select class="selectCss" ng-model="selectedBL.cargoNature" ng-options="x for x in cargoNatureArray">
						</select>
               </td>
               </tr>
                <tr style="line-height: .7rem">
               
               <th class="veseelRemoveBorder">Gross Weight</th>

               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].grossWeight}}" ng-model="selectedBL.grossWeight" ng-change="setTwoNumberDecimal(this,blIndex,12,3,'grosWeight');" >
               </td>
               
               <th class="veseelRemoveBorder">Unit</th>
               <td>
               <select class="selectCss" ng-model="selectedBL.unit_of_weight" ng-options="x for x in unitArray">
				</select>
                <!--     <select class="smallDPCss" ng-model="selectedBL.unit_of_weight"  >
							<option value="{{BLS[blIndex].unit_of_weight}}">{{BLS[blIndex].unit_of_weight}}</option>
							<option value="KGS">KGS</option> 
							<option value="MTS">MTS</option>
							<option value="MBT">MBT</option>
							
					</select> -->
               </td>
               </tr>
               <tr style="line-height: .7rem">
               
                <th class="veseelRemoveBorder" >Cargo Movement
               </th>
               <td>
                <select class="selectCss" ng-model="selectedBL.cargoMovmnt" ng-options="x for x in cargoMovmentArray">
				</select>
                <!--  <input type="text" class="cargoCss" value="{{BLS[blIndex].cargoMovmnt}}" disabled="disabled"> -->
               </td>
                  <th class="veseelRemoveBorder">Item Type
               </th>
               <td>
                   <select class="selectCss" ng-model="selectedBL.itemType"  > <!-- TODO disabled in iteam no is not null -->
				 		<option value="OT">OT</option>
				 		<option value="UB">UB</option>
				 		<option value="GC">GC</option>
				   </select>
                <!--  <input type="text" class="cargoCss" value="{{BLS[blIndex].itemType}}"  ng-model="selectedBL.itemType" disabled="disabled"> -->
               </td>
             </tr>
             <tr>
               <th class="veseelRemoveBorder" >CBM</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].cbm}}" ng-model="selectedBL.cbm" >
               </td>
               <th class="veseelRemoveBorder" >High Value</th>

               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].hightValue}}" ng-model="selectedBL.hightValue" >
                 <!-- <input type="text" class="cargoCss" value="{{BLS[blIndex].hightValue}}" ng-model="selectedBL.hightValue" disabled="disabled"> -->
               </td>
              
             </tr>
			<tr>
               <th class="veseelRemoveBorder">Volume</th>
               <td>
                 <input type="text" class="cargoCss" ng-model="selectedBL.volume" value="{{BLS[blIndex].volume}}">

						<!-- hidden fields -->
						<input type="hidden" class="cargoCss"
						value="{{selectedBL.consolidatorPan}}"
						ng-model="selectedBL.consolidatorPan"> <input type="hidden"
						class="cargoCss" value="{{selectedBL.nxtPrtOfUnlading}}"
						ng-model="selectedBL.nxtPrtOfUnlading"> <input type="hidden"
						class="cargoCss" ng-model="selectedBL.typOfCrgo"
						value="selectedBL.typOfCrgo"> <input type="hidden"
						class="cargoCss" ng-model="selectedBL.prtOfAcptCdd"
						value="selectedBL.prtOfAcptCdd"> <input type="hidden"
						class="cargoCss" ng-model="selectedBL.prtOfAcptName"
						value="selectedBL.prtOfAcptName"> <input type="hidden"
						class="cargoCss" ng-model="selectedBL.prtOfReceiptCdd"
						value="selectedBL.prtOfReceiptCdd"> <input type="hidden"
						class="cargoCss" ng-model="selectedBL.prtOfReceiptName"
						value="selectedBL.prtOfReceiptName"> <input type="hidden"
						class="cargoCss" value="{{selectedBL.hsCd}}"
						ng-model="selectedBL.hsCd">
						<!--new fields addded  -->
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.houseBl"
						value="selectedBL.houseBl">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.dn_pld "
						value="selectedBL.dn_plr">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.dn_plr"
						value="selectedBL.dn_plr">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.gstStateCode"
						value="selectedBL.gstStateCode">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.pointName"
						value="selectedBL.pointName">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.portName"
						value="selectedBL.portName">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.acceptanceName"
						value="selectedBL.acceptanceName">
						<input type="hidden"
						class="cargoCss" ng-model="selectedBL.recieptName "
						value="selectedBL.recieptName ">
						
						<!-- hidden fields -->

					</td>

					<th class="veseelRemoveBorder">Unit</th>
               <td>
                 <input type="text" class="cargoCss" ng-model="selectedBL.unit" value="{{BLS[blIndex].unit}}">
               </td>
             </tr>
             <tr>
               <th class="veseelRemoveBorder" ng-if="pageType=='EGM'" >IMO Class</th>
               <th class="veseelRemoveBorder"  ng-if="pageType=='IGM'" >IMO Number</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].csn_submitted_type}}" ng-model="selectedBL.csn_submitted_type" >
               </td>
               <th class="veseelRemoveBorder" >UNO</th>
               <td>
                 <input type="text" class="cargoCss" value="{{BLS[blIndex].uno_code}}" ng-model="selectedBL.uno_code">
                 <!-- hidden fields -->
                 <input type="hidden" class="cargoCss" value="{{selectedBL.crgoItemSeqNmbr}}" ng-model="selectedBL.crgoItemSeqNmbr">
                 <input type="hidden" class="cargoCss" value="{{selectedBL.hsCd}}" ng-model="selectedBL.hsCd">
                 <input type="hidden" class="cargoCss" value="{{selectedBL.crgoItemDesc}}" ng-model="selectedBL.crgoItemDescsCd">
                 <input type="hidden" class="cargoCss" value="{{selectedBL.imdgCd}}" ng-model="selectedBL.imdgCd">
                 
               </td>
             </tr>
             <tr>
               <th class="veseelRemoveBorder" >EnBlock Movement
               </th>
               <td>
               <select class="selectCss" ng-model="selectedBL.enblockMovement"  >
				<option value="{{BLS[blIndex].enblockMovement}}">{{BLS[blIndex].enblockMovement}}</option></select>
                <!--  <input type="text" class="cargoCss" value="{{BLS[blIndex].enblockMovement}}" ng-model="selectedBL.enblockMovement" disabled="disabled"> -->
               </td>
             </tr>
            
           </table>
           
           </div>
           
           <div style="float:left; width: 358px; height: 100%;">
         <!--  	<fieldset>
				<legend>TP-Details</legend> -->
          <table class="whitebg" border="0" style="display: inline-table; background-color: #CCCCCC; ">
          
          <tr>
          <th class="veseelRemoveBorder">
         	</th>
         	 <td>
         	 <input class="event_btnbutton" style="width: 100px" type="button" name="figm001" id="btnUpload" value="Previous"  ng-click="setPrevious()">&nbsp;&nbsp;
             <input class="event_btnbutton" style="width: 100px" type="button" name="figm001" id="btnUpload" value="Next"  ng-click="setNext()">
             
               </td></tr>
         	<tr>
               <th class="veseelRemoveBorder">TP No</th>
                  <td><input type="text"  class="seqCssNew" style="width:35%" value="{{BLS[blIndex].tpNo}}" ng-model="selectedBL.tpNo"> 
					<input type="text" style="width:35%" class="seqCssNew" id="tpDate" value="{{BLS[blIndex].tpDate}}" ng-model="selectedBL.tpDate"> </td>
               </tr>
         	<tr>
               <th class="veseelRemoveBorder"  >Transport Mode</th>
               
                <td>
                
                 <select class="SelectCargo" ng-model="selectedBL.mode_of_transport"  >
				 <option value="{{BLS[blIndex].mode_of_transport}}">{{BLS[blIndex].mode_of_transport}}</option>
                        <option value="1" >Sea</option>
                        <option value="2">Rail</option>
                        <option value="3">Truck</option>
                        <option value="4">Air</option>
                     </select>
            <!--   <input type="text" class="cargoCss" value="{{BLS[blIndex].transportMode}}" disabled="disabled">  -->
               
               </td>
              </tr>
				<tr>
               <th class="veseelRemoveBorder">TP Bond No</th>

               <td>
                 <input type="text" class="inputCargo" value="{{BLS[blIndex].tpBondNo}}" ng-model="selectedBL.tpBondNo">
               </td>
                
               </tr>
               
               <tr>
               <th class="veseelRemoveBorder"  >Carrier No</th>
               
                <td>
                 <input type="text" class="inputCargo" value="{{BLS[blIndex].carrierNo}}"   ng-model="selectedBL.carrierNo">
               
               </td>
</tr>
	<tr>
               <th class="veseelRemoveBorder">Agency Type</th>

               <td>
                  <input type="text" class="inputCargo" value="{{BLS[blIndex].agencyType}}"   ng-model="selectedBL.agencyType">
               </td>
                
               </tr>

				<tr>
               <th class="veseelRemoveBorder">Invoice Value(FC)</th>

               <td>
                  <input type="text" class="inputCargo" value="{{BLS[blIndex].invoiceValueFc}}"   ng-model="selectedBL.invoiceValueFc">
               </td>
                
               </tr>

<tr>
               <th class="veseelRemoveBorder">Invoice Value(INR)</th>

               <td>
                  <input type="text" class="inputCargo" value="{{BLS[blIndex].invoiceValueInr}}"   ng-model="selectedBL.invoiceValueInr">
               </td>
                
               </tr>
<tr>
               <th class="veseelRemoveBorder">Currency</th>

               <td>
               
                 <select class="SelectCargo" ng-model="selectedBL.currency" ng-options="x for x in currencyArray">
				</select>
                 <!--  <input type="text" class="inputCargo" value="{{BLS[blIndex].currency}}"   ng-model="selectedBL.currency"> -->
               </td>
               </tr>
<tr>
               <th class="veseelRemoveBorder">Invoice Terms</th>

               <td>
                  <input type="text" class="inputCargo" value="{{BLS[blIndex].invoiceItems}}"   ng-model="selectedBL.invoiceItems">
               </td>
                
               </tr>
               
<tr>
               <th class="veseelRemoveBorder">Duty INR</th>

               <td>
                  <input type="text" class="inputCargo" value="{{BLS[blIndex].invoiceItems}}"   ng-model="selectedBL.invoiceItems"> 
               </td>
                
               </tr>
               
<tr>
               <th class="veseelRemoveBorder">Mode Of TP Fee</th>
               <td>
	               <select class="SelectCargo" ng-model="selectedBL.modeOfTpFee" ng-options="x for x in modeOfTpFeeArray">
				   </select>
                 <!--  <input type="text" class="inputCargo" value="{{BLS[blIndex].modeOfTpFee}}"   ng-model="selectedBL.modeOfTpFee"> -->
               </td>
                
               </tr>
				<tr>
               <th class="veseelRemoveBorder">Remark</th>

               <td>
                 <textarea rows="3" cols="43"  ng-model="selectedBL.remark">
			{{BLS[blIndex].remark}}
		</textarea>	 	
               </td>
                
               </tr>               
               </table>
          <!--  	</fieldset> -->
				
           </div>

         </div>
       </div>
       
    <div id="blDetailsTabs">   
	<ul>
		<li><a href="#marksNos"><span>Marks & Nos</span></a></li>
		<li><a href="#marksDescription"><span>Goods Description</span></a></li>
		<li><a href="#remarks"><span> Remarks</span></a></li>
		<li><a href="#ConsigneeDetail"><span>Consignee</span></a></li>
		<li><a href="#Consigner"><span>Consigner</span></a></li>
		<li><a href="#NotifyParty"><span>Notify Party</span></a></li>
		<li><a href="#previousDeclaration"><span>Previous Declaration</span></a></li>
		<li><a href="#Notify2"><span>Notify2</span></a></li>
	</ul>

	<div id="marksNos">
		<div class="tab-title"> Marks & Nos</div>
	
		<textarea rows="10" cols="50"  ng-model="selectedBL.marksNumber[0].marksNumbers">
			{{BLS[blIndex].marksNumber[0].marksNumbers}}
		</textarea>	 	
	
	</div>
	
	<div id="marksDescription">

	 <div class="tab-title">Goods Description</div>
	
		<textarea rows="10" cols="50" ng-model="selectedBL.marksNumber[0].description">
			{{BLS[blIndex].marksNumber[0].description}}
		</textarea>	 
	
	</div>
	
	<div id="remarks">

	 <div class="tab-title"> DO Remarks</div>
		
		<textarea rows="10" cols="50" ng-model="selectedBL.marksNumber[0].droRemarks">
			{{BLS[blIndex].marksNumber[0].droRemarks}}
		</textarea>	 
	
	</div>
	<div id="ConsigneeDetail">
	<legend>Consignee</legend>
		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">

			<tr>
				<td class="veseelRemoveBorder">Consignee
					Code</td>
				<td><span id="consigneeCode">
							 <input type="text"
						class="seqCss" value="{{consignee.customerCode}}"
						ng-model="consignee.customerCode"></span></td>

				<td class="veseelRemoveBorder">Consignee
					Name</td>
				<td><span id="consigneeName"> <input type="text"
						class="seqCss" value="{{consignee.customerName}}"
						ng-model="consignee.customerName"></span></td>

				<td class="veseelRemoveBorder">Consignee
					Address 1</td>
				<td id="consigneeAdress1"><input type="text" class="seqCss"
					value="{{consignee.addressLine1}}"
					ng-model="consignee.addressLine1"></td>

				<td class="veseelRemoveBorder">Consignee
					Address 2</td>
				<td id="consigneeAdress2"><input type="text" class="seqCss"
					value="{{consignee.addressLine2}}"
					ng-model="consignee.addressLine2"></td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">Consignee
					Address 3</td>

				<td id="consigneeAdress3"><input type="text"
					value="{{consignee.addressLine3}}"
					ng-model="consignee.addressLine3" class="seqCss"></td>

				<td class="veseelRemoveBorder">Consignee
					Address 4</td>
				<td id="consigneeAdress4"><input type="text"
					value="{{consignee.addressLine4}}"
					ng-model="consignee.addressLine4" class="seqCss"></td>

				<td class="veseelRemoveBorder">City
				</td>
				<td><span id="consigneeCity"> <input type="text"
						value="{{consignee.city}}" ng-model="consignee.city" maxlength="70"
						class="seqCss"></span></td>

				<td class="veseelRemoveBorder">State
				</td>
				<td><span id="consigneeState"><input type="text"
						value="{{consignee.state}}" ng-model="consignee.state" maxlength="2"
						class="seqCss"></span></td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">Country
				</td>
				<td><span id="consigneeCountry"> <input type="text"
						value="{{consignee.countryCode}}" ng-model="consignee.countryCode" maxlength="2"
						class="seqCss"></span></td>

				<td class="veseelRemoveBorder">Zip
				</td>
				<td><span id="consigneeZip"> <input type="text"
						value="{{consignee.zip}}" ng-model="selectedBL.pointName" maxlength="9"
						class="roundshap2"></span></td>

				<td class="veseelRemoveBorder" >Move to Notify Party </td>
				<td><button class="hover" id="moveToconsignee" style="background-color: #167FF6;border-radius: 6px;width:50px"> <i class="fa fa-arrow-right" style="font-size:20px;color:#080808" ng-click="getDataMoveToNextTab(this,'Consignee')"></i> </button> </td>
			</tr>
			<tr>
			<td>
			<!-- hidden fields -->
			
			<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgneCntrySubDivName" value="selectedBL.cnsgneCntrySubDivName">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgneCntrySubDiv" value="selectedBL.cnsgneCntrySubDiv">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgneCntrySubDivName" value="selectedBL.cnsgneCntrySubDivName">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgneCntrySubDivName" value="selectedBL.cnsgneCntrySubDivName">
			
			</td>
			
			</tr>
		</table>
	</div>


	<div id="Consigner">
		<legend>Consigner</legend>

		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">
			<tr>
				<td class="veseelRemoveBorder">Consigner
					Code <span id="ConsignerCode"></span>
				</td>
				<td><span id="ConsignerCode"> <input type="text"
						class="seqCss" value="{{consigner.customerCode}}"
						ng-model="consigner.customerCode"></span></td>

				<td class="veseelRemoveBorder">Consigner
					Name <span id="ConsignerName"></span>
				</td>
				<td><span id="ConsignerName"> <input type="text"
						class="seqCss" value="{{consigner.customerName}}"
						ng-model="consigner.customerName" maxlength="70"></span></td>

				<td class="veseelRemoveBorder">Consigner
					Address 1</td>
				<td id="ConsignerAdress1"><input type="text" class="seqCss"
					value="{{consigner.addressLine1}}"
					ng-model="consigner.addressLine1" maxlength="35"></td>

				<td class="veseelRemoveBorder">Consigner
					Address 2</td>
				<td id="ConsignerAdress2"><input type="text" class="seqCss"
					value="{{consigner.addressLine2}}"
					ng-model="consigner.addressLine2" maxlength="35"></td>
				<td><span id="consigneeCode"></span></td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">Consigner
					Address 3</td>
				<td id="ConsignerAdress3"><input type="text" class="seqCss"
					value="{{consigner.addressLine3}}"
					ng-model="consigner.addressLine3" maxlength="35"></td>

				<td class="veseelRemoveBorder">Consigner
					Address 4</td>
				<td id="ConsignerAdress4"><input type="text" class="seqCss"
					value="{{consigner.addressLine4}}"
					ng-model="consigner.addressLine4" maxlength="35"></td>

				<td class="veseelRemoveBorder">City</td>

				<td id="ConsignerCity"><input type="text" class="seqCss"
					value="{{consigner.city}}" ng-model="consigner.city" maxlength="70" ></td>
				<td class="veseelRemoveBorder">State

				
				<td id="ConsignerCity"><input type="text" class="seqCss" maxlength="2">
				</td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">Country</td>
				<td id="ConsignerCity"><input type="text" class="seqCss"
					value="{{consigner.countryCode}}" ng-model="consigner.countryCode" maxlength="2">
				</td>

				<td class="veseelRemoveBorder">Zip</td>
				<td id="ConsignerCity"><input type="text" class="roundshap2"
					value="{{consigner.zip}}" ng-model="consigner.zip" maxlength="9"></td>

				<td class="whitebg" id="ConsignerBlNo"></td>
				
			</tr>
			<tr>
			<td>
			<!-- hidden fields -->
			 	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCdTyp" value="selectedBL.cnsgnrCdTyp">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivName" value="selectedBL.cnsgnrCntrySubDivName">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivCd" value="selectedBL.cnsgnrCntrySubDivCd">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.typOfCd" value="selectedBL.typOfCd">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivName" value="selectedBL.cnsgnrCntrySubDivName">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivName" value="selectedBL.cnsgnrCntrySubDivName">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivName" value="selectedBL.cnsgnrCntrySubDivName">
             	<input type="hidden" class="cargoCss" ng-model="selectedBL.cnsgnrCntrySubDivName" value="selectedBL.cnsgnrCntrySubDivName">
             	</td>
			</tr>
		</table>
	</div>

	<div id="NotifyParty">
		<legend>Notify Party</legend>
		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">
			<tr>
				<td class="veseelRemoveBorder">Notify
					Code</td>
				<td><input id="NotifyCode" type="text" class="seqCss"
					value="{{notifyParty.costumerCode}}"
					ng-model="notifyParty.costumerCode"></td>

				<td class="veseelRemoveBorder">Notify
					Name</td>
				<td><input id="NotifyName" type="text" class="seqCss"
					value="{{notifyParty.costumerName}}"
					ng-model="notifyParty.costumerName" maxlength="70"></td>

				<td class="veseelRemoveBorder">Notify
					Address 1</td>
				<td><input id="NotifyAdress1" type="text" class="seqCss"
					value="{{notifyParty.addressLine1}}" maxlength="35"
					ng-model="notifyParty.addressLine1"></td>

				<td class="veseelRemoveBorder">Notify
					Address 2</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine2}}" maxlength="35"
					ng-model="notifyParty.addressLine2"></td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">Notify
					Address 3</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine3}}" maxlength="35"
					ng-model="notifyParty.addressLine3"></td>


				<td class="veseelRemoveBorder">Notify
					Address 4</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine4}}" maxlength="35"
					ng-model="notifyParty.addressLine4"></td>

				<td class="veseelRemoveBorder">City
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss" maxlength="70"
					value="{{notifyParty.city}}" ng-model="notifyParty.city"></td>

				<td class="veseelRemoveBorder">State
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss" maxlength="2"
					value="{{notifyParty.state}}" ng-model="notifyParty.state">
				</td>

			</tr>

			<tr>
				<td class="veseelRemoveBorder">Country
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.countryCode}}"  maxlength="2"
					ng-model="notifyParty.countryCode"></td>

				<td class="veseelRemoveBorder">Zip
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss" maxlength="9"
					value="{{notifyParty.zip}}" ng-model="notifyParty.zip"></td>

				<td class="veseelRemoveBorder" >Move to Consignee </td>
				<td><button id="moveToNotifyParty" style="background-color: #167FF6;border-radius: 6px;width:50px"> <i class="fa fa-arrow-left" style="font-size:20px;color:#080808" ></i></butten></td>
			</tr>
			
			<tr>
			<td>
			<!-- hidden fields -->
			<input type="hidden" class="cargoCss" ng-model="selectedBL.nameOfAnyOtherNotfdParty" value="selectedBL.nameOfAnyOtherNotfdParty">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.panOfNotfdParty" value="selectedBL.panOfNotfdParty">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.typOfNotfdPartyCd" value="selectedBL.typOfNotfdPartyCd">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.notfdPartyCntrySubDivName" value="selectedBL.notfdPartyCntrySubDivName">
			<input type="hidden" class="cargoCss" ng-model="selectedBL.notfdPartyCntrySubDiv" value="selectedBL.notfdPartyCntrySubDiv">
			</td>
			</tr>
			
		</table>
	</div>

	 
	<div  id="previousDeclaration">
	
			<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">

			<tr>
				<td class="veseelRemoveBorder">Previous Declaration</td>
				<td><span id="consigneeCode">
						<select class="smallDPCss" ng-model="selectedBL.previousDeclaration[0].previous_declaration" ng-options="x for x in previousDeclarationArray">
					   </select>		 
					</span>
				</td>
				<td class="veseelRemoveBorder">Split Indicator</td>
				<td><span id="consigneeName"> 
					<select class="smallDPCss" ng-model="selectedBL.previousDeclaration[0].split_indicator" ng-options="x for x in socFlagObj">
					</select>
				</span></td>
			</tr>
			
			<tr>
				<td class="veseelRemoveBorder">CSN No</td>
				<td id="consigneeAdress1">
					<input type="text" class="seqCss" value="{{BLS[blIndex].previousDeclaration[0].csn_number}}" ng-model="selectedBL.previousDeclaration[0].csn_number">
				</td>

				<td class="veseelRemoveBorder">CSN Date</td>
				<td id="consigneeAdress2"><input type="text" class="seqCss" id="csnDate"  value="{{BLS[blIndex].previousDeclaration[0].csn_date}}" 
				ng-model="selectedBL.previousDeclaration[0].csn_date" onclick="dateToCommon(this)" ></td>
			</tr>
			<tr>
				<td class="veseelRemoveBorder">PCIN</td>

				<td id="consigneeAdress3">
				<input type="text" class="seqCss" ng-model="selectedBL.pcin" value="{{BLS[blIndex].previousDeclaration[0].previous_pcin}}" ng-model="selectedBL.previousDeclaration[0].previous_pcin">
				</td>

			<td class="veseelRemoveBorder">MCIN</td>

				<td id="consigneeAdress3">
				<input type="text" class="seqCss" ng-model="selectedBL.mcin" value="{{BLS[blIndex].previousDeclaration[0].previous_mcin}}" ng-model="selectedBL.previousDeclaration[0].previous_mcin">
				</td>
				 
				 			</tr>
		</table>
		
	</div>
	<div id="Notify2">
		<div ng-if="pageType=='IGM'">
		<table  class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%"   >
				<textarea rows="10" cols="50"  ng-model="selectedBL.marksNumber[0].notifyPartyCodes">
					{{BLS[blIndex].marksNumber[0].notifyPartyCode}}
				</textarea>	
		</table>
		</div>
		<div ng-if="pageType=='EGM'" >
	 	<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%"  >
			<tr>
				<td class="TableLeftSub bl_detail">Notify
					Code</td>
				<td><input id="NotifyCodeTwo" type="text" class="seqCss"
					value="{{notifyPartyTwo.costumerCode}}"
					ng-model="notifyPartyTwo.costumerCode"></td>

				<td class="TableLeftSub bl_detail">Notify
					Name</td>
				<td><input id="NotifyNameTwo" type="text" class="seqCss"
					value="{{notifyPartyTwo.costumerName}}"
					ng-model="notifyPartyTwo.costumerName"></td>

				<td class="TableLeftSub bl_detail">Notify
					Address 1</td>
				<td><input id="NotifyAdressTwo1" type="text" class="seqCss"
					value="{{notifyPartyTwo.addressLine1}}"
					ng-model="notifyPartyTwo.addressLine1"></td>

				<td class="TableLeftSub bl_detail">Notify
					Address 2</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.addressLine2}}"
					ng-model="notifyPartyTwo.addressLine2"></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Notify
					Address 3</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.addressLine3}}"
					ng-model="notifyPartyTwo.addressLine3"></td>


				<td class="TableLeftSub bl_detail">Notify
					Address 4</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.addressLine4}}"
					ng-model="notifyPartyTwo.addressLine4"></td>

				<td class="TableLeftSub bl_detail">City
				</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.city}}" ng-model="notifyPartyTwo.city"></td>

				<td class="TableLeftSub bl_detail">State
				</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.state}}" ng-model="notifyPartyTwo.state">
				</td>

			</tr>

			<tr>
				<td class="TableLeftSub bl_detail">Country
				</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.countryCode}}"
					ng-model="notifyPartyTwo.countryCode"></td>

				<td class="TableLeftSub bl_detail">Zip
				</td>
				<td><input id="NotifyAdressTwo2" type="text" class="seqCss"
					value="{{notifyPartyTwo.zip}}" ng-model="notifyPartyTwo.zip"></td>

				<td class="whitebg" id="NotifyBlNoTwo"></td>
			</tr>
		</table>
		</div>
	</div>
</div>

<style>
   .selectCss{
    margin: 7px;
    width: 90%;
    height: 25px;
    border-color: blue;
   }
   .SelectCargo{
    margin: 7px;
    width: 52%;
    height: 25px;
    border-color: blue;
   }
</style>