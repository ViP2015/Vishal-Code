<html>
<head>
<title>Vishal WebTech HW-6</title>
<style type="text/css">
.center {
   	text-align: center;
   	width: 70%;
  	margin-left: auto;
  	margin-right: auto;
}
.contentDiv {
	margin: auto;
   	text-align: center;
   	width: 90%;
  	margin-left: auto;
  	margin-right: auto;
}
fieldset { 
	margin-left: auto;
  	margin-right: auto;
    display: block;
    border-color: black;
    width: 450px;
    height: 150px;
    padding-top: 10px;
    border: 5px groove (internal value);
}
.footerDiv{
	text-align: center;
   	width: 70%;
  	margin-left: auto;
  	margin-right: auto;
}
caption{
	text-align: left;
	padding-left: 5px;
	padding-top: 2px;
	padding-bottom: 2px;
    border: 1px solid;
    border-radius: 5px;
	border-color:#000000;
	background-color: #F5E4B3;
}
</style>
<script type="text/javascript">
	function validateForm(){
	var streetAddress = document.getElementById("streetAddress");
	var city = document.getElementById("city");
	var stateOption = document.getElementById("state");
	
	var blankAddress = false;
	if(streetAddress.value.trim() == '') {
		blankAddress = true;
	}
	var blankCity = false;
	if(city.value.trim() == '') {
		blankCity = true;
	}
	var blankStateOption = false;
	if(stateOption.value.trim() == 'blank') {
		blankStateOption = true;
	}

	if(blankAddress == true) {
		if(blankCity == true) {
			if (blankStateOption == true) {
				alert('Please enter values for Street Address, City and State');
				return false;
			}
			else {
				alert('Please enter values for Street Address and City');
				return false;
			}
		}
		else{
			if(blankStateOption == true) {
				alert('Please enter values for Street Address and State');
				return false;
			}
			else {
				alert('Please enter value for Street Address')	;
				return false;
			}
		}
	}
	if(blankAddress == false && blankCity == true) {
		if(blankStateOption == true) {
			alert("Please enter the values for City and State");
			return false;
		}
		else {
			alert("Please enter the value for City");
			return false;
		}
	}
	if(blankAddress == false && blankCity == false && blankStateOption == true) {
		alert("Please enter the value for State");
		return false;
	}
	if (blankAddress == false && blankCity == false && blankStateOption == false ) {
		// alert("submitForm");
		// document.getElementById("mainForm").submit();
		return true;
	}
}
</script>
</head>
<body>
	<div class="center" id="mainDiv">
		<form name="mainForm" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" id="mainForm" method="post" onsubmit="return validateForm();">
			<h2>Real Estate Search</h2>
			<fieldset>
					<table>
						<tr><td>Street Address:* </td> <td><input type="text" id="streetAddress" name="streetAddress" size="30px" value="<?php echo isset($_POST['streetAddress']) ? $_POST['streetAddress'] : '' ?>"></td></tr>
						<tr><td>City:* </td> <td><input type="text" id="city" name="city" size="30px" value="<?php echo isset($_POST['city']) ? $_POST['city'] : '' ?>"></td></tr>
						<tr><td>State:*</td>
						<td><select name="state" id="state">
								<option value="blank">--</option>
								<option value="AL" <?php if(isset($_POST['state'])) { echo $SELECTED = ($_POST['state'] == 'AL') ? 'SELECTED' : ''; }?> >AL</option>
								<option value="AK" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'AK') ? 'SELECTED' : ''; }?> >AK</option>
								<option value="AZ" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'AZ') ? 'SELECTED' : ''; }?> >AZ</option>
								<option value="AR" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'AR') ? 'SELECTED' : ''; }?> >AR</option>
								<option value="CA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'CA') ? 'SELECTED' : ''; }?> >CA</option>
								<option value="CO" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'CO') ? 'SELECTED' : ''; }?> >CO</option>
								<option value="CT" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'CT') ? 'SELECTED' : ''; }?> >CT</option>
								<option value="DE" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'DE') ? 'SELECTED' : ''; }?> >DE</option>
								<option value="DC" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'DC') ? 'SELECTED' : ''; }?> >DC</option>
								<option value="FL" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'FL') ? 'SELECTED' : ''; }?> >FL</option>
								<option value="GA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'GA') ? 'SELECTED' : ''; }?> >GA</option>
								<option value="HI" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'HI') ? 'SELECTED' : ''; }?> >HI</option>
								<option value="ID" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'ID') ? 'SELECTED' : ''; }?> >ID</option>
								<option value="IL" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'IL') ? 'SELECTED' : ''; }?> >IL</option>
								<option value="IN" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'IN') ? 'SELECTED' : ''; }?> >IN</option>
								<option value="IA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'IA') ? 'SELECTED' : ''; }?> >IA</option>
								<option value="KS" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'KS') ? 'SELECTED' : ''; }?> >KS</option>
								<option value="KY" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'KY') ? 'SELECTED' : ''; }?> >KY</option>
								<option value="LA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'LA') ? 'SELECTED' : ''; }?> >LA</option>
								<option value="ME" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'ME') ? 'SELECTED' : ''; }?> >ME</option>
								<option value="MD" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MD') ? 'SELECTED' : ''; }?> >MD</option>
								<option value="MA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MA') ? 'SELECTED' : ''; }?> >MA</option>
								<option value="MI" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MI') ? 'SELECTED' : ''; }?> >MI</option>
								<option value="MN" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MN') ? 'SELECTED' : ''; }?> >MN</option>
								<option value="MS" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MS') ? 'SELECTED' : ''; }?> >MS</option>
								<option value="MO" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MO') ? 'SELECTED' : ''; }?> >MO</option>
								<option value="MT" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'MT') ? 'SELECTED' : ''; }?> >MT</option>
								<option value="NB" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NB') ? 'SELECTED' : ''; }?> >NB</option>
								<option value="NV" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NV') ? 'SELECTED' : ''; }?> >NV</option>
								<option value="NH" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NH') ? 'SELECTED' : ''; }?> >NH</option>
								<option value="NJ" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NJ') ? 'SELECTED' : ''; }?> >NJ</option>
								<option value="NM" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NM') ? 'SELECTED' : ''; }?> >NM</option>
								<option value="NY" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NY') ? 'SELECTED' : ''; }?> >NY</option>
								<option value="NC" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'NC') ? 'SELECTED' : ''; }?> >NC</option>
								<option value="ND" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'ND') ? 'SELECTED' : ''; }?> >ND</option>
								<option value="OH" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'OH') ? 'SELECTED' : ''; }?> >OH</option>
								<option value="OK" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'OK') ? 'SELECTED' : ''; }?> >OK</option>
								<option value="OR" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'OR') ? 'SELECTED' : ''; }?> >OR</option>
								<option value="PA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'PA') ? 'SELECTED' : ''; }?> >PA</option>
								<option value="RI" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'RI') ? 'SELECTED' : ''; }?> >RI</option>
								<option value="SC" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'SC') ? 'SELECTED' : ''; }?> >SC</option>
								<option value="SD" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'SD') ? 'SELECTED' : ''; }?> >SD</option>
								<option value="TN" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'TN') ? 'SELECTED' : ''; }?> >TN</option>
								<option value="TX" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'TX') ? 'SELECTED' : ''; }?> >TX</option>
								<option value="UT" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'UT') ? 'SELECTED' : ''; }?> >UT</option>
								<option value="VT" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'VT') ? 'SELECTED' : ''; }?> >VT</option>
								<option value="VA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'VA') ? 'SELECTED' : ''; }?> >VA</option>
								<option value="WA" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'WA') ? 'SELECTED' : ''; }?> >WA</option>
								<option value="WV" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'WV') ? 'SELECTED' : ''; }?> >WV</option>
								<option value="WI" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'WI') ? 'SELECTED' : ''; }?> >WI</option>
								<option value="WY" <?php if(isset($_POST['state'])) { echo $selected = ($_POST['state'] == 'WY') ? 'SELECTED' : ''; }?> >WY</option>
							</select></td></tr>
							<tr><td></td><td><input type="submit" id="submitButton" name="submitButton" value="Search"/></td></tr>
							<tr><td><p style="font-style: italic;">*- Mandatory Fields</p></td>
							<td align="right"><img src="http://www.zillow.com/widgets/GetVersionedResource.htm?path=/static/logos/Zillowlogo_150x40_rounded.gif" width="150" height="40" alt="Zillow Real Estate Search" /></td></tr>
					</table>
			</fieldset>
		</form>
	</div>
	<div id="contentDiv" class="contentDiv">
		<?php
			date_default_timezone_set('America/Los_Angeles');
			$streetAddress = $city = $state = "";
			if(!empty($_POST)) {
			   $streetAddress = test_input($_POST["streetAddress"]);
			   $city = test_input($_POST["city"]);
			   $state = test_input($_POST["state"]);

			   $zillowUrl = "http://www.zillow.com/webservice/GetDeepSearchResults.htm?zws-id=X1-ZWz1dxij5akuff_65xu9"
						."&address=".str_replace(' ', '+', $streetAddress)
						."&citystatezip=".str_replace(' ', '+', $city)."%2C".$state."&rentzestimate=true";

				$xml=simplexml_load_file($zillowUrl);
				$message = $xml->message->text;

				if($message == 'Request successfully processed') {
					?>
					<h2>Search Result</h2>
					<?php
					$result = $xml->response->results->result;
 						 //echo $result->links->homedetails. "\n";
 						 $homeDetailsLink = '<a style="text-decoration: none;" href="'. $result->links->homedetails.'">';
						 $appendedString = $result->address->street.", ".$result->address->city.", ".$result->address->state."-".$result->address->zipcode;
					?>
					<div id="outputDiv" class="contentDiv">
						<table width= "1000px">
							<caption>See more details for  <?php echo $homeDetailsLink ." ". $appendedString; ?> </a> on Zillow </caption>
						        <tr align="left">
						        	<th>Property Type:</th>
						        	<?php 
						        	if(!empty($result->useCode)) {
						        		$theVal = $result->useCode;
						        	}
						        	else {
						        		$theVal = "N/A";	
						        	}

						        	?>
						        	<td><?php echo $theVal ?></td>
						        	<th>Last Sold Price:</th>
						        	<td>
						        		<?php
							        		$price = $result->lastSoldPrice;
							        		if(!empty($price)){
								        		$lastSoldPrice = number_format((float)$price, 2);
								        		$currency = $price['currency'];
								        		if($currency == "USD") {
								        			$currency = '$';
								        		}
								        		echo $currency ." ". $lastSoldPrice;
							        		}
							        		else {
							        			echo "N/A";
							        		}
						        		?>
						        	</td>
						        </tr>
						        <tr align="left">
						        	<th>Year Built:</th>
						        	<td><?php echo getValue($result->yearBuilt) ?></td>
						        	<th>Last Sold Date:</th>
						        	<td>
						        		<?php
						        		if(!empty($result->lastSoldDate)) {
						        			$lastSoldDate = strtotime($result->lastSoldDate);
						        			echo date('d-M-Y', $lastSoldDate); 	
						        		}
						        		else {
						        			echo "N/A";
						        		}
						        		?>
						        	</td>
						        </tr>
						        <tr align="left">
						        	<th>Lot Size:</th>
						        	<?php
						        	if(!empty($result->lotSizeSqFt)) {
						        		$lotSize = getValue($result->lotSizeSqFt);
						        		$lotSize = number_format((float)$lotSize) ." sq. ft.";
						        	}
						        	else {
						        		$lotSize = "N/A";
						        	}
						        	?>
						        	<td><?php echo $lotSize ?></td>
						        	<?php
						        		if(!empty($result->zestimate->{'last-updated'})){
						        			$d = strtotime($result->zestimate->{'last-updated'});
						        			$lastUpdatedDate = date('d-M-Y', $d); 
						        		}
						        		else {
						        			$lastUpdatedDate = "--";
						        		}
						        	?>
						        	<th>Zestimate&reg; Property Estimate as of <?php echo $lastUpdatedDate ?> :</th>
						        	<td><?php
						        		if(!empty($result->zestimate->amount)) {
						        			$amt = $result->zestimate->amount;
							        		$propEstimate = number_format((float)$amt, 2);
							        		$currency = $amt['currency'];
							        		if($currency == "USD") {
							        			$currency = '$';
							        		}
						        			echo $currency." ".$propEstimate;
						        		}
						        		else {
						        			echo "N/A";
						        		}
						        		?></td>
						        </tr>
						        <tr align="left">
						        	<th>Finished Area:</th>
						        	<td><?php 
						        		if(!empty($result->finishedSqFt)) {
						        			$finishedArea = number_format((float)$result->finishedSqFt) ." sq. ft.";
						        			echo $finishedArea;
						        		}
						        		else{
						        			echo "N/A";
						        		}
						        	?></td>
						        	<?php
						        		if(!empty($result->zestimate->valueChange)) {
							        		$valueChange = $result->zestimate->valueChange;
							        		if($valueChange > 0) {
							        			$arrow = '<img src="http://cs-server.usc.edu:45678/hw/hw6/up_g.gif" />';
							        		}
							        		else {
							        			$arrow = '<img src="http://cs-server.usc.edu:45678/hw/hw6/down_r.gif" />';	
							        		}
							        		$absValue = abs((float)$valueChange);
							        		$finalValue = number_format((float)$absValue, 2);
								        	$currency = $valueChange['currency'];
								        	if($currency == "USD") {
								        		$currency = '$';
								        	}
								        	$finalVal = $currency." ".$finalValue;
						        		}
						        		else {
						        			$arrow = "";
						        			$finalVal = "N/A";
						        		}
						        	?>
						        	<th>30 Days Overall Change  <?php echo $arrow ?> :</th>
						        	<td><?php echo $finalVal ?></td>
						        </tr>
						        <tr align="left">
						        	<th>Bathrooms:</th>
						        	<?php
						        		if(!empty($result->bathrooms) OR $result->bathrooms == 0) {
						        			$theVal = $result->bathrooms;
						        		}
						        		else {
						        			$theVal = "N/A";
						        		}
						        	?>
						        	<td><?php echo $theVal ?></td>
						        	<?php 
						        		if(!empty($result->zestimate->valuationRange->low) and !empty($result->zestimate->valuationRange->high)) {
						        			$valuationRangeLow = $result->zestimate->valuationRange->low;
						        			$valuationRangeHigh = $result->zestimate->valuationRange->high;
						        			$finalLow = number_format((float)$valuationRangeLow, 2);
						        			$finalHigh = number_format((float)$valuationRangeHigh, 2);
						        			$currencyLow = $valuationRangeLow['currency'];
							        		if($currencyLow == "USD") {
							        			$currencyLow = '$';
							        		}
						        			$currencyHigh = $valuationRangeHigh['currency'];
							        		if($currencyHigh == "USD") {
							        			$currencyHigh = '$';
							        		}
							        		$theValue = $currencyLow." ".$finalLow."-". $currencyHigh." ".$finalHigh;
						        		}
						        		else {
						        			$theValue = "N/A";
						        		}
						        	?>
						        	<th>All Time Property Range:</th>
						        	<td><?php echo $theValue ?></td>
						        </tr>
						        <tr align="left">
						        	<th>Bedrooms:</th>
						        	<?php
						        	 	if(!empty($result->bedrooms) OR $result->bedrooms == 0) {
						        	 		$theVal = $result->bedrooms;
						        	 	}
						        	 	else {
						        	 		$theVal = "N/A";
						        	 	}
						        	?>
						        	<td><?php echo $theVal ?></td>
						        	<?php
						        		if(!empty($result->rentzestimate->{'last-updated'})) {
						        			$d = strtotime($result->rentzestimate->{'last-updated'});
						        			$lastUpdatedDate = date('d-M-Y', $d); 	
						        		}
						        		else {
						        			$lastUpdatedDate = "--";
						        		}
						        	?>
						        	<th>Rent Zestimate&reg; Valuation as of <?php echo $lastUpdatedDate ?>:  </th>
						        	<td><?php
						        		if(!empty($result->rentzestimate->amount)) {
							        		$amt = $result->rentzestimate->amount;
							        		$finalAmt = number_format((float)$amt, 2);
							        		$currency = $amt['currency'];
							        		if($currency == "USD") {
								        		$currency = '$';
								        	}
							        		echo $currency." ".$finalAmt;	
						        		}
						        		else {
						        			echo "N/A";
						        		}
						        		?></td>
						        </tr>
						        <tr align="left">
						        	<th>Tax Assessment Year:</th>
						        	<td><?php echo getValue($result->taxAssessmentYear) ?></td>
						        	<?php
						        		if(!empty($result->rentzestimate->valueChange)){
							        		$valueChange = $result->rentzestimate->valueChange;
							        		if($valueChange > 0) {
							        			$arrow = '<img src="http://cs-server.usc.edu:45678/hw/hw6/up_g.gif" />';
							        		}
							        		else {
							        			$arrow = '<img src="http://cs-server.usc.edu:45678/hw/hw6/down_r.gif" />';	
							        		}
							        		$absValue = abs((float)$valueChange);
							        		$finalValue = number_format((float)$absValue, 2);
								        	$currency = $valueChange['currency'];
								        	if($currency == "USD") {
								        		$currency = '$';
								        	}	
								        	$finalVal = $currency." ".$finalValue;
						        		}
						        		else {
						        			$arrow = "";
						        			$finalVal = "N/A";
						        		}
						        	?>
						        	<th>30 Days Rent Change  <?php echo $arrow ?> :</th>
						        	<td><?php echo $finalVal ?></td>
						        </tr>
						        <tr align="left">
						        	<th>Tax Assessment:</th>
							        	<?php
							        		if(!empty($result->taxAssessment)){
							        			$tax = $result->taxAssessment;
								        		$finalValue = number_format((float)$tax, 2);	
									       		if($currency == "USD") {
									        		$currency = '$';
									        	}	
									        	$finalVal = $currency." ".$finalValue;
							        		}
							        		else {
							        			$finalVal = "N/A";
							        		}						        	
							        	?>
						        	<td><?php echo $finalVal ?></td>
						        	<?php
						        		if(!empty($result->rentzestimate->valuationRange->low) and !empty($result->rentzestimate->valuationRange->high)) {
						        			$valuationRangeLow = $result->rentzestimate->valuationRange->low;
						        			$valuationRangeHigh = $result->rentzestimate->valuationRange->high;
						        			$finalLow = number_format((float)$valuationRangeLow, 2);
						        			$finalHigh = number_format((float)$valuationRangeHigh, 2);
						        			$currencyLow = $valuationRangeLow['currency'];
							        		if($currencyLow == "USD") {
							        			$currencyLow = '$';
							        		}
						        			$currencyHigh = $valuationRangeHigh['currency'];
							        		if($currencyHigh == "USD") {
							        			$currencyHigh = '$';
							        		}
							        		$theValue = $currencyLow." ".$finalLow."-". $currencyHigh." ".$finalHigh;
						        		}
						        		else {
						        			$theValue = "N/A";
						        		}
						        	?>
						        	<th>All Time Rent Change:</th>
						        	<td><?php echo $theValue ?></td>
						        </tr>
						</table>
					</div>
					<br/>
				<?php
					}
					
				else {
					echo '<font>No Exact match found -- Varify that the given address is correct.</font>';
				}
			}
			function test_input($data) {
				$data = trim($data);
				$data = stripslashes($data);
				$data = htmlspecialchars($data);
				return $data;
			}
			function getValue($value) {
				if(!empty($value)) {
					return $value;
				}
				else {
					return "N/A";
				}
			}
			?>
	</div>
	<br />
	<div id="footerDiv" class="footerDiv">
		&copy; Zillow, Inc., 2006-2014. Use is subject to <a href="http://www.zillow.com/corp/Terms.htm"> Terms of Use </a><br/>
		<a href="http://www.zillow.com/wikipages/What-is-a-Zestimate/"> What's a Zestimate? </a>
	</div>
</body>
</html>