
$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){	
		event.preventDefault();	
		
		var href=$(this).attr('href');
		
		$.get(href, function(availability){		
			$('#idEdit').val(availability.id);
			$('#firstNameEdit').val(availability.firstName);
			$('#lastNameEdit').val(availability.lastName);
			$('#licenseEdit').val(availability.license);
		});		
		$('#editModal').modal();
	});	
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		var href=$(this).attr('href');
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal();		
	});
});