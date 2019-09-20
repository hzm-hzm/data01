//进入指定的话题话题
 function to_topic(tid){
 	$.ajax({
 		url:"/toTopic",
 		data:"tid="+tid,
 		type:"GET",
 		success:function(result){
// 			console.log(result);
 			$("#web_message_nav").text("你进入了标题为：‘"+result.map.topic.title+"’的话题中。")
 			build_to_topic_model(result);
 			build_to_topic_comments_model(result);
 			enlargeImg();
 		}
 	});
 }

//构建指定话题详情页
 function build_to_topic_model(result){
 	$("#to_topic_div").css("display","");
 	$("#add_topic_div").css("display","none");
 	$("#topics_div").css("display","none");
 	$("#pages_div").css("display","none");
 	
 	$("#topic_pictures").empty();
 	$("#to_topic_title").empty();
 	$("#to_topic_user").empty();

 	
 	var topic=result.map.topic;
 	var user=topic.user;
 	var evaluate=topic.evaluate;
 	var sort=topic.sort;
 	
 	$("#none_topic_tid").text(topic.tid);
 	$("#to_topic_title").append(topic.title);
 	$("#to_topic_user_image").attr("src",user.picture).addClass("enlargeImg");
 	var topic_time=dateFormat(topic.time);
 	$("#to_topic_user").append("发布者："+user.nickname).append("<br/>").append("发布时间："+topic_time);
 	
 	var topic_pictures=topic.picture;
 	var pictures=topic_pictures.split("-,-");

 	var i=0;
 	$.each(pictures,function(index,item){
 		var picture=$("<img/>").css("width","150px").css("height","150px").css("border","solid 1px")
 		.css("margin-left","15px").addClass("enlargeImg");
 		if(item=="null,"){
 			picture.css("display","none");
 			i++;
 		}else{
 			picture.attr("src",item);
 		};
 		if(i==3){
 			$("#to_topic_content").css("margin-top","80px");
 		}else{
 			$("#to_topic_content").css("margin-top","");
 		}
 		
 		$("#topic_pictures").append(picture);
 	});
 	
 	$("#to_topic_content").text(topic.content);
 	
 	$("#good").text(evaluate.admire);
 	$("#good_a").attr("href","javascript:topic_good(/"+evaluate.eid+"/)");
 	$("#bad").text(evaluate.trample);
 	$("#bad_a").attr("href","javascript:topic_bad(/"+evaluate.eid+"/)");
 	
 	
 	$("#to_topic_sort").text("该话题类型属于："+sort.sname);
 	
 	if(message==null){
 		$("#topic_delete").css("display","none");
 	}else if(user.uid==message.uid){
 		$("#topic_delete").css("display","").attr("href","javascript:topic_delete(/"+topic.tid+"/)");
 	}else{
 		$("#topic_delete").css("display","none");
 	};	
 };



//建立话题的评论
function build_to_topic_comments_model(result){
	$("#topic_comments").empty();
	
	
	var comments=result.map.comments;
//	console.log(comments);
	if(comments.length==0){
		$("#comments_slogan").text("暂时还没有评论");
	}else{
		$("#comments_slogan").text("以下是评论区：");
	}
	var i=comments.length;
	$.each(comments,function(index,item){
		var sSort=$("<ss></ss>").css("color","red").append(i);
		i--;
		var divSort=$("<b></b>").css("font-size","20px").append("#").append(sSort).append("楼 "); 
		var img=$("<img src="+item.user.picture+"/>").css("width","50px").css("height","50px").css("margin-left","5px")
		.css("border","solid 1px").css("border-radius","50%").addClass("enlargeImg");
		var h4Time=$("<h4></h4>").css("float","right").css("margin-right","20px").append(dateFormat(item.time));
		var divTop=$("<div></div>").css("height","55px").css("border-bottom","solid 1px").css("background-color","#eee")
		.append(divSort).append(img).append(" ").append(item.user.nickname).append(h4Time);
		
		var divContent=$("<div></div>").css("word-break","break-all").css("width","750px")
		.css("text-indent","25px").css("min-height","50px").css("font-size","18px").append(item.content)
		var aDelete=$("<a></a>").attr("type","button").addClass("btn btn-danger")
		.css("float","right").css("margin-top","-35px").css("margin-right","20px").append("删除");
		if(message==null){
			aDelete.css("display","none");
		}else if(item.user.uid==message.uid){
			aDelete.css("display","").attr("href","javascript:topic_comment_delete(/"+item.cid+"/,/"+item.tid+"/)");
		}else{
			aDelete.css("display","none");
		};	
		
		var divComment=$("<div></div>").css("border","solid 1px").css("margin-top","20px")
		.css("width","820px").css("margin-right","auto").css("margin-left","auto").append(divTop).append(divContent).append(aDelete)
		$("#topic_comments").append(divComment);
	});
}


