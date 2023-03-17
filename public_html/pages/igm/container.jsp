<style>
	
#containerDetailTabs{
 	height: 772px;
 }
</style>
<div id="containerDetailTabs">



	<div>



		<div class="tab-title">Container Details</div>
		<div class="underLine"></div>
		<br>
		<table class="whitebg">

			<tr>
				<td class="table_lable" style="border-left: 1px solid gray;">Seq No.</td>
				<td class="table_lable">Container No.</td>
				<td class="table_lable">Container Seal No.</td>
				<td class="table_lable">Container Agent Code</td>
				<td class="table_lable">Container Status</td>
				<td class="table_lable">Total No. Of Packages</td>
				<td class="table_lable">Weight</td>
				<td class="table_lable">ISO Code</td>
				<td class="table_lable">Equip Load Status</td>
				<td class="table_lable">SOC Flag</td>
				<td class="table_lable">Equipment seal type</td>

			</tr>

			<tr  ng-repeat="item in selectedBL.containerDetailes track by $index" class="containerTableBody" ng-click="setContainer(item)">
				<td id="containerDetailsinfo">
							{{$index+1}}
				</td>
				<td>
					{{item.containerNumber}}
				</td>
				<td> 
					{{item.containerSealNumber}}
				</td>
				<td>
					{{selectedServcies.agentCode}}
				</td>
				<td>
					{{item.status}}
				</td>
				<td>
					{{item.totalNumberOfPackagesInContainer}}
				</td>
				<td>
					{{item.containerWeight}}
				</td>
				<td>
					{{item.isoCode}}
				</td>
				<td>
				<select class="smallDPCss" ng-model="item.equipmentLoadStatus" value = "{{item.equipmentLoadStatus}} "  ng-click="selectedItemChanged(item.equipmentLoadStatus)" selected= "{{item.equipmentLoadStatus}} "  ng-options="x for x in containerStatusArray">
			    </select>

				</td>
				<td>
				<select class="smallDPCss" ng-model="item.soc_flag" ng-options="x for x in socFlagObj">
				</select>
					<!-- <select class="smallDPCss" id="0SOC Flagr" name="SOC Flagr" ng-model="selectedContainer.soc_flag">
					        <option value="{{item.soc_flag}}" selected>{{item.soc_flag}}</option>
							<option value="Y">Y</option>
							<option value="N">N</option>
					</select> -->
				</td>
				<td>
					<select class="smallDPCss" ng-model="selectedContainer.equipment_seal_type" ng-options="x for x in SealTypeObj">
					</select>
				</td>
			</tr>
		</table>
		
		<br><br>
		<div class="tab-title">Select Container {{selectedContainer.containerNumber}}</div>
		<div class="underLine"></div>
		<br><br>
		
		<div id="container" ng-if="selectedContainer.containerNumber">
			<table id="blTable" style="width: 100%;background-color: #CCCCCC;	">
				<tr >
					<td class="TableLeftSub bl_detail" >Container No</td>
					
					 <td>
					 <input type="text" class="seqCss" value="{{selectedContainer.containerNumber}}" ng-model="selectedContainer.containerNumber" maxlength="17">
					</td>
					
					<td class="TableLeftSub bl_detail" >Total Packages</td>
					
					 <td>
					 <input type="text" class="seqCss" value="{{selectedContainer.totalNumberOfPackagesInContainer}}" ng-model="selectedContainer.totalNumberOfPackagesInContainer" maxlength="8" ng-change="checkNumericNoPackage(selectedContainer,'totalNumberOfPackagesInContainer');">
					</td>
					
					<td class="TableLeftSub bl_detail">Container Weight</td>
					
					 <td>
					 <input type="text" class="seqCssNd" value="{{selectedContainer.containerWeight}}" ng-model="selectedContainer.containerWeight"  ng-change="setTwoNumberDecimalContainer(selectedContainer,14,2,'containerWeight')">
					 <input type="text" class="seqCssNd" value="NPS" ng-model="selectedContainer.containerWeightUnit" >
					</td>
				</tr>
				
				<tr >
					<td class="TableLeftSub bl_detail">Seal Type</td>
					 <td>
					 <select class="smallDPCss" ng-model="selectedContainer.equipment_seal_type" ng-options="x for x in SealTypeObj">
					</select>
					</td>
					
					<td class="TableLeftSub bl_detail">Seal NO </td>
					 
					 <td>
					 <input type="text" class="seqCss	" value="{{selectedContainer.containerSealNumber}}" ng-model="selectedContainer.containerSealNumber"  maxlength="15">
					</td>
					
					<td class="TableLeftSub bl_detail">CBM </td>
					
					 <td>
					 <input type="text" class="seqCss	" value="{{selectedContainer.cbm}}" ng-model="selectedContainer.cbm" ng-change="setTwoNumberDecimalContainercbm(selectedContainer,12,3,'cbm')">
					</td>
					<td>
					 <input type="hidden" class="seqCss"   value="{{selectedContainer.totalNoOfTrnsprtEqmtMnfsted}}" ng-model="selectedContainer.totalNoOfTrnsprtEqmtMnfsted" > <!-- hidden fields -->
					</td>
					
				</tr>
				
				<tr >
					<td class="TableLeftSub bl_detail">Container Agent</td>
					
					 <td>
					 	 <input type="text" class="seqCss" value="{{selectedServcies.agentCode}}" ng-model="selectedServcies.agentCode" maxlength="10" >
					 
					</td>
					
					<td class="TableLeftSub bl_detail">Container Status </td>
					
					 <td>
					 
					 <select class="smallDPCss" value= "{{selectedContainer.equipmentLoadStatus}}" ng-model="selectedContainer.equipmentLoadStatus" ng-options="x for x in containerStatusArray">
					 </select>
					 <!--  <input type="text" class="seqCss" value="{{selectedContainer.status}}" ng-model="selectedContainer.status" > -->
					 <!-- <select class="smallDPCss" name="Equipment Load Status"  ng-model="selectedContainer.equipmentLoadStatus">
						<option value="FCL">FCL</option>
						<option value="LCL">LCL</option>
						<option value="EMP">EMP</option>
						</select> -->
					</td>
					
					<td class="TableLeftSub bl_detail">ISOCode </td>
					
					 <td>
					 <select class="smallDPCss" ng-model="selectedContainer.isoCode" ng-options="x for x in ISOCodeObj">
						 </select>
					 <!-- <input type="text" class="seqCss	" value="{{selectedContainer.isoCode}}" ng-model="selectedContainer.isoCode" maxlength="4"> -->
					</td>
					
				</tr>
				
				<tr >
					<td class="TableLeftSub bl_detail">SOC FLag  	Y/N</td>
					
					 <td>
						 <select class="smallDPCss" ng-model="selectedContainer.soc_flag" ng-options="x for x in socFlagObj">
						 </select>
					 	<!-- <select class="smallDPCss" id="0SOC Flagr" name="SOC Flagr" ng-model="selectedContainer.soc_flag">
						<option value="Y">Y</option>
						<option value="N">N</option>
						</select> -->
					</td>
					
					 <td class="TableLeftSub bl_detail">UNO </td>
					 <td>
					  	<input type="text" class="seqCss	" value="{{selectedContainer.unos}}" ng-model="selectedContainer.unos" >
					  <!--  <input type="text" class="seqCss" value="{{selectedContainer.uno_code}}" ng-model="selectedContainer.uno_code">
					   -->
					 </td>
					 <td class="TableLeftSub bl_detail" ng-if="pageType=='EGM'" >IMO Class</td>
					 <td class="TableLeftSub bl_detail" ng-if="pageType=='IGM'">IMO Number</td>
					 <td>
					 	<input type="text" class="seqCss	" value="{{selectedContainer.imo}}" ng-model="selectedContainer.imo" >
					 </td>
					
				</tr>
				
					<tr>
						 <td class="TableLeftSub bl_detail">Container BondFlag</td>
						 <td ng-if="pageType=='IGM'">
							 <select class="smallDPCss" ng-model="selectedContainer.containerBondFlag" ng-options="x for x in containerBondFlagArray">
							 </select>
						</td>
						<td ng-if="pageType=='EGM'">
							<select class="smallDPCss" id="0SOC Flagr" name="SOC Flagr" ng-model="selectedContainer.containerBondFlag">
								<option value="Y">Y</option>
								<option value="N">N</option>
					        </select>
						</td>
						<td class="TableLeftSub bl_detail">TYPE </td>
						<td ng-if="pageType=='EGM'">
							 <input type="text" class="seqCss " value="{{selectedContainer.containerType}}" ng-model="selectedContainer.containerType" >
						</td>
						<td ng-if="pageType=='IGM'">
							 <select class="smallDPCss" ng-model="selectedContainer.containerType" ng-options="x for x in containerTypeArray">
							 </select>
						</td>
						<td class="TableLeftSub bl_detail">Type Detail </td>
						<td>
						 	<input type="text" class="seqCss	" value="{{selectedContainer.typeDetail}}" ng-model="selectedContainer.typeDetail" >
						</td>
				  </tr>
				
				<tr >
					<td class="TableLeftSub bl_detail">Classification</td>
					
					 <td>
					  	<textarea rows="5" cols="20" ng-model="selectedContainer.classification" >{{selectedContainer.classification}}</textarea>
					 
					</td>
					
					<td class="tabTableLeftSub bl_detail">Remarks </td>
					
					 <td>
					  	 	<textarea rows="5" cols="20" ng-model="selectedContainer.remarks" >{{selectedContainer.remarks}}</textarea>
					 
					  
					</td>
					
					 
					
				</tr>
			</table>
			
			<!-- hidden fields -->
			
			<input type="hidden" class="seqCss" value="{{selectedBL.stowageImport}}" ng-model="selectedBL.stowageImport" >
			
			<!-- hidden fields -->
		</div>
		<br> <br>
		 
   

	</div>
       <div class="buttons_box" style="width:100%">
			<input type="button" value="Weight" name="btnWeight" id="weightType" class="event_btnbutton" ng-click="weightBtn()">
		    <input type="button" value="Packages" name="btnPackages" id="packagesType" class="event_btnbutton" onclick="packages()">
			<input type="button" value="Default" name="btnDefault" id="defaultType" class="event_btnbutton" onclick=" defaultcontainerDtl()"> 	
			<input type="button" value="Close" name="btnClose" id="closeType" class="event_btnbutton" onclick="closeBtn()">
		</div>
</div>
