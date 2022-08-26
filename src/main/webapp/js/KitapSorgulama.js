 function KitapIsmiGetir(){
	e.preventDefault();
	var kitapIsmi = "";
	var id = document.getElementById("kitapId");
	console.log(id);
	$.ajax({
		url: "./RestServices/LibraryService/kitapIsmiGetir/"+id,
		type: "GET",
		dataType: "TEXT_PLAIN",
		succes:function(data){
			 //kitapIsmi += "<div class='layer'>" + + "</div>";
			 document.getElementsByName("kitapId").value = data;
			 console.log(data);
		}
	})
}