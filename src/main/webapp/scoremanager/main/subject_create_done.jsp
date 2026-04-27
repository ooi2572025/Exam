<%-- 科目登録完了JSP --%>
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

            <%-- No.1 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <div class="mx-4">

                <%-- No.2 完了メッセージ（緑背景バー） --%>
                <p class="bg-success bg-opacity-25 py-2 px-4">
                    登録が完了しました
                </p>

                <%-- No.3 戻るリンク → 科目登録画面へ遷移 --%>
                <a href="SubjectCreate.action">戻る</a>

                <%-- No.4 科目一覧リンク → 科目管理一覧画面へ遷移 --%>
                <a href="SubjectList.action" class="ms-4">科目一覧</a>

            </div>

        </section>
    </c:param>
</c:import>
