$(function () {
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		title: null,
		basPatient: {}
	},
	methods: {
		update: function (event) {
			var patId = getSelectedRow();
			if(patId == null){
				return ;
			}
            vm.title = "修改";
            vm.getInfo(patId)
		},
		saveOrUpdate: function (event) {
			var url = vm.basPatient.patId == null ? "baspatient/save" : "baspatient/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.basPatient),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getInfo: function(patId){
			$.get(baseURL + "baspatient/info/"+patId, function(r){
                vm.basPatient = r.basPatient;
            });
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});