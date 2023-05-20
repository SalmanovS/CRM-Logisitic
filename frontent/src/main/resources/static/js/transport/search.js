function searchForNumber(){
var number  = document.getElementById('numberSearchInput').value;
if(number == ""){
alert('Enter transport number');
return false;
 }
$.ajax({
        url: 'searchNumber',
        datatype: 'json',
        type: 'get',
        cache: false,
        data:({search: number}),
        success: function(data){
        document.getElementById('numberSearchInput').value = "";
            if(!data){
                alert('Transport not found')
            }else {
            alert('Transport: ' + data);
            }



        }
});
}