$(function(){

	if_login_model();
	build_sorts_model();
	to_page(1);
	showTime();
	enlargeImg();
});

//	判断用户是否登录了
	function if_login_model(){
//		var uid=$("#online_user").text();

		if(message == "" &&  message == null){
			$("#defalue_model").css("display","");
			$("#online_model").css("display","none");
		}else if(message != null){
			$("#defalue_model").css("display","none");
			$("#online_model").css("display","");
//			var message = [[${session.user}]];
//			console.log(message);
//			alert(message);
			$("#online_name").text(message.nickname);
			$("#online_picture").attr("src",message.picture);

			
		}
	};


//	建立分类框
	function build_sorts_model(){
		$.ajax({
			url:"/findAllSort",
			type:"GET",
			success:function(result){
//				console.log(result.map);
				clear_all();
				build_sorts(result);
			}})
		};
		
//		建立分类
		function build_sorts(result){
			var sorts=result.map.sorts;
			var sorts_=sorts.slice(0,6);
			
			var sortQ=$("<a></a>").addClass("navbar-brand").attr("href","javascript:to_page(1)")
			 	.css("margin-left","20px").append("全部");
				$("#sorts_model").append(sortQ);
			
//			遍历显示的类别
			 $.each(sorts_,function(index,item){
				 var sortA=$("<a></a>").addClass("navbar-brand").attr("href","javascript:to_page_bysort(1,"+item.sid+")")
				 .css("margin-left","20px").append(item.sname);
				$("#sorts_model").append(sortA);
			 });
			 
			 
			 $("#sorts_model a").click(function(){
				 $("#sorts_model a").css("color","");
	             $(this).css("color","red");
			 })
			$("#sorts_model a").each(function(index,item){
//				alert(item);
			});
			 
			 
			 
//			 遍历全部类别
			 $.each(sorts,function(index,item){
					var sortLia=$("<a></a>").append(item.sname+"社区").attr("href","javascript:to_page_bysort(1,"+item.sid+")");
					sortLia=$("<li></li>").append(sortLia);
					$("#sort_more").append(sortLia)
			 });
			 
			 

		};
		
		

//		判断多种状态的分页查询
		//（1.全部查询，2.通过类别的查询，3通过标题的查询,4通过用户的查询,5通过用户指定的评论的查询）
		function to_page_choose(id,ph,re){
			if(id==1){
				to_page(ph);
			}else if(id==2){
				var sid=re;
				to_page_bysort(ph,sid);
			}else if(id==3){
				find_topic_bytitle(ph);
			}else if(id==4){
				to_page_byuid(ph);
			}else if(id==5){
				to_page_byuidcomment(ph);
			}
		};
		
		
//		指定一页话题(id=1)
		function to_page(ph){
			$.ajax({
				url:"/allTopics",
				data:"ph="+ph,
				type:"GET",
				success:function(result){
//					console.log(result);
					$("#web_message_nav").text("您好，欢迎来到hlao论坛首页。")
					build_topics(result);
					build_page_model(result);
				}
			});
		}
		
//		通过类别查找指定一页话题(id=2)
		function to_page_bysort(ph,sid){
			build_add_topic();
			$.ajax({
				url:"/topicsBySort",
				data:"ph="+ph+"&sid="+sid,
				type:"GET",
				success:function(result){
//					console.log(result);
					$("#clear_state").text("");
					$("#web_message_nav").text("您好，这一页是<"
							+result.map.sname+
							">类型相关的话题");
					build_topics(result);
					build_page_model(result);
					
				}
			});
		}
		
//		利用标题模糊查询一页话题(id=3)
		function find_topic_bytitle(ph){
//			alert(ph);
			var title=$("#find_title").val();
			if(title==""){
				alert("不能输入空值！");
				return false;
			}
			$.ajax({
				url:"/topicsByTtile",
				data:"title="+title,
				type:"GET",
				success:function(result){
//					console.log(result);
					if(result.map.pageinfo.list==""){
						alert("没有查到相关话题");
						$("#clear_state").text("");
						to_page(1);
						false;
					}
					$("#clear_state").text("");
					$("#web_message_nav").text("您好，这一页是你根据“"+title+
							"”字眼查询出来的话题。")
					$("#sorts_model a").css("color","");
					build_topics(result);
					build_page_model(result);
				}
			});
		}
		
