<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action ="${pageContext.request.contextPath}/admin/category/insert" method ="post" enctype="multipart/form-data">
  <!-- Nhập tên danh mục -->
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname" ><br><br>
  
  <!-- Nhập hình ảnh -->
  <label for="image">Images:</label><br>
	<c:if test="${ cate.images.substring(0,5)=='https'}">
				<c:url value="${cate.images }" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${ cate.images.substring(0,5)!='https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>

			<img height="150" width="200" src="${imgUrl}" id = "images"/>
	<input type = "file" onchange= "chooseFile(this)"  id="images" name = "images" value = "${cate.images}" ><br>
  	<input type="text" id="posterURL" name="posterURL" placeholder="Nhập URL hình ảnh" oninput="chooseUrl(this)" ><br>
  <!-- Chọn trạng thái bằng radio button -->
  <label for="status">Status:</label><br>
  <input type="radio" id="active" name="status" value="1" checked>
  <label for="active">Đang hoạt động</label><br>
  
  <input type="radio" id="inactive" name="status" value="0">
  <label for="inactive">Khóa</label><br><br>
  
  <!-- Nút submit -->
  <input type="submit" value="Submit">
</form>
