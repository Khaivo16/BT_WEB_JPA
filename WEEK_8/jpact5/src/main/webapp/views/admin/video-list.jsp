<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/video/add">Add
	Video</a>

<form action="${pageContext.request.contextPath}/admin/video/search" method="GET">
    <input type="hidden" name="id" value='${video.category.categoryId}'>
    <input type="text" name="keyword" placeholder="Nhập từ khóa tìm kiếm">
    <input type="submit" value="Tìm kiếm">
</form>

<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Title</th>
		<th>Poster</th>
		<th>Description</th>
		<th>view</th>
		<th>CategoryId</th>
		<th>Active</th>
	</tr>

	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${video.title}</td>
			
			
			<c:if test="${ video.poster.substring(0,5)=='https'}">
				<c:url value="${video.poster }" var="imgUrl"></c:url>
			</c:if>

			<c:if test="${ video.poster.substring(0,5)!='https'}">
				<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${video.description}</td>
			<td>${video.views}</td>

			<c:if test="${video.category != null}">
					<td>${video.category.categoryId}</td>
				</c:if> <c:if test="${video.category == null}">
					<td>Không có danh mục</td>
				</c:if>
			<td><c:if test="${video.active==1}">
					<span> Đang hoạt động </span>
				</c:if> <c:if test="${video.active!=1}">
					<span> Khóa </span>
				</c:if></td>
			<td><a
				href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
				<a
				href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a>
				<a
				href="<c:url value='/admin/video/watch?id=${video.videoId}'/>">Xem video</a>
		</tr>
	</c:forEach>

</table>
