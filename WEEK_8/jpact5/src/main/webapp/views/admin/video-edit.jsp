<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form
	action="${pageContext.request.contextPath}/admin/video/update" method = "post" enctype="multipart/form-data">
	<input type="text" id="videoid" name="videoid" hidden="hidden"
		value="${video.videoId}"><br>
		

	<label for="title">Title:</label><br> 	
	<input type="text" id="title" name="title" value = "${video.title }"><br>
	 
	 <label for="description">Description:</label><br> 
	<input type="text" id="description" name="description" value = "${video.description }"><br>
	 
	<label for="poster">Poster:</label><br>
	<c:if test="${ video.poster.substring(0,5)=='https'}">
				<c:url value="${video.poster }" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${ video.poster.substring(0,5)!='https'}">
				<c:url value="/poster?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>

			<img height="150" width="200" src="${imgUrl}" id = "poster" />
	<input type = "file" onchange= "chooseFile(this)" id="poster" name = "poster" value = "${video.poster}" ><br>
	<input type="text" id="posterURL" name="posterURL" placeholder="Nhập URL hình ảnh" oninput="chooseUrl(this)" value ="${video.poster}"><br>
	
	 <!-- nhập video -->
    <label for="video">Tải lên video:</label><br>
    <input type="file" id="video" name="video" accept="video/*"><br><br>
  	<label for="videoUrl">Hoặc nhập URL video:</label><br>
    <input type="text" id="videoUrl" name="videoUrl" placeholder="Nhập URL video"><br><br>
	
	<p>active:</p>
	<!-- Radio button cho trạng thái đang hoạt động -->
	<input type="radio" id="ston" name="active" value="1"
		${video.active == 1 ? 'checked' : ''}> <label for="ston">Đang
		hoạt động</label><br>

	<!-- Radio button cho trạng thái khóa -->
	<input type="radio" id="stoff" name="active" value="0"
		${video.active == 0 ? 'checked' : ''}> <label for="stoff">Khóa</label><br>

	<!-- Nút cập nhật -->
	<input type="submit" value="Update">

</form>
