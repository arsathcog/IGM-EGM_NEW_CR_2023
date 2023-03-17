 <div class="flex-container">
         <div class="flex-bl-list">

		<div style="height: 200px;">
			<table>
				<tbody>
					<tr>
						  <td>Line No.</td>
						
						   <td><input type="text" class="seqCss" ></td>
						    <td>Sub Line No</td>
						    <td><input type="text" class="seqCss" value="{{BLS[blIndex].subLineNumber}}" ng-model="selectedBL.subLineNumber"></td>
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
							<td><input type="text"  class="seqCssNew" value="{{BLS[blIndex].bl}}" ng-model="selectedBL.bl"> 
							<input type="text"  class="seqCssNew" value="{{BLS[blIndex].blDate}}" ng-model="selectedBL.blDate"> </td>
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
							<td>Cargo Movement</td>    </tr>
						<tr>	<td><select class="smallDPCss"
								ng-model="selectedBL.cargoMovmnt">            
									<option value=" {{BLS[blIndex].cargoMovmnt}}" selected>{{BLS[blIndex].cargoMovmnt}}"</option>
									<option value=" FCL"> FCL</option> 
									<option value="LCL">LCL</option>
									<option value="BKL">BKL</option>
									<option value="TSC">TSC</option>
							</select></td>    </tr>

						 <tr>	<td>Item Type</td>    </tr>
						 <tr>	<td>
								<select class="smallDPCss"
								ng-model="selectedBL.itemType">            
									<option value="LC">LC</option>
									<option value="TI">TI</option>
									<option value="TC">TC</option>
									<option value="DT">DT</option>
									<option value="FT">FT</option>
							</select></td>    </tr>
							
					</tbody>
				</table>
			</fieldset>
		</div>

	</div>
         <div class="flex-bl-detail">

		<div style="float:left;">
           <table class="whitebg" border="0" style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">
           
             <tr style="line-height: .7rem">
             <th class="TableLeftSub bl_detail" >Port of Receipt</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].port_of_receipt}}" ng-model="selectedBL.port_of_receipt" >
              	</td>
               
              <td>
              </td>
              <td>
              </td>
             </tr>
             <tr style="line-height: .7rem">
             <th class="TableLeftSub bl_detail" >  Port of Loading</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].portOfLoading}}" ng-model="selectedBL.portOfLoading">
              	</td>
                 
               <td>
              </td>
              <td>
              </td>
             </tr>
                <tr style="line-height: .7rem">
                <th class="TableLeftSub bl_detail" >  Port of Destination</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].portOfDestination}}" ng-model="selectedBL.portOfDestination" >
              	</td>
                 
              <td>
              </td>
              <td>
              </td>
             </tr>
                
             <tr style="line-height: .7rem">
                
               <th class="TableLeftSub bl_detail" >Port of Descharged/CFS</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].type_of_package}}" ng-model="selectedBL.portOfDeschargedCfs">
               </td>
               
                <td>
              </td>
              <td>
              </td>
             </tr>
               
               <tr style="line-height: .7rem"> 
                <th class="TableLeftSub bl_detail">DPD Movement</th>

               <td>
                 <input type="text" class="seqCss" ng-model="selectedBL.dpdMovement" value="{{BLS[blIndex].dpdMovement}}" >
               </td>

               <th class="TableLeftSub bl_detail">DPD Code</th>

               <td>
                 <input type="text" class="seqCss" ng-model="selectedBL.dpdCode" value="{{BLS[blIndex].dpdCode}}">
               </td>
               
               
               </tr>
               
               <tr style="line-height: .7rem"> 
               
               <th class="TableLeftSub bl_detail"  >Total Packages</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].total_number_of_packages}}" >
               </td>
                <th class="TableLeftSub bl_detail"  >Type</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].type_of_package}}" ng-model="selectedBL.type_of_package">
               </td>
              </tr>
               
               <tr style="line-height: .7rem">

               <th class="TableLeftSub bl_detail" >Multipal pakages
               </th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].multipalPakages}}" ng-model="selectedBL.multipalPakages"  >
               </td>

               <th class="TableLeftSub bl_detail"  >Cargo Nature</th>
               <td>
                 <select class="smallDPCss" ng-model="selectedBL.cargoNature">
							<option value="{{BLS[blIndex].cargoNature}}" selected>{{BLS[blIndex].cargoNature}}</option>
							<option value="LC">LC</option>
							<option value="TI">TI</option>
							<option value="TC">TC</option>
							<option value="DT">DT</option>
							<option value="FT ">FT</option>
					</select>
               </td>
               
               </tr>
               
                <tr style="line-height: .7rem">
               
               <th class="TableLeftSub bl_detail">Gross Weight</th>

               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].grosWeight}}" ng-model="selectedBL.grosWeight" >
               </td>
               
               <th class="TableLeftSub bl_detail"  >Unit</th>
               <td>
                  <select class="smallDPCss" ng-model="selectedBL.unit">
							<option value="{{BLS[blIndex].unit}}" selected>{{BLS[blIndex].unit}}</option>
							<option value="KGS">KGS</option> 
							<option value="MTS">MTS</option>
							<option value="MBT">MBT</option>
							
					</select>
               </td>
               
               </tr>
 
 
               
               <tr style="line-height: .7rem">
               
                <th class="TableLeftSub bl_detail" >Cargo Movement
               </th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].cargoMovmnt}}" disabled="disabled">
               </td>
               
                                <th class="TableLeftSub bl_detail" >Item Type
               </th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].itemType}}"  ng-model="selectedBL.itemType" >
               </td>
               
               
             </tr>

             <tr>
               <th class="TableLeftSub bl_detail" >CBM</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].cbm}}" ng-model="selectedBL.cbm" >
               </td>
               <th class="TableLeftSub bl_detail" >High Value</th>

               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].hightValue}}" ng-model="selectedBL.hightValue" >
               </td>
               
             </tr>
			
			<tr>
               <th class="TableLeftSub bl_detail">Volume</th>

               <td>
                 <input type="text" class="seqCss" ng-model="selectedBL.volume" value="{{BLS[blIndex].volume}}">
               </td>
               
              <th class="TableLeftSub bl_detail">Unit</th>

               <td>
                 <input type="text" class="seqCss" ng-model="selectedBL.unit" value="{{BLS[blIndex].unit}}">
               </td>
               
              
             </tr>
             
              
             <tr>
               
               <th class="TableLeftSub bl_detail"  >IMO</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].csn_submitted_type}}" ng-model="selectedBL.csn_submitted_type" >
               </td>

               <th class="TableLeftSub bl_detail" >UNO</th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].uno_code}}" ng-model="selectedBL.uno_code">
               </td>

             </tr>
             <tr>
               <th class="TableLeftSub bl_detail" >EnBlock Movement
               </th>
               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].enblockMovement}}" ng-model="selectedBL.enblockMovement" >
               </td>
             </tr>
            
           </table>
           
           </div>
           
           <div style="float:left;">
         <!--  	<fieldset>
				<legend>TP-Details</legend> -->
          <table class="whitebg" border="0" style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">

         	<tr>
               <th class="TableLeftSub bl_detail"  >Transport Mode</th>
               
                <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].transportMode}}" disabled="disabled">
               
               </td>
              </tr>
			<tr>
               <th class="TableLeftSub bl_detail">TP Bond No</th>

               <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].transportMode}}" disabled="disabled">
               </td>
                
               </tr>
               
               <tr>
               <th class="TableLeftSub bl_detail"  >Carrier No</th>
               
                <td>
                 <input type="text" class="seqCss" value="{{BLS[blIndex].carrierNo}}"   ng-model="selectedBL.carrierNo">
               
               </td>
