/**
 * 
 */

function write_check() {
	if($.trim($('#guest_name').val()) == '') {
		$('#error_writer').html("<font size='1' color='red'>글쓴이를 입력하세요</font>");
		$('#guest_name').val('').focus();
		return false;
	} else {
		$('#error_writer').text('');
	}
	
	if($.trim($('#guest_title').val()).length == 0) {
		$('error_title').html("<font size='1' color='red'>글제목을 입력하세요</font>");
		$('#guest_title').val('').focus();
		return false;
	} else {
		$('#error_title').text('');
	}
	
	if($.trim($('#guest_cont').val()) == '') {
		$('error_content').html("<font size='1' color='red'>글 내용을 입력하세요</font>");
		$('#guest_cont').val('').focus();
		return false;
	} else {
		$('#error_content').text('');
	}
} // write_check()

function reset() {
	$('#guest_name').focus();
	$('#error_writer').text('');
	$('#error_title').text('');
	$('#error_content').text('');
}