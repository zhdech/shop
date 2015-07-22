jQuery(function($) {
	
	$("#form_save_submit_btn").click(function()
	{
		if($('#form_save_id').valid())
		{
			ajaxSubmitAndRefresh('form_save_id','goods/getGoodsList');
		}
	});
	
	$('#form_save_id').validate({
		rules:
		{
			'goods.brand':
			{
				required: true
			},
			'goods.model':
			{
				required: true
			},
			'goods.color':
			{
				required: true
			},
			'goods.pirce':
			{
				required: true,
				digits:true
			},
			'goods.stock':
			{
				required: true,
				digits:true
			}
		},
		messages:
		{
			'goods.brand':
			{
				required:"必选字段"
			},
			'goods.model':
			{
				required:"必选字段"
			},
			'goods.color':
			{
				required:"必选字段"
			},
			'goods.pirce':
			{
				required:"必选字段",
				digits:"只能输入整数"
			},
			'goods.stock':
			{
				required:"必选字段",
				digits:"只能输入整数"
			}
		}
	});
			
});