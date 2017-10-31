<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	
$(function () {
    var obj = $(".view_big_img");

    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px solid #5272A0');
    });

    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');
    });

    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });

    obj.on('drop', function (e) {
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');

         var files = e.originalEvent.dataTransfer.files;
         if(files.length < 1)
              return;

         F_FileMultiUpload(files, obj);
    });

});

//파일 멀티 업로드
function F_FileMultiUpload(files, obj) {
  if(confirm(files.length + "개의 파일을 업로드 하시겠습니까?") ) {
      var data = new FormData();
      for (var i = 0; i < files.length; i++) {
         data.append('file', files[i]);
      }

      var url = "../../../../upload/photo/2017";
      $.ajax({
         url: url,
         method: 'post',
         data: data,
         dataType: 'json',
         processData: false,
         contentType: false,
         success: function(res) {
             F_FileMultiUpload_Callback(res.files);
         }
      });
  }
}

//파일 멀티 업로드 Callback
function F_FileMultiUpload_Callback(files) {
  for(var i=0; i < files.length; i++)
      console.log(files[i].file_nm + " - " + files[i].file_size);
}


</script>
<main class="main">

	<form action="" method="post" enctype="multipart/form-data">
		<div>
			<div class="view_wrap" id="view_top">
				<div class="profile">

					<div class="left">
						<h2>제목:
							<input type="text" name="title" value="${n.title}" />
						</h2>
					</div>

					<div class="right">
						<h2>사진작가명</h2>
					</div>
				</div>

				<div class="view_big_img">
				
					<img src="" alt="사진을 넣으세요!">
					<button>사진 업로드</button>
				</div>				
				
				<h2>설명</h2>
				<div class="detail"><textarea name="content"></textarea></div>
				<br>
					<div class="view_img_btn">
						<ul>
							<li></li>
							<li><button type="button" class="view_down btn_down">
									<img src="/static/common/img/view_icon_01.png" alt="등록"><span
										class="view_btn_txt"></span>
								</button></li>



							<li><button type="button" class="view_sns">
									<img src="/static/common/img/view_icon_02.png" alt="취소">
								</button></li>

						</ul>
					</div>
				</div>
		</div>

	</form>

</main>