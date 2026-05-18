<%-- 科目登録JSP --%>
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

            <!-- タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <!-- 登録フォーム -->
            <form action="SubjectCreateExecute.action" method="post">

                <div class="mx-4">

                    <!-- ===============================
                         科目コード
                    =============================== -->
                    <div class="mb-3">
                        <label class="form-label">科目コード</label>

                        <input class="form-control"
                               type="text"
                               name="cd"
                               value="${cd}"
                               maxlength="10"
                               required
                               oninvalid="this.setCustomValidity('このフィールドを入力してください')"
                               oninput="this.setCustomValidity('')"
                               placeholder="科目コードを入力してください">

                        <!-- ★ エラーメッセージ -->
                        <c:if test="${not empty errors.cd}">
                            <div class="text-warning small">
                                ${errors.cd}
                            </div>
                        </c:if>
                    </div>

                    <!-- ===============================
                         科目名
                    =============================== -->
                    <div class="mb-3">
                        <label class="form-label">科目名</label>

                        <input class="form-control"
                               type="text"
                               name="name"
                               value="${name}"
                               maxlength="30"
                               required
                               oninvalid="this.setCustomValidity('このフィールドを入力してください')"
                               oninput="this.setCustomValidity('')"
                               placeholder="科目名を入力してください">

                        <!-- ★ エラーメッセージ -->
                        <c:if test="${not empty errors.name}">
                            <div class="text-warning small">
                                ${errors.name}
                            </div>
                        </c:if>
                    </div>

                    <!-- ===============================
                         登録ボタン
                    =============================== -->
                    <div class="mb-3">
                        <button class="btn btn-secondary" type="submit">
                            登録して終了
                        </button>
                    </div>

                    <!-- 戻る -->
                    <a href="SubjectList.action">戻る</a>

                </div>

            </form>

        </section>
    </c:param>
</c:import>
``