//点赞
function topic_good(eid){
	if(message==null){
		alert("请登录才能进行该操作！");
		$('#myModal').modal('show');
		false;
	};
	$.ajax({
		url:"/addGood",
		type:"GET",
		data:"eid="+eid+"&uid="+message.uid,
		success:function(result){
			if(result.code==200){
				alert("你已经进行过该类型的操作了！");
			}else if(result.code==100){
				to_topic(result.map.eid);
			}
		}
	});
};

//点踩
function topic_bad(eid){
	if(message==null){
		alert("请登录才能进行该操作！");
		$('#myModal').modal('show');
		false;
	};
	$.ajax({
		url:"/addBad",
		type:"GET",
		data:"eid="+eid+"&uid="+message.uid,
		success:function(result){
			if(result.code==200){
				alert("你已经进行过该类型的操作了！");
			}else if(result.code==100){
				to_topic(result.map.eid);
			}
		}
	});
};

//评论操作
function add_comment(){
	if(message==null){
		alert("请进行登录后才能操作！");
		$('#myModal').modal('show');
	}else if(message!=null){
		$('#CommentModal').modal('show');
	}
};

//发表评论
function publish_comment(){
	$.ajax({
		url:"/Comment",
		data:"content="+$("#comment_content").val()+"&uid="+message.uid+"&tid="+$("#none_topic_tid").text(),
		type:"POST",
		success:function(result){
			if(result.code==200){
				alert("你输入的内容有错！");
				$("#comment_content_error").text(result.map.errors.content);
				$("#comment_content_group").addClass("has-error");
			}else if(result.code==100){
				alert("发表成功！");
				$('#CommentModal').modal('hide');
				to_topic("/"+$("#none_topic_tid").text()+"/");
			}
		}
		
	});
};

//删除指定的评论
function topic_comment_delete(cid,tid){
	if(confirm("您确定要删除该评论？")){
		$.ajax({
			url:"/Comment",
			data:{_method:"DELETE",cid:cid},
			type:"POST",
			success:function(result){
				alert("删除成功！");
				to_topic(tid);
			}
		});
	};
};



//删除指定的话题
function topic_delete(tid){
	if(confirm("你确定要删除该话题？")){
		$.ajax({
			url:"/Topic",
			data:{_method:"DELETE",tid:tid},
			type:"POST",
			success:function(result){
				alert("删除成功！");
				to_page(1);
			}
		});
	};
};

//查看大图
function enlargeImg() {
  $(".enlargeImg").on('click',function() {
    $(this).after("<div onclick='closeImg()' class='enlargeImg_wrapper'></div>");
    var imgSrc = $(this).attr('src');
    $(".enlargeImg_wrapper").css("background-image", "url(" + imgSrc + ")");
    $(".enlargeImg_wrapper").fadeIn(200);
  })
};

//关闭并移除图层
function closeImg() {
  $(".enlargeImg_wrapper").fadeOut(200).remove();
}



