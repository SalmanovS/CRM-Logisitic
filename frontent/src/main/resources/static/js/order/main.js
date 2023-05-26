function changeStatus(id, status){
  if(confirm("Сonfirm the action?")){
    $.ajax({
            url: 'status/'+id+'/'+status,
            datatype: 'json',
            type: 'get',
            cache: false,
            success: function(){

            location.reload();
            }
    });
    }
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