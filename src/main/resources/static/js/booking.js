
$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){	
		event.preventDefault();	
		
		$('#editModal').modal();
	});	
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		var href=$(this).attr('href');
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal();		
	});
});