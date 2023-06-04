    <style>
#saveDataModals{
 border : 1px solid #8b8b8b;
  width:30%;
  margin-left: 38%;
  height: 352px;
  padding: 4px;
  border-radius: 4px;
  display:none;
  position: absolute;
  z-index: 1;
  background-color: white;
  top: 30%;
}
.head {
    background-color: #d7d7d7;
    margin: 4px 0px;
    border-radius: 3px;
    padding: 5px;
    font-weight: 600;
    
}
.heading{
  width:28%;
  display: inline-block;
  font-size: 24px;
  padding-left:11px;
}
.nav{
  width: 69%;
  display: inline-block;
  text-align: end;
}
.closeBtn{
  background-color: white;
  display:inline;
  padding: 0px 5px;
  border-radius: 2px;
  cursor: pointer;
}
.footerModal{
  border-top: 1px solid #d7d7d7;
  text-align:end;
  padding: 15px;
}
.cancelbtn{
  border: 1px solid #d7d7d7;
  display:inline;
  padding:5px 10px;
  cursor: pointer;
  background-color: #f0f0f0;
}
#modalBody{
  min-height: 200px;
  max-height: 200px;
	overflow: auto;
  padding:15px;
  font-size: 14px;
  font-weight: 600;
}
.textHeading{
  font-size:18px;
  font-weight:600;
  margin: 0 15px;
  margin-top: 15px;
  border-bottom: 1px solid #d7d7d7;
}

.loadingMenu{
	display:inline;font-weight:600;
	color: green;
	font-size: 12px;
}
.loadingMenu.active{
  border: 2px solid #c4c4c4;
  border-radius: 50%;
  border-top: 2px solid #0052CC;
  width: 15px;
  height: 15px;
  -webkit-animation: spin 1s linear infinite; /* Safari */
  animation: spin 1s linear infinite;
  display: inline-block;
  margin-left: 20px;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

</style>
<div id="saveDataModal" title="IGM Save Details">
  <div>
    <p class="textHeading"> Due to huge No of BL It may take Some time.</p>
    <div >
      <div>Vessel Voyage Save: <div class="loadingMenu active" id="phaseVsslVoyg"></div></div>
      <br>
      <div>BL Save:  
        <div class="paginationclass"
							ng-repeat="row in blSavePhases" >  
		   <div style="padding-left:20px; font-weight:100;">
           		<div>BL Save Phase {{row+1}} : <div class="loadingMenu active" id="phase{{row}}"></div></div>
           </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
 
</script>