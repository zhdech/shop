jQuery(function($) {
	
	$("#form_update_submit_btn").click(function()
	{
		if($('#form_update_id').valid())
		{
			ajaxSubmitAndRefresh('form_update_id','loginUser/getUserList');
		}
	});
	
	$('#form_update_id').validate({
		rules:
		{
			'userInfo.name':
			{
				required: true
			},
			'userInfo.password':
			{
				required: true
			},
			confirm_password:
			{
				required: true,
				equalTo: "#userInfopassword"
			},
			'userInfo.mobile':
			{
				isMobile:true
			}
		},
		messages:
		{
			'userInfo.name':
			{
				required:_lang_acc_name
			},
			'userInfo.password':
			{
				required:_lang_common_password
			},
			confirm_password:
			{
				required: _lang_common_confirm_password,
				equalTo: _lang_common_password_equalTo
			}
		}
	});
			
});