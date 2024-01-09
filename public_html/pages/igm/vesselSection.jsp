 <div class="flex-container whitebg">
      <div style="float: left; width: 710px; height: 100%; margin-left: 9px;">
       <div style="float: left; width: 350px;">
            <table class="whitebg">
               <tr>               
                  <th class="veseelRemoveBorder">Submitter Type</th>
                  <td>
                    <select 
                        ng-model="selectedServcies.submitter_type" class="smallDPCss" >
                        <option value="ASA">ASA</option>
                        <option value="ATO">ATO</option>
                        <option value="ASC">ASC</option>
                        <option value="ANC">ANC</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Agent Code</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.agentCode}}" ng-model ="selectedServcies.agentCode" >
                  </td>
               </tr>
               <tr> 
                  <th class="veseelRemoveBorder" ng-if="pageType=='IGM'" >Terminal</th>
                  <th class="veseelRemoveBorder" ng-if="pageType=='EGM'" >From Terminal</th>
                  <td ng-if="pageType=='IGM'" >
						<select class="smallDPCss" ng-model="selectedServcies.podTerminalPort" ng-options="x for x in terminalCodeArray">
						</select>
                  </td>
                  <td ng-if="pageType=='EGM'" ><input type="text" class="roundshap2"
                     value="selectedServcies.polTerminalPort"
                     ng-model="selectedServcies.polTerminalPort"  >
                  </td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Sub Terminal</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.subTermil}}"
                     ng-model="selectedServcies.subTermil"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Vessel Type</th>
                  <td>
                       <select ng-model="selectedServcies.vesselType" class="smallDPCss" ng-options="x.value as x.name for x in VesselTypesArrayObj">
                      </select>
                  </td>
               </tr>
               <tr>                   
                  <th class="veseelRemoveBorder">Mode of Transport</th>
                  <td>
                      <select ng-model="selectedServcies.mode_of_transport" class="smallDPCss"  >
                        <option value="1" >Sea</option>
                        <option value="2">Rail</option>
                        <option value="3">Truck</option>
                        <option value="4">Air</option>
                     </select>
                     
                    <input type="hidden" class="roundshap2"
                     value="selectedServcies.prtOfCallSeqNmbr"
                     ng-model="selectedServcies.prtOfCallSeqNmbr" >
                     
                     <input type="hidden" class="roundshap2"
                     value="selectedServcies.prtOfCallCdd"
                     ng-model="selectedServcies.prtOfCallCdd"  >
                     
                     <input type="hidden" class="roundshap2"
                     value="selectedServcies.prtOfCallName"
                     ng-model="selectedServcies.prtOfCallName"  >
                     
                     <input type="hidden" class="roundshap2"
                     value="selectedServcies.nxtPrtOfCallCdd"
                     ng-model="selectedServcies.nxtPrtOfCallCdd"  >
                     
                     <input type="hidden" class="roundshap2"
                     value="selectedServcies.nxtPrtOfCallName"
                     ng-model="selectedServcies.nxtPrtOfCallName"  >

                  </td>
                  
               </tr>
               <tr>
               <tr>
                  <th class="veseelRemoveBorder">Type Transport
                     Means
                  </th>
                  <td>    
                       <select name="figm001" id="typeTransportMeans"
                        ng-model="selectedServcies.typeTransportMeans"
                        class="smallDPCss" >
                        <option value="10">10</option>
                        <option value="11">11</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">EquimentType</th>
                  <td>
                     <select ng-model="selectedServcies.equimentType"
                        class="smallDPCss" ng-model="selectedServcies.equimentType" >
                        <option value="BB"  >BB</option>
                        <option value="BL"  >BL</option>
                        <option value="CH"  >CH</option>
                        <option value="CN" selected="">CN</option>
                        <option value="OBE"  >OBE</option>
                        <option value="TE" >TE</option>
                     </select>
                  </td>
               </tr>
              
            </table>
         </div>
         <div style="float: left; width: 350px;">
            <table class="whitebg">
              <tr>
               <th class="veseelRemoveBorder">Call Sign</th>
               <td><input type="text" class="roundshap2"
                  value="{{selectedServcies.callSing}}"
                  ng-model="selectedServcies.callSing"></td>
                </tr>   
               <tr>
             <tr>
               <th class="veseelRemoveBorder">Line Code</th>
               <td ng-if="pageType=='IGM'">
	               <!-- <select  
	                        class="smallDPCss" ng-model="selectedServcies.lineCode" >
	                        <option value="RCL"  >RCL</option>
	                        <option value="RCA1"  >RCA1</option>
	                        <option value="RCA1"  >RCA1</option  ng-model="selectedServcies.lineCode"
	                </select> -->
	                <!-- <select class="smallDPCss" ng-options="x for x in vesseLineCodeArray"> </select> -->
	                
	             <!--   <input type="text" class="roundshap2"
	                  value="{{selectedServcies.lineCode}}"
	                  ng-model="selectedServcies.lineCode">  -->   
	                    
	               <input type="text" class="roundshap2" ng-model="selectedServcies.lineCode"> 
               </td> 
               <td ng-if="pageType=='EGM'">
	                 <input type="text" class="roundshap2"
	                  value="{{selectedServcies.lineCode}}"
	                  ng-model="selectedServcies.lineCode">
               </td> 
                </tr>   
               <tr>
                  <th class="veseelRemoveBorder">Shipping Line
                     Bond No.
                  </th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.shipping_line_bond_no_r}}"
                     ng-model="selectedServcies.shipping_line_bond_no_r"></td>
               </tr>
               <tr> 
                  <th class="veseelRemoveBorder">Igm No</th>
                  <td ng-if="pageType=='IGM'"><input  type="text" class="roundshap2"
                     value="{{selectedServcies.igmNumber}}" ng-change="igmDateUpdate()"
                     ng-model="selectedServcies.igmNumber" disabled="disabled" ></td>
                   <td ng-if="pageType=='EGM'"><input  type="text" class="roundshap2"
                     value="{{selectedServcies.igmNumber}}" ng-change="igmDateUpdate()"
                     ng-model="selectedServcies.igmNumber" ></td>
                     
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Igm year</th>
                  <td ng-if="pageType=='IGM'"><input type="text" class="roundshap2"  
                     value="{{selectedServcies.igmYear}}"
                     ng-model="selectedServcies.igmYearVal" disabled="disabled"  ></td>
                  <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2"  
                     value="{{selectedServcies.igmYear}}"
                     ng-model="selectedServcies.igmYearVal" ></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">IGM Date</th>
                  <td ng-if="pageType=='IGM'"><input type="text" class="roundshap2"
                     value="{{selectedServcies.igmDate}}"
                     ng-model="selectedServcies.igmDateVal" id="igmDate" onclick="dateToCommon(this)" disabled="disabled"   ></td>
                  <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2"
                     value="{{selectedServcies.igmDate}}"
                     ng-model="selectedServcies.igmDateVal" id="igmDate" onclick="dateToCommon(this)" ></td>   
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Rotation No</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.rotnNo}}"
                     ng-model="selectedServcies.rotnNo"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Rotation Date</th>
                  <td><input type="text" class="roundshap2" id="rotnDate" onclick="dateToFormat(this)"
                     value="{{selectedServcies.rotnDate}}"
                     ng-model="selectedServcies.rotnDate" ></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">General
                     Description
                  </th>
                  <td>
                  <select ng-model="selectedServcies.genDesc"
                        class="smallDPCss" ng-model="selectedServcies.equimentType" >
                        <option value="3"  >3</option>
                        <option value="7"  >7</option>
                        <option value="9"  >9</option>
                  </select>
                  <!-- <input type="text" class="roundshap2"
                     value="{{selectedServcies.genDesc}}" disabled="disabled"> -->
                  </td>
               </tr>
               <tr>
                  <td></td>
               </tr>
            </table>
         </div>
         <div id="vessel-ports"
            style="float: left; width: 700px; height: 50%;">
            <ul>
               <li><a href="#ports">Ports</a></li>
               <li><a href="#Totals">Totals</a></li>
               <li><a href="#nos">nos</a></li>
               <li><a href="#Enter(Y/N)">ENTER(Y/N)</a></li>
               <li><a href="#ApplyIgmDetails">ApplyIgmDetails</a></li>
            </ul>
            <div id="ports">
               <div class="tab-title">Ports</div>
				<table class="whitebg">
					<tr>
						<th class="veseelRemoveBorder">Port Of Origin</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.portOrigin}}"
							ng-model="selectedServcies.portOrigin"> <input
							type="hidden"
							value="{{selectedServcies.port_of_call_name_portOrigin}}"
							ng-model="selectedServcies.port_of_call_name_portOrigin" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Port Of Origin')" /></td>
						<th class="veseelRemoveBorder">Port Of Arrival</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.portArrival}}"
							ng-model="selectedServcies.portArrival"> <input
							type="hidden"
							value="{{selectedServcies.port_of_call_name_portArrival}}"
							ng-model="selectedServcies.port_of_call_name_portArrival" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Port Of Arrival')" /></td>
					</tr>
					<tr>
						<th class="veseelRemoveBorder">Last Port -1</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.lastPort3}}"
							ng-model="selectedServcies.lastPort3" /> <input type="button"
							value=". . ." name="igmpolterminalfield" class="lookUpbtnbutton"
							ng-click="portPopUp('Last Port -1')" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_last3}}"
							ng-model="selectedServcies.port_of_call_name_last3" /></td>
						<th class="veseelRemoveBorder">Next Port 1</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.nextport1}}"
							ng-model="selectedServcies.nextport1" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_nextport1}}"
							ng-model="selectedServcies.port_of_call_name_nextport1" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Next Port 1')" /></td>
					</tr>
					<tr>
						<th class="veseelRemoveBorder">Last Port -2</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.lastPort2}}"
							ng-model="selectedServcies.lastPort2" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_last2}}"
							ng-model="selectedServcies.port_of_call_name_last2" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Last Port -2')" /></td>
						<th class="veseelRemoveBorder">Next Port 2</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.nextport2}}"
							ng-model="selectedServcies.nextport2" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_nextport2}}"
							ng-model="selectedServcies.port_of_call_name_nextport2" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Next Port 2')" /></td>
					</tr>
					<tr>
						<th class="veseelRemoveBorder">Last Port -3</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.lastPort1}}"
							ng-model="selectedServcies.lastPort1" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_last1}}"
							ng-model="selectedServcies.port_of_call_name_last1" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Last Port -3')" /></td>
						<th class="veseelRemoveBorder">Next Port 3</th>
						<td><input type="text" class="roundshap9"
							value="{{selectedServcies.nextport3}}"
							ng-model="selectedServcies.nextport3" /> <input type="hidden"
							value="{{selectedServcies.port_of_call_name_nextport3}}"
							ng-model="selectedServcies.port_of_call_name_nextport3" /> <input
							type="button" value=". . ." name="igmpolterminalfield"
							class="lookUpbtnbutton" ng-click="portPopUp('Next Port 3')" /></td>
					</tr>
				</table>
			</div>
            <div id="Totals">
               <div class="tab-title">Totals</div>
               <table class="whitebg">
                  <tr>
                     <th class="veseelRemoveBorder">Total Items</th> 
                     <td><input type="text" class="roundshap2"  ng-model="selectedServcies.totalItem"
                        value="{{selectedServcies.totalItem}}">
                        
                        <input type="hidden" class="roundshap2"  ng-model="selectedServcies.totalNmbrOfLines"
                        value="{{selectedServcies.totalNmbrOfLines}}" disabled> <!-- number of container  -->
                         <input type="hidden" class="roundshap2"  ng-model="selectedServcies.containerMsBl"
                        value="{{selectedServcies.containerMsBl}}" disabled> <!-- number of container exclude HBL  -->
                        
                        </td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">No Of Item In
                        Prior
                     </th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.noOfItemInPrior}}"></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">NoOfItemInFinal</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.noOfItemInFil}}"></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">No Of Item In
                        Supplimentary
                     </th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.noOfItemInSupplimentary}}"></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Total Packages</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.total_number_of_packages}}"></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Total Weight</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.totalWeight}}"></td>
                  </tr>
               </table>
            </div>
            <div id="nos">
               <div class="tab-title">Nos</div>
               <table class="whitebg">
                  <tr>
                     <th class="veseelRemoveBorder">No of Passenger</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.noOfPassenger}}"></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">No of Crew</th> 
                     <td><input type="text" class="roundshap2" ng-model = "selectedServcies.noOfCrew"
                        value="{{selectedServcies.noOfCrew}}" readonly ></td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Remark</th>
                     <td><input type="text" class="roundshap2" 
                        value="{{selectedServcies.remarkVessel}}"></td>
                  </tr>
               </table>
            </div>
            <div id="Enter(Y/N)">
               <div class="tab-title">Enter(Y/N)</div>
               <table class="whitebg">
                  <tr>
                     <th class="veseelRemoveBorder">Select Bottom Cargo</th>
                     <td>
                        <select ng-model="selectedServcies.smBtCargo"
                           class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                     <th class="veseelRemoveBorder">Passenger List</th>
                     <td>
                        <select ng-model="selectedServcies.passengerList"
                           class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Ship Store
                        Declaration
                     </th>
                     <td>
                        <select ng-model="selectedServcies.shipStrDect"
                           class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                     <th class="veseelRemoveBorder">Crew Effect</th>
                     <td>
                        <select ng-model="selectedServcies.crewEffect"  class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Crew List
                        Declaration
                     </th>
                     <td><select ng-model="selectedServcies.passengerList"
                           class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select><!-- <input type="text" class="roundshap2"
                        value="{{selectedServcies.crewListDeclaration}}"
                        ng-model="selectedServcies.crewListDeclaration"> --></td>
                     <th class="veseelRemoveBorder">Maritime
                        Declaration
                     </th>
                     <td>
                        <select ng-model="selectedServcies.mariTimeDecl"
                           class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">Cargo Declaration</th>
                     <td>
                        <select ng-model="selectedServcies.cargoDeclaration"  class="smallDPCss">
                           <option value="Y">Y</option>
                           <option value="N">N</option>
                        </select>
                     </td>
                  </tr>
               </table>
            </div>
            <div id="ApplyIgmDetails">
               <div class="tab-title">ApplyIgmDetails</div>
               <table class="whitebg">
                  <tr>
                     <th class="veseelRemoveBorder">IGM Number</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.igmNumber}}"
                        ng-model="selectedServcies.igmNumber"  ></td>
                         <th> <input type="button" value="Apply"  class="event_btnbutton" ng-click="applySave()"   ></th> 
                  </tr>
                  <tr>
                     <th class="veseelRemoveBorder">IGM Date</th>
                     <td><input type="text" class="roundshap2"
                        value="{{selectedServcies.igmDate}}"
                        ng-model="selectedServcies.igmDate" id="igmDateApplyIgmDetails" ></td>
                  </tr>
                 
               </table>
            </div>
         </div>
      </div>
     
        <div style="float: left; display: table-cell; width: 400px; height: 100%;">
            <table class="whitebg">
               <tr>
                  <th class="veseelRemoveBorder">Job No</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.jobNo}}"
                     ng-model="selectedServcies.jobNo"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Job Date</th>
                  <td><input type="text" class="roundshap2" id="jobDate" onclick="dateToCommon(this)"
                     value="{{selectedServcies.jobDate}}"
                     ng-model="selectedServcies.jobDate"  ></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Master Name</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.masterName}}"
                     ng-model="selectedServcies.masterName"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Nationality Of
                     Vessel
                  </th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.vesselNation}}"
                     ng-model="selectedServcies.vesselNation"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder" ng-if="pageType=='IGM'">Arrival Date</th>
                    <th class="veseelRemoveBorder" ng-if="pageType=='EGM'">Departure Date</th>
                    
                  <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2" id="arrivalDate" onclick="dateToCommon(this)"
                     value="{{selectedServcies.arrivalDate}}" ng-model="selectedServcies.departureDate"  >
                  </td>
                    
                  <td ng-if="pageType=='IGM'"><input type="text" class="roundshap2" id="arrivalDate" onclick="dateToCommon(this)"
                     value="{{selectedServcies.arrivalDate}}" ng-model="selectedServcies.arrivalDate"  >
                  </td>
                  
                    <!-- <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2" id="arrivalDate" onclick="dateToCommon(this)"
                     value="{{selectedServcies.departureDate}}" ng-model="selectedServcies.departureDate"  >
                  </td> -->
               </tr>
               <tr>
                  <th class="veseelRemoveBorder" ng-if="pageType=='IGM'">Arrival Time</th>
                    <th class="veseelRemoveBorder" ng-if="pageType=='EGM'">Departure Time</th>
                  
                  <td ng-if="pageType=='IGM'"><input type="text" class="roundshap2"  ng-model="selectedServcies.arrivalTime" 
                     value="{{selectedServcies.arrivalTime}}"  >
                  </td>
                  
                    <td ng-if="pageType=='EGM'"><input type="text" class="roundshap2"  ng-model="selectedServcies.departureTime" 
                     value="{{selectedServcies.departureTime}}"  >
                  </td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Position</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.position}}"
                     ng-model="selectedServcies.position"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Lighthouse Due</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.lightDue}}" ng-model="selectedServcies.lightDue"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Gross Weight
                     Vessel
                  </th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.grossWeight}}"
                     ng-model="selectedServcies.grossWeight"  ></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Net Weight Vessel</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.netWeight}}"
                     ng-model="selectedServcies.netWeight"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Exchange Rate</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.exchangeRate}}"
                     ng-model="selectedServcies.exchangeRate"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Departure Date</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.cigmDate}}"
                     ng-model="selectedServcies.cigmDate" id="cIgm" onclick="dateToCommon(this)"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Departure Time</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.cigmNo}}"
                     ng-model="selectedServcies.cigmNo"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Smtp No</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.smtpNo}}"
                     ng-model="selectedServcies.smtpNo"></td>
               </tr>
               <tr>
                  <th class="veseelRemoveBorder">Smtp Date</th>
                  <td><input type="text" class="roundshap2"
                     value="{{selectedServcies.smtpDate}}"
                     ng-model="selectedServcies.smtpDate" id="smtp" onclick="dateToCommon(this)"></td>
               </tr>
            </table>
         </div>
    <div>
         <div style="color: #cccccc">Master Pane</div>
          <div style="color:#CCCCCC; border: 1px solid #cccccc; width:220px;height:120px"> </div>
         <table>
         <tr>
         <th class="veseelRemoveBorder">Sender ID</th>
         <td>
         <input type="text" class="roundshap2"
                     value="{{selectedServcies.senderId}}"
                     ng-model="selectedServcies.senderId"></td>
         </tr>
         <tr>
         <th class="veseelRemoveBorder">Receiver ID</th>
         <td>
         <input type="text" class="roundshap2"
                     value="{{selectedServcies.recieverId}}"
                     ng-model="selectedServcies.recieverId"></td>
         </tr>
         <tr>
         <th class="veseelRemoveBorder">Authorized Representative Code</th>
         <td>
           <input type="text" class="roundshap2"
                     value="{{selectedServcies.authReprsntvCd}}"
                     ng-model="selectedServcies.authReprsntvCd"></td>
         </td>
         </tr>
         <tr  ng-if="pageType=='EGM'" >
         <th>shipping Bill</th>
				<td>
					<form enctype="multipart/form-data" id="shippingFileForm">
						<input class="event_btnbutton" type="file"
							name="shippingFile" id="shippingFile" size="50px" />
						<button type="button" class="btn btn-info event_btnbutton"
							ng-click="onUploadShippingBill()">Upload</button>
					</form>


				</td>
			</tr>
         </table>
        
      </div> 
   
     </div>
     
   
    