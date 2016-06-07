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
	 					$("input[name=filmID]").val(but.substring(6)); 	
	 					$("#deleteFilm").submit();		 										
	 				},
	 				Cancel: function() {	 						
	 					$( this ).dialog( "close" );		 					 									
	 				}
	 			}
	 		});
		 	return false;
	 	});
		 	
   });