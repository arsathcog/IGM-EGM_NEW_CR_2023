
<div class="container">
	<div class="table-responsive">
	<form enctype="multipart/form-data" id="fileCrewEffect">
		<br />
		<div align="center">
			Select File <input class="browse" id="csvCrewEffet" type="file" name="fileExl">
				<input id="bl" name="bl" value="{{BLS[blIndex].bl}}" type='hidden'>
				<input id="vessel" name="vessel" value="{{selectedServcies.vessel}}" type='hidden'>
				<input id="voyage" name="voyage" value="{{selectedServcies.voyage}}" type='hidden'>
				<input id="pod" name="pod" value="{{selectedServcies.pod}}" type='hidden'>
				<button type="button"  ng-click="importCVSFileAjax('C')" class="btn btn-info">Import</button>
				<button type="button" onClick="downloadCVSFileAjax('C')" class="btn btn-info">Download</button>
				<button type="button" ng-click="cVSFileDeleteAjex('C')" class="btn btn-info">Delete</button>
				<input id="checkCSV" name="checkCSV" value="C" type='hidden'>
		</div>
		</form>
		<br />
		<div id="crewEffet">
		
		<table  id="crewEffetTable" class="whitebg">
			<tr>
				<th class="TableLeftSub tableVessel-width">Person on Board sequence no.</th>
				<th class="TableLeftSub tableVessel-width">Sequence No.</th>
				<th class="TableLeftSub tableVessel-width">Description Code</th>
				<th class="TableLeftSub tableVessel-width">Description</th>
				<th class="TableLeftSub tableVessel-width">Quantity On Board</th>
				<th class="TableLeftSub tableVessel-width">Quantity On Board Code</th>
				<th class="TableLeftSub tableVessel-width">Vessel SrNo</th>
				
            <tr>
            <tr ng-repeat="cellData in crewEffetTable"    >
            	  					 <td class="bl_detail_Store">   {{cellData.personOnBoardSequenceNo}}    </td>  
            	  					 <td class="bl_detail_Store">   {{cellData.sequenceNo}}    </td>  
									 <td class="bl_detail_Store">   {{cellData.crewEfctDescCdd}}    </td>  
									 <td class="bl_detail_Store">   {{cellData.crewEfctsDesc}}    </td>  
									 <td class="bl_detail_Store">   {{cellData.crewEfctQntyOnbrd }}   </td>  
									 <td class="bl_detail_Store">   {{cellData.crewEfctQntyCdOnbrd  }}  </td>  
									 <td class="bl_detail_Store">   {{cellData.crewEfctsVsslSeqNmbr}}    </td>  
            </tr>
			</table>
		
		</div>
		
	</div>
</div>
<script>

</script>