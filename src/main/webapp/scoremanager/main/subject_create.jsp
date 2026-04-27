<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp" >
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <form action="SubjectCreateExecute.action" method="post">
                <div class="mx-4">

                    <%-- 科目番号 --%>
                    <div class="mb-3">
                        <label class="form-label" for="subject-id-input">科目番号</label>
                        <input class="form-control" type="text"
                               id="subject-id-input" name="id"
                               value="${id}"
                               maxlength="10"
                               placeholder="科目番号を入力してください">
                    </div>

                    <%-- 科目名！ --%>
                    <div class="mb-3">
                        <label class="form-label" for="subject-name-input">科目名</label>
                        <input class="form-control" type="text"
                               id="subject-name-input" name="name"
                               value="${name}"
                               maxlength="30"
                               placeholder="科目名を入力してください">
                    </div>

                    <%-- エラーメッセージ --%>
                    <c:if test="${not empty errorMessage}">
                        <div class="mt-2 text-warning">${errorMessage}</div>
                    </c:if>

                    <%-- 登録ボタン --%>
                    <div class="mb-3">
                        <button class="btn btn-secondary" type="submit" name="end">
                            登録して終了
                        </button>
                    </div>

                    <%-- 戻るリンク --%>
                    <a href="SubjectList.action">戻る</a>

                </div>
            </form>

        </section>
    </c:param>
</c:import>