</tr>
	<tr>
               <th class="TableLeftSub bl_detail">Agency Type</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].carrierNo}}"   ng-model="selectedBL.agencyType">
               </td>
                
               </tr>

				<tr>
               <th class="TableLeftSub bl_detail">Invoice Value(FC)</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].invoiceValueFc}}"   ng-model="selectedBL.invoiceValueFc">
               </td>
                
               </tr>

<tr>
               <th class="TableLeftSub bl_detail">Invoice Value(INR)</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].invoiceValueInr}}"   ng-model="selectedBL.invoiceValueInr">
               </td>
                
               </tr>


<tr>
               <th class="TableLeftSub bl_detail">Currency</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].currency}}"   ng-model="selectedBL.currency">
               </td>
                
               </tr>
               
               
<tr>
               <th class="TableLeftSub bl_detail">invoiceItems</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].invoiceItems}}"   ng-model="selectedBL.invoiceItems">
               </td>
                
               </tr>
               
               
               
<tr>
               <th class="TableLeftSub bl_detail">Duty INR</th>

               <td>
                  <input type="text" class="seqCss" value="Missing"   >
               </td>
                
               </tr>
               
<tr>
               <th class="TableLeftSub bl_detail">Mode Of TP Fee</th>

               <td>
                  <input type="text" class="seqCss" value="{{BLS[blIndex].modeOfTpFee}}"   ng-model="selectedBL.modeOfTpFee">
               </td>
                
               </tr>

				<tr>
               <th class="TableLeftSub bl_detail">Remark</th>

               <td>
                 <textarea rows="10" cols="50"  ng-model="selectedBL.remark">
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
		<li><a href="#marksDescription"><span>  Good Description</span></a></li>
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

	 <div class="tab-title">Good Description</div>
	
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
		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">

			<tr>
				<td class="TableLeftSub bl_detail">Consignee
					Code</td>
				<td><span id="consigneeCode">
							 <input type="text"
						class="seqCss" value="{{consignee.customerCode}}"
						ng-model="consignee.customerCode"></span></td>

				<td class="TableLeftSub bl_detail">Consignee
					Name</td>
				<td><span id="consigneeName"> <input type="text"
						class="seqCss" value="{{consignee.customerName}}"
						ng-model="consignee.customerName"></span></td>

				<td class="TableLeftSub bl_detail">Consignee
					Address 1</td>
				<td id="consigneeAdress1"><input type="text" class="seqCss"
					value="{{consignee.addressLine1}}"
					ng-model="consignee.addressLine1"></td>

				<td class="TableLeftSub bl_detail">Consignee
					Address 2</td>
				<td id="consigneeAdress2"><input type="text" class="seqCss"
					value="{{consignee.addressLine2}}"
					ng-model="consignee.addressLine2"></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Consignee
					Address 3</td>

				<td id="consigneeAdress3"><input type="text"
					value="{{consignee.addressLine3}}"
					ng-model="consignee.addressLine3" class="seqCss"></td>

				<td class="TableLeftSub bl_detail">Consignee
					Address 4</td>
				<td id="consigneeAdress4"><input type="text"
					value="{{consignee.addressLine4}}"
					ng-model="consignee.addressLine4" class="seqCss"></td>

				<td class="TableLeftSub bl_detail">City
				</td>
				<td><span id="consigneeCity"> <input type="text"
						value="{{consignee.city}}" ng-model="consignee.city"
						class="seqCss"></span></td>

				<td class="TableLeftSub bl_detail">State
				</td>
				<td><span id="consigneeState"><input type="text"
						value="{{consignee.state}}" ng-model="consignee.state"
						class="seqCss"></span></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Country
				</td>
				<td><span id="consigneeCountry"> <input type="text"
						value="{{consignee.countryCode}}" ng-model="consignee.countryCode"
						class="seqCss"></span></td>

				<td class="TableLeftSub bl_detail">Zip
				</td>
				<td><span id="consigneeZip"> <input type="text"
						value="{{consignee.zip}}" ng-model="consignee.zip"
						class="roundshap2"></span></td>

				<td class="whitebg" id="consigneeBlNo">
				<td></td>
			</tr>
		</table>
	</div>


	<div id="Consigner">
		<legend>Consigner</legend>

		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">
			<tr>
				<td class="TableLeftSub bl_detail">Consigner
					Code <span id="ConsignerCode"></span>
				</td>
				<td><span id="ConsignerCode"> <input type="text"
						class="seqCss" value="{{consigner.customerCode}}"
						ng-model="consigner.customerCode"></span></td>

				<td class="TableLeftSub bl_detail">Consigner
					Name <span id="ConsignerName"></span>
				</td>
				<td><span id="ConsignerName"> <input type="text"
						class="seqCss" value="{{consigner.customerName}}"
						ng-model="consigner.customerName"></span></td>

				<td class="TableLeftSub bl_detail">Consigner
					Address 1</td>
				<td id="ConsignerAdress1"><input type="text" class="seqCss"
					value="{{consigner.addressLine1}}"
					ng-model="consigner.addressLine1"></td>

				<td class="TableLeftSub bl_detail">Consigner
					Address 2</td>
				<td id="ConsignerAdress2"><input type="text" class="seqCss"
					value="{{consigner.addressLine2}}"
					ng-model="consigner.addressLine2"></td>
				<td><span id="consigneeCode"></span></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Consigner
					Address 3</td>
				<td id="ConsignerAdress3"><input type="text" class="seqCss"
					value="{{consigner.addressLine3}}"
					ng-model="consigner.addressLine3"></td>

				<td class="TableLeftSub bl_detail">Consigner
					Address 4</td>
				<td id="ConsignerAdress4"><input type="text" class="seqCss"
					value="{{consigner.addressLine4}}"
					ng-model="consigner.addressLine4"></td>

				<td class="TableLeftSub bl_detail">City</td>

				<td id="ConsignerCity"><input type="text" class="seqCss"
					value="{{consigner.city}}" ng-model="consigner.city"></td>
				<td class="TableLeftSub bl_detail">State

				
				<td id="ConsignerCity"><input type="text" class="seqCss">
				</td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Country</td>
				<td id="ConsignerCity"><input type="text" class="seqCss"
					value="{{consigner.countryCode}}" ng-model="consigner.countryCode">
				</td>

				<td class="TableLeftSub bl_detail">Zip</td>
				<td id="ConsignerCity"><input type="text" class="roundshap2"
					value="{{consigner.zip}}" ng-model="consigner.zip"></td>

				<td class="whitebg" id="ConsignerBlNo"></td>
			</tr>
		</table>
	</div>

	<div id="NotifyParty">
		<legend>Notify Party</legend>
		<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">
			<tr>
				<td class="TableLeftSub bl_detail">Notify
					Code</td>
				<td><input id="NotifyCode" type="text" class="seqCss"
					value="{{notifyParty.costumerCode}}"
					ng-model="notifyParty.costumerCode"></td>

				<td class="TableLeftSub bl_detail">Notify
					Name</td>
				<td><input id="NotifyName" type="text" class="seqCss"
					value="{{notifyParty.costumerName}}"
					ng-model="notifyParty.costumerName"></td>

				<td class="TableLeftSub bl_detail">Notify
					Address 1</td>
				<td><input id="NotifyAdress1" type="text" class="seqCss"
					value="{{notifyParty.addressLine1}}"
					ng-model="notifyParty.addressLine1"></td>

				<td class="TableLeftSub bl_detail">Notify
					Address 2</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine2}}"
					ng-model="notifyParty.addressLine2"></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">Notify
					Address 3</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine3}}"
					ng-model="notifyParty.addressLine3"></td>


				<td class="TableLeftSub bl_detail">Notify
					Address 4</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.addressLine4}}"
					ng-model="notifyParty.addressLine4"></td>

				<td class="TableLeftSub bl_detail">City
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.city}}" ng-model="notifyParty.city"></td>

				<td class="TableLeftSub bl_detail">State
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.state}}" ng-model="notifyParty.state">
				</td>

			</tr>

			<tr>
				<td class="TableLeftSub bl_detail">Country
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.countryCode}}"
					ng-model="notifyParty.countryCode"></td>

				<td class="TableLeftSub bl_detail">Zip
				</td>
				<td><input id="NotifyAdress2" type="text" class="seqCss"
					value="{{notifyParty.zip}}" ng-model="notifyParty.zip"></td>

				<td class="whitebg" id="NotifyBlNo"></td>
			</tr>
		</table>
	</div>

	 
	<div  id="previousDeclaration">
	
			<table class="whitebg" border="0"
			style="display: inline-table; background-color: #CCCCCC; font-size: 10px; word-spacing: 5px; width: 100%">

			<tr>
				<td class="TableLeftSub bl_detail">Previous Declaration</td>
				<td><span id="consigneeCode">
							 
					<select class="smallDPCss"  ng-model="selectedBL.previousDeclaration[0].previous_declaration">
                   <option value="N">No</option>
                   <option value="Y">Yes</option>                    
                 </select>		 
					</span></td>

				<td class="TableLeftSub bl_detail">Split Indicator</td>
				<td><span id="consigneeName"> 
					<select class="smallDPCss"  ng-model="selectedBL.previousDeclaration[0].split_indicator">
                   <option value="N">No</option>
                   <option value="Y">Yes</option>                    
                 </select>		
				</span></td>
			</tr>
			
			<tr>
				<td class="TableLeftSub bl_detail">CSN No</td>
				<td id="consigneeAdress1">
					<input type="text" class="seqCss" value="{{BLS[blIndex].previousDeclaration[0].csn_number}}" ng-model="selectedBL.previousDeclaration[0].csn_number">
				</td>

				<td class="TableLeftSub bl_detail">CSN Date</td>
				<td id="consigneeAdress2"><input type="text" class="seqCss" onclick="dateToCommon(this)"
				 value="{{BLS[blIndex].previousDeclaration[0].csn_date}}" ng-model="selectedBL.previousDeclaration[0].csn_date"></td>
			</tr>
			<tr>
				<td class="TableLeftSub bl_detail">PCIN</td>

				<td id="consigneeAdress3">
				<input type="text" class="seqCss" 	 value="{{BLS[blIndex].previousDeclaration[0].previous_pcin}}"  		ng-model="selectedBL.previousDeclaration[0].previous_pcin">
				</td>

			<td class="TableLeftSub bl_detail">MCIN</td>

				<td id="consigneeAdress3">
				<input type="text" class="seqCss" 	 value="{{BLS[blIndex].previousDeclaration[0].previous_mcin}}"  		ng-model="selectedBL.previousDeclaration[0].previous_mcin">
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