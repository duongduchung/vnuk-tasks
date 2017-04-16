$(function() {
    $('.my-task').on('click', function (){
        var id = $(this).val();
        
        $.post("completeTask", {'id' : id}, function() {
            var date = new Date();
            var today = ("0" + date.getDate()).slice(-2) 
                    + "/" + ("0" + (date.getMonth() + 1)).slice(-2) 
                    + "/" + date.getFullYear();
            
            $("#task-" + id).hide();
            $("#status-of-task-" + id).text("Complete");
            $("#date-of-achievement-for-task-" + id).text(today);
            
        });
    });
});
