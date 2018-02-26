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

function chooseImage(group) {
	wx.chooseImage({
		count : 4,
		sizeType : ['original','compressed'],// 
		success : function(res) {
			images.localId = res.localIds;
			for ( var i = 0; i < res.localIds.length; i++) {
				var imgId = res.localIds[i];
				if(imgId.indexOf("://")>0){
					imgId = imgId.split("://")[1];
				}
				var html = '<div class="col-md-3 col-xs-3"><img class="img-responsive pad wx_img" id="' + imgId + '" src="' + res.localIds[i] + '" ontouchstart="gtouchstart(this)" ontouchmove="gtouchmove()" ontouchend="gtouchend(this)" /></div>';
				$("#group"+group).find(".row").find(".col-md-4").first().before(html);
				var leng = $("#group"+group).children('img').length();
				alert(leng);
				/*if(leng == 5){
					$("#group"+group).find(".row").find(".col-md-4").first().hide();
				}*/
			}
		}
	});
}
