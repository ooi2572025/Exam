<%-- 科目削除完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

            <div class="mx-4">

                <%-- 完了メッセージ --%>
                <p class="bg-success bg-opacity-25 py-2 px-4">削除が完了しました</p>

                <%-- 科目一覧リンク --%>
                <a href="SubjectList.action">科目一覧</a>

            </div>
        </section>
    </c:param>
</c:import>