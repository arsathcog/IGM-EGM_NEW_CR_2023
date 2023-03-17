 <div>
 <table class="whitebg">
         <tr>{{selectedServcies.totalItems}}
            <th class="TableLeftSub tableVessel-width">Sr.No</th>
            <th class="TableLeftSub tableVessel-width" ng-if="selectedServcies.fromItemNo == null || selectedServcies.fromItemNo == '' " >Select All
            	<input type="checkbox"  value="Y" name="selectall" title="Specify From Item No"  disabled onclick="selectsAll()"  >
            </th>
            <th class="TableLeftSub tableVessel-width" ng-if="selectedServcies.fromItemNo != null && selectedServcies.fromItemNo !='' " >Select All
            	<input type="checkbox"  value="Y" name="selectall" id = "selectAllCheckBox" onclick="selectsAll()" ng-model="isBlSelecteSav" ng-checked = "isBlSelecteSave == 'true'" ng-click="blcheckTotalIteamSelectAll(this)" >
            </th>
            <th class="TableLeftSub tableVessel-width">BL NO</th>
            <th class="TableLeftSub tableVessel-width">BL Date</th>
            <th class="TableLeftSub tableVessel-width">Vessel Code</th>
            <th class="TableLeftSub tableVessel-width">Voyage No</th>
            <th class="TableLeftSub tableVessel-width">Line No</th>
            <th class="TableLeftSub tableVessel-width">Cargo Movem</th>
            <th class="TableLeftSub tableVessel-width">Port of Shippment</th>
            <th class="TableLeftSub tableVessel-width">Port of Destination</th>
            <th class="TableLeftSub tableVessel-width">BL Type</th>
            <th class="TableLeftSub tableVessel-width">Importer Name</th>
            <th class="TableLeftSub tableVessel-width">Consignee Name</th>
            
         </tr>
         <tr   ng-repeat="item in BLS" ng-style="{'border-left':(item.bl==BLS[blIndex].bl)?'10px solid #2196F3':''}" ng-dblclick="setIndex($index);">
            
            
            <td class="bl_detail_Newl">{{$index + 1}}</td>
            <td class="bl_detail_Newl" ng-if="selectedServcies.fromItemNo == null || selectedServcies.fromItemNo=='' " >  
            	<input type="checkbox" name="chk"  onclick="unSelects(this)"  ng-model="item.isBlSave" title="Specify From Item No"  
             		   ng-checked="item.isBlSave == 'true'" value="item.isBlSave'" disabled>
            </td>
            <td class="bl_detail_Newl" ng-if="selectedServcies.fromItemNo != null && selectedServcies.fromItemNo!='' " >  
            	<input type="checkbox" name="chk" id = "subCheckBox" onclick="unSelects(this)" ng-model="item.isBlSave" ng-checked="item.isBlSave == 'true'"  
            		   ng-click="blcheckTotalIteam(this)" value="item.isBlSave'">
            </td>
			<td class="bl_detail_Newl">{{item.bl}}</td>
			<td class="bl_detail_Newl">{{item.blDate}}</td>
            <td class="bl_detail_Newl">{{item.vessel}}</td>
            <td class="bl_detail_Newl">{{item.voyage}}</td>
            <td class="bl_detail_Newl">{{item.itemNumber}} </td>
            <td class="bl_detail_Newl">{{item.cargoMovmnt}}</td>
            <td class="bl_detail_Newl">{{item.portOfLoading}}</td>
            <td class="bl_detail_Newl">{{item.pod}}</td>
            <td class="bl_detail_Newl">{{item.blType}}</td>
            <td class="bl_detail_Newl">{{item.pol}} </td>
            <td class="bl_detail_Newl">{{item.consigneeName}} </td>
                     
         </tr>
	
      </table>

    </div>
    
    <style>  
    .bl_detail_Newl:nth-child(even) {
       background-color: #dddddd;
     }
     
       input[type=checkbox] {
            vertical-align: middle;
            position: relative;
            bottom: 1px;
        }
</style>
 <script type="text/javascript">  
 function selectsAll(){  
	 return false;
     var eleAll=document.getElementsByName('selectall');
     var ele=document.getElementsByName('chk');
     debugger;
		if (eleAll[0].checked == true) {
				for (var i = 0; i < ele.length; i++) {
					if (ele[i].type == 'checkbox') {
						ele[i].checked = true;
					}
				}
			} else {
				for (var i = 0; i < ele.length; i++) {
					if (ele[i].type == 'checkbox')
						ele[i].checked = false;
				}
			}
  }

		function unSelects(id) {
			debugger;
			var eleAll = document.getElementsByName('selectall');
			if (id.checked == false)
				eleAll[0].checked = false;

		}
	</script>