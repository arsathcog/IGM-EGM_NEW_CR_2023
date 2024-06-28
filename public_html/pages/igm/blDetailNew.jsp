 <div>
 <table class="whitebg">
         <tr>{{selectedServcies.totalItems}}
            <th class="TableLeftSub tableVessel-width">Sr.No</th>
            <th class="TableLeftSub tableVessel-width" ng-if="selectedServcies.fromItemNo == null || selectedServcies.fromItemNo == '' " >Select All
            	<input type="checkbox"  value="Y" name="selectall" title="Specify From Item No"  disabled   >
            </th>
            <th class="TableLeftSub tableVessel-width" ng-if="selectedServcies.fromItemNo != null && selectedServcies.fromItemNo !='' " >Select All
            	<input type="checkbox"  value="Y" name="selectall" id = "selectAllCheckBox"  ng-model="isBlSelecteSav" ng-checked = "isBlSelecteSave == 'true'" ng-click="blcheckTotalIteamSelectAll(this);" >
            </th>
	    <th class="TableLeftSub tableVessel-width">HBL NO<i class="fa fa-angle-double-down"></i></th>
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
         <tbody   ng-repeat="item in BLS" ng-style="{'border-left':(item.bl==BLS[blIndex].bl)?'10px solid #2196F3':''}" ng-dblclick="setIndex($index);">
            <tr>
            
            <td class="bl_detail_Newl">{{$index + 1}}</td>
            <td class="bl_detail_Newl" ng-if="selectedServcies.fromItemNo == null || selectedServcies.fromItemNo=='' " >  
            	<input type="checkbox" name="chk"    ng-model="item.isBlSave" title="Specify From Item No"  
             		   ng-checked="item.isBlSave == 'true'" value="item.isBlSave'" disabled>
            </td>
            <td class="bl_detail_Newl" ng-if="selectedServcies.fromItemNo != null && selectedServcies.fromItemNo!='' " >  
            	<input type="checkbox" name="chk" id = "subCheckBox"  ng-model="item.isBlSave" ng-checked="item.isBlSave == 'true'"  
            		   onclick="unSelect(this);" ng-click="blcheckTotalIteam(this);" value="item.isBlSave'">
            </td>
	    <td class="bl_detail_Newl" ng-if=" item.blCriteria=='HBL'" ></td>
	    <%-- <td class="bl_detail_Newl" ng-if=" item.blCriteria=='MBL'></td> --%>
	    <td class="bl_detail_Newl" ng-if=" item.blCriteria=='MBL' && item.hblCount > 0" ><i class='fa fa-sort' style='font-size:20px;color:#4C8EED;margin-left: 25%;' ng-click="getHBLNo(this,$index);" ></i></td>
	    <td class="bl_detail_Newl" ng-if=" item.blCriteria=='MBL' && item.hblCount == 0" ></i></td>
	    <td class="bl_detail_Newl" ng-if=" item.blCriteria=='HBL'" ><i ng-click="getHBLNo(this,$index);" ></i></td>
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
	 <tr>
		         	 <td colspan = "14" >
			         	 <table id="{{item.bl}}" style="display:none;float:left;margin-left:200px">
			         	  <tr>
			         	    <th class="TableLeftSub tableVessel-width">Sqn NO</th>
			         	    <th class="TableLeftSub tableVessel-width">Select HBL</th>
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
			         	  <tr ng-repeat="item1 in item.hblArr"  ng-dblclick="setIndexHBL($index,this);">	
			         	  			<td class="bl_detail_Newl">{{$index + 1}}</td>
			         	  			<td class="bl_detail_Newl"><input type="go" name="chkHbl" id = "subCheckBoxHbl" onclick="unSelects(this)" ng-model="item1.isBlSave" ng-checked="item1.isBlSave == 'true'"  
            		   						ng-click="hblcheckTotalIteam(this);" value="item1.isBlSave'"></td>
			         	  			<td class="bl_detail_Newl">{{item1.hblNo}}</td>
									<td class="bl_detail_Newl">{{item1.blDate}}</td>
						            <td class="bl_detail_Newl">{{item1.vessel}}</td>
						            <td class="bl_detail_Newl">{{item1.voyage}}</td>
						            <td class="bl_detail_Newl">{{item1.itemNumber}} </td>
						            <td class="bl_detail_Newl">{{item1.cargoMovmnt}}</td>
						            <td class="bl_detail_Newl">{{item1.portOfLoading}}</td>
						            <td class="bl_detail_Newl">{{item1.pod}}</td>
						            <td class="bl_detail_Newl">{{item1.blType}}</td>
						            <td class="bl_detail_Newl">{{item1.pol}} </td>
						            <td class="bl_detail_Newl">{{item1.consigneeName}} </td>
						  </tr>       
			         	 </table>
		         	 </td>
		         	 
		          
		          </tr>
          </tbody>
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
 /* function selectAll(){  
	 debugger;
     var eleAll=document.getElementsByName('selectall');
     var ele=document.getElementsByName('chk');
     debugger;
		if (eleAll[0].checked == true) {
				for (var i = 0; i < ele.length; i++) {
					if (ele[i].type == 'checkbox'  && ele[i].checked == false) {
						ele[i].checked = true;
					}
				}
			} else {
				for (var i = 0; i < ele.length; i++) {
					if (ele[i].type == 'checkbox')
						ele[i].checked = false;
				}
			} 
  } */

 	function unSelect(id) {
		debugger;
		var eleAll = document.getElementsByName('selectall');
		if (id.checked == false)
			eleAll[0].checked = false; 
	
	} 
	</script>