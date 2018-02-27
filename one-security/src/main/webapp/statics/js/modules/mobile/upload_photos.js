var images = {
	localId : [],
	serverId : []
};

var vm = new Vue({
	el:'#uploadPhotos',
	data:{
		title: null,
		entity:{
			name : "",
			age : "",
			gender : "",
			type : "",
			deptName : "",
			contactWay : ""
		}
	},
    created:function () {
    },
	mounted: function () {
		  this.$nextTick(function () {
			  this.getInfo();
		  });
	},
	methods: {
		saveOrUpdate: function (event) {
			var url = "baspatient/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.basPatient),
			    success: function(r){
			    	if(r.code === 0){
						alert('保存成功', function(index){
							window.location.href = baseURL + "modules/mobile/home.html";
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getInfo:function (){
			var infoId = $("#infoId").val();
			var tableName = $("#tableName").val();
	        $.ajax({
	            type: "GET",
	            url: baseURL + "bastoothposition/getPatient/"+infoId+"-"+tableName,
	            async:false,
	            success: function(data){
	            	console.log(data.entity);
	            	vm.entity = data.entity;
	            	console.log(vm.entity);
	            }
	        });
		}
	}
});

$(function(){
	getConfig();
});

function getConfig() {
	var url = "basfile/getConfig";
	var htmlUrl = location.href;
	$.ajax({
		url: baseURL + url,
		type : 'post',
		dataType : "json",
		async : true,
		data : {
			htmlUrl : htmlUrl
		},
		success : function(r) {
			wx.config({
				debug : false,
				appId : r.appId,
				timestamp : r.timestamp,
				nonceStr : r.nonceStr,
				signature : r.signature,
				jsApiList : [ 'chooseImage', 'previewImage', 'uploadImage' ]
			});
		}
	});
}

function chooseImage() {
	wx.chooseImage({
		count : 9,
		sizeType : ['original','compressed'],// 
		success : function(res) {
			images.localId = res.localIds;
			for ( var i = 0; i < res.localIds.length; i++) {
				var imgId = res.localIds[i];
				if(imgId.indexOf("://")>0){
					imgId = imgId.split("://")[1];
				}
				var html = '<div class="col-md-3 col-xs-3"><img class="img-responsive pad wx_img" id="' + imgId + '" src="' + res.localIds[i] + '" ontouchstart="gtouchstart(this)" ontouchmove="gtouchmove()" ontouchend="gtouchend(this)" /></div>';
				$("#upload-img-div").find(".row").find(".imgArray").first().before(html);
			}
		}
	});
}

//开始按
function gtouchstart(obj) {
	var imgId = $(obj).attr("id");
	timeOutEvent = setTimeout("longPress('"+imgId+"')", 500);
	//这里设置定时器，定义长按500毫秒触发长按事件，时间可以自己改，个人感觉500毫秒非常合适
	return false;
};

//如果手指有移动，则取消所有事件，此时说明用户只是要移动而不是长按
function gtouchmove() {
	clearTimeout(timeOutEvent);
	//清除定时器
	timeOutEvent = 0;
};

//手释放，如果在500毫秒内就释放，则取消长按事件，此时可以执行onclick应该执行的事件
function gtouchend(obj) {
	clearTimeout(timeOutEvent);
	//清除定时器
	if (timeOutEvent != 0) {
	//这里写要执行的内容（尤如onclick事件）
		previewImage(obj);
	}
	return false;
};

//图片预览
function previewImage(item) {
	var urls = [];
	var current = $(item).attr("src");
	current = current.replace("-thum.jpg", ".jpg");
	var img_div = $(item).parent().parent().parent();
	var imgs = img_div.find("img");
	var len = imgs.length;
	imgs.each(function(i) {
		var src = $(this).attr("src");
		src = src.replace("-thum.jpg", ".jpg");
		urls.push(src);
	});
	wx.previewImage({
		current : current,
		urls : urls
	});
}


function savePat(){
	//从微信服务器下载图片
	var serverIds = '';
	var lid = '';
	var from = $("#imgFrm");
	var img_items = from.find(".wx_img");
	img_items.each(function() {
		lid += $(this).attr("src") + ",";
	});
	var ids;
	var i = 0;
	var length = 0;
	if (lid != '') {
		lid = lid.substring(0, lid.length - 1);
		ids = lid.split(",");
		length = ids.length;
	}
	
	if (length > 0) {
		upload(i, length, ids, serverIds);
		//upload(i, length, ids, serverIds, patId,schemeId, flag, oldImgId);
	} else {
		//uploadToServer('', patId,schemeId, flag, oldImgId);
		alert("asd");
	}
}

function upload(i, length, ids, serverIds) {
	wx.uploadImage({
		localId : ids[i],
		isShowProgressTips : 0,
		success : function(res) {
			i++;
			serverIds += res.serverId + ",";
			if (i < length) {
				upload(i, length, ids, serverIds);
			} else {
				serverIds = serverIds.substring(0, serverIds.length - 1);
				uploadToServer(serverIds);
			}
		},
		fail : function(res) {
		}
	});
}

//保存图片
function uploadToServer(serverIds) {
	var infoId = $("#infoId").val();
	var tableName = $("#tableName").val();
	var folder = "\\statics\\images\\basFile\\" + tableName + infoId;
	$.ajax({
		url: baseURL + "bastoothposition/addBasToothPosition",
		type : 'post',
		dataType : "text",
		async : false,
		data : {
			infoId : infoId,
			tableName : tableName,
			serverIds : serverIds,
			folder : folder,
		},
		success : function(data) {
			if (data == 'ok') {
				alert('保存成功！');
			}
		}
	});
}
