$(function(){
	blur_errors();
	build_age_select();
});

	function build_age_select(){
		for(var i=18;i<81;i++){
			var ageOp=$("<option></option>").append(i).val(i).appendTo("#age");
		};
	};
	
	function save_user(){

		var formData=new FormData($("#add_user_model")[0]);
		var size=formData.get("file").size;
		if(size == 0){
			if(confirm("您确定不需要头像吗？	")){
				
				$.ajax({
					url:"/user",
					type:"POST",
					data:$("#add_user_model").serialize()+formData,
					data:formData,
					 async: false,
				     cache: false,
				     contentType: false,
				     processData: false,
					success:function(result){
						if(result.code== 200){
//							alert("false");
							alert("抱歉，您注册的信息有误。");
							 $("#save_user_bth").attr("disabled","disabled");
//							console.log(result);
							build_errors(result);
						}else if(result.code==100){
							show_msg(result)
//							console.log(result);
						}
					}
				});
				
		}
			}else{
				$.ajax({
					url:"/user",
					type:"POST",
					data:$("#add_user_model").serialize()+formData,
					data:formData,
					 async: false,
				     cache: false,
				     contentType: false,
				     processData: false,
					success:function(result){
						if(result.code== 200){
//							alert("false");
							alert("抱歉，您注册的信息有误。");
							 $("#save_user_bth").attr("disabled","disabled");
//							console.log(result);
							build_errors(result);
						}else if(result.code==100){
							show_msg(result)
//							console.log(result);
						}
					}
				});
				
			}
		

	};
	//显示提示信息
	function show_msg(result){
		$("#regist_model").css("display","none");
		$("#msg_model").css("display","");
//		conlose.log(result);
	}
	
	
		
//		构建错误显示
		function build_errors(result){
//			console.log(result.map.fieldErrors);
			var errors=result.map.fieldErrors;
				clear_errors();
			$.each(errors,function(index,item){
//				$("#"+index+"_group").addClass(" has-error");
//				$("#"+index+"_error").text(item);
				add_error(index,item);
			});
			
		};
		
		
		
		
		
//		清除全部错误信息
		function clear_errors(){
			clear_error("email");
			clear_error("nickname");
			clear_error("username");
			clear_error("phone");
			clear_error("password1");
			clear_error("password");
		};
		


	
	
	
//	单个错误增加
	function add_error(id,item){
		$("#"+id+"_group").addClass(" has-error");
		$("#"+id+"_error").text(item);
	};
	
//	单个错误清除
	function clear_error(id){
		$("#"+id+"_group").removeClass(" has-error");
		$("#"+id+"_error").text("");
	};

	

	function blur_errors(){
		blur_error_email();
		blur_error_password();
		blur_error_password1();
		blur_error_phone();
		blur_error_empty("nickname");
		blur_error_empty("username");
	};
	
	function blur_error_empty(id){
		$("#"+id).blur(function(){
			verify_error_empty(id);
		});
	}
	
	function verify_error_empty(id){
		var text=$("#"+id).val();
		if(text=='null' || text==''){
			add_error(id,"该项不能为空！");
			$("#save_user_bth").attr("disabled","disabled");
			return false;
		}else{
			clear_error(id);
			$("#save_user_bth").removeAttr("disabled");
			return true;
		}
	};
	
	
//	判断邮箱的
	function blur_error_email(){
		$("#email").blur(function(){
			var bool=verify_error_empty("email");
			if(bool == true){
				var email=$("#email").val();
	 			 var regEmail=/^([a-z0-9_.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	 			 if(!regEmail.test(email)){
	 				add_error("email","邮箱格式错误！");
	 				$("#save_user_bth").attr("disabled","disabled");
	 			 }else{
	 				clear_error("email");
	 				$("#save_user_bth").removeAttr("disabled");
	 			 }
			}
		});
	};
	
//判断密码是否一致
	function blur_error_password(){
		$("#password").blur(function(){
			var bool=verify_error_empty("password");
			if(bool == true){
				var password=$("#password").val();
				var password1=$("#password1").val();
				if(password !=password1){
					add_error("password","两次密码不一致");
					$("#save_user_bth").attr("disabled","disabled");
				}else{
					clear_error("password");
					$("#save_user_bth").removeAttr("disabled");
				}
			}
		});
	}
	function blur_error_password1(){
		$("#password1").blur(function(){
			var bool=verify_error_empty("password1");
			if(bool == true){
				var password=$("#password").val();
				var password1=$("#password1").val();
				if(password !=password1){
					add_error("password","两次密码不一致");
					$("#save_user_bth").attr("disabled","disabled");
				}else{
					clear_error("password");
					$("#save_user_bth").removeAttr("disabled");
				}
			}
		});
	}
	
//判断手机错误
	function blur_error_phone(){
		$("#phone").blur(function(){
			var bool=verify_error_empty("phone");
			if(bool==true){
				var phone=$("#phone").val();
				var regPhone = /(1[3-9]\d{9}$)/;
				 if(!regPhone.test(phone)){
					 add_error("phone","电话格式错误！");
					 $("#save_user_bth").attr("disabled","disabled");
				 }else{
					 clear_error("phone");
					 $("#save_user_bth").removeAttr("disabled");
				 }
			}
		});
	}
	


	
	

	
	
	
	
	
	