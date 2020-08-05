
$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){	
		event.preventDefault();			
		var href=$(this).attr('href');		
		$.get(href, function(user, status){		
			$('#idEdit').val(user.id);
			$('#firstNameEdit').val(user.firstName);
			$('#lastNameEdit').val(user.lastName);
			$('#licenseEdit').val(user.license);
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