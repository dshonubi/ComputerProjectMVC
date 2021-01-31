<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.8/themes/default/style.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.8/jstree.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
      
<script>
	$(document).ready(function() {
		$("#submitUpload").click(function(event) {
			
			event.preventDefault();
			
			
			var form = $('#uploadForm')[0];
			var data = new FormData(form);
			
			$("#submitUpload").prop("disabled", true);
			
			url = "/optimise",
			
			$("#uploadModal").modal('hide');
			$.post({
				url : url,
				enctype: 'multipart/form-data',
				data : data,
		        processData: false,  
		        contentType: false,
		        cache: false,
				
				success : function(response) {

					$('#uploadClassModal form :input').val("");
					
					var obj = JSON.parse(response);
					var mappedToArray = obj.map(d => Array.from(Object.values(d)))
					var mappedToArray2 = obj.map(d => Array.from(Object.keys(d)))
					var obj = JSON.stringify(obj, null, 0);
					console.log(mappedToArray);
					console.log(mappedToArray2);
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart);
				      
					function drawChart() {
						var data = new google.visualization.DataTable();
					
						data.addColumn('string', 'Storage Locations');
						data.addColumn('number', 'Path To Take');
						data.addRows(mappedToArray);
						
				        var options = {
				          title: 'Travel Optimisation Graph',
				          hAxis: {title: 'Location'},
				          vAxis: {title: 'Path To Take'}
				        };

				        var chart = new google.visualization.ScatterChart(document.getElementById('chart_div'));


				        chart.draw(data, options);
					}
					
				}
			})

			return false;
		});
	});
</script>


<title>Travel Route Optimisation Tool</title>

<style>  
.modal-header {
	background-color: #337AB7;
	padding: 16px 16px;
	color: #FFF;
	border-bottom: 2px dashed #337AB7;
}
.col {
    text-align: center;
}

</style>

</head>
<body style="background-color: #F8F8F8;">
<h1 style="text-align: center;">Travel Route Optimisation Tool</h1>
<div class="container">
	<div class="row" >
	<div id="chart_div" style="width: 900px; height: 500px; text-align: center"></div>
			<div class="col-6 col-md-4">
			</div>
			<div class="col-6 col-md-4">
		
				<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#uploadModal">Upload New Floor Plan</button>
			</div>
			<div class="col-6 col-md-4">
		</div>	
	</div>	
</div>
	<div class="modal fade" id="uploadModal">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<form id="uploadForm">
					<div class="modal-header">
						<h3>Please submit a valid text file in order to find an optimal Travel Route</h3>
					</div>
					<div class="modal-body">
						<fieldset>
							<div class="modal-body">
								<input name="file" id="filename" type="file" />
							</div>
						</fieldset>
					</div>
					<div class="modal-footer">
						<button type=submit class="btn btn-success" id="submitUpload">Upload</button>
						<button class="btn btn-danger" id="closeUpload"data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>