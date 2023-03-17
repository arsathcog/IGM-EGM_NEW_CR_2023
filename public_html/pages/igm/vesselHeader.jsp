 
      <table class="whitebg">
         <tr>
            <th class="TableLeftSub tableVessel-width">Sr.No</th>
            <th class="TableLeftSub tableVessel-width">Vessel Code</th>
            <th class="TableLeftSub tableVessel-width">Voyage No</th>
            <th class="TableLeftSub tableVessel-width">Vessel Name</th>
            <th class="TableLeftSub tableVessel-width">Nationality</th>
            <th class="TableLeftSub tableVessel-width">EDI</th>
            <th class="TableLeftSub tableVessel-width">NON EDI</th>
            <th class="TableLeftSub tableVessel-width">Parent Voy</th>
            <th class="TableLeftSub tableVessel-width">Service</th>
            <th class="TableLeftSub tableVessel-width">VIA/VCN</th>
            <th class="TableLeftSub tableVessel-width">Custom Code</th>
            <th class="TableLeftSub tableVessel-width">IMO Code</th>
            <th class="TableLeftSub tableVessel-width">Port</th>
            <!--<th class="TableLeftSub tableVessel-width">Terminal</th>-->
            <th class="TableLeftSub tableVessel-width">Custom Terminal Code</th>
            <th class="TableLeftSub tableVessel-width">From Item No</th>
            <th class="TableLeftSub tableVessel-width">To Item No</th>
            
            <th class="TableLeftSub tableVessel-width">New Vessel</th>
            <th class="TableLeftSub tableVessel-width">Road Carr. Code</th>
            <th class="TableLeftSub tableVessel-width">TP Bond No</th>
            
         </tr>
         <tr>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.serialNumber}}"
               ng-model="selectedServcies.serialNumber" disabled="disabled"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.vessel}}" 
               ng-model="selectedServcies.vessel" disabled="disabled" ></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.voyage}}"
               ng-model="selectedServcies.voyage" disabled="disabled"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.vesselName}}"
               ng-model="selectedServcies.vesselName" disabled="disabled"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.vesselNation}}"
               ng-model="selectedServcies.vesselNation" disabled="disabled"></td>
            <td>
               <select ng-model="selectedServcies.edi" class="smallDPCss">
                  <option value="A"  >A</option>
                  <option value="D"  >D</option>
                  <option value="F" selected>F</option>
                  <option value="P"  >P</option>
                  <option value="V"  >V</option>
               </select>
            </td>
            <td>
               <select ng-model="selectedServcies.nonEdi" class="smallDPCss">
                  <option value="F"  >F</option>
                  <option value="P" selected>P</option>
                  <option value="S"  >S</option>
               </select>
            </td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.parentVoy}}"
               ng-model="selectedServcies.parentVoy"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.service}}"
               ng-model="selectedServcies.service" disabled="disabled"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.viaVcn}}"
               ng-model="selectedServcies.viaVcn"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.customCode}}"
               ng-model="selectedServcies.customCode"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.imoCode}}"
               ng-model="selectedServcies.imoCode" disabled="disabled"></td>
            <td ng-if="pageType=='IGM'"><input type="text" class="roundshap2"
               value="{{selectedServcies.pod}}"
               ng-model="selectedServcies.pod" disabled="disabled"></td>
			 <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2"
               value="{{selectedServcies.pol}}"
               ng-model="selectedServcies.pol" disabled="disabled"></td>
			 
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.customTerminalCode}}"
               ng-model="selectedServcies.customTerminalCode" ></td>
            <td><input type="text" class="roundshap2"
               
               ng-model="selectedServcies.fromItemNo" ></td>
            <td><input type="text" class="roundshap2"
               value="{{changefromItemNo()}}"
               ng-model="selectedServcies.toItemNo" disabled="disabled"></td>
            <td><input type="text" class="roundshap2"
               value="{{selectedServcies.newVessel}}"
               ng-model="selectedServcies.newVessel"  ></td>
           
            <td> 
			    <select ng-model="selectedBL.roadCarrCodeVVS" class="smallDPCss" ng-change="roadCarrCodeHandle()">
   					 <option ng-repeat="at in roadCarrCode" value="{{ at.partnerValuedre }}" title="{{ at.descriptiondrw }}" >{{ at.partnerviews }}</option>
				</select>
		    </td>

			<td>
				 <select ng-model="selectedBL.tpbondnoVVS" class="smallDPCss" ng-change="roadTpBondNoHandler()" ng-disabled="TPVALIDATION">
	  					 <option ng-repeat="at in tpBondNoArr" value="{{ at.partnerValuedre }}" title="{{ at.descriptiondrw }}" >{{ at.partnerviews }}</option>
				</select>
			</td>

			<!-- <td>
              <select class="roundshap6"  onchange="cfsHandler(this)" ng-model="selectedBL.cfsCustomCode">
							 		<option ng-repeat="item in cfsCustomCode" value="{{item.podValue}}">
										{{item.descriptiondrw}}
										</option>
							 </select>
            </td> -->
            <!-- </td> -->
         </tr>
      </table>
   