$(document).ready(function() {
	$('#LoginSubmit').click(function() {
		var loginname = $('#LoginUsername').val();
		var password = $('#LoginPassword').val();
		$.ajax({
			url : '../CBUserServlet',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
			type : 'POST',
			data : {
				'method' : 'doLoginwang',
				'loginname' : loginname,
				'password' : password
			},
	        async : false,
			dataType : 'json',
			success : function(data, textStatus, httpRequest) {
				
				var hasUser = data.hasUser;
				var user = data.user;
				var flag = data.flag;
				if(flag==false&&hasUser==false){
					alert('账号不存在');
				}else if(flag==false&&hasUser==true){
					alert('密码输入错误');
				}else{
					location.href='index.html';
				}
			}
		});
	});
});