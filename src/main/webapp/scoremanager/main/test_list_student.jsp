<%-- 成績一覧（学生）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">

            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧（学生）
            </h2>

            <div class="mx-3">

                <%-- 検索フォーム --%>
                <form action="TestList.action" method="post">

                    <%-- 科目情報検索 --%>
                    <div class="border rounded p-3 mb-3">
                        <div class="row align-items-end g-2">

                            <div class="col-auto">
                                <label class="form-label mb-1">入学年度</label>
                                <select name="admissionYear" class="form-select form-select-sm">
                                    <option value="">----------</option>
                                    <c:forEach var="year" items="${admissionYearList}">
                                        <option value="${year}"
                                            <c:if test="${year == form.admissionYear}">selected</c:if>>
                                            ${year}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto">
                                <label class="form-label mb-1">クラス</label>
                                <select name="classCode" class="form-select form-select-sm">
                                    <option value="">----------</option>
                                    <c:forEach var="cls" items="${classList}">
                                        <option value="${cls}"
                                            <c:if test="${cls == form.classCode}">selected</c:if>>
                                            ${cls}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto">
                                <label class="form-label mb-1">科目</label>
                                <select name="subjectCode" class="form-select form-select-sm">
                                    <option value="">----------</option>
                                    <c:forEach var="subject" items="${subjectList}">
                                        <option value="${subject.subjectCode}"
                                            <c:if test="${subject.subjectCode == form.subjectCode}">selected</c:if>>
                                            ${subject.subjectName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-auto">
                                <button type="submit" name="action" value="subjectSearch"
                                        class="btn btn-secondary btn-sm">検索</button>
                            </div>

                        </div>
                    </div>

                    <%-- 学生情報検索 --%>
                    <div class="border rounded p-3 mb-3">
                        <div class="row align-items-end g-2">

                            <div class="col-auto">
                                <label class="form-label mb-1">学生番号</label>
                                <input type="text"
                                       name="studentNumber"
                                       value="${form.studentNumber}"
                                       placeholder="学生番号を入力してください"
                                       class="form-control form-control-sm" />
                            </div>

                            <div class="col-auto">
                                <button type="submit" name="action" value="studentSearch"
                                        class="btn btn-secondary btn-sm">検索</button>
                            </div>

                        </div>

                        <%-- 学生番号未入力エラー --%>
                        <c:if test="${not empty errorMsg}">
                            <div class="mt-2">
                                <span class="text-danger small">${errorMsg}</span>
                            </div>
                        </c:if>

                        <%-- 氏名（学生番号）：検索後に枠内下部に表示 --%>
                        <c:if test="${searchDone and not empty studentInfo}">
                            <div class="mt-2 small">
                                氏名：${studentInfo.name}（${studentInfo.studentNumber}）
                            </div>
                        </c:if>

                    </div>

                </form>

                <%-- 検索結果エリア（学生番号検索実行後のみ表示） --%>
                <c:if test="${searchDone}">
                    <div class="mt-3">

                        <c:choose>
                            <%-- 成績一覧テーブル --%>
                            <c:when test="${not empty scoreList}">
                                <table class="table table-bordered table-sm table-hover">
                                    <thead class="table-secondary">
                                        <tr>
                                            <th>科目名</th>
                                            <th>科目コード</th>
                                            <th>回数</th>
                                            <th>点数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="score" items="${scoreList}">
                                            <tr>
                                                <td>${score.subjectName}</td>
                                                <td>${score.subjectCode}</td>
                                                <td>${score.times}</td>
                                                <td>${score.score}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>

                            <%-- 成績情報が存在しない場合 --%>
                            <c:otherwise>
                                <p class="text-danger small">成績情報が存在しませんでした。</p>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </c:if>

            </div>
        </section>
    </c:param>
</c:import>
