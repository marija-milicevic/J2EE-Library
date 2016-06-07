	$(document).ready(function() {
		
	     $("button[name^=delete]").click(function(){
	    	$( "#dialog:ui-dialog" ).dialog( "destroy" );

	    	$( "#dialog:ui-dialog" ).dialog({ autoOpen: false })
	    	
	    	var but = $( this ).attr("name");	
	    		
	 		$( "#dialog-confirm" ).dialog({
	 			width:400,
	 			modal: true,
	 			buttons: {
	 				"Delete": function() { 		 					
	 					$( this ).dialog( "close" );
	 					$("input[name=directorID]").val(but.substring(6));	 	
	 					$("#deleteDirector").submit();							
	 				},
	 				Cancel: function() {	 					
	 					$( this ).dialog( "close" );		 										
	 				}
	 			}
	 		});
		 	return false;
	 	});
		 	
   });