//		利用uid查询一页话题(id=4)
		function to_page_byuid(ph){
			if(message==null){
				alert("请进行登录后才能操作！");
				$('#myModal').modal('show');
			}else if(message!=null){
				$('#myMessageModal').modal('hide');
				$("#sorts_model a").css("color","");
				clear_all();
				var uid=message.uid;
				$.ajax({
					url:"/topicsByUid",
					type:"GET",
					data:"ph="+ph+"&uid="+uid,
					success:function(result){
						$("#web_message_nav").text("您好，这一页是你所发布出去的话题。")
						build_topics(result);
						build_page_model(result);
					}
				});
			}
		};
		
//		通过用户指定的评论的查询(id=5)
		function to_page_byuidcomment(ph){
			if(message==null){
				alert("请进行登录后才能操作！");
				$('#myModal').modal('show');
			}else if(message!=null){
				$('#myMessageModal').modal('hide');
				$("#sorts_model a").css("color","");
				$.ajax({
					url:"/topicsByUidComment",
					data:"uid="+message.uid,
					type:"GET",
					success:function(result){
						$("#web_message_nav").text("您好，这一页是你评论过的全部话题。");
						build_topics(result);
						build_page_model(result);
					}
				});
			}
		};
		
		
		

		
		
//		时间转换格式
		function dateFormat(time){
			 var arr1=time.split("T");
			 time= arr1[0];
			return time;
			 
		}
	
		
//		建立全部话题
		function build_topics(result){
			$("#add_topic_div").css("display","none");
			$("#to_topic_div").css("divplay","none");
			$("#topics_div").css("display","");
			$("#pages_div").css("display","");
			$("#topics_model ").empty();
			var topics=result.map.pageinfo.list;
			
			if(topics.length==0){
				$("#show_empty_message").css("display","");
				$("#pages_div nav").css("display","none");
			}else{
				$("#show_empty_message").css("display","none");
				$("#pages_div nav").css("display","");
			}
			
			$.each(topics,function(index,item){
				var time=dateFormat(item.time);
//				alert(item.evaluate.admire);
				
				var idTd=$("<td></td>").append(index+1);
				var titleA=$("<a></a>").append(item.title).attr("href","javascript:to_topic(/"+item.tid+"/)");
				var titleTd=$("<td></td>").append(titleA);
				var nicknameTd=$("<td></td>").append(item.user.nickname);
				var admireSpan=$("<span></span>").addClass("glyphicon glyphicon-heart").css("color","red");
				var admireTd=$("<td></td>").append(admireSpan).append(item.evaluate.admire);
				var timeTd=$("<td></td>").append(time);
				$("<tr></tr>").append(idTd).append(titleTd).append(nicknameTd)
				.append(admireTd).append(timeTd).appendTo("#topics_model");
			});
		};
		
		
