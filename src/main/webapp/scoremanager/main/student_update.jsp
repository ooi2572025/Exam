<%-- 学生変更JSP --%>
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
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

            <form action="StudentUpdateExecute.action" method="post">
                <div class="mx-4">

                    <%-- 入学年度（readonly） --%>
                    <div class="mb-3">
                        <label class="form-label">入学年度</label>
                        <input class="form-control" type="text"
                               name="ent_year" value="${ent_year}" readonly>
                    </div>

                    <%-- 学生番号（readonly） --%>
                    <div class="mb-3">
                        <label class="form-label">学生番号</label>
                        <input class="form-control" type="text"
                               name="no" value="${no}" readonly>
                    </div>

                    <%-- 氏名（編集可・必須・最大30文字） --%>
                    <div class="mb-3">
                        <label class="form-label">氏名</label>
                        <input class="form-control" type="text"
                               name="name" value="${name}"
                               maxlength="30"
                               placeholder="氏名を入力してください">
                    </div>

                    <%-- クラス --%>
                    <div class="mb-3">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="class_num">
                            <c:forEach var="classNum" items="${classNumList}">
                                <option value="${classNum}"
                                    <c:if test="${classNum == class_num}">selected</c:if>
                                >${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 在学中チェックボックス --%>
                    <div class="mb-3 form-check">
                        <label class="form-check-label" for="is-attend-check">在学中
                            <input class="form-check-input" type="checkbox"
                                   id="is-attend-check" name="is_attend" value="t"
                                   <c:if test="${is_attend}">checked</c:if> />
                        </label>
                    </div>

                    <%-- エラーメッセージ --%>
                    <c:if test="${not empty errorMessage}">
                        <div class="mt-2 text-warning">${errorMessage}</div>
                    </c:if>

                    <%-- 変更ボタン --%>
                    <div class="mb-3">
                        <button class="btn btn-secondary" type="submit" name="login">変更</button>
                    </div>

                    <%-- 戻るリンク --%>
                    <a href="StudentList.action">戻る</a>

                </div>
            </form>
        </section>
    </c:param>
</c:import>