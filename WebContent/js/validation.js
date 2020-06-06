/*
 * JS function to check if movie name is selected or not 
 */
function validate() {
	var movieName = document.forms[0].movieName.value;
	if (movieName == "") {
		alert("Please select movie name");
		document.forms[0].movieName.focus();
		return false;
	}
	return true;
}

/*
 * JS function to check if correct number is entered for quantity of tickets to book.
 * and also to check if available tickets should be more than or equal to number of movie tickets to book
 */
function check() {
	var quantity = document.forms[0].quantity.value;
	var avTickets = document.forms[0].avTickets.value;
	if (quantity == "" || quantity <= 0) {
		alert("Enter correct number of tickets!!!");
		document.forms[0].quantity.focus();
		return false;
	}
	if (isNaN(quantity)) {
		alert("Quantity should be integer!!!");
		document.forms[0].quantity.focus();
		return false;
	}
	if (avTickets < quantity) {
		alert("Invalid number of tickets");
		document.forms[0].quantity.focus();
		return false;
	}
	return true;
}


function checkAddMovie() {
	var title = document.forms[0].title.value;
	var Director = document.forms[0].director.value;
	var Cast = document.forms[0].cast.value;
	var Description = document.forms[0].description.value;
	var Status = document.forms[0].status.value;

	
	if (title == "") {
		alert("Please enter title name");
		document.forms[0].movieName.focus();
		return false;
	}
	if (Director == "") {
		alert("Please enter director name");
		document.forms[0].movieName.focus();
		return false;
	}
	if (Cast == "") {
		alert("Please enter cast");
		document.forms[0].movieName.focus();
		return false;
	}
	if (Description == "") {
		alert("Please enter description");
		document.forms[0].movieName.focus();
		return false;
	}
	if (Status == "") {
		alert("Please enter Status(Y/N)");
		document.forms[0].movieName.focus();
		return false;
	}
	return true;
}
