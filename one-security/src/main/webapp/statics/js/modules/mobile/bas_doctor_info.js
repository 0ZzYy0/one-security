var vm = new Vue({
	el:'#basDoctorInfo',
	data:{
		title: null,
		basDoctor:{
			docId : "",
			deptId : "",
			docName : "",
			contactWay : "",
			jobNum : "",
			docAddress : "",
			remark : ""
		},
		deptList:[]
	},
    created:function () {
    	var deptRowss;
        $.ajax({
            type: "GET",
            url: baseURL + "baspatmember/getDeptSelect",
            async:false,
            success: function(data){
        		var deptRows = '[';
        		for(var i = 0 ; i < data.length ; i++){
        			if(data[i].deptType == "医院"){
	        			if(data[i].parentName != null && data[i].parentName != ""){
	        				deptRows += '{name:\''+data[i].parentName +':' + data[i].name +'\',id:\''+data[i].deptId+'\'},';
	        			}else{
	        				deptRows += '{name:\'' + data[i].name +'\',id:\''+data[i].deptId+'\'},';
	        			}
        			}
        		}
        		deptRows = deptRows.substring(0, deptRows.length - 1);
        		deptRows += ']';
        		deptRowss = eval("("+deptRows+")");
            }
        });
		this.deptList = deptRowss;
        this.basDoctor.deptId = this.deptList.length>0 ? this.deptList[0].id : "";
    },
	mounted: function () {
		  this.$nextTick(function () {
			  this.getInfo();
		  });
	},
	methods: {
		saveOrUpdate: function (event) {
			var url = "basdoctor/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.basDoctor),
			    success: function(r){
			    	if(r.code === 0){
						alert('保存成功', function(index){
							window.location.href = baseURL + "modules/mobile/home_doctor.html";
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getInfo:function (){
            $.get(baseURL + "basdoctor/getInfo", function(r){
            	vm.basDoctor = r.basDoctor;
            });
		}
	}
});