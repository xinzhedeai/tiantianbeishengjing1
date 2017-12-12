$(function() {
	$('.header').load("header.html");
	$('.footer').load("footer.html");
	var page = 1, rows = 10, total = page * rows;

	$(".loadmore").click(function() {
		page += 1;
		getAllBlogs(userId, page, rows)
		numa = page * rows;
	});
	$('#confirmBtn').click(function() {
		addScripture();
	});
	$('#searchBtn').click(function() {
		getScripture();
	});

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
	
//	$('.form_date').datetimepicker({
//		
//        weekStart: 1,
//        todayBtn:  1,
//		autoclose: 1,
//		todayHighlight: 1,
//		startView: 2,
//		minView: 2,
//		forceParse: 0
//    });
	$('#datetimepicker').datetimepicker({
		language:'zh-CN',
		autoclose: 1,
		startView: 2,
		minView: 2,
		todayHighlight: 1,
		todayBtn:  1,
	    format: 'yyyy-mm-dd'
	    	
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
	$.post('/scriptureAction/searchScripturesByNo.action', $('#seachForm')
			.serialize(), function(result) {
		if (result.success) {
			var result = result.result, scriptureStr = '';
			if (result && result.length > 0) {
				for (var i = 0; i < result.length; i++) {
					scriptureStr += result[i].scriptureText + '</br><hr/>';
				}

				$('#previewArea').html(scriptureStr);
			}
		} else {
			alert(result.msg);
		}
	}, "JSON");
}
