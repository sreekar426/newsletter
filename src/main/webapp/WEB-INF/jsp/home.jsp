<html>
<header> Newsletter subscription </header>
<head>
<style>
    .card{
        margin: 30px;
    }
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script>
	
	$(document).ready(
			function() {
				$('#test1').hide();
				$.ajax({
					url : "/getAll",
					datatype : "JSON",
					type : "Get",
					cache : false,
					success : function(data) {
						
						var trHTML = '';
						
						$.each(data, function(index, element) {
							 trHTML += '<img src="'+element.url+'"></img>'
							  +'<input type="checkbox" name="selector[]" value="'+element.name+'" class="removeLater"> <label>subscribe</label>';
						});
						$("#test").append(trHTML)
						
					},
 
				});

			});
	
	
	function submitForm(){
		
		var selector = [];
			$('input.removeLater:checked').each(function(i,e) {
				selector.push($(this).val());
			});
		
			var ename = $("#txt_subscribe").val();
			var postData={'selector': selector.join(),email:ename};
		     $.ajax({
		        type: "post",
		        url: "/getSub",
		        cache: false,       
		        data:postData,
		        success: function(response){
		        	
		        	$.ajax({
						url : 'getByEmail/'+ename,
						datatype : "JSON",
						type : "Get",
						cache : false,
						success : function(data) {
							$('#test').hide();
							var trHTMLL = '';
							$.each(data, function(index, element) {
								 trHTMLL += '<img src="'+element.url+'"></img>'
								  +'<input type="checkbox" name="selector[]" value="'+element.name+'"class="removeLater" checked="checked"> <label>subscribe</label>'
								  +'<input type="checkbox" name="unselector[]" value="'+element.name+'"class="removeNow" > <label>unsubscribe</label> <br>'
							});
							$("#test1").append(trHTMLL)
							$('#test1').show();
						},
						
					});
		        },

		        error: function(){                      
		            alert('Error while request..');
		        }
		    }); 

		}
	
	
function uncheck(){
		
		var unselector = [];
			$('input.removeNow:checked').each(function(i,e) {
				unselector.push($(this).val());
			});
		console.log(JSON.stringify(unselector))
			var ename = $("#txt_subscribe").val();
			var postData={'unselector': unselector.join(),email:ename};
		     $.ajax({
		        type: "post",
		        url: "/unSub",
		        cache: false,       
		        data:postData,
		        success: function(response){
		        	
		        	$.ajax({
						url : 'getByEmail/'+ename,
						datatype : "JSON",
						type : "Get",
						cache : false,
						success : function(data) {
							$('#test').hide();
							var trHTMLL = '';
							$.each(data, function(index, element) {
								 trHTMLL += '<img src="'+element.url+'"></img>'
								  +'<input type="checkbox" name="selector[]" value="'+element.name+'"class="removeLater" checked="checked"> <label>subscribe</label>'
								  +'<input type="checkbox" name="unselector[]" value="'+element.name+'"class="removeNow" > <label>unsubscribe</label> <br>'
							});
							$("#test2").append(trHTMLL)
							
						},
						
					});
		        },

		        error: function(){                      
		            alert('Error while request..');
		        }
		    }); 

		}
	
	</script>
	
	
</head>
<body>


	<div id='test1'>
		<div>
			<input type="text" name="email" id="txt_unsubscribe"
				placeholder="email">
			<button type="button" onclick="uncheck()">unsubscribe</button>
		</div>
		<div id='test2'>
		
		</div>
	</div>



	<form>
		<div id='test'>
			<div>
				<input type="text" name="email" id="txt_subscribe"
					placeholder="email">
				<button type="button" onclick="submitForm()">subscribe</button>
			</div>
		</div>

	</form>





</body>

</html>