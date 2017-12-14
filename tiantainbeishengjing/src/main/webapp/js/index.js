$(function() {
	$('.header').load("header.html");
	$('.footer').load("footer.html");
	$('#confirmBtn').click(function() {
		addScripture();
	});
	$('#searchBtn').click(function() {
		getScripture();
	});
	var nowTime = new Date()
	if(nowTime.getDay() != 7){
		$('.alert-warning').css('visibility', 'hidden');
	}else{
		$('#type option:gt(1)').hide();
		$('.alert-warning').css('visibility', 'visible');
	}
	$('#importBtn').click(function() {
		$('#upload_scripture_modal').modal();
		/*var uploadDiv = $('#upload_scripture_div');
		uploadDiv.fileUpload();
		uploadDiv.plupload('getUploader').setOption('url',
				'/scriptureAction/impScriptureBatch.action');*/
	});
	
	$('#upload_scripture_div').plupload({
		// General settings
		runtimes : 'html5,flash,silverlight,html4',
		url : '/scriptureAction/impScriptureBatch.action',

		// User can upload no more then 20 files in one go (sets multiple_queues to false)
		max_file_count: 20,
		
		chunk_size: '1mb',
		// Resize images on clientside if we can
		resize : {
			width : 200, 
			height : 200, 
			quality : 90,
			crop: true // crop to exact dimensions
		},
		filters : {
			// Maximum file size
			max_file_size : '1000mb',
			// Specify what files to browse for
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
				{title : "Zip files", extensions : "zip"},
				{title : "Excel files", extensions:"xls,xlsx"}
			]
		},
		// Rename files by clicking on their titles
		rename: true,
		// Sort files
		sortable: true,
		// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
		dragdrop: true,
		// Views to activate
		views: {
			list: true,
			thumbs: true, // Show thumbs
			active: 'thumbs'
		},
		   flash_swf_url : 'lib/plugin/plupload-2.1.2/Moxie.swf',
		silverlight_xap_url : 'lib/plugin/plupload-2.1.2/MoxiDe.xap'
	});
	$('.createDate').datetimepicker({
		language:'zh-CN',
		autoclose: 1,
		startView: 2,
		minView: 2,
		todayHighlight: 1,
		todayBtn:  1,
	    format: 'yyyy-mm-dd'
	}).on('changeDate', function(ev){
		/*formatDate(ev.timeStamp)
		console.log(ev);
	    layer.alert('时间改变了。。。。。。'+formatDate(ev.timeStamp));*/
		getScripture();
	});;
	$("#copy").zclip({
		path: '/lib/js/ZeroClipboard.swf',
		copy: function(){
			var str = $('#previewArea').html().replace(/<br><hr>/g,'\n');
			str = str.replace(/<([a-zA-Z]+)\s*[^><]*>/g,"<$1>");//去掉属性
			str = str.replace(new RegExp("<span([^>]{0,})>", "g"), "");//去掉<span>
			str = str.replace(new RegExp("</span>", "g"), "");//去掉</span>
			return str;
		},
		afterCopy: function(){
//			alert('已成功复制到粘贴板上了~');
			 var $copysuc = $("<div class='copy-tips'><div class='copy-tips-wrap'>☺ 复制成功</div></div>");
             $("body").find(".copy-tips").remove().end().append($copysuc);
             $(".copy-tips").fadeOut(3000);
		}
	});
	//修改经文
	$(document).on('dblclick', '#previewArea span', function(){
		var editable_span_dom = '<textarea type="text" class="form-control">'+ $(this).html() +'</textarea>'+
								'<button type="button" class="btn btn-primary" onclick="modScripture(this)" data-no="'+ $(this).data('no') +'" data-type="'+ $(this).data('type') +'"><span class="glyphicon glyphicon-ok"></span>保存</button>';
		$(this).after(editable_span_dom).hide();
	});
	
	//添加经文
	$('#add_scripture_btn').click(function(){
		$('#add_scripture_modal').modal();
	});
	//模态款彻底显示后逻辑处理
	$('#add_scripture_modal').on('shown.bs.modal', function () {
		getNextScriptureDate();
	});
	//经文类型修改后,实时显示下一个日期
	$('#type').change(function(){
		getNextScriptureDate();
	});
	//搜索条件改变时，自动搜索
	$('#search_form_type').change(function(){
		getScripture();
	});
});



function addScripture() {
	$.post('/scriptureAction/addScripture.action', $(
			'#add_scripture_modal form').serialize(), function(result) {
		if (result.success) {
//			$('#reset_btn').click();
			$('#sripture_content, #scripture_url').val('');
			layer.alert(result.msg);
			getNextScriptureDate();
		} else {
			layer.alert(result.msg);
		}
	}, "JSON");
}
/**
 * params[0]:经文编号
 * params[1]:经文内容/url
 * params[2]:修改的内容类型  如果为’url‘修改的则是连接。否则默认修改时圣经内容 
 * 
 */
function modScripture(target){
	var data_obj = $(target).data(), type = data_obj.type, reqParam = {};

	reqParam.scripture_no = data_obj.no;
	if(type == 'url'){
		reqParam.url = $(target).prev().val();
	}else{
		reqParam.scripture_text = $(target).prev().val();
	}
	$.post('/scriptureAction/modScripture.action', reqParam, function(result) {
		if (result.success) {
			layer.alert(result.msg);
			$('#searchBtn').trigger('click');
		} else {
			layer.alert(result.msg);
		}
	}, "JSON");
}
function getScripture() {
	$.post('/scriptureAction/searchScripturesByDate.action', $('#seachForm')
			.serialize(), function(result) {
		if (result.success) {
			var result = result.result, scriptureStr = '', url = '';
			if (result && result.length > 0) {
				for (var i = 0; i < result.length; i++) {
					if(i == 0){
						scriptureStr += result[i].create_date + '</br><hr/>';
						url = result[i].url ? '<span data-no="'+ result[i].scripture_no +'" data-type="url">' + result[i].url + '</span>' : '';
						scriptureStr += '<span data-no="'+ result[i].scripture_no +'" data-type="scripture">' + 
						result[i].scripture_text + '</span></br><hr/>';
						continue;
					}
					if(i == 1){
						scriptureStr += '复习:</br><hr/>';
						scriptureStr += '<span data-no="'+ result[i].scripture_no +'" data-type="scripture">' + 
						result[i].scripture_text + '</span></br><hr/>';
						continue;
					}
					scriptureStr += '<span data-no="'+ result[i].scripture_no +'" data-type="scripture">' + 
									result[i].scripture_text + '</span></br><hr/>';
					
					
				}
				scriptureStr += url;
				console.log(scriptureStr);
				$('#previewArea').html(scriptureStr);
			}
		} else {
			alert(result.msg);
		}
	}, "JSON");
}
function getNextScriptureDate(){
	var reqParam = {};
	reqParam.type = $('#type').val();
	$.post('/scriptureAction/getNextScriptureDate.action', reqParam, function(res) {
		if (res.success) {
//			layer.alert(res.msg);
			$('#scrpture_create_date').text(res.result.next_create_date);
		} else {
			layer.alert(res.msg);
		}
	}, "JSON");
}