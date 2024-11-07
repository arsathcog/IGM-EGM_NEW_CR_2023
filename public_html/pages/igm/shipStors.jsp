 <div class="container">
   <div class="table-responsive">
   <form enctype="multipart/form-data" id="fileShipStore">
    <br />
    <div align="center">Select File 
            <input id="csvShipStore" class="browse" type="file" name="fileExl">
			<input id="bl" name="bl" value=" {{BLS[blIndex].bl}}" type='hidden'>
			<input id="vessel" name="vessel" value="{{selectedServcies.vessel}}" type='hidden'>
			<input id="voyage" name="voyage" value="{{selectedServcies.voyage}}" type='hidden'>
			<input id="pod" name="pod" value="{{selectedServcies.pod}}" type='hidden'>
			<button type="button" ng-click="importCVSFileAjax('S')" class="btn btn-info">Import</button>
			<button type="button" onClick="downloadCVSFileAjax('S')" class="btn btn-info">Download</button>
			<button type="button" ng-click="cVSFileDeleteAjex('S')" class="btn btn-info">Delete</button>
			<input id="checkCSV" name="checkCSV" value="S" type='hidden'>
    </div>
     </form>
    <br />
    <div id="shipStore">
    <table  id="shipStoreTable" class="whitebg">
			<tr>
				<th class="TableLeftSub tableVessel-width">Sequence No.</th>
				<th class="TableLeftSub tableVessel-width">Article Name  Code</th>
				<th class="TableLeftSub tableVessel-width">Article</th>
				<th class="TableLeftSub tableVessel-width">Location</th>
				<th class="TableLeftSub tableVessel-width">Quantity On Board</th>
				<th class="TableLeftSub tableVessel-width">Quantity On Board Code</th>
			  <th class="TableLeftSub tableVessel-width">Vessel Srno</th> 
				
            <tr>
            <tr ng-repeat="cellData in shipStoreTable"    >
            
              <td class="bl_detail_Store">    {{$index + 1}}    </td>  
									 <td class="bl_detail_Store"> {{  cellData.articleNameCdd}}    </td>  
									 <td class="bl_detail_Store">  {{ cellData.articleNameText}}    </td>  
									 <td class="bl_detail_Store">   {{cellData.locOnbrdText }}   </td>  
									 <td class="bl_detail_Store">   {{cellData.qntyOnbrd}}    </td>  
									 <td class="bl_detail_Store">   {{cellData.qntyCdOnbrd}}    </td>  
									  <td class="bl_detail_Store">   {{cellData.vesselSrno}}    </td> 
            </tr>
            
			</table>
		
    
    </div>
   
   </div>
  </div>
<script>


 
	
</script>