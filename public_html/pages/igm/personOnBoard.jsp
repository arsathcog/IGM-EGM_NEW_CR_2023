
<div class="container">
	<div class="table-responsive">
		<form enctype="multipart/form-data" id="filePersonOnBoard">
			<br />
			<div align="center">
				Select File <input class="browse" id="csv" type="file" name="fileExl">
				<input id="bl" name="bl" value="{{BLS[blIndex].bl}}" type='hidden'>
				<input id="vessel" name="vessel" value="{{selectedServcies.vessel}}" type='hidden'>
				<input id="voyage" name="voyage" value="{{selectedServcies.voyage}}" type='hidden'>
				<input id="pod" name="pod" value="{{selectedServcies.pod}}" type='hidden'>
				<input id="pol " name="pol" value="{{selectedServcies.pol}}" type='hidden'>
				<button type="button" ng-click="importCVSFileAjax('P')" class="btn btn-info">Import</button>
				<button type="button" onClick="downloadCVSFileAjax('P')" class="btn btn-info">Download</button>
				<button type="button" ng-click="cVSFileDeleteAjex('P')" class="btn btn-info">Delete</button>
				<input id="checkCSV" name="checkCSV" value="P" type='hidden'>
			</div>
		</form>
		<br />
		<div id="prsnOnBord" style="width: 1462px; overflow-y: scroll;  height: 400px; overflow-x: scroll;margin-left: -19px;">
			<table  id="prsnOnBordTable" class="whitebg">
			<tr>
				<th class="TableLeftSub tableVessel-width">Sequence No.</th>
				<th class="TableLeftSub tableVessel-width">Type Code</th>
				<th class="TableLeftSub tableVessel-width">Family Name</th>
				<th class="TableLeftSub tableVessel-width">Given Name</th>
				<th class="TableLeftSub tableVessel-width">Nationality Code</th>
				<th class="TableLeftSub tableVessel-width">Intransit Indicator</th>
				<th class="TableLeftSub tableVessel-width">Crew Member Rank Or Rating</th>
				<th class="TableLeftSub tableVessel-width">Crew Member Rank Or Rating Code</th>
				<th class="TableLeftSub tableVessel-width">Port of Embarkation Code</th>
				<th class="TableLeftSub tableVessel-width">Port of Embarkation Name</th>
				<th class="TableLeftSub tableVessel-width">Port of Disembarkation Code</th>
				<th class="TableLeftSub tableVessel-width">Port of Disembarkation Name</th>
				<th class="TableLeftSub tableVessel-width">Gender Code</th>
				<th class="TableLeftSub tableVessel-width">Date Of Birth</th>
				<th class="TableLeftSub tableVessel-width">Placeofbirth Name</th>
				<th class="TableLeftSub tableVessel-width">Country Of Birth Code</th>
				<th class="TableLeftSub tableVessel-width">Identity DOC Expiry Date</th>
				<th class="TableLeftSub tableVessel-width">Identity Or Travel DOC Issuingnation Code</th>
				<th class="TableLeftSub tableVessel-width">Identity Or Travel DOC Nmbr</th>
				<th class="TableLeftSub tableVessel-width">Identity Or Travel DOC Type Code</th>
				<th class="TableLeftSub tableVessel-width">Visa</th>
				<th class="TableLeftSub tableVessel-width">PNR Number</th>
            <tr>
            <tr   ng-repeat="cellData in prsnOnBordTable"    >
           			 <td class="bl_detail_Store">{{$index + 1}} </td>
					 <td class="bl_detail_Store">{{cellData.prsnTypCdd}} </td>
					 <td class="bl_detail_Store">{{cellData.prsnFamilyName }} </td>								 							 
					 <td class="bl_detail_Store"> {{cellData.prsnGivenName}}  </td>
					 <td class="bl_detail_Store">  {{ cellData.prsnNatnltyCdd}}    </td>  
					 <td class="bl_detail_Store"> {{  cellData.psngrInTransitIndctr}}    </td>  
					 <td class="bl_detail_Store"> {{  cellData.crewmemberRankOrRating}}    </td>  
					 <td class="bl_detail_Store"> {{  cellData.crewmemberRankOrRatingCdd}}    </td>  
					 <td class="bl_detail_Store"> {{  cellData.psngrPrtOfEmbrktnCdd  }}  </td>  
					 <td class="bl_detail_Store"> {{  cellData.psngrPrtOfEmbrktnName }}   </td>  
					 <td class="bl_detail_Store"> {{  cellData.psngrPrtOfDsmbrktnCdd }}   </td>  
					 <td class="bl_detail_Store"> {{  cellData.psngrPrtOfDsmbrktnName }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnGenderCdd }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnDtOfBirth  }}  </td>  
					 <td class="bl_detail_Store">   {{cellData.prsnPlaceOfBirthName}}    </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnCntryOfBirthCdd }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnIdDocExpiryDt  }}  </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnIdOrTravelDocIssuingNationCdd }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnIdOrTravelDocNmbr }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.prsnIdOrTravelDocTypCdd }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.visa }}   </td>  
					 <td class="bl_detail_Store">  {{ cellData.pnrNumber}}    </td>	
						
						
						            </tr>
			</table>

		</div>
	</div>
</div>
