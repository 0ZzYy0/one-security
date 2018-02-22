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
				var html = '<div class="col-md-4 col-xs-4"><img class="img-responsive pad wx_img" id="' + imgId + '" src="' + res.localIds[i] + '" ontouchstart="gtouchstart(this)" ontouchmove="gtouchmove()" ontouchend="gtouchend(this)" /></div>';
				$("#upload-img-div").find(".row").find(".col-md-4").first().before(html);
			}
		}
	});
}