//		构建分页栏
		function build_page_model(result){
			clear_all();
			$("#page_model").empty();
			var pageinfo=result.map.pageinfo;
			var pageModel=$("#page_model");
			var id=result.map.choose.id;
			var re=result.map.choose
			
			if(id==1){
				//设置首页
				var firstA=$("<a></a>").attr("href","javascript:to_page_choose("+id+",1,1)").append("首页");
				//设置上一页
				var previousA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum-1)+",1)").attr("aria-label","Previous");
			}else if(id==2){
				var sid=re.sid;
				var firstA=$("<a></a>").attr("href","javascript:to_page_choose("+id+",1,"+sid+")").append("首页");
				var previousA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum-1)+","+sid+")").attr("aria-label","Previous");
			}else if(id==3){
				var title=re.title;
				var firstA=$("<a></a>").attr("href","javascript:to_page_choose("+id+",1,"+title+")").append("首页");
				var previousA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum-1)+","+title+")").attr("aria-label","Previous");
			}else if(id==4){
				var firstA=$("<a></a>").attr("href","javascript:to_page_choose("+id+",1,1").append("首页");
				var previousA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum-1)+",1)").attr("aria-label","Previous");
			}else if(id==5){
				var firstA=$("<a></a>").attr("href","javascript:to_page_choose("+id+",1,1").append("首页");
				var previousA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum-1)+",1)").attr("aria-label","Previous");
			}
			
			var previousSpan=$("<span></span>").attr("aria-hidden","true").append("&laquo;").appendTo(previousA);
			
			//判断
			if(pageinfo.pageNum<2){
				var firstLi=$("<li></li>").addClass("disabled").append(firstA);
				var previousLi=$("<li></li>").addClass("disabled").append(previousA);
			}else{
				var firstLi=$("<li></li>").append(firstA);
				var previousLi=$("<li></li>").append(previousA);
			}
			pageModel.append(firstLi).append(previousLi);
			
			//遍历
			$.each(pageinfo.navigatepageNums,function(index,item){
				if(id==1){
					var pagesA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+item+",1)").append(item).appendTo("<li></li>");
				}else if(id==2){
					var sid=re.sid;
					var pagesA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+item+","+sid+")").append(item).appendTo("<li></li>");
				}else if(id==3){
					var title=re.title;
					var pagesA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+item+","+title+")").append(item).appendTo("<li></li>");
				}else if(id==4){
					var pagesA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+item+",1)").append(item).appendTo("<li></li>");
				}else if(id==5){
					var pagesA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+item+",1)").append(item).appendTo("<li></li>");
				}
				
				//判定在当前页的显示
				if(pageinfo.pageNum==item){
					var pagesLi=$("<li></li>").addClass("active").append(pagesA);
				}else{
					var pagesLi=$("<li></li>").append(pagesA);
				}
				
				pageModel.append(pagesLi);
			});
			
			if(id==1){
				//设置下一页
				var nextA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum+1)+",1)").attr("aria-label","Next");
				//设置最后一页
				var lastA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+pageinfo.pages+",1)").append("尾页").appendTo("<li></li>");
			}else if(id==2){
				var sid=re.sid;
				var nextA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum+1)+","+sid+")").attr("aria-label","Next");
				var lastA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+pageinfo.pages+","+sid+")").append("尾页").appendTo("<li></li>");					
			}else if(id==3){
				var title=re.title;
				var nextA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum+1)+","+title+")").attr("aria-label","Next");
				var lastA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+pageinfo.pages+","+title+")").append("尾页").appendTo("<li></li>");					
			}else if(id==4){
				var nextA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum+1)+",1)").attr("aria-label","Next");
				var lastA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+pageinfo.pages+",1)").append("尾页").appendTo("<li></li>");					
			}else if(id==5){
				var nextA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+(pageinfo.pageNum+1)+",1)").attr("aria-label","Next");
				var lastA=$("<a></a>").attr("href","javascript:to_page_choose("+id+","+pageinfo.pages+",1)").append("尾页").appendTo("<li></li>");					
			}
			
				var nextSpan=$("<span></span>").attr("aria-hidden","true").append("&raquo;").appendTo(nextA);
			
			//判断
			if(pageinfo.pageNum>(pageinfo.pages-1)){
				var nextLi=$("<li></li>").addClass("disabled").append(nextA);
				var lastLi=$("<li></li>").addClass("disabled").append(lastA);
			}else{
				var nextLi=$("<li></li>").append(nextA);
				var lastLi=$("<li></li>").append(lastA);
			}
			pageModel.append(nextLi).append(lastLi)
			
			}
		

		
//报时
function showTime(){
	var curTime = new Date();
	var time=curTime=curTime.toLocaleString();
	var times=time.split("午");
	time=times[1];
	$("#nowtime").html(time);
	setTimeout("showTime()", 1000);
};


//打开我的信息框
function open_my_message(){
	if(message==null){
		alert("请进行登录后才能操作！");
		$('#myModal').modal('show');
	}else if(message!=null){
		$('#myMessageModal').modal('show');
		show_my_message();
	}
};

// 显示我的信息框的信息
function show_my_message(){
	 $("#show_my_picture").attr("src",message.picture).addClass("enlargeImg");
	 $("#emailTd").text(message.email);
	 $("#nicknameTd").text(message.nickname);
	 $("#usernameTd").text(message.username);
	 if(message.sex==1){
		 $("#sexTd").text("男性");
	 }else{
		 $("#sexTd").text("女性");
	 }
	 $("#phoneTd").text(message.phone);
	 $("#ageTd").text(message.age+"岁");
	 $("#addressTd").text(message.address);
		$.ajax({
			url:"findCount",
			data:"uid="+message.uid,
			type:"GET",
			success:function(result){
				$("#my_all_topics").text("总话题数："+result.map.topicsC+"条");
				$("#my_all_comments").text("总评论数："+result.map.commentsC+"条");
			}
		});
		enlargeImg();	
	}


		
		

	
