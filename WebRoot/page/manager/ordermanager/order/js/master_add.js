jQuery(function($) {
	
	$("#form_save_submit_btn").click(function()
	{
		if($('#form_save_id').valid())
		{
			ajaxSubmitAndRefresh('form_save_id','master/getMasterList');
		}
	});
	
	$('#form_save_id').validate({
		rules:
		{
			'master.name':
			{
				required: true
			},
			'master.password':
			{
				required: true
			},
			confirm_password:
			{
				required: true,
				equalTo: "#masterpassword"
			},
			'master.mobile':
			{
				isMobile:true
			},
			'master.email':
			{
				email:true
			}
		},
		messages:
		{
			'master.name':
			{
				required:_lang_acc_name
			},
			'master.password':
			{
				required:_lang_common_password
			},
			confirm_password:
			{
				required: _lang_common_confirm_password,
				equalTo: _lang_common_password_equalTo
			},
			'master.email':
			{
				email:_lang_common_email
			}
		}
	});
			
});