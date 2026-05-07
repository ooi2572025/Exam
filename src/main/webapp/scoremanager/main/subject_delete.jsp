<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

            <form action="SubjectDeleteExecute.action" method="post">
                <div class="mx-4">

                    <%-- 確認メッセージ --%>
                    <p>「${subject_name}（${subject_cd}）」を削除しますか？</p>

                    <%-- hidden項目 --%>
                    <input type="hidden" name="subject_cd" value="${subject_cd}">
                    <input type="hidden" name="subject_name" value="${subject_name}">

                    <%-- 削除ボタン --%>
                    <button class="btn btn-danger" type="submit">削除</button>

                    <%-- 戻るリンク --%>
                    <a href="SubjectList.action" class="ms-3">戻る</a>

                </div>
            </form>
        </section>
    </c:param>
</c:import>