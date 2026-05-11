<%-- 成績参照検索JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">

            <%-- No.1 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

            <div class="mx-3">

                <%-- エラーメッセージ --%>
                <c:if test="${not empty errorMessage}">
                    <div class="mt-2 text-warning">${errorMessage}</div>
                </c:if>

                <%-- No.2 科目情報ラベル --%>
                <p class="fw-bold mt-3">科目情報</p>

                <%-- 科目情報検索フォーム --%>
                <form method="get" action="TestListSubjectExecute.action">
                    <input type="hidden" name="f" value="sj">
                    <div class="row mb-3 align-items-end border rounded py-2">

                        <%-- No.3 入学年度ラベル・No.6 セレクトボックス --%>
                        <div class="col-3">
                            <label class="form-label">入学年度</label>
                            <select class="form-select form-select-sm" name="f1" size="1">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${entYearList}">
                                    <option value="${year}"
                                        <c:if test="${year == f1}">selected</c:if>
                                    >${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.4 クラスラベル・No.7 セレクトボックス --%>
                        <div class="col-3">
                            <label class="form-label">クラス</label>
                            <select class="form-select form-select-sm" name="f2" size="1">
                                <option value="0">--------</option>
                                <c:forEach var="classNum" items="${classNumList}">
                                    <option value="${classNum}"
                                        <c:if test="${classNum == f2}">selected</c:if>
                                    >${classNum}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.5 科目ラベル・No.8 セレクトボックス --%>
                        <div class="col-3">
                            <label class="form-label">科目</label>
                            <select class="form-select form-select-sm" name="f3" size="1">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subjectList}">
                                    <option value="${subject.cd}"
                                        <c:if test="${subject.cd == f3}">selected</c:if>
                                    >${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.9 検索ボタン --%>
                        <div class="col-auto">
                            <button class="btn btn-secondary" type="submit" name="31">検索</button>
                        </div>

                    </div>
                </form>

                <%-- No.10 学生情報ラベル --%>
                <p class="fw-bold mt-3">学生情報</p>

                <%-- 学生情報検索フォーム --%>
                <form method="get" action="TestListStudentExecute.action">
                    <input type="hidden" name="f" value="st">
                    <div class="row mb-3 align-items-end border rounded py-2">

                        <%-- No.11 学生番号ラベル・No.12 テキスト --%>
                        <div class="col-4">
                            <label class="form-label">学生番号</label>
                            <input type="text" class="form-control"
                                   name="f4"
                                   value="${f4}"
                                   maxlength="10"
                                   placeholder="学生番号を入力してください">
                        </div>

                        <%-- No.13 検索ボタン --%>
                        <div class="col-auto">
                            <button class="btn btn-secondary" type="submit" name="32">検索</button>
                        </div>

                    </div>
                </form>

            </div>
        </section>
    </c:param>
</c:import>