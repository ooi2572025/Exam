<%-- 点数変更画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績変更
            </h2>

            <form action="TestUpdateExecute.action" method="post">
                <div class="mx-3 mb-3">

                    <div class="mb-3">
                        <label class="form-label">点数</label>
                        <input type="number"
                               class="form-control"
                               name="point"
                               value="${point}"
                               min="0"
                               max="100"
                               required>
                    </div>

                    <!-- hidden パラメータ -->
                    <input type="hidden" name="studentNo" value="${studentNo}">
                    <input type="hidden" name="subjectCd" value="${subjectCd}">
                    <input type="hidden" name="testNo" value="${testNo}">

                    <button class="btn btn-primary" type="submit">
                        変更
                    </button>

                    <a href="TestRegist.action" class="btn btn-secondary ms-2">
                        戻る
                    </a>
                </div>
            </form>

        </section>
    </c:param>
</c:import>