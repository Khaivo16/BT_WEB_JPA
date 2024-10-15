<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action ="${pageContext.request.contextPath}/admin/video/insert" method ="post" enctype="multipart/form-data">

  <!-- Nhập tên danh mục -->
	<label for="title">Title:</label><br> 
	<input type="text" id="title" name="title" value = "${video.title }"><br>
  <br>
  <label for="description">Description:</label><br> 
	<input type="text" id="description" name="description" value = "${video.description }"><br>
  <!-- Nhập hình ảnh -->
  <label for="poster">Poster:</label><br>
	<c:if test="${ video.poster.substring(0,5)=='https'}">
				<c:url value="${video.poster }" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${ video.poster.substring(0,5)!='https'}">
				<c:url value="/poster?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>

			<img height="150" width="200" src="${imgUrl}" id = "poster"/>
	<input type = "file" onchange= "chooseFile(this)"  id="poster" name = "poster" value = "${video.poster}" ><br>
  <input type="text" id="posterURL" name="posterURL" placeholder="Nhập URL hình ảnh" oninput="chooseUrl(this)" ><br>
  	<!-- nhập video -->
    <label for="video">Tải lên video:</label><br>
    <input type="file" id="video" name="video" accept="video/*"><br><br>
  	<label for="videoUrl">Hoặc nhập URL video:</label><br>
    <input type="text" id="videoUrl" name="videoUrl" placeholder="Nhập URL video"><br><br>
    
  <!-- Chọn trạng thái bằng radio button -->
  <label for="active">active:</label><br>
  <input type="radio" id="active" name="active" value="1" checked>
  <label for="active">Đang hoạt động</label><br>
  
  <input type="radio" id="inactive" name="active" value="0">
  <label for="inactive">Khóa</label><br><br>
  
  <!-- Nút submit -->
  <input type="submit" value="Submit">
</form>
