<%-- 学生登録JSP --%>
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
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

            <form action="StudentCreateExecute.action" method="post">
                <div class="mx-4">

                    <%-- 入学年度 --%>
                    <div class="mb-3">
                        <label class="form-label" for="ent-year-select">入学年度</label>
                        <select class="form-select" id="ent-year-select" name="ent_year">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${entYearList}">
                                <option value="${year}"
                                    <c:if test="${year == ent_year}">selected</c:if>
                                >${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 学生番号 --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-no-input">学生番号</label>
                        <input class="form-control" type="text"
                               id="student-no-input" name="no"
                               value="${no}"
                               maxlength="10"
                               placeholder="学生番号を入力してください">
                    </div>

                    <%-- 氏名 --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input class="form-control" type="text"
                               id="student-name-input" name="name"
                               value="${name}"
                               maxlength="30"
                               placeholder="氏名を入力してください">
                    </div>

                    <%-- クラス --%>
                    <div class="mb-3">
                        <label class="form-label" for="class-num-select">クラス</label>
                        <select class="form-select" id="class-num-select" name="class_num">
                            <c:forEach var="classNum" items="${classNumList}">
                                <option value="${classNum}"
                                    <c:if test="${classNum == class_num}">selected</c:if>
                                >${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- エラーメッセージ --%>
                    <c:if test="${not empty errorMessage}">
                        <div class="mt-2 text-warning">${errorMessage}</div>
                    </c:if>

                    <%-- 登録して終了ボタン --%>
                    <div class="mb-3">
                        <button class="btn btn-secondary" type="submit" name="end">登録して終了</button>
                    </div>

                    <%-- 戻るリンク --%>
                    <a href="StudentList.action">戻る</a>

                </div>
            </form>
        </section>
    </c:param>
</c:import>