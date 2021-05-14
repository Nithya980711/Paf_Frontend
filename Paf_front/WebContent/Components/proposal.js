$(document).on("click", "#btnCreateproposal", function(event){ 
	
	
	// Clear alerts---------------------
 $("#alertSuccess").text("Successfully Inserted"); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateproductForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "proposalAPI", 
 type : type, 
 data : $("#formproposal").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onproposalSaveComplete(response.responseText, status); 
 } 
 });
});


// CLIENT-MODEL================================================================
function validateproposalForm(){
	// pcode
	if ($("#pcode").val().trim() == ""){
		return "Insert proposal code.";
	}
	// title
	if ($("#title").val().trim() == ""){
		return "Insert Title.";
	}

	// expBudget-------------------------------
	if ($("#expBudget").val().trim() == ""){
		return "Insert Exp Budget.";
	}
	// Description-------------------------------
	if ($("#discription").val().trim() == ""){
		return "Insert product description.";
	}
	
	
	return true;
}


function onproposalSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidItemIDSave").val(""); 
 
}


$(document).on("click", ".btnRemove", function(event) { 
		 $.ajax( 
		 	{ 
		 	url : "proposalAPI", 
		 	type : "DELETE", 
		 	data : "code=" + $(this).data("code"),
		 	dataType : "text", 
		 	complete : function(response, status) { 
		 		onproposalDeleteComplete(response.responseText, status); 
		 	} 
		}); 
})
		
function onproposalDeleteComplete(response, status){ 
	if (status == "success") { 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") { 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divItemsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") { 
 			$("#alertError").text(resultSet.data); 
 			$("#alertError").show(); 
 		} 
 	} else if (status == "error") { 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else { 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
 	} 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	 $("#hidpcodeSave").val($(this).data("pcode"));
		 $("#pcode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#title").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#expBudget").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#description").val($(this).closest("tr").find('td:eq(3)').text()); 
		 
		});