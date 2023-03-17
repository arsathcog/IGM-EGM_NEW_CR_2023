
	<table class="whitebg">
		<tr>
			<th class="TableLeftSub tableVessel-width">Sr.No</th>
			<th class="TableLeftSub tableVessel-width">Vsl.Sr.No</th>
			<th class="TableLeftSub tableVessel-width">Vessel</th>
			<th class="TableLeftSub tableVessel-width">Voyage</th>
			<th class="TableLeftSub tableVessel-width">IGM NO & Date</th>
			<th class="TableLeftSub tableVessel-width">Consl.Ind.</th>
			<th class="TableLeftSub tableVessel-width">Msg EDI</th>
			<th class="TableLeftSub tableVessel-width">NON EDI</th>
			<th class="TableLeftSub tableVessel-width">B/L Type</th>
			<th class="TableLeftSub tableVessel-width">CustCode</th>
			<th class="TableLeftSub tableVessel-width">IMO Code</th>
		</tr>
		<tr>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.serialNumber}}" ng-model="selectedServcies.serialNumber" disabled="disabled"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.vesselCode}}" ng-model="selectedServcies.vesselCode" disabled="disabled"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.vessel}}" 	ng-model="selectedServcies.vessel" disabled="disabled"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.voyage}}" ng-model="selectedServcies.voyage" disabled="disabled"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.igmNumber}}" ng-model="selectedServcies.igmNumber" disabled="disabled"></td>      
		    <td><input type="text" class="roundshap2" value="{{selectedServcies.consolidated_indicator}}" ng-model="selectedServcies.consolidated_indicator" disabled="disabled"></td> 
			<td><select ng-model="selectedServcies.edi" class="smallDPCss" disabled="disabled"> <option value="F" selected>F</option> 	</select></td>
			<td><select ng-model="selectedServcies.nonEdi" class="smallDPCss" disabled="disabled"> <option value="F" selected>P</option></select></td>
			<td><input type="text" class="roundshap2" 	value="{{selectedServcies.service}}" 	ng-model="selectedServcies.service" disabled="disabled"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.customTerminalCode}}" 	ng-model="selectedServcies.customTerminalCode"></td>
			<td><input type="text" class="roundshap2" value="{{selectedServcies.imoCode}}" ng-model="selectedServcies.codeCode"></td>

		</tr>
	</table>
