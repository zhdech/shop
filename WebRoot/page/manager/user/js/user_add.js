jQuery(function($) {
	
	$("#form_save_submit_btn").click(function()
	{
		if($('#form_save_id').valid())
		{
			ajaxSubmitAndRefresh('form_save_id','loginUser/getUserList');
		}
	});
	
	$('#form_save_id').validate({
		rules:
		{
			'loginUser.name':
			{
				required: true
			},
			'loginUser.password':
			{
				required: true
			},
			confirm_password:
			{
				required: true,
				equalTo: "#masterpassword"
			},
			'loginUser.mobile':
			{
				isMobile:true
			}
		},
		messages:
		{
			'loginUser.name':
			{
				required:_lang_acc_name
			},
			'loginUser.password':
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