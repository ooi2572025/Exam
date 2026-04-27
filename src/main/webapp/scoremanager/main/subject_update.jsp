<%-- 科目変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <!-- ① 画面タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <!-- 変更フォーム -->
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mx-4">

                    <%-- 科目コード（readonly）②③ --%>
                    <div class="mb-3">
                        <label class="form-label">科目コード</label>
                        <input class="form-control" type="text"
                               name="subject_code"
                               value="${subject_code}"
                               readonly>
                    </div>

                    <%-- 科目名（編集可）④⑤ --%>
                    <div class="mb-3">
                        <label class="form-label">科目名</label>
                        <input class="form-control" type="text"
                               name="subject_name"
                               value="${subject_name}"
                               maxlength="30"
                               placeholder="科目名を入力してください">
                    </div>

                    <%-- エラーメッセージ --%>
                    <c:if test="${not empty errorMessage}">
                        <div class="mt-2 text-warning">
                            ${errorMessage}
                        </div>
                    </c:if>

                    <%-- 変更ボタン ⑥ --%>
                    <div class="mb-3">
                        <button class="btn btn-secondary" type="submit">
                            変更
                        </button>
                    </div>

                    <%-- 戻るリンク ⑦ --%>
                    <a href="SubjectList.action">戻る</a>

                </div>
            </form>
        </section>
    </c:param>
</c:import>
``