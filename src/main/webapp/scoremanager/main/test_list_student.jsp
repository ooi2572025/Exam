<%-- 学生別成績一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>

            <div class="mx-3">

                <%-- 検索フォーム --%>
                <div class="border rounded p-3 mb-3">

                    <%-- 科目情報検索 --%>
                    <form method="get" action="TestListSubjectExecute.action">
                        <input type="hidden" name="f" value="sj">
                        <div class="row align-items-end g-2 mb-2">
                            <div class="col-auto" style="width:80px;">
                                <label class="form-label mb-1">科目情報</label>
                            </div>
                            <div class="col-auto">
                                <label class="form-label mb-1">入学年度</label>
                                <select class="form-select form-select-sm" name="f1">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${entYearList}">
                                        <option value="${year}"
                                            <c:if test="${year == f1}">selected</c:if>
                                        >${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-auto">
                                <label class="form-label mb-1">クラス</label>
                                <select class="form-select form-select-sm" name="f2">
                                    <option value="0">--------</option>
                                    <c:forEach var="classNum" items="${classNumList}">
                                        <option value="${classNum}"
                                            <c:if test="${classNum == f2}">selected</c:if>
                                        >${classNum}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-auto">
                                <label class="form-label mb-1">科目</label>
                                <select class="form-select form-select-sm" name="f3">
                                    <option value="0">--------</option>
                                    <c:forEach var="subject" items="${subjectList}">
                                        <option value="${subject.cd}"
                                            <c:if test="${subject.cd == f3}">selected</c:if>
                                        >${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-auto align-self-end">
                                <button class="btn btn-secondary btn-sm" type="submit">検索</button>
                            </div>
                        </div>
                    </form>

                    <hr class="my-2">

                    <%-- 学生情報検索 --%>
                    <form method="get" action="TestListStudentExecute.action">
                        <input type="hidden" name="f" value="st">
                        <div class="row align-items-end g-2">
                            <div class="col-auto" style="width:80px;">
                                <label class="form-label mb-1">学生情報</label>
                            </div>
                            <div class="col-auto">
                                <label class="form-label mb-1">学生番号</label>
                                <input type="text" class="form-control form-control-sm"
                                       name="f4"
                                       value="${f4}"
                                       maxlength="10"
                                       placeholder="学生番号を入力してください">
                            </div>
                            <div class="col-auto align-self-end">
                                <button class="btn btn-secondary btn-sm" type="submit">検索</button>
                            </div>
                        </div>
                    </form>

                </div>

                <%-- エラーメッセージ --%>
                <c:if test="${not empty errorMsg}">
                    <div class="mt-2 text-warning">${errorMsg}</div>
                </c:if>

                <%-- 氏名表示 --%>
                <c:if test="${not empty studentInfo}">
                    <div class="mb-2">
                        氏名：${studentInfo.studentName}（${studentInfo.studentNo}）
                    </div>
                </c:if>

                <%-- 成績一覧テーブル --%>
                <c:if test="${not empty scoreList}">
                    <div class="border rounded p-2">
                        <table class="table table-hover mb-0">
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                            <c:forEach var="score" items="${scoreList}">
                                <tr>
                                    <td>${score.subjectName}</td>
                                    <td>${score.subjectCd}</td>
                                    <td>${score.no}</td>
                                    <td>${score.point}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>

                <c:if test="${searchDone and empty scoreList and empty errorMsg}">
                    <div class="text-warning">成績情報が存在しませんでした。</div>
                </c:if>

            </div>
        </section>
    </c:param>
</c:import>