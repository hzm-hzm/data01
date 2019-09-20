//查找自己的user
var user;
function find_my_user(uid){
	$.ajax({
		url:"/find_user",
		data:"uid="+$("#online_user").text(),
		type:"GET",
		success:function(result){
			user=result;
		}
	});
};


//登录
function login(){
	$.ajax({
		url:"/userLogin",
		type:"POST",
		data:$("#login_model").serialize(),
		xhrFields: {withCredentials: true},
		success:function(result){
//			alert(result);
			if(result.code==200){
				verify_controller(result);
			}else if(result.code==100){
				$('#myModal').modal('hide');
				history.go(0);
			};

			
		}
		
	});
};

function verify_controller(result){
	var errors=result.map.fieldErrors;
	clear_errors();
	$.each(errors,function(index,item){
		$("#"+index+"_group").addClass(" has-error");
		$("#"+index+"_error").text(item);
	});
}

function clear_errors(){
	clear_error("email");
	clear_error("password");
};

function clear_error(id){
	$("#"+id+"_group").removeClass(" has-error");
	$("#"+id+"_error").text("");
};


//登出操作
function user_exit(){
	if(confirm("您确定要退出吗？")){
		$.ajax({
			url:"/exit",
			type:"GET",
			success:function(result){
				console.log(result);
				history.go(0);
			}
		});
	}
	
};