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
	});
	$("#copy").zclip({
		path: '/lib/js/ZeroClipboard.swf',
		copy: function(){
			return $('#previewArea').html().replace(/<br><hr>/g,'\n');
		},
		afterCopy: function(){
//			alert('已成功复制到粘贴板上了~');
			 var $copysuc = $("<div class='copy-tips'><div class='copy-tips-wrap'>☺ 复制成功</div></div>");
             $("body").find(".copy-tips").remove().end().append($copysuc);
             $(".copy-tips").fadeOut(3000);
		}
	});
});

function addScripture() {
	$.post('/scriptureAction/addScripture.action', $(
			'#add_scripture_modal form').serialize(), function(result) {
		if (result.success) {
			alert(result.msg);
		} else {
			alert(result.msg);
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
					if(i == 0)
						scriptureStr += result[i].create_date + '</br><hr/>';
					if(i == 1)
						scriptureStr += '复习:</br><hr/>';
					scriptureStr += result[i].scripture_text + '</br><hr/>';
					url = result[i].url ? result[i].url : '';
				}
				scriptureStr += url;
				$('#previewArea').html(scriptureStr);
			}
		} else {
			alert(result.msg);
		}
	}, "JSON");
}
