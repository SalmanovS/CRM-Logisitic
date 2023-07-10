function changeStatus(id, status){
  if(confirm("Сonfirm the action?")){
    $.ajax({
            url: 'status/'+id+'/'+status,
            datatype: 'json',
            type: 'get',
            cache: false,
            success: function(){
            //reloading the table
            $("#today-table").load(location.href+" #today-table>*","");
            $("#order-in-progress-table").load(location.href+" #order-in-progress-table>*","");

            }
    });
    }
}

function destinationAddressFunc() {
    var weight = document.getElementById('cargoWeight').value;
    var start = document.getElementById('departureAddress').value;
    var end = document.getElementById('destinationAddress').value;
    $.ajax({
    url:'directions',
    dataType: 'json',
    type: 'get',
    cache: false,
    data: ({startLocation: start, endLocation: end}),
    success: function(data){
         document.getElementById('routeLength').value = data;
         var price = weight * 2 + data * 0.2;
          document.getElementById('orderPrice').value = price;


        }
    });
}


function todayBtn(){
  document.getElementById("today-table").style.display = "block";
  document.getElementById("today-btn").style.borderBottom = "1px solid red";
  document.getElementById("tomorrow-table").style.display = "none";
  document.getElementById("otherDays-table").style.display = "none";
  document.getElementById("tomorrow-btn").style.borderBottom = "1px solid #1B7FF4";
  document.getElementById("other-btn").style.borderBottom = "1px solid #1B7FF4";

}

function tomorrowBtn(){
    document.getElementById("today-table").style.display = "none";
    document.getElementById("otherDays-table").style.display = "none";
    document.getElementById("tomorrow-table").style.display = "block";
    document.getElementById("tomorrow-btn").style.borderBottom = "1px solid red";
    document.getElementById("today-btn").style.borderBottom = "1px solid #1B7FF4";
    document.getElementById("other-btn").style.borderBottom = "1px solid #1B7FF4";

}

function otherDaysBtn(){
    document.getElementById("today-table").style.display = "none";
    document.getElementById("tomorrow-table").style.display = "none";
    document.getElementById("otherDays-table").style.display = "block";
    document.getElementById("other-btn").style.borderBottom = "1px solid red";
    document.getElementById("tomorrow-btn").style.borderBottom = "1px solid #1B7FF4";
    document.getElementById("today-btn").style.borderBottom = "1px solid #1B7FF4";

}

// -------------------------------------------------------------------



function inProgress(){
  document.getElementById("order-in-progress-table").style.display = "block";
  document.getElementById("inProgressBtn").style.borderBottom = "1px solid red";
  document.getElementById("scheduled-table").style.display = "none";
  document.getElementById("completed-table").style.display = "none";
  document.getElementById("scheduledBtn").style.borderBottom = "1px solid #1B7FF4";
  document.getElementById("completedBtn").style.borderBottom = "1px solid #1B7FF4";

}

function scheduled(){
    document.getElementById("order-in-progress-table").style.display = "none";
    document.getElementById("completed-table").style.display = "none";
    document.getElementById("scheduled-table").style.display = "block";
    document.getElementById("scheduledBtn").style.borderBottom = "1px solid red";
    document.getElementById("inProgressBtn").style.borderBottom = "1px solid #1B7FF4";
    document.getElementById("completedBtn").style.borderBottom = "1px solid #1B7FF4";

}

function completed(){
    document.getElementById("order-in-progress-table").style.display = "none";
    document.getElementById("scheduled-table").style.display = "none";
    document.getElementById("completed-table").style.display = "block";
    document.getElementById("completedBtn").style.borderBottom = "1px solid red";
    document.getElementById("scheduledBtn").style.borderBottom = "1px solid #1B7FF4";
    document.getElementById("inProgressBtn").style.borderBottom = "1px solid #1B7FF4";

}