document.getElementById('departureDate').min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
document.getElementById('destinationDate').min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];



function destinationDateFunction(){
var routeLength = document.getElementById('routeLength').value;
var days = Math.round(routeLength / 500);
var departureDate = document.getElementById('departureDate').value;
var destinationDate = new Date(departureDate);
destinationDate.setDate(destinationDate.getDate() + days);
var day = ("0"+ destinationDate.getDate()).slice(-2);
var month= ("0"+(destinationDate.getMonth() + 1)).slice(-2);
var today = destinationDate.getFullYear() + "-"+ (month) + "-"+(day);
document.getElementById('destinationDate').value = today;

}