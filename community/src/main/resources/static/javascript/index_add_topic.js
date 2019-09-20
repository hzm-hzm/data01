
function clear_all(){
	$("#add_topic_div").css("display","none");
	$("#to_topic_div").css("display","none");
	$("#topics_div").css("display","");
	$("#pages_div").css("display","");
};


function add_topic(){
	if(message==null){
		$("#add_topic_div").css("display","none");
		$("#topics_div").css("display","");
		$("#pages_div").css("display","");
		alert("请进行登录后才能操作！");
		$('#myModal').modal('show');
	}else if(message!=null){
		$("#add_topic_div").css("display","");
		$("#to_topic_div").css("display","none")
		$("#topics_div").css("display","none");
		$("#pages_div").css("display","none");
		$("#web_message_nav").text("可以把你心中所想话题发布出去让大家谈论。");
		build_add_topic();
	}
};

function build_add_topic(){
	$.ajax({
			url:"/findAllSort",
			type:"GET",
			success:function(result){
				build_add_sorts(result);
			}
		});
	};
		
//		构建增加话题栏的分类
		function build_add_sorts(result){
			$("#sid").empty();
			var sorts=result.map.sorts;
			$.each(sorts,function(index,item){
				var sortOp=$("<option></option>").attr("value",item.sid).text(item.sname);
				$("#sid").append(sortOp);
			});
		};
		
//	添加话题
		function save_topic(){
			var formData=new FormData($("#add_topic_model")[0]);
//		 	console.log($("#add_topic_model").serialize());
			var uid=message.uid;
//			alert(message.uid);
			formData.append("uid",uid);
			$.ajax({
				url:"/topic",
				data:formData,
				type:"POST",
				async: false,
			     cache: false,
			     contentType: false,
			     processData: false,
				success:function(result){
					if(result.code==100){
						alert("该话题发表成功！");
//						console.log(result);
						to_page_byuid(1);
					} else if(result.code==200){
						alert("你输入的信息有错！");
						build_add_topic_error(result);
					}
				}
			});
		};
		
		function build_add_topic_error(result){
			var errors=result.map.errors;
			$.each(errors,function(index,item){
				$("#"+index+"_group").addClass("has-error");
				$("#"+index+"_error").text(item);
			});
		};
		
